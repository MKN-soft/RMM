package pz2015.habits.rmm.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

import pz2015.habits.rmm.others.ConnectionDetector;
import pz2015.habits.rmm.others.JSONParser;
import pz2015.habits.rmm.R;

//Ekran do ładowania po wpisaniu loginu i hasła, docelowo będzie "bardziej uniwersalny", do rejestracji, logowania itp...

public class LoadingActivity extends Activity {

    //Progress Dialog
    private ProgressDialog pDialog;

    //Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    //URL to post
    private static String URL_LOGIN = "http://www.patra.waw.pl/php/check_login.php";

    //JSON Node names
    private static final String TAG_SUCCESS = "success";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        //start thread login
        new LoginThread().execute();
    }

    class LoginThread extends AsyncTask<String, String, Void> {

        String username;
        String password;

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Sprawdza czy już się logowaliśmy za pierwszym razem
            SharedPreferences prefs = getSharedPreferences("rmm_sign_up", MODE_PRIVATE);
            username = prefs.getString("username", null);
            password = prefs.getString("password", null);
        }

        /**
         * getting from url
         * */
        @Override
        protected Void doInBackground(String... params) {
            //Building parameters
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("username", username));
            list.add(new BasicNameValuePair("password", password));

            JSONObject json = null;

            //Check internet connection
            ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            if (cd.isConnectingToInternet() == true) {
                //Getting json object
                json = jParser.getJSONFromUrl(URL_LOGIN, list);
                try {
                    //Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        // USER FOUND
                        return null;
                    }
                    else {
                        // USER NOT EXISTS
                        //TODO REJESTRACJA
                        // Hooking Activity
                        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                        startActivity(intent);

                        // Close this activity
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            //TODO Sprawdzenie wszystkich możliwych opcji - istnieje user, brak usera, przejście do rejestracji itd.

            //Check logcat for JSON Response
            Log.d("RMM - JSON - ", json.toString());
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Hooking Activity
            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
            startActivity(intent);

            // Close this activity
            finish();
        }

    }

}