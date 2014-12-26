package com.capr.beans;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.capr.views.View_Foto;
import com.capr.views.View_Si_No;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/10/14.
 */
public class Encuesta_DTO {

    private boolean encuesta_estado;
    private String encuesta_campana_id;
    private String encuesta_recurso_id;
    private String encuesta_descripcion;
    private ArrayList<Respuesta_DTO> encuesta_respuesta_dtos;

    private JSONObject encuesta_json;
    private String id_local;
    private String id_variable;

    /**
     * View's Encuesta
     */
    private Foto_DTO foto_dto;

    private ArrayList<Si_No_DTO> si_no_dtos = new ArrayList<Si_No_DTO>();
    private ArrayList<Comentario_DTO> comentario_dtos = new ArrayList<Comentario_DTO>();
    private ArrayList<Sub_Comentario_DTO> sub_comentario_dtos = new ArrayList<Sub_Comentario_DTO>();
    private ArrayList<Rango_DTO> rango_dtos = new ArrayList<Rango_DTO>();
    private ArrayList<Precio_DTO> precio_dtos = new ArrayList<Precio_DTO>();
    private Timer_DTO timer_dto;
    private Atencion_DTO atencion_dto;
    private Directo_Indirecto_DTO directo_indirecto_dto;
    private Precio_DTO precio_dto;
    private Presente_DTO presente_dto;

    public Encuesta_DTO() {
    }

    public Encuesta_DTO(JSONObject encuesta_json) {
        this.encuesta_json = encuesta_json;
    }


    public String getId_local() {
        return id_local;
    }

    public String getId_variable() {
        return id_variable;
    }

    public void setId_local(String id_local) {
        this.id_local = id_local;
    }

    public void setId_variable(String id_variable) {
        this.id_variable = id_variable;
    }

    public boolean isEncuesta_estado() {
        return encuesta_estado;
    }

    public JSONObject getEncuesta_json() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (getEncuesta_recurso_id().endsWith("null")) {
                jsonObject.put("recurso_id", JSONObject.NULL);
            } else {
                jsonObject.put("recurso_id", getEncuesta_recurso_id());
            }
            if (getEncuesta_campana_id().endsWith("null")) {
                jsonObject.put("campana_id", JSONObject.NULL);
            } else {
                jsonObject.put("campana_id", getEncuesta_campana_id());
            }
            if (getEncuesta_descripcion().endsWith("null")) {
                jsonObject.put("descripcion", JSONObject.NULL);
            } else {
                jsonObject.put("descripcion", getEncuesta_descripcion());
            }

            JSONArray jsonArray = new JSONArray();

            if (getFoto_dto() != null) {
                jsonArray.put(getFoto_dto().getRespuesta_dto().getRespuesta_json());
            }

            if (getSi_no_dto() != null) {
                for (int i = 0; i < getSi_no_dto().size(); i++) {
                    jsonArray.put(getSi_no_dto().get(i).getRespuesta_dto().getRespuesta_json());
                }
            }

            if (getComentario_dto() != null) {
                for (int i = 0; i < getComentario_dto().size(); i++) {
                    jsonArray.put(getComentario_dto().get(i).getRespuesta_dto().getRespuesta_json());
                }
            }

            if (getSub_comentario_dto() != null) {
                for (int i = 0; i < getSub_comentario_dto().size(); i++) {
                    jsonArray.put(getSub_comentario_dto().get(i).getRespuesta_dto().getRespuesta_json());
                }
            }

            if (getRango_dto() != null) {
                for (int i = 0; i < getRango_dto().size(); i++) {
                    jsonArray.put(getRango_dto().get(i).getRespuesta_dto().getRespuesta_json());
                }
            }

            if (getPrecio_dtos() != null) {
                for (int i = 0; i < getPrecio_dtos().size(); i++) {
                    jsonArray.put(getPrecio_dtos().get(i).getRespuesta_dto().getRespuesta_json());
                }
            }

