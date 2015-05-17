package pz2015.habits.rmm;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import pz2015.habits.rmm.adapter.HabitAdapter;
import pz2015.habits.rmm.fragment.HomeFragment;
import pz2015.habits.rmm.model.Habit;

/**
 * Created by Natka on 2015-04-30.
 */
public class LogicBase {

    private static ArrayAdapter habitItemArrayAdapter;
    private static int position;
    private static HomeFragment homeFragment;

    public static void setHomeFragment(HomeFragment hf){
        homeFragment = hf;
    }

    public static void setHabitItemArrayAdapter(ArrayAdapter hiaa){
        habitItemArrayAdapter = hiaa;
    }

    public static void setPosition(int pos){
        position = pos;
    }

    public static int getPosition(){
        return position;
    }

    public static Habit getHabitAt(int position){
        int count = habitItemArrayAdapter.getCount();

        if(position < count){
            return (Habit)habitItemArrayAdapter.getItem(position);
        }else{
            return null;
        }

    }

    public static void refreshList(){
        homeFragment.setListAdapter(habitItemArrayAdapter);
    }

    public static void setHabitAt(int position, Habit habit){
        int count = habitItemArrayAdapter.getCount();

        // loop which creat new list but avoid delete element from old list
        List<Habit> modifiedList = new ArrayList<Habit>(); //count -1
        for(int i = 0 ; i < count ; i++){
            if(i == position) {
                modifiedList.add(habit);
                continue;
            }
            modifiedList.add((Habit) habitItemArrayAdapter.getItem(i));
        }

        habitItemArrayAdapter = new HabitAdapter(habitItemArrayAdapter.getContext(), modifiedList); //habitItemArrayAdapter.getContext()
        homeFragment.setListAdapter(habitItemArrayAdapter);
    }

    public static void addHabit(Habit newHabit){
        int count = habitItemArrayAdapter.getCount();
         List<Habit> newList = new ArrayList<Habit>(); //count -1
        for(int i = 0 ; i < count ; i++){
            newList.add((Habit) habitItemArrayAdapter.getItem(i));
        }
        newList.add(newHabit);

        habitItemArrayAdapter = new HabitAdapter(habitItemArrayAdapter.getContext(), newList); //habitItemArrayAdapter.getContext()
        homeFragment.setListAdapter(habitItemArrayAdapter);
    }

    public static void removeHabitItemAt(int position){


        int count = habitItemArrayAdapter.getCount();


        // loop which creat new list but avoid delete element from old list
        List<Habit> listWithoutRemovedElement = new ArrayList<Habit>(); //count -1
        for(int i = 0 ; i < count ; i++){
            if(i == position)
                continue;
            listWithoutRemovedElement.add((Habit) habitItemArrayAdapter.getItem(i));
        }

        habitItemArrayAdapter = new HabitAdapter(habitItemArrayAdapter.getContext(), listWithoutRemovedElement); //habitItemArrayAdapter.getContext()
        homeFragment.setListAdapter(habitItemArrayAdapter);
        //habitItemArrayAdapter.remove(habitItemArrayAdapter.getItem(position));

    }
}
