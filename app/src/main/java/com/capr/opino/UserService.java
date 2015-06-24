package com.capr.opino;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.capr.actividades.Entrar;
import com.capr.actividades.Opino;
import com.capr.session.Session_Manager;

import java.util.Timer;
import java.util.TimerTask;

public class UserService extends Service {

    public static final String END_SESSION_TIME_A = "24:00:00";

    private static Timer timer = new Timer();
    private Context ctx;

    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        ctx = this;
        startService();
    }

    private void startService() {
        timer.scheduleAtFixedRate(new mainTask(), 0, 1000);
    }

    private class mainTask extends TimerTask {
        public void run() {
            toastHandler.sendEmptyMessage(0);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped ...", Toast.LENGTH_SHORT).show();
    }

    private final Handler toastHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (getCurrentTime().equals(END_SESSION_TIME_A)) {
                Session_Manager session_manager = new Session_Manager(ctx);
                session_manager.cerrarSessionv2();
                session_manager.setMode(true);
            }
        }
    };

    public String getCurrentTime() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        return today.format("%k:%M:%S");
    }
}
