package pz2015.habits.rmm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pz2015.habits.rmm.R;
import pz2015.habits.rmm.model.Habit;

/**
 * Created by ASUS on 2015-04-27.
 */
public class HabitAdapter extends ArrayAdapter {

    private LayoutInflater inflater;
    private List<Habit> habits;

    public HabitAdapter(Context activity, String[] items) {
        super(activity, R.layout.row_habit, items);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public HabitAdapter(Context activity, List<Habit> habits) {
        super(activity, R.layout.row_habit, habits);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.habits = habits;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.row_habit, parent, false);

        TextView title = (TextView) rowView.findViewById(R.id.habitTitle);
        TextView description = (TextView) rowView.findViewById(R.id.habitDescription);
        TextView date = (TextView) rowView.findViewById(R.id.habitDate);
        ImageView image = (ImageView) rowView.findViewById(R.id.imageView);

        title.setText(habits.get(position).getTitle());
        description.setText(habits.get(position).getDescription());
        date.setText("DATKA");
        image.setImageDrawable(habits.get(position).getImage());

        return rowView;
    }

}
