package com.capr.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.capr.actividades.Opino;
import com.capr.beans_v2.Respuesta_DTO;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Opino extends LinearLayout {

    protected Opino opino;
    protected int id_layout;
    protected View view;

    private Respuesta_DTO respuesta_dto;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public View_Opino(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int id_layout) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.id_layout = id_layout;
        initView();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
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

    public Respuesta_DTO getRespuesta_dto() {
        return respuesta_dto;
    }

    public void setRespuesta_dto(Respuesta_DTO respuesta_dto) {
        this.respuesta_dto = respuesta_dto;
    }
}
