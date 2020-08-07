package com.myroutineapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Calendar;
import java.util.Objects;

public class FourthFragment extends Fragment {
    private HabitViewModel habitViewModel;
    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout linearLayout;
    Bundle bundle = new Bundle();
    int hr = 0, min = 0, sec = 0;
    long nowhr = 0, nowmin = 0, nowsec = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = view.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        bundle = getArguments();
        Calendar rightNow = Calendar.getInstance();

        long offset = rightNow.get(Calendar.ZONE_OFFSET) + rightNow.get(Calendar.DST_OFFSET);
        long sinceMid = (rightNow.getTimeInMillis() + offset) % (24 * 60 * 60 * 1000);

        String name = Objects.requireNonNull(bundle.get("Habit_Name")).toString();
        String desc = Objects.requireNonNull(bundle.get("Habit_Desc")).toString();
        String time = Objects.requireNonNull(bundle.get("Habit_Time")).toString();

        ((TextView) view.findViewById(R.id.nameofhabittextView)).setText(name);
        ((TextView) view.findViewById(R.id.descofhabittextView)).setText(desc);


        hr = Integer.parseInt(time.substring(0, 2));
        min = Integer.parseInt(time.substring(3, 5));
        sec = Integer.parseInt(time.substring(6, 8));

        long timer = (((hr * 60 * 60) + (min * 60) + sec) * 1000) - sinceMid;

        nowsec = timer / 1000;
        nowhr = nowsec / 3600;
        nowsec = nowsec - (nowhr * 3600);
        if (nowsec > 0) {
            nowmin = nowsec / 60;
            nowsec = nowsec - (nowsec * 60);
        }

        new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long l) {
                if (nowsec >= 10) {
                    ((TextView) view.findViewById(R.id.timeofhabittextView)).setText(String.valueOf(nowhr).concat(":").concat(String.valueOf(nowmin).concat(":").concat(String.valueOf(nowsec))));
                } else {
                    ((TextView) view.findViewById(R.id.timeofhabittextView)).setText(String.valueOf(nowhr).concat(":").concat(String.valueOf(nowmin).concat(":0").concat(String.valueOf(nowsec))));
                }
                nowsec--;
                if (nowsec < 0) {
                    nowmin--;
                    nowsec = 59;
                }
                if (nowmin < 0) {
                    nowhr--;
                    nowmin = 59;
                }
            }

            @Override
            public void onFinish() {
                ((TextView) view.findViewById(R.id.timeofhabittextView)).setText("00:00:00");
            }
        }.start();

        ((CheckBox) view.findViewById(R.id.habitcheckBox)).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fourthFragment_to_sixthFragment));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bottomrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final HabitListAdapter rAdapter = new HabitListAdapter(getContext());
        recyclerView.setAdapter(rAdapter);

        ((TextView) view.findViewById(R.id.timeofhabittextView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }
}