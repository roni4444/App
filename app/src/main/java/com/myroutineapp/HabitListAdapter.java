package com.myroutineapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HabitListAdapter extends RecyclerView.Adapter<HabitListAdapter.HabitViewHolder> {

    int selectedPosition = 0;
    Bundle bundle = new Bundle();
    FirstFragment firstFragment = new FirstFragment();

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.habitlistitem, parent, false);
        return new HabitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HabitViewHolder holder, final int position) {
        if (habits != null) {
            Habit cur = habits.get(position);
            holder.habitItemname.setText(cur.getHabitName());
            holder.habitItemdesc.setText(cur.getHabitDesc());
            holder.habitItemtime.setText(cur.getHabitTime());
        } else {
            holder.habitItemname.setText("No Habit");
            holder.habitItemdesc.setText("No Description");
            holder.habitItemtime.setText("No Time");
        }

/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = position;
                Habit cur = habits.get(position);
                String name = cur.getHabitName();
                String desc = cur.getHabitDesc();
                String time = cur.getHabitTime();

                bundle.putString("Habit_Name", name);
                bundle.putString("Habit_Desc", desc);
                bundle.putString("Habit_Time", time);
                firstFragment.setArguments(bundle);
                fragmentJump(name, desc, time);
/*
                Navigation.findNavController(firstFragment.getV()).navigate(R.id.action_FirstFragment_to_thirdFragment,bundle);

            }
        });*/


    }
/*
    private void fragmentJump(String name, String desc, String time) {

    }
*/


    void setHabits(List<Habit> hab) {
        habits = hab;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (habits != null) {
            return habits.size();
        } else
            return 0;
    }

    public class HabitViewHolder extends RecyclerView.ViewHolder {
        private final TextView habitItemname;
        private final TextView habitItemdesc;
        private final TextView habitItemtime;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            habitItemname = itemView.findViewById(R.id.habitnametextView);
            habitItemdesc = itemView.findViewById(R.id.habitdesctextView);
            habitItemtime = itemView.findViewById(R.id.habittimetextView);
        }
    }

    private final LayoutInflater layoutInflater;
    private static List<Habit> habits;

    public static String getName(int position) {
        return habits.get(position).getHabitName();
    }

    public static String getDesc(int position) {
        return habits.get(position).getHabitDesc();
    }

    public static String getTime(int position) {
        return habits.get(position).getHabitTime();
    }

    HabitListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

}
