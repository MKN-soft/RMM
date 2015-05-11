package pz2015.habits.rmm.fragment;

/**
 * Created by Marcin on 2015-04-26.
 */

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pz2015.habits.rmm.LogicBase;
import pz2015.habits.rmm.R;
import pz2015.habits.rmm.activity.HabitDetailActivity;
import pz2015.habits.rmm.adapter.HabitAdapter;
import pz2015.habits.rmm.model.Habit;

public class HomeFragment extends ListFragment {

    private ListView habitListView;
    private ArrayAdapter habitItemArrayAdapter;
    //private List<Habit> habits = new ArrayList();
    private Habit[] habits = new Habit[5];
    //private static final String HABITS_CACHE_FILE = "habit_cache.ser";

    private View view;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //na razie randomowe habitsy
        randomHabits(this.habits);

        //habitItemArrayAdapter = new ArrayAdapter(rootView.getContext(), android.R.layout.simple_list_item_1, stringArray);
        //habitItemArrayAdapter = new HabitAdapter(rootView.getContext(), new String[10]);
        //habitItemArrayAdapter = new HabitAdapter(rootView.getContext(), habits);
        //habitListView = (ListView) rootView.findViewById(R.id.habitList);
        //habitListView.setAdapter(habitItemArrayAdapter);

        //to chyba usunąć i poprawić całą metode
        setListAdapter(habitItemArrayAdapter);
        LogicBase.setHabitItemArrayAdapter(habitItemArrayAdapter); // remember reference to list of items
        LogicBase.setHomeFragment(this);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//        Fragment mFragment = new HabitDetailFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.frame_container, mFragment);
//        transaction.addToBackStack(null);
//
//        transaction.commit();

        LogicBase.setPosition(position); // remmber selected item position
        Intent intent = new Intent(getActivity(), HabitDetailActivity.class);
        getActivity().startActivity(intent);
    }

    private void randomHabits(Habit[] habits) {
        for (int i = 0; i < 5; i++) {
            Habit habit = new Habit("Tytuł nawyku #" + i, "Trochę tekstu dla body #" + i);
            habits[i] = habit;
        }
    }

}
