package com.capr.views;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.capr.beans.Respuesta_DTO;
import com.capr.beans.Timer_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Timer extends View_Opino {

    private Timer_DTO timer_dto;
    private TextView txttimer;

    long startTime = 0;

    public View_Timer(Context context) {
        super(context, R.layout.view_timer);
    }

    public View_Timer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_timer);
    }

    public View_Timer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_timer);
    }

    public View_Timer(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_timer);
    }


    @Override
    protected void initView() {
        super.initView();
        txttimer = (TextView) getView().findViewById(R.id.txttimer);
        txttimer.setTypeface(Util_Fonts.setPNASemiBold(getContext()));

        final Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {

            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                txttimer.setText(String.format("%d:%02d", minutes, seconds));
                timerHandler.postDelayed(this, 500);
            }
        };

        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    public Timer_DTO getTimer_dto() {
        return timer_dto;
    }

    public void setTimer_dto(Timer_DTO timer_dto) {
        this.timer_dto = timer_dto;
    }
}
