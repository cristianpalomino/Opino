package com.capr.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private LinearLayout container_extra;
    private FrameLayout container_general;
    private LinearLayout container_general_v2;

    public View_Encuesta(Context context, Encuesta_DTO encuesta_dto) {
        super(context, R.layout.item_encuesta);
        this.encuesta_dto = encuesta_dto;
    }

    public View_Encuesta(Context context, AttributeSet attrs, int defStyleAttr, Encuesta_DTO encuesta_dto) {
        super(context, attrs, defStyleAttr, R.layout.item_encuesta);
        this.encuesta_dto = encuesta_dto;
    }

    public View_Encuesta(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, Encuesta_DTO encuesta_dto) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.item_encuesta);
        this.encuesta_dto = encuesta_dto;
    }

    public View_Encuesta(Context context, AttributeSet attrs, Encuesta_DTO encuesta_dto) {
        super(context, attrs, R.layout.item_encuesta);
        this.encuesta_dto = encuesta_dto;
    }


    @Override
    protected void initView() {
        super.initView();
    }

    public void init() {

        nombreencuesta = (TextView) getView().findViewById(R.id.txtnombreencuesta);
        view_foto = (View_Foto) getView().findViewById(R.id.view_foto);
        container_extra = (LinearLayout) getView().findViewById(R.id.container_extra);
        container_general = (FrameLayout) getView().findViewById(R.id.container_general);
        container_general_v2 = (LinearLayout) getView().findViewById(R.id.container_general_v2);

        nombreencuesta.setText(encuesta_dto.getEncuesta_descripcion().toUpperCase());
        nombreencuesta.setTypeface(Util_Fonts.setPNASemiBold(getContext()));

        String variable = getOpino().getVariable_dto().getVariable_nombre();

        if (encuesta_dto.getFoto_dto() != null) {
            view_foto.setFoto_dto(encuesta_dto.getFoto_dto());
            view_foto.setVisibility(View.VISIBLE);
        }

        /**
         * PRESENTE - DIRECTO - INDIRECTO
         */
        if(encuesta_dto.getPresente_dto() != null){
            View_Presente view_presente = new View_Presente(getOpino());
            view_presente.setPresente_dto(encuesta_dto.getPresente_dto());
            container_general_v2.addView(view_presente);
        }

        /**
         * Validaciones
         */
        if (variable.equals("Sku")) {
            if (encuesta_dto.getSi_no_dto() != null) {
                for (int i = 0; i < encuesta_dto.getSi_no_dto().size(); i++) {
                    View_Si_No view = new View_Si_No(getOpino());
                    view.setSi_no_dto(encuesta_dto.getSi_no_dto().get(i), View.VISIBLE);

                    String nombre = encuesta_dto.getSi_no_dto().get(i).getRespuesta_dto().getVariable_nombre();
                    if (nombre.equals("EXHIBIDO") || nombre.equals("SINO")) {
                        container_general_v2.addView(view);
                    } else {
                        container_extra.addView(view);
                    }
                }
            }
        } else if (variable.equals("Afiche")) {
            if (encuesta_dto.getSi_no_dto() != null) {
                Toast.makeText(getOpino(),encuesta_dto.getSi_no_dto().size()+"",Toast.LENGTH_SHORT).show();
                for (int i = 0; i < encuesta_dto.getSi_no_dto().size(); i++) {
                    View_Si_No view = new View_Si_No(getOpino());
                    view.setSi_no_dto(encuesta_dto.getSi_no_dto().get(i), View.VISIBLE);
                    container_extra.addView(view);
                }
            }
        } else if (variable.equals("Promoción")) {
            if (encuesta_dto.getSi_no_dto() != null) {
                for (int i = 0; i < encuesta_dto.getSi_no_dto().size(); i++) {
                    View_Si_No view = new View_Si_No(getOpino());
                    view.setSi_no_dto(encuesta_dto.getSi_no_dto().get(i), View.VISIBLE);

                    String nombre = encuesta_dto.getSi_no_dto().get(i).getRespuesta_dto().getVariable_nombre();
                    if (nombre.equals("PRESENTE")) {
                        container_general_v2.addView(view);
                    } else {
                        container_extra.addView(view);
                    }
                }
            }
        }

        if (variable.equals("Sku")) {
            if (encuesta_dto.getPrecio_dtos() != null) {
                for (int i = 0; i < encuesta_dto.getPrecio_dtos().size(); i++) {
                    View_Precio view = new View_Precio(getOpino());
                    view.setComentario_dto(encuesta_dto.getPrecio_dtos().get(i));
                    String nombre = encuesta_dto.getPrecio_dtos().get(i).getRespuesta_dto().getVariable_nombre();
                    if (nombre.equals("PRECIO")) {
                        container_general_v2.addView(view);
                    } else {
                        container_extra.addView(view);
                    }
                }
            }
        } else if (variable.equals("Afiche")) {
            if (encuesta_dto.getPrecio_dtos() != null) {
                for (int i = 0; i < encuesta_dto.getPrecio_dtos().size(); i++) {
                    View_Precio view = new View_Precio(getOpino());
                    view.setComentario_dto(encuesta_dto.getPrecio_dtos().get(i));
                    container_extra.addView(view);
                }
            }
        } else if (variable.equals("Promoción")) {
            if (encuesta_dto.getPrecio_dtos() != null) {
                for (int i = 0; i < encuesta_dto.getPrecio_dtos().size(); i++) {
                    View_Precio view = new View_Precio(getOpino());
                    view.setComentario_dto(encuesta_dto.getPrecio_dtos().get(i));
                    container_extra.addView(view);

                }
            }
        }

        if (encuesta_dto.getComentario_dto() != null) {
            for (int i = 0; i < encuesta_dto.getComentario_dto().size(); i++) {
                View_Comentario view = new View_Comentario(getOpino());
                view.setComentario_dto(encuesta_dto.getComentario_dto().get(i));
                container_extra.addView(view);
            }
        }

        if (encuesta_dto.getSub_comentario_dto() != null) {
            for (int i = 0; i < encuesta_dto.getSub_comentario_dto().size(); i++) {
                View_Sub_Comentario view = new View_Sub_Comentario(getOpino());
                view.setSub_comentario_dto(encuesta_dto.getSub_comentario_dto().get(i));
                container_extra.addView(view);
            }
        }

        if (encuesta_dto.getTimer_dto() != null) {
            View_Timer view_timer = new View_Timer(getOpino());
            view_timer.setTimer_dto(encuesta_dto.getTimer_dto());
            container_general_v2.addView(view_timer);
        }

        if (encuesta_dto.getAtencion_dto() != null) {
            View_Atencion view_atencion = new View_Atencion(getOpino());
            view_atencion.setatencion_dto(encuesta_dto.getAtencion_dto());
            container_general_v2.addView(view_atencion);
        }
        if (encuesta_dto.getDirecto_indirecto_dto() != null) {
            View_Directo_Indirecto view_directo_indirecto = new View_Directo_Indirecto(getOpino());
            view_directo_indirecto.setDirecto_Indirecto_DTO(encuesta_dto.getDirecto_indirecto_dto());
            container_general_v2.addView(view_directo_indirecto);
        }
        if (encuesta_dto.getRango_dto() != null) {
            for (int i = 0; i < encuesta_dto.getRango_dto().size(); i++) {
                View_Rango view = new View_Rango(getOpino());
                view.setRango_dto(encuesta_dto.getRango_dto().get(i), View.VISIBLE);
                container_extra.addView(view);
            }
        }
        container_general_v2.setVisibility(View.VISIBLE);

        if (variable.equals("Calidad")) {
            if (encuesta_dto.getTimer_dto() != null) {
                View_Timer view_timer = new View_Timer(getOpino());
                view_timer.setTimer_dto(encuesta_dto.getTimer_dto());
                container_general.addView(view_timer);
            }
            if (encuesta_dto.getAtencion_dto() != null) {
                View_Atencion view_atencion = new View_Atencion(getOpino());
                view_atencion.setatencion_dto(encuesta_dto.getAtencion_dto());
                container_general.addView(view_atencion);
            }
            if (encuesta_dto.getDirecto_indirecto_dto() != null) {
                View_Directo_Indirecto view_directo_indirecto = new View_Directo_Indirecto(getOpino());
                view_directo_indirecto.setDirecto_Indirecto_DTO(encuesta_dto.getDirecto_indirecto_dto());
                container_general.addView(view_directo_indirecto);
            }
            if (encuesta_dto.getRango_dto() != null) {
                for (int i = 0; i < encuesta_dto.getRango_dto().size(); i++) {
                    View_Rango view = new View_Rango(getOpino());
                    view.setRango_dto(encuesta_dto.getRango_dto().get(i), View.GONE);
                    container_general.addView(view);
                }
            }
            container_general_v2.setVisibility(GONE);
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
