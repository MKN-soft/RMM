package pz2015.habits.rmm.fragment;

/**
 * Created by Marcin on 2015-04-26.
 * Displays list of Habit objects
 */

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pz2015.habits.rmm.LogicBase;
import pz2015.habits.rmm.R;
import pz2015.habits.rmm.activity.HabitDetailActivity;
import pz2015.habits.rmm.adapter.HabitAdapter;
import pz2015.habits.rmm.model.Habit;
import pz2015.habits.rmm.model.HabitToFile;
import pz2015.habits.rmm.model.Statistic;

public class HomeFragment extends ListFragment {

    // Wykomentowane na pozniej do serializacji
    private static final String STATISTICS_CACHE_FILE = "statistics_cache.ser";
    private static final String HABITS_CACHE_FILE = "habit_cache.ser";
    public static List<Habit> hab;
    private ListView habitListView;
    private ArrayAdapter habitItemArrayAdapter;
    private List<Habit> habits;
    private View view;


    public HomeFragment() {
    }

    public HomeFragment(List<Habit> habits) {
        this.habits = habits;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.view = rootView;

        //na razie randomowe habitsy
        //habits = randomHabits();

        if (this.habits == null) {
            habits =  new ArrayList();
        }
        else {
            hab = habits;
        }



        //create array adapter for homeFragment with given random habits
        habitItemArrayAdapter = new HabitAdapter(rootView.getContext(), habits);

        //Place which connect with components logicBase
        setListAdapter(habitItemArrayAdapter);
        LogicBase.setHabitItemArrayAdapter(habitItemArrayAdapter); // remember reference to list of items
        LogicBase.setHomeFragment(this);


        return rootView;
    }

    private void writeHabitsToFile(List<Habit> habits, View v) {
        try {
            List<Statistic> statistics = new ArrayList<>();

            for (int i = 0; i < habits.size(); i++)
                statistics.add(new Statistic(habits.get(i)));

            FileOutputStream fos = v.getContext().openFileOutput(STATISTICS_CACHE_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(statistics);
            oos.close();
            fos.close();
            Log.d("RMM", "Successfully wrote statistics to the file");
        } catch (Exception e) {
            Log.e("RMM", "Error writing statistics", e);
        }
    }


    private List<Habit> randomHabits() {
        List<Habit> habits = new ArrayList<Habit>();
        Drawable image = getResources().getDrawable(R.mipmap.ic_pages);

        Date date = new Date();
        String date1 = new SimpleDateFormat("dd MMMM yyyy").format(date);

        Habit habit1 = new Habit("Bieganie", "Jestem gruby i chcę biegać", "1",image, "Motywacja", date1, 0);
        habits.add(habit1);
        return habits;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        LogicBase.setPosition(position); // remember selected item position
        Intent intent = new Intent(getActivity(), HabitDetailActivity.class);
        getActivity().startActivity(intent);
    }

    public static List<Habit> getHabit() {
        return hab;
    }

}
