package pz2015.habits.rmm.activity.login_and_registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pz2015.habits.rmm.PostManagement;
import pz2015.habits.rmm.R;
import pz2015.habits.rmm.activity.MainActivity;
import pz2015.habits.rmm.others.ConnectionDetector;
import pz2015.habits.rmm.others.Errors;

//Ekran do ładowania po wpisaniu loginu i hasła, docelowo będzie "bardziej uniwersalny", do rejestracji, logowania itp...

public class LoadingActivity extends Activity {
    //Context
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        // Start thread login
        new LoginThread().execute();
    }

    private void popupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(getResources().getString(R.string.title_no_account))
                .setMessage(getResources().getString(R.string.description_no_account))
                .setIcon(R.mipmap.ic_launcher);
        final FrameLayout frameView = new FrameLayout(context);
        builder.setView(frameView);

        final AlertDialog alertDialog = builder.create();
        LayoutInflater inflater = alertDialog.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.popup_dialog, frameView);
        alertDialog.show();

        Button yesButton = (Button) dialogLayout.findViewById(R.id.dialogButtonOK);
        Button noButton = (Button) dialogLayout.findViewById(R.id.dialogButtonNO);

        //Register
        yesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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

    class LoginThread extends AsyncTask<String, String, Void> {

        String username;
        String password;
        Errors result;

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            SharedPreferences prefs = getSharedPreferences("rmm", MODE_PRIVATE);
            username = prefs.getString("username", null);
            password = prefs.getString("password", null);
        }

        /**
         * Getting from url
         * */
        @Override
        protected Void doInBackground(String... params) {
            // Building parameters
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("username", username));
            list.add(new BasicNameValuePair("password", password));

            JSONObject json = null;

            // Check internet connection
            ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            if (cd.isConnectingToInternet() == true) {
                // Is User Exists?
                PostManagement pm = new PostManagement(list);
                result = pm.isUserExists();
            }
            else {
                // no internet connection
                result = Errors.USER_NO_INTERNET_SERVICE;
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            switch(this.result) {
                case USER_NOT_EXISTS:
                    // Register
                    popupDialog();
                    break;
                case USER_FOUND:
                    // User found

                    // Hooking Activity
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Close this activity
                    finish();
                    break;
                case USER_JSON_IS_NULL:
                    // JSON IS NULL ? (Maybe cannont connect to server)

                    break;
                case USER_NO_INTERNET_SERVICE:
                    // No service internet connection

                    break;
                default:

                    break;
            }
        }

    }

}