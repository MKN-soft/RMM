package pz2015.habits.rmm.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

import pz2015.habits.rmm.JSONParser;
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
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


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

            //Getting json object
            JSONObject json = jParser.getJSONFromUrl(URL_LOGIN, list);

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