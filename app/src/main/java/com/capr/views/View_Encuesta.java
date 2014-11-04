package com.capr.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capr.beans.Comentario_DTO;
import com.capr.beans.Encuesta_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Encuesta extends View_Opino {

    private Encuesta_DTO encuesta_dto;

    private TextView nombreencuesta;
    private View_Foto view_foto;
    private LinearLayout container_rango;
    private LinearLayout container_switch;
    private LinearLayout container_comentario;
    private LinearLayout container_sub_comentario;
    private FrameLayout container_general;

    public View_Encuesta(Context context,Encuesta_DTO encuesta_dto) {
        super(context, R.layout.item_encuesta);
        this.encuesta_dto=encuesta_dto;
    }

    public View_Encuesta(Context context, AttributeSet attrs, int defStyleAttr,Encuesta_DTO encuesta_dto) {
        super(context, attrs, defStyleAttr, R.layout.item_encuesta);
        this.encuesta_dto=encuesta_dto;
    }

    public View_Encuesta(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes,Encuesta_DTO encuesta_dto) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.item_encuesta);
        this.encuesta_dto=encuesta_dto;
    }

    public View_Encuesta(Context context, AttributeSet attrs,Encuesta_DTO encuesta_dto) {
        super(context, attrs, R.layout.item_encuesta);
        this.encuesta_dto=encuesta_dto;
    }


    @Override
    protected void initView() {
        super.initView();
    }

    public void init() {

        nombreencuesta = (TextView) getView().findViewById(R.id.txtnombreencuesta);
        view_foto = (View_Foto) getView().findViewById(R.id.view_foto);
        container_rango = (LinearLayout) getView().findViewById(R.id.container_rango);
        container_switch = (LinearLayout) getView().findViewById(R.id.container_switch);
        container_comentario = (LinearLayout) getView().findViewById(R.id.container_comentario);
        container_sub_comentario = (LinearLayout) getView().findViewById(R.id.container_sub_comentario);
        container_general = (FrameLayout) getView().findViewById(R.id.container_general);

        nombreencuesta.setText(encuesta_dto.getEncuesta_descripcion());
        nombreencuesta.setTypeface(Util_Fonts.setPNASemiBold(getContext()));

        String variable = getOpino().getVariable_dto().getVariable_nombre();
        if (!variable.equals("Calidad") && !variable.equals("Interacción")) {
            if (encuesta_dto.getFoto_dto() != null) {
                view_foto.setFoto_dto(encuesta_dto.getFoto_dto());
                view_foto.setVisibility(View.VISIBLE);
            }

            if (encuesta_dto.getRango_dto() != null) {
                for (int i = 0; i < encuesta_dto.getRango_dto().size(); i++) {
                    View_Rango view = new View_Rango(getOpino());
                    view.setRango_dto(encuesta_dto.getRango_dto().get(i));
                    container_rango.addView(view);
                }
            }

            if (encuesta_dto.getSi_no_dto() != null) {
                for (int i = 0; i < encuesta_dto.getSi_no_dto().size(); i++) {
                    View_Si_No view = new View_Si_No(getOpino());
                    view.setSi_no_dto(encuesta_dto.getSi_no_dto().get(i));
                    container_switch.addView(view);
                }
            }

            if (encuesta_dto.getComentario_dto() != null) {
                for (int i = 0; i < encuesta_dto.getComentario_dto().size(); i++) {
                    View_Comentario view = new View_Comentario(getOpino());
                    view.setComentario_dto(encuesta_dto.getComentario_dto().get(i));
                    container_comentario.addView(view);
                }
            }

            if (encuesta_dto.getSub_comentario_dto() != null) {
                for (int i = 0; i < encuesta_dto.getSub_comentario_dto().size(); i++) {
                    View_Sub_Comentario view = new View_Sub_Comentario(getOpino());
                    view.setSub_comentario_dto(encuesta_dto.getSub_comentario_dto().get(i));
                    container_sub_comentario.addView(view);
                }
            }
        } else if (variable.equals("Calidad")) {
            if (encuesta_dto.getSi_no_dto() != null) {
                for (int i = 0; i < encuesta_dto.getSi_no_dto().size(); i++) {
                    View_Si_No view = new View_Si_No(getOpino());
                    view.setSi_no_dto(encuesta_dto.getSi_no_dto().get(i));
                    container_general.addView(view);
                }
            }
            if (encuesta_dto.getTimer_dto() != null){
                View_Timer view_timer = new View_Timer(getOpino());
                view_timer.setTimer_dto(encuesta_dto.getTimer_dto());
                container_general.addView(view_timer);
            }
        }
    }

    public void setEncuesta_dto(Encuesta_DTO encuesta_dto) {
        this.encuesta_dto = encuesta_dto;
    }

    public Encuesta_DTO getEncuesta_dto() {
        return encuesta_dto;
    }

    public View_Foto getView_foto() {
        return view_foto;
    }
}
