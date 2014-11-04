package com.capr.interfaces;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Gantz on 3/11/14.
 */
public interface Interface_Notification {

    void createNotification(Context context);
    void setProgress(int max,int progress);
    void onFinish();
    void vibrate();
    void invalidate();

    NotificationCompat.Builder getBuilder();
    NotificationManager getManager();
}
