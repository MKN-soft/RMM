package pz2015.habits.rmm.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import pz2015.habits.rmm.LogicBase;
import pz2015.habits.rmm.R;
import pz2015.habits.rmm.model.Habit;

/**
 * Created by Natka on 2015-05-22.
 */
public class AddImageActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_image);

        ImageButton Image1 = (ImageButton) findViewById(R.id.image1);
        ImageButton Image2 = (ImageButton) findViewById(R.id.image2);
        ImageButton Image3 = (ImageButton) findViewById(R.id.image3);
        ImageButton Image4 = (ImageButton) findViewById(R.id.image4);
        ImageButton Image5 = (ImageButton) findViewById(R.id.image5);
        ImageButton Image6 = (ImageButton) findViewById(R.id.image6);
        ImageButton Image7 = (ImageButton) findViewById(R.id.image7);
        ImageButton Image8 = (ImageButton) findViewById(R.id.image8);
        ImageButton Image9 = (ImageButton) findViewById(R.id.image9);


        Image1.setImageResource(R.mipmap.ic_communities);
        Image2.setImageResource(R.mipmap.ic_home);
        Image3.setImageResource(R.mipmap.ic_launcher);
        Image4.setImageResource(R.mipmap.ic_pages);
        Image5.setImageResource(R.mipmap.ic_people);
        Image6.setImageResource(R.mipmap.ic_photos);
        Image7.setImageResource(R.mipmap.ic_running);
        Image8.setImageResource(R.mipmap.ic_whats_hot);
        Image9.setImageResource(R.mipmap.ic_question_mark);

        //Drawable image = getResources().getDrawable(R.mipmap.ic_question_mark);//obojetnie co
        //final Habit habit = new Habit("title", "description", "frequency", image, "notes");
        //LogicBase.addHabit(habit);

        Image1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_communities);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);
                //Drawable image = getResources().getDrawable(R.mipmap.ic_communities);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_home);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_launcher);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_pages);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_people);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_photos);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_running);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_whats_hot);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable image = getResources().getDrawable(R.mipmap.ic_question_mark);
                Habit habit = new Habit("title", "description", "frequency", image, "notes");
                LogicBase.addHabit(habit);

                //int position = LogicBase.getLastPosition();
                //habit.setImage(image);
                //exchange old version habit on edit version
                //LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });
    }
}
