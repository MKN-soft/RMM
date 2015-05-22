package pz2015.habits.rmm.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import pz2015.habits.rmm.LogicBase;
import pz2015.habits.rmm.R;
import pz2015.habits.rmm.model.Habit;


public class EditHabitActivity extends ActionBarActivity {
    public ImageView habitImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);

        Button editHabitButton = (Button) findViewById(R.id.editHabitButton);
        Button editImageButton = (Button) findViewById(R.id.editImageButton);

        int position = LogicBase.getPosition();
        Habit habit = LogicBase.getHabitAt(position);

        final EditText habitName = (EditText) findViewById(R.id.editHabitName);
        final EditText habitDescription = (EditText) findViewById(R.id.editHabitDescription);
        final EditText habitFrequency = (EditText) findViewById(R.id.editHabitFrequency);
        final EditText habitNotes = (EditText) findViewById(R.id.editHabitNotes);
        habitImage =(ImageView)findViewById(R.id.editHabitImage);



        habitName.setText(habit.getTitle());
        habitDescription.setText(habit.getDescription());
        habitFrequency.setText(habit.getFrequency());
        habitNotes.setText(habit.getNotes());
        habitImage.setImageDrawable(habit.getImage());

        editImageButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditHabitActivity.this, EditImageActivity.class);
                startActivity(intent);

                onResume();


            }
        });

        editHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //give position (from file HomeFragment)
               int position = LogicBase.getPosition();
                //give habit from position
               Habit habit = LogicBase.getHabitAt(position);//new Habit(habitName.getText().toString(),habitFrequency.getText().toString() );
                //rewrite habitName on new
               habit.setTitle(habitName.getText().toString());
                //description
               habit.setDescription(habitDescription.getText().toString());
                //frequency
               habit.setFrequency(habitFrequency.getText().toString());
                //notes
               habit.setNotes(habitNotes.getText().toString());
                //exchange old version habit on edit version
               LogicBase.setHabitAt(position, habit);
               // LogicBase.refreshList();

               finish();
            }
        });
    }

    protected void onResume(){
        super.onResume();
        int position2 = LogicBase.getPosition();
        Habit habit2 = LogicBase.getHabitAt(position2);
        habitImage.setImageDrawable(habit2.getImage());
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
