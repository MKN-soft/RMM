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

    WhichSide DarkSide;

    public enum WhichSide {
        LOGIN {
            public Errors make(Context context, List<NameValuePair> list) {
                // is user exists ?
                PostManagement pm = new PostManagement(context, list);
                return pm.isUserExists();
            }
        },
        REGISTER {
            public Errors make(Context context, List<NameValuePair> list) {
                // register user
                PostManagement pm = new PostManagement(context, list);
                return pm.registerNewUser();
            }
        },
        SYNCHRONIZE {
            public Errors make(Context context, List<NameValuePair> list) {
                // synchronize
                PostManagement pm = new PostManagement(context, list);
                return pm.synchro();
            }
        };

        public abstract Errors make(Context context, List<NameValuePair> list);
    }

    public ConnectionTask(Context context, WhichSide DarkSide) {
        this.context = context;
        this.p = new ProgressDialog(context);
        this.DarkSide = DarkSide;
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

            // LOOK TO ENUM CLASS UPPER!!!
            result = this.DarkSide.make(context, list);
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
