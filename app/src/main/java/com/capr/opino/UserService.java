package com.capr.opino;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class UserService extends Service {

    private Timer timer = new Timer();
    public static final String END_SESSION_TIME = "14:47:00";

    public UserService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("SERVICE", "SERVICE CREADO");
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SERVICE", "SERVICIO INICIADO");

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("SERVICE",getCurrentTime());
            }
        }, 0, 1000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("SERVICE", "DESTRUIDO");
    }

    public String getCurrentTime() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        return today.format("%k:%M:%S");
    }
}
