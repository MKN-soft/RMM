package pz2015.habits.rmm.activity.login_and_registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pz2015.habits.rmm.PostManagement;
import pz2015.habits.rmm.others.ConnectionDetector;
import pz2015.habits.rmm.others.Errors;

/**
 * Created by ASUS on 2015-05-29.
 */
public class ConnectionTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog p;

    String username;
    String password;

    List<NameValuePair> list;

    JSONObject json;

    ConnectionDetector cd;

    Errors result;

    private final Context context;

    Boolean LoginOrRegister;

    public ConnectionTask(Context context, Boolean LoginOrRegister) {
        this.context = context;
        this.p = new ProgressDialog(context);
        this.LoginOrRegister = LoginOrRegister;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        p.setMessage("Connecting...");
        p.setIndeterminate(false);
        p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        p.setCancelable(false);
        p.setCanceledOnTouchOutside(false);
        p.show();

        // Get login and password
        SharedPreferences prefs = context.getSharedPreferences("rmm", Context.MODE_PRIVATE);
        username = prefs.getString("username", null);
        password = prefs.getString("password", null);

        // Build list params
        list = new ArrayList<>();
        list.add(new BasicNameValuePair("username", username));
        list.add(new BasicNameValuePair("password", password));

        // Initialize json object
        json = null;

        // Initialize internet detector
        cd = new ConnectionDetector(context);
    }

    @Override
    protected Void doInBackground(Void... params) {
        // Check internet connection
        if (cd.isConnectingToInternet() == true) {
            // internet connection

            if (LoginOrRegister == true) {
                // is user exists ?
                PostManagement pm = new PostManagement(list);
                result = pm.isUserExists();
            }
            else {
                // register user
                PostManagement pm = new PostManagement(list);
                result = pm.registerNewUser();
            }
        }
        else {
            // no internet connection
            result = Errors.USER_NO_INTERNET_SERVICE;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        p.dismiss();

        this.result.make(context, this.list);
    }

}
