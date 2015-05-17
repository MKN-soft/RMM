package pz2015.habits.rmm.activity;

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


public class EditHabitActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);

        Button editHabitButton = (Button) findViewById(R.id.editHabitButton);

        final EditText habitName = (EditText) findViewById(R.id.editHabitName);
        final EditText habitFrequency = (EditText) findViewById(R.id.editHabitFrequency);

        editHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int position = LogicBase.getPosition();
               Habit habit = new Habit(habitName.getText().toString(),habitFrequency.getText().toString() );
               LogicBase.setHabitAt(position, habit);

               finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_habit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
