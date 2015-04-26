package pz2015.habits.rmm;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by ASUS on 2015-04-27.
 */
public class HabitAdapter extends ArrayAdapter {

    private LayoutInflater inflater;

    public HabitAdapter(Context activity, String[] items) {
        super(activity, R.layout.row_habit, items);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return inflater.inflate(R.layout.row_habit, parent, false);
    }

}
