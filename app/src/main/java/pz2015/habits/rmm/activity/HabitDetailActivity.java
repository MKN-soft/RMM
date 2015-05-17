package pz2015.habits.rmm.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pz2015.habits.rmm.LogicBase;
import pz2015.habits.rmm.R;

/**
 * Created by ASUS on 2015-04-27.
 */
public class HabitDetailActivity extends Activity {

    private Button _editHabit, _deleteHabit;

    private Context selfContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail);

        selfContext = this;

        _editHabit = (Button) findViewById(R.id.editHabit);
        _deleteHabit = (Button) findViewById(R.id.deleteHabit);

        _editHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Hooking Activity
                Intent intent = new Intent(HabitDetailActivity.this, EditHabitActivity.class);
                startActivity(intent);



            }
        });

        _deleteHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Hooking Activity
                //Intent intent = new Intent(HabitDetailActivity.this, DeleteHabitActivity.class);
                //startActivity(intent);
                // remove this item from list

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                int position = LogicBase.getPosition();
                                LogicBase.removeHabitItemAt(position);
                                finish(); // ends current activity and go to previous
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(selfContext);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });
    }


//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.activity_habit_detail, container, false);
//
//        return rootView;
//    }

}
