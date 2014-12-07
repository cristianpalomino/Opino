package com.capr.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    int time = 0;
    Timer t;
    TimerTask task;

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

        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getOpino().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time += 1;
                        int minutes = (time % 3600) / 60;
                        int seconds = time % 60;
                        String timeString = String.format("%02d:%02d", minutes, seconds);
                        txttimer.setText(timeString);
                    }
                });
            }
        };
        t.scheduleAtFixedRate(task, 0, 1000);

        txttimer.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getOpino(),timer_dto.getRespuesta_dto().getVariable_nombre(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public Timer_DTO getTimer_dto() {
        return timer_dto;
    }

    public void setTimer_dto(Timer_DTO timer_dto) {
        this.timer_dto = timer_dto;
    }
}
