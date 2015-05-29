package pz2015.habits.rmm.others;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.NameValuePair;

import java.util.List;

import pz2015.habits.rmm.PostManagement;
import pz2015.habits.rmm.R;
import pz2015.habits.rmm.activity.MainActivity;
import pz2015.habits.rmm.activity.login_and_registration.ConnectionTask;

/**
 * Created by ASUS on 2015-05-28.
 */
public enum Errors {
    // User exists errors
    USER_NOT_EXISTS(2) {
        public void make(final Context context, final List<NameValuePair> list) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    .setTitle(context.getResources().getString(R.string.title_no_account))
//                    .setMessage(context.getResources().getString(R.string.description_no_account))
//                    .setIcon(R.mipmap.ic_launcher);
            final FrameLayout frameView = new FrameLayout(context);
            builder.setView(frameView);

            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = alertDialog.getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.popup_dialog, frameView);

            TextView title = (TextView) frameView.findViewById(R.id.popupTitle);
            title.setText(context.getResources().getString(R.string.title_no_account));

            TextView description = (TextView) frameView.findViewById(R.id.popupDescription);
            description.setText(context.getResources().getString(R.string.description_no_account));

            ImageView image = (ImageView) frameView.findViewById(R.id.popupImageView);
            image.setImageResource(R.mipmap.ic_launcher);

            alertDialog.show();

            Button yesButton = (Button) dialogLayout.findViewById(R.id.popupButtonOK);
            Button noButton = (Button) dialogLayout.findViewById(R.id.popupButtonNO);

            //Register
            yesButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Register user
                    ConnectionTask connectionTask = new ConnectionTask(context, false);
                    connectionTask.execute();
                }

            });

            //Exit from program...
            noButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.exit(0);
                }

            });
        }
    },
    USER_FOUND(1) {
        public void make(Context context, List<NameValuePair> list) {
            // Hooking Activity
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

            // Close this activity
            ((Activity)context).finish();
        }
    },
    USER_JSON_IS_NULL(0) {
        public void make(Context context, List<NameValuePair> list) {
            dialogError(context, context.getResources().getString(R.string.dialog_title_noserverconn)
                    , context.getResources().getString(R.string.dialog_noserverconn));
        }
    },
    USER_NO_INTERNET_SERVICE(-1) {
        public void make(Context context, List<NameValuePair> list) {
            dialogError(context, context.getResources().getString(R.string.dialog_title_internetconnection)
                    , context.getResources().getString(R.string.dialog_internetconnection));
        }
    },
    USER_BAD_PASSWORD(-2) {
        public void make(Context context, List<NameValuePair> list) {
            dialogError(context, context.getResources().getString(R.string.dialog_title_badpassword)
                    , context.getResources().getString(R.string.dialog_badpassword));
        }
    },
    CRITICAL_ERROR(666) {
        public void make(Context context, List<NameValuePair> list) {
            dialogError(context, context.getResources().getString(R.string.dialog_title_criticalerror)
                    , context.getResources().getString(R.string.dialog_criticalerror));
        }
    },
    USER_CREATED(3) {
        public void make(Context context, List<NameValuePair> list) {
            // Login to new created user
            ConnectionTask connectionTask = new ConnectionTask(context, true);
            connectionTask.execute();
        }
    };

    public abstract void make(Context context, List<NameValuePair> list);

    private int value;

    private Errors(int value) {
        this.value = value;
    }

    public int getValue() { return this.value;}

    public void dialogError(Context context, String title, String description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(description)
                .setTitle(title)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }
}