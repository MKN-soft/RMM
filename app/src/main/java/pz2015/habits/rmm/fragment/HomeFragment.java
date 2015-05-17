package pz2015.habits.rmm.fragment;

/**
 * Created by Marcin on 2015-04-26.
 * Displays list of Habit objects
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
    private List<Habit> habits = new ArrayList();

    // Wykomentowane na pozniej do serializacji
    //private static final String HABITS_CACHE_FILE = "habit_cache.ser";

    private View view;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //na razie randomowe habitsy
        habits = randomHabits();

        //?????
        habitItemArrayAdapter = new HabitAdapter(rootView.getContext(), habits);

        //to chyba usunąć i poprawić całą metode
        setListAdapter(habitItemArrayAdapter);
        LogicBase.setHabitItemArrayAdapter(habitItemArrayAdapter); // remember reference to list of items
        LogicBase.setHomeFragment(this);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //NIE RUSZAC - moze sie przydac :)
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

    private List<Habit> randomHabits() {
        List<Habit> habits = new ArrayList<Habit>();
        for (int i = 0; i < 5; i++) {
            Habit habit = new Habit("Tytuł nawyku #" + i, "Trochę tekstu dla body #" + i);
            habits.add(habit);
        }
        return habits;
    }

}
