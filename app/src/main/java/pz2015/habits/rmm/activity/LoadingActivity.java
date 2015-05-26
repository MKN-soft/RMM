package pz2015.habits.rmm.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import org.json.*;

import pz2015.habits.rmm.JSONParser;
import pz2015.habits.rmm.R;

public class LoadingActivity extends Activity {

    //Progress Dialog
    private ProgressDialog pDialog;

    //Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    //URL to post
    private static String URL_LOGIN = "http://77.255.48.157/php/check_login.php";

    //JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        //start thread login
        new LoginThread().execute();

        //TODO: back to login screen or signup or habit list


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loading, menu);
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

    class LoginThread extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoadingActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

            //Hooking Activity
            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
            startActivity(intent);
        }

        /**
         * getting from url
         * */
        @Override
        protected String doInBackground(String... params) {
            JSONObject json = jParser.getJSONFromUrl(URL_LOGIN);

            //Check logcat for JSON Response
            Log.d("RMM - JSON - ", json.toString());
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            //dismiss the dialog after log in
            pDialog.dismiss();
        }

    }

}