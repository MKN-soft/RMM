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

    //method return habit on given in arguments position
    public static Habit getHabitAt(int position){
        int count = habitItemArrayAdapter.getCount();

        if(position < count){
            return (Habit)habitItemArrayAdapter.getItem(position);
        }else{
            return null;
        }

    }

    // method only updating the list view, may be useful later
    public static void refreshList(){
        homeFragment.setListAdapter(habitItemArrayAdapter);
    }

    // exchange old habit on new with change in this habit
    public static void setHabitAt(int position, Habit habit){
        int count = habitItemArrayAdapter.getCount();

        // loop which create new list but avoid delete element from old list
        List<Habit> modifiedList = new ArrayList<Habit>(); //count -1
        for(int i = 0 ; i < count ; i++){
            if(i == position) {
               //add modified habit
                modifiedList.add(habit);
                continue;
            }
            // add Habit from list (habitItemArrayAdapter keep all list habits)
            modifiedList.add((Habit) habitItemArrayAdapter.getItem(i));
        }

        //exchange on new HabitAdapter with new List
        habitItemArrayAdapter = new HabitAdapter(habitItemArrayAdapter.getContext(), modifiedList);
        homeFragment.setListAdapter(habitItemArrayAdapter);
    }

    //method to add habit - create new list, rewrites old habits and add  at the and new habit
    public static void addHabit(Habit newHabit){
        int count = habitItemArrayAdapter.getCount();
         List<Habit> newList = new ArrayList<Habit>(); //count -1
        for(int i = 0 ; i < count ; i++){
            newList.add((Habit) habitItemArrayAdapter.getItem(i));
        }
        newList.add(newHabit);

        habitItemArrayAdapter = new HabitAdapter(habitItemArrayAdapter.getContext(), newList);
        homeFragment.setListAdapter(habitItemArrayAdapter);
    }

    //method to delete habit
    public static void removeHabitItemAt(int position){


        int count = habitItemArrayAdapter.getCount();


        // loop which create new list but avoid delete element from old list
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

    public static Habit getLastHabit(int position){
        int count = habitItemArrayAdapter.getCount();
            if(position == count - 1){
                return (Habit)habitItemArrayAdapter.getItem(position);
            }else{
                return null;
            }

        }
    public static int getLastPosition(){
        int count = habitItemArrayAdapter.getCount();
        int position = count - 1;
        return position;
    }


}
