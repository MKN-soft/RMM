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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import pz2015.habits.rmm.R;
import pz2015.habits.rmm.activity.MainActivity;
import pz2015.habits.rmm.activity.login_and_registration.ConnectionTask;
import pz2015.habits.rmm.model.Habit;
import pz2015.habits.rmm.model.HabitToFile;
import pz2015.habits.rmm.model.Statistic;
import pz2015.habits.rmm.others.ConnectionDetector;

/**
 * Created by ASUS on 2015-05-30.
 */
public class SynchroService extends Service {

    private static final String STATISTICS_CACHE_FILE = "statistics_cache.ser";
    private static final String HABITS_CACHE_FILE = "habit_cache.ser";
    public static Runnable runnable = null;
    public Context context = this;
    public Handler handler = null;

    // Initialize internet detector
    ConnectionDetector cd;


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
        // First: Authorization with server
        // Second: Send prepared data

        // User salt for authorization
        SharedPreferences prefs = getSharedPreferences("rmm", MODE_PRIVATE);
        String salt = prefs.getString("salt", null);

        // Prepare data for send
        List<Statistic> statistics = readHabitsFromFile();
        final List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("salt", salt));
        params.add(new BasicNameValuePair("count", Integer.toString(statistics.size())));

        for (int i = 0; i < statistics.size(); i++) {
            Statistic statistic = statistics.get(i);

            if (statistic.emptyLastHabit())
                continue;
            String string = statistic.getDone() + ";" +
                    statistic.getDate() + ";" + statistic.getFrequency() + ";" +
                    statistic.getLastDate() + ";" + statistic.getBestScore() + ";" +
                    statistic.getAverageLong() + ";" + statistic.getSuccessPercent() + statistic.getHabitSize();
            params.add(new BasicNameValuePair("habit" + i, string));
        }

        // Create new thread for networking
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Connection detector
                cd = new ConnectionDetector(context);

                // If connection detected, SEND
                ConnectionTask.WhichSide darkside = ConnectionTask.WhichSide.SYNCHRONIZE;
                if (cd.isConnectingToInternet() == true)
                    darkside.make(context, params);
            }
        });

        // Start thread
        thread.start();
    }

    private List<Statistic> readHabitsFromFile() {
        List<HabitToFile> readHabitToFile;
        List<Habit> habits = new ArrayList<>();
        List<Statistic> statistics = new ArrayList<>();
        try {
            FileInputStream fis = getApplicationContext().openFileInput(HABITS_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            readHabitToFile = (List<HabitToFile>) ois.readObject();

            ois.close();
            fis.close();



            if (readHabitToFile != null) {
                for (int i = 0; i < readHabitToFile.size(); i++) {
                    habits.add(new Habit(getApplicationContext(), readHabitToFile.get(i)));
                }
                for (int i = 0; i < habits.size(); i++) {
                    statistics.add(new Statistic(habits.get(i)));
                }
            }


            Log.d("RMM", "Successfully read statistics from the file");
        } catch (Exception e) {
            Log.e("RMM", "Error reading statistics", e);
        }

        return statistics;
    }


}