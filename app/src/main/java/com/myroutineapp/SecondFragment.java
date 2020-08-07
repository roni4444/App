package com.myroutineapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    private HabitViewModel habitViewModel;

    private EditText nameeditText;
    private EditText desceditText;
    private EditText timeeditText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameeditText = view.findViewById(R.id.editTextHabitName);
        desceditText = view.findViewById(R.id.editTextHabitDesc);
        timeeditText = view.findViewById(R.id.editTextHabitTime);


        view.findViewById(R.id.newhabitbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Habit habit = new Habit(nameeditText.getText().toString(), desceditText.getText().toString(), timeeditText.getText().toString());
                habitViewModel.insert(habit);
                NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}