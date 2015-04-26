package pz2015.habits.rmm;

/**
 * Created by Marcin on 2015-04-26.
 */

import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ListView habitListView;
    private ArrayAdapter habitItemArrayAdapter;
    //private List<Habit> habits = new ArrayList();
    private String[] stringArray;

    private View view;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        stringArray = new String[10];
        for(int i=0; i < stringArray.length; i++){
            stringArray[i] = "String " + i;
        }

        habitItemArrayAdapter = new ArrayAdapter(rootView.getContext(), android.R.layout.simple_list_item_1, stringArray);
        habitListView = (ListView) rootView.findViewById(R.id.habitList);
        habitListView.setAdapter(habitItemArrayAdapter);


        return rootView;
    }

}
