package pz2015.habits.rmm.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pz2015.habits.rmm.R;

/**
 * Created by ASUS on 2015-04-27.
 */
public class SettingsFragment extends Fragment {

    private SharedPreferences prefs;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        prefs = this.getActivity().getSharedPreferences("rmm", Context.MODE_PRIVATE);
        String username = prefs.getString("username", null);
        String password = prefs.getString("password", null);
        String salt = prefs.getString("salt", null);

        TextView userLogin = (TextView) rootView.findViewById(R.id.userLogin);
        TextView userPassword = (TextView) rootView.findViewById(R.id.userPassword);
        TextView userSalt = (TextView) rootView.findViewById(R.id.userSalt);

        userLogin.setText(username);
        userPassword.setText(password);
        userSalt.setText(salt);

        return rootView;
    }

}
