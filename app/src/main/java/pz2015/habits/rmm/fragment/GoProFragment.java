package pz2015.habits.rmm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pz2015.habits.rmm.R;


public class GoProFragment extends Fragment {

    public GoProFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_go_pro, container, false);

        return rootView;
    }

}