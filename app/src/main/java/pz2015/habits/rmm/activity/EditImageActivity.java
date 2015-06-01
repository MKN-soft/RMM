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
 * Class change image in habit.
 */
public class EditImageActivity extends Activity {
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


        Image1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_communities);
                habit.setImage(image, R.mipmap.ic_communities);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_home);
                habit.setImage(image, R.mipmap.ic_home);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_launcher);
                habit.setImage(image, R.mipmap.ic_launcher);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_pages);
                habit.setImage(image, R.mipmap.ic_pages);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_people);
                habit.setImage(image, R.mipmap.ic_people);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_photos);
                habit.setImage(image, R.mipmap.ic_photos);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_running);
                habit.setImage(image, R.mipmap.ic_running);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_whats_hot);
                habit.setImage(image, R.mipmap.ic_whats_hot);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });

        Image9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = LogicBase.getPosition();
                Habit habit = LogicBase.getHabitAt(position);
                Drawable image = getResources().getDrawable(R.mipmap.ic_question_mark);
                habit.setImage(image, R.mipmap.ic_question_mark);
                LogicBase.setHabitAt(position, habit);

                //onResume();
                finish();
            }
        });
    }
}

