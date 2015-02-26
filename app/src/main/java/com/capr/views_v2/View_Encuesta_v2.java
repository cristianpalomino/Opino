package com.capr.views_v2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capr.actividades.Encuestas;
import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Respuesta_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;
import com.capr.views.View_Opino;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Encuesta_v2 extends View_Opino implements View.OnLongClickListener {

    private Encuesta_DTO encuesta_dto;
    private TextView nombre_encuesta;
    private LinearLayout container_main;
    private LinearLayout container_second;
    private LinearLayout container_calidad;

    public View_Encuesta_v2(Context context, Encuesta_DTO encuesta_dto) {
        super(context, R.layout.item_encuesta);
        this.encuesta_dto = encuesta_dto;
    }

    public View_Encuesta_v2(Context context, AttributeSet attrs, int defStyleAttr, Encuesta_DTO encuesta_dto) {
        super(context, attrs, defStyleAttr, R.layout.item_encuesta);
        this.encuesta_dto = encuesta_dto;
    }

    public View_Encuesta_v2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, Encuesta_DTO encuesta_dto) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.item_encuesta);
        this.encuesta_dto = encuesta_dto;
    }

    public View_Encuesta_v2(Context context, AttributeSet attrs, Encuesta_DTO encuesta_dto) {
        super(context, attrs, R.layout.item_encuesta);
        this.encuesta_dto = encuesta_dto;
    }

    @Override
    protected void initView() {
        super.initView();
        setOnLongClickListener(this);
    }

    public void init() {
        nombre_encuesta = (TextView) getView().findViewById(R.id.txtnombreencuesta);
        nombre_encuesta.setTypeface(Util_Fonts.setPNASemiBold(getContext()));
        nombre_encuesta.setText(encuesta_dto.getDescripcion().toUpperCase());

        container_main = (LinearLayout) getView().findViewById(R.id.container_general);
        container_second = (LinearLayout) getView().findViewById(R.id.container_extra);
        container_calidad = (LinearLayout) getView().findViewById(R.id.container_calidad);

        String variable_name = ((Encuestas) getContext()).getVariable_dto().getIdvariable();

        if (variable_name.equals("sku")) {
            container_main.setVisibility(VISIBLE);
            container_second.setVisibility(VISIBLE);
            sku();
        } else if (variable_name.equals("pop")) {
            container_main.setVisibility(VISIBLE);
            container_second.setVisibility(VISIBLE);
            afiche();
        } else if (variable_name.equals("promocion")) {
            container_main.setVisibility(VISIBLE);
            container_second.setVisibility(VISIBLE);
            promocion();
        } else if (variable_name.equals("calidad")) {
            container_main.setVisibility(GONE);
            container_second.setVisibility(GONE);
            calidad();
        }
    }

    private void sku() {
        ArrayList<Respuesta_DTO> respuesta_dtos = encuesta_dto.getRespuesta_dtos();
        for (int i = 0; i < respuesta_dtos.size(); i++) {
            Respuesta_DTO respuesta_dto = respuesta_dtos.get(i);
            String tipo = respuesta_dto.getTipo();
            switch (Integer.parseInt(tipo)) {
                case 0:
                    View_Si_No_v2 view_si_no_v2 = new View_Si_No_v2(getContext());
                    view_si_no_v2.setRespuesta_dto(respuesta_dto);
                    view_si_no_v2.setPadre(container_main);
                    view_si_no_v2.init();
                    validateViewSku(respuesta_dto.getVariable_nombre(), view_si_no_v2);
                    break;
                case 1:
                    View_Comentario_v2 view_comentario_v2 = new View_Comentario_v2(getContext());
                    view_comentario_v2.setRespuesta_dto(respuesta_dto);
                    view_comentario_v2.init();
                    validateViewSku(respuesta_dto.getVariable_nombre(), view_comentario_v2);
                    break;
                case 3:
                    View_Rango_v2 view_rango_v2 = new View_Rango_v2(getContext());
                    view_rango_v2.setRespuesta_dto(respuesta_dto);
                    view_rango_v2.init();
                    validateViewSku(respuesta_dto.getVariable_nombre(), view_rango_v2);
                    break;
                case 7:
                    View_Foto_v2 view_foto_v2 = new View_Foto_v2(getContext());
                    view_foto_v2.setRespuesta_dto(respuesta_dto);
                    view_foto_v2.setEncuesta_dto(encuesta_dto);
                    view_foto_v2.init();
                    validateViewSku(respuesta_dto.getVariable_nombre(), view_foto_v2);
                    break;
                case 8:
                    View_Precio_v2 view_precio_v2 = new View_Precio_v2(getContext());
                    view_precio_v2.setRespuesta_dto(respuesta_dto);
                    view_precio_v2.init();
                    validateViewSku(respuesta_dto.getVariable_nombre(), view_precio_v2);
                    break;
            }
        }
    }

    private void afiche() {
        ArrayList<Respuesta_DTO> respuesta_dtos = encuesta_dto.getRespuesta_dtos();
        for (int i = 0; i < respuesta_dtos.size(); i++) {
            Respuesta_DTO respuesta_dto = respuesta_dtos.get(i);
            String tipo = respuesta_dto.getTipo();
            switch (Integer.parseInt(tipo)) {
                case 0:
                    View_Presente_v2 v2 = new View_Presente_v2(getContext());
                    v2.setRespuesta_dto(respuesta_dto);
                    v2.init();
                    validateViewAfiche(respuesta_dto.getVariable_nombre(), v2);
                    break;
                case 1:
                    View_Comentario_v2 view_comentario_v2 = new View_Comentario_v2(getContext());
                    view_comentario_v2.setRespuesta_dto(respuesta_dto);
                    view_comentario_v2.init();
                    validateViewAfiche(respuesta_dto.getVariable_nombre(), view_comentario_v2);
                    break;
                case 3:
                    View_Rango_v2 view_rango_v2 = new View_Rango_v2(getContext());
                    view_rango_v2.setRespuesta_dto(respuesta_dto);
                    view_rango_v2.init();
                    validateViewAfiche(respuesta_dto.getVariable_nombre(), view_rango_v2);
                    break;
                case 7:
                    View_Foto_v2 view_foto_v2 = new View_Foto_v2(getContext());
                    view_foto_v2.setRespuesta_dto(respuesta_dto);
                    view_foto_v2.setEncuesta_dto(encuesta_dto);
                    view_foto_v2.init();
                    validateViewAfiche(respuesta_dto.getVariable_nombre(), view_foto_v2);
                    break;
            }
        }
    }

    private void promocion() {
        ArrayList<Respuesta_DTO> respuesta_dtos = encuesta_dto.getRespuesta_dtos();
        for (int i = 0; i < respuesta_dtos.size(); i++) {
            Respuesta_DTO respuesta_dto = respuesta_dtos.get(i);
            String tipo = respuesta_dto.getTipo();
            switch (Integer.parseInt(tipo)) {
                case 0:
                    View_Si_No_v2 view_si_no_v2 = new View_Si_No_v2(getContext());
                    view_si_no_v2.setRespuesta_dto(respuesta_dto);
                    view_si_no_v2.init();
                    validateViewPromocion(respuesta_dto.getVariable_nombre(), view_si_no_v2);
                    break;
                case 1:
                    View_Comentario_v2 view_comentario_v2 = new View_Comentario_v2(getContext());
                    view_comentario_v2.setRespuesta_dto(respuesta_dto);
                    view_comentario_v2.init();
                    validateViewPromocion(respuesta_dto.getVariable_nombre(), view_comentario_v2);
                    break;
                case 3:
                    View_Rango_v2 view_rango_v2 = new View_Rango_v2(getContext());
                    view_rango_v2.setRespuesta_dto(respuesta_dto);
                    view_rango_v2.init();
                    validateViewPromocion(respuesta_dto.getVariable_nombre(), view_rango_v2);
                    break;
                case 7:
                    View_Foto_v2 view_foto_v2 = new View_Foto_v2(getContext());
                    view_foto_v2.setRespuesta_dto(respuesta_dto);
                    view_foto_v2.setEncuesta_dto(encuesta_dto);
                    view_foto_v2.init();
                    validateViewPromocion(respuesta_dto.getVariable_nombre(), view_foto_v2);
                    break;
            }
        }
    }

    private void calidad() {
        ArrayList<Respuesta_DTO> respuesta_dtos = encuesta_dto.getRespuesta_dtos();
        for (int i = 0; i < respuesta_dtos.size(); i++) {
            Respuesta_DTO respuesta_dto = respuesta_dtos.get(i);
            String tipo = respuesta_dto.getTipo();
            switch (Integer.parseInt(tipo)) {
                case 5:
                    View_Directa view_directa = new View_Directa(getContext());
                    view_directa.setRespuesta_dto(respuesta_dto);
                    view_directa.init();
                    validateViewCalidad(view_directa);
                    break;
                case 6:
                    View_Rango_Calidad view_rango_calidad = new View_Rango_Calidad(getContext());
                    view_rango_calidad.setRespuesta_dto(respuesta_dto);
                    view_rango_calidad.init();
                    validateViewCalidad(view_rango_calidad);
                    break;
            }
        }
    }

    private void validateViewSku(String variable_nombre, View view) {
        if (variable_nombre.equals("STOCK") ||
                variable_nombre.equals("EXHIBIDO") ||
                variable_nombre.equals("PRECIO") ||
                variable_nombre.equals("FOTO")) {
            container_main.addView(view);
        } else {
            container_second.addView(view);
        }
    }

    private void validateViewAfiche(String variable_nombre, View view) {
        if (variable_nombre.equals("PRESENTE") ||
                variable_nombre.equals("FOTO")) {
            container_main.addView(view);
        } else {
            container_second.addView(view);
        }
    }


    private void validateViewPromocion(String variable_nombre, View view) {
        if (variable_nombre.equals("PRESENTE") ||
                variable_nombre.equals("FOTO")) {
            container_main.addView(view);
        } else {
            container_second.addView(view);
        }
    }


    private void validateViewCalidad(View view) {
        container_calidad.addView(view);
    }

    @Override
    public boolean onLongClick(View v) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < container_main.getChildCount(); i++) {
                View child = container_main.getChildAt(i);
                if (child instanceof View_Opino) {
                    View_Opino view_opino = (View_Opino) child;
                    jsonArray.put(view_opino.getRespuesta_dto().getDataSourceRespuesta());
                }
            }

            JSONObject json_encuenta = new JSONObject();
            json_encuenta.put(encuesta_dto.campana_id, encuesta_dto.getCampana_id());
            json_encuenta.put(encuesta_dto.recurso_id, encuesta_dto.getRecurso_id());
            json_encuenta.put(encuesta_dto.descripcion, encuesta_dto.getDescripcion());
            json_encuenta.put(encuesta_dto.KEY_JSON_ARRAY_RESPUESTA, jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Encuesta_DTO getEncuesta_dto() {
        try {
            JSONArray jsonArray = new JSONArray();
            String variable = ((Encuestas) getContext()).getVariable_dto().getIdvariable();
            if (variable.equals("calidad")) {
                for (int i = 0; i < container_calidad.getChildCount(); i++) {
                    View child = container_calidad.getChildAt(i);
                    if (child instanceof View_Opino) {
                        View_Opino view_opino = (View_Opino) child;
                        jsonArray.put(view_opino.getRespuesta_dto().getDataSourceRespuestaCalidad());
                    }
                }

                JSONObject json_encuenta = new JSONObject();
                json_encuenta.put(encuesta_dto.variable_id, encuesta_dto.getVariable_id());
                json_encuenta.put(encuesta_dto.descripcion, encuesta_dto.getDescripcion());
                json_encuenta.put(encuesta_dto.KEY_JSON_ARRAY_RESPUESTA, jsonArray);
                encuesta_dto.setDataSource(json_encuenta);

                return encuesta_dto;
            }
            if (variable.equals("pop")) {
                for (int i = 0; i < container_main.getChildCount(); i++) {
                    View child = container_main.getChildAt(i);
                    if (child instanceof View_Opino) {
                        View_Opino view_opino = (View_Opino) child;
                        for (int j = 0; j < view_opino.getChildCount(); j++) {
                            if (view_opino instanceof View_Presente_v2) {
                                jsonArray.put(view_opino.getRespuesta_dto().getDataSourceRespuestaPop());
                            } else {
                                jsonArray.put(view_opino.getRespuesta_dto().getDataSourceRespuesta());
                            }
                        }
                    }
                }

                for (int i = 0; i < container_second.getChildCount(); i++) {
                    View child = container_second.getChildAt(i);
                    if (child instanceof View_Opino) {
                        View_Opino view_opino = (View_Opino) child;
                        for (int j = 0; j < view_opino.getChildCount(); j++) {
                            View subchild = view_opino.getChildAt(j);
                            if (subchild instanceof View_Presente_v2) {
                                jsonArray.put(view_opino.getRespuesta_dto().getDataSourceRespuestaPop());
                            } else {
                                jsonArray.put(view_opino.getRespuesta_dto().getDataSourceRespuesta());
                            }
                        }
                    }
                }

                JSONObject json_encuenta = new JSONObject();
                json_encuenta.put(encuesta_dto.campana_id, encuesta_dto.getCampana_id());
                json_encuenta.put(encuesta_dto.recurso_id, encuesta_dto.getRecurso_id());
                json_encuenta.put(encuesta_dto.descripcion, encuesta_dto.getDescripcion());
                json_encuenta.put(encuesta_dto.KEY_JSON_ARRAY_RESPUESTA, jsonArray);
                encuesta_dto.setDataSource(json_encuenta);

                return encuesta_dto;
            } else {
                for (int i = 0; i < container_main.getChildCount(); i++) {
                    View child = container_main.getChildAt(i);
                    if (child instanceof View_Opino) {
                        View_Opino view_opino = (View_Opino) child;
                        jsonArray.put(view_opino.getRespuesta_dto().getDataSourceRespuesta());
                    }
                }

                for (int i = 0; i < container_second.getChildCount(); i++) {
                    View child = container_second.getChildAt(i);
                    if (child instanceof View_Opino) {
                        View_Opino view_opino = (View_Opino) child;
                        jsonArray.put(view_opino.getRespuesta_dto().getDataSourceRespuesta());
                    }
                }

                JSONObject json_encuenta = new JSONObject();
                json_encuenta.put(encuesta_dto.campana_id, encuesta_dto.getCampana_id());
                json_encuenta.put(encuesta_dto.recurso_id, encuesta_dto.getRecurso_id());
                json_encuenta.put(encuesta_dto.descripcion, encuesta_dto.getDescripcion());
                json_encuenta.put(encuesta_dto.KEY_JSON_ARRAY_RESPUESTA, jsonArray);
                encuesta_dto.setDataSource(json_encuenta);

                return encuesta_dto;
            }
        } catch (Exception e) {
            Log.e("ERROR SAVE",e.getMessage());
            return null;
        }
    }
}
