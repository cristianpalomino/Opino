package com.capr.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.capr.opino.Opino;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Opino extends LinearLayout {

    protected Opino opino;
    protected int id_layout;
    protected View view;

    public View_Opino(Context context, int id_layout) {
        super(context);
        this.id_layout = id_layout;
        initView();
    }

    public View_Opino(Context context, AttributeSet attrs, int id_layout) {
        super(context, attrs);
        this.id_layout = id_layout;
        initView();
    }

    @TargetApi(Build.VERSION_CODES.L)
    public View_Opino(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int id_layout) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.id_layout = id_layout;
        initView();
    }

    public View_Opino(Context context, AttributeSet attrs, int defStyleAttr, int id_layout) {
        super(context, attrs, defStyleAttr);
        this.id_layout = id_layout;
        initView();
    }

    protected void initView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(id_layout, this, true);
    }

    public Opino getOpino() {
        return (Opino) getContext();
    }

    public void setOpino(Opino opino) {
        this.opino = opino;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}