            jsonObject.put("respuestas", jsonArray);

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public ArrayList<Respuesta_DTO> getEncuesta_respuesta_dtos() {
        ArrayList<Respuesta_DTO> respuesta_dtos = new ArrayList<Respuesta_DTO>();
        try {
            for (int i = 0; i < encuesta_json.getJSONArray("respuestas").length(); i++) {
                Respuesta_DTO respuesta_dto = new Respuesta_DTO(encuesta_json.getJSONArray("respuestas").getJSONObject(i));
                respuesta_dtos.add(respuesta_dto);
            }
            return respuesta_dtos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return respuesta_dtos;
    }

    public String getEncuesta_descripcion() {
        try {
            return encuesta_json.getString("descripcion");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Descripcion";
    }

    public String getEncuesta_recurso_id() {
        try {
            return encuesta_json.getString("recurso_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Recurso_Id";
    }

    public String getEncuesta_campana_id() {
        try {
            return encuesta_json.getString("campana_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Campana_Id";
    }

    public void setEncuesta_respuesta_dtos(ArrayList<Respuesta_DTO> encuesta_respuesta_dtos) {
    }

    public void setEncuesta_descripcion(String encuesta_descripcion) {
        this.encuesta_descripcion = encuesta_descripcion;
    }

    public void setEncuesta_recurso_id(String encuesta_recurso_id) {
        this.encuesta_recurso_id = encuesta_recurso_id;
    }

    public void setEncuesta_estado(boolean encuesta_estado) {
        this.encuesta_estado = encuesta_estado;
    }

    public void setEncuesta_campana_id(String encuesta_campana_id) {
        this.encuesta_campana_id = encuesta_campana_id;
    }

    public void setEncuesta_json(JSONObject encuesta_json) {
        this.encuesta_json = encuesta_json;
    }

    public void setTimer_dto(Timer_DTO timer_dto) {
        this.timer_dto = timer_dto;
    }

    public Timer_DTO getTimer_dto() {
        return timer_dto;
    }

    public void setAtencion_dto(Atencion_DTO atencion_dto) {
        this.atencion_dto = atencion_dto;
    }

    public Atencion_DTO getAtencion_dto() {
        return atencion_dto;
    }

    public Directo_Indirecto_DTO getDirecto_indirecto_dto() {
        return directo_indirecto_dto;
    }

    public void setDirecto_indirecto_dto(Directo_Indirecto_DTO directo_indirecto_dto) {
        this.directo_indirecto_dto = directo_indirecto_dto;
    }

    /**
     * Getter's and Setter's de las View's Encuestas
     */


    public Foto_DTO getFoto_dto() {
        return foto_dto;
    }

    public void setFoto_dto(Foto_DTO foto_dto) {
        this.foto_dto = foto_dto;
    }

    public ArrayList<Sub_Comentario_DTO> getSub_comentario_dto() {
        return sub_comentario_dtos;
    }

    public void setSub_comentario_dto(ArrayList<Sub_Comentario_DTO> sub_comentario_dto) {
        this.sub_comentario_dtos = sub_comentario_dto;
    }

    public ArrayList<Comentario_DTO> getComentario_dto() {
        return comentario_dtos;
    }

    public void setComentario_dto(ArrayList<Comentario_DTO> comentario_dto) {
        this.comentario_dtos = comentario_dto;
    }

    public ArrayList<Si_No_DTO> getSi_no_dto() {
        return si_no_dtos;
    }

    public void setSi_no_dto(ArrayList<Si_No_DTO> si_no_dto) {
        this.si_no_dtos = si_no_dto;
    }

    public ArrayList<Rango_DTO> getRango_dto() {
        return rango_dtos;
    }

    public void setRango_dto(ArrayList<Rango_DTO> rango_dto) {
        this.rango_dtos = rango_dto;
    }

    public void addRango_DTO(Rango_DTO rango_dto) {
        rango_dtos.add(rango_dto);
    }

    public void addSi_No_DTO(Si_No_DTO si_no_dto) {
        si_no_dtos.add(si_no_dto);
    }

    public void addComentario_DTO(Comentario_DTO comentario_dto) {
        comentario_dtos.add(comentario_dto);
    }

    public void addSub_Comentario_DTO(Sub_Comentario_DTO sub_comentario_dto) {
        sub_comentario_dtos.add(sub_comentario_dto);
    }

    public ArrayList<Precio_DTO> getPrecio_dtos() {
        return precio_dtos;
    }

    public void setPrecio_dtos(ArrayList<Precio_DTO> precio_dtos) {
        this.precio_dtos = precio_dtos;
    }

    public void addPrecio_DTO(Precio_DTO precio_dto) {
        precio_dtos.add(precio_dto);
    }


    public Precio_DTO getPrecio_dto() {
        return precio_dto;
    }

    public void setPrecio_dto(Precio_DTO precio_dto) {
        this.precio_dto = precio_dto;
    }

    public Presente_DTO getPresente_dto() {
        return presente_dto;
    }

    public void setPresente_dto(Presente_DTO presente_dto) {
        this.presente_dto = presente_dto;
    }
}

