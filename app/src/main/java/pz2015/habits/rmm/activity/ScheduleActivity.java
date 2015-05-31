package pz2015.habits.rmm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pz2015.habits.rmm.R;

/**
 * Class schedule having menu to calendar and statistic.
 */
public class ScheduleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        Button calendar = (Button) findViewById(R.id.Calendar);
        Button statisticsHabit = (Button) findViewById(R.id.Statistics);



        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(ScheduleActivity.this, CalendarActivity.class);
                startActivity(intent);

                finish();

            }
        });


        statisticsHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*Intent intent = new Intent(ScheduleActivity.this, CalendarActivity.class);
                startActivity(intent);*/

            }
        });
    }

}
