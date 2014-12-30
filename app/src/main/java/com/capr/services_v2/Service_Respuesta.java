package com.capr.services_v2;

import android.content.Context;

import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Opino_DTO;
import com.capr.crud_v2.Encuesta_CRUD;
import com.capr.interfaces_v2.OnSuccessEncuestas;
import com.capr.interfaces_v2.OnSuccessRespuesta;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public class Service_Respuesta {

    private Context context;
    private OnSuccessRespuesta onSuccessRespuesta;

    public Service_Respuesta(Context context) {
        this.context = context;
    }

    public void setOnSuccessRespuesta(OnSuccessRespuesta onSuccessRespuesta) {
        this.onSuccessRespuesta = onSuccessRespuesta;
    }

    public void sendRequest(JSONArray respuesta,String idLocal,String idVariable) {
        try {
            Session_Manager session_manager = new Session_Manager(context);
            ByteArrayEntity entity = new ByteArrayEntity(respuesta.toString().getBytes("UTF-8"));
            AsyncHttpClient asyncHttpClient_response = new AsyncHttpClient();
            asyncHttpClient_response.addHeader("Token", session_manager.getSession().getUsuario_token());
            asyncHttpClient_response.addHeader("Content-Type", "application/json");

            asyncHttpClient_response.post(context,
                    Opino_WS.WS_SUBIR_INFORMACION.replace("%",idLocal).replace("#",idVariable),
                    entity,
                    null,
                    new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers,JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    onSuccessRespuesta.onSuccessRespuesta(true,response.toString());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    onSuccessRespuesta.onSuccessRespuesta(false,context.getString(R.string.message_api_error));
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            onSuccessRespuesta.onSuccessRespuesta(false,context.getString(R.string.message_api_error));
        }
    }
}
