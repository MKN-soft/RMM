package pz2015.habits.rmm.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import pz2015.habits.rmm.R;
import pz2015.habits.rmm.activity.MainActivity;
import pz2015.habits.rmm.activity.login_and_registration.ConnectionTask;
import pz2015.habits.rmm.model.Statistic;
import pz2015.habits.rmm.others.ConnectionDetector;

/**
 * Created by ASUS on 2015-05-30.
 */
public class SynchroService extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    // Initialize internet detector
    ConnectionDetector cd;
    private static final String HABITS_CACHE_FILE = "habit_cache.ser";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // Notification
        makeNotification();

        // Synchro
        makeSynchro();
    }

    @Override
    public void onDestroy() {
        /* IF YOU WANT THIS SERVICE KILLED WITH THE APP THEN UNCOMMENT THE FOLLOWING LINE */
        //handler.removeCallbacks(runnable);
        //Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;
    }

    private List<Statistic> readHabits() {
        List<Statistic> statistics = new ArrayList<>();
        try {

            FileInputStream fis = openFileInput(HABITS_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            statistics = (List<Statistic>) ois.readObject();

            ois.close();
            fis.close();
            Log.d("MasterChief", "Successfully read statistics from the file");
        } catch(Exception e) {
            Log.e("MasterChief", "Error reading statistics", e);
        }

        return statistics;
    }

    private void makeNotification() {
        // Make notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_halo)
                        .setContentTitle(context.getResources().getString(R.string.notification_habit_title))
                        .setContentText(context.getResources().getString(R.string.master_chef_1));
        int NOTIFICATION_ID = 12345;

        Intent targetIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void makeSynchro() {

        SharedPreferences prefs = getSharedPreferences("rmm", MODE_PRIVATE);
        String salt =  prefs.getString("salt", null);

        List<Statistic> statistics = readHabits();

        final List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("salt", salt));

        for (int i = 0; i < statistics.size(); i++) {
            params.add(new BasicNameValuePair("habit"+i, statistics.get(i).getId() + ";" + statistics.get(i).getDate() + ";" + statistics.get(i).getFrequency()));
        }

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                // Connection detector
                cd = new ConnectionDetector(context);

                ConnectionTask.WhichSide darkside = ConnectionTask.WhichSide.SYNCHRONIZE;
                if (cd.isConnectingToInternet() == true)
                    darkside.make(context, params);
            }
        });
        thread.start();

    }


}