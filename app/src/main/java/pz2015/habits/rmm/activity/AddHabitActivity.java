package pz2015.habits.rmm.activity;

import android.preference.EditTextPreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pz2015.habits.rmm.LogicBase;
import pz2015.habits.rmm.R;
import pz2015.habits.rmm.model.Habit;


public class AddHabitActivity extends ActionBarActivity {

    //TODO: adding habit to list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        Button addHabitButton = (Button) findViewById(R.id.addHabitButton);

        final EditText habbitName = (EditText) findViewById(R.id.addHabitName);
        final EditText habbitFrequency = (EditText) findViewById(R.id.addHabitFrequency);

        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Habit newHabit = new Habit(habbitName.getText().toString(), habbitFrequency.getText().toString());
                LogicBase.addHabit(newHabit);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_habit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addHabit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
