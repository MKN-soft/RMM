package pz2015.habits.rmm;

import android.widget.ArrayAdapter;

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

    public static void removeHabitItemAt(int position){


        int count = habitItemArrayAdapter.getCount();

        //do zmiany na Habit
        Habit[] array = new Habit[count -1]; //
        int idA = 0;
        for(int i = 0 ; i < count ; i++){
            if(i == position)
                continue;

            array[idA] = (Habit) habitItemArrayAdapter.getItem(i);
            idA++;
        }

        habitItemArrayAdapter = new HabitAdapter(habitItemArrayAdapter.getContext(), array);
        homeFragment.setListAdapter(habitItemArrayAdapter);
        //habitItemArrayAdapter.remove(habitItemArrayAdapter.getItem(position));

    }
}
