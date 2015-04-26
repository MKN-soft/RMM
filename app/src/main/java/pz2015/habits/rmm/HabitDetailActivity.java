package pz2015.habits.rmm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ASUS on 2015-04-27.
 */
public class HabitDetailActivity extends Activity {

    private Button _addHabit, _deleteHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail);

        _addHabit = (Button) findViewById(R.id.addHabit);
        _deleteHabit = (Button) findViewById(R.id.deleteHabit);

        _addHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Hooking Activity
                Intent intent = new Intent(HabitDetailActivity.this, AddHabitActivity.class);
                startActivity(intent);
            }
        });

        _deleteHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Hooking Activity
                Intent intent = new Intent(HabitDetailActivity.this, DeleteHabitActivity.class);
                startActivity(intent);
            }
        });
    }


//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.activity_habit_detail, container, false);
//
//        return rootView;
//    }

}
