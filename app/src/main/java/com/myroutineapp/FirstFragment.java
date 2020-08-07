package com.myroutineapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FirstFragment extends Fragment {

    private HabitViewModel habitViewModel;
    private HabitListAdapter adapter;
    Bundle bundle = new Bundle();

    public FirstFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final HabitListAdapter rAdapter = new HabitListAdapter(getContext());
        recyclerView.setAdapter(rAdapter);

        view.findViewById(R.id.addnewhabitbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        habitViewModel.getAllHabits().observe(getViewLifecycleOwner(), new Observer<List<Habit>>() {
            @Override
            public void onChanged(List<Habit> habits) {
                rAdapter.setHabits(habits);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String name = HabitListAdapter.getName(viewHolder.getAdapterPosition());
                String desc = HabitListAdapter.getDesc(viewHolder.getAdapterPosition());
                String time = HabitListAdapter.getTime(viewHolder.getAdapterPosition());

                bundle.putString("Habit_Name", name);
                bundle.putString("Habit_Desc", desc);
                bundle.putString("Habit_Time", time);
                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_thirdFragment, bundle);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }
/*
    public void navigateToThird(Bundle bundle){
        Toast.makeText(getContext(),"Working",Toast.LENGTH_LONG).show();
        //NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_thirdFragment,bundle);
    }
    public View getV(){
        return getView();
    }
/*
    void switchContent(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }*/
}