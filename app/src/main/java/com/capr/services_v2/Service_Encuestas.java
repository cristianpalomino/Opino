package com.capr.services_v2;

import android.content.Context;

import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Opino_DTO;
import com.capr.crud_v2.Encuesta_CRUD;
import com.capr.interfaces_v2.OnSuccessEncuestas;
import com.capr.interfaces_v2.OnSuccessLocales;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public class Service_Encuestas {

    private Context context;
    private OnSuccessEncuestas onSuccessEncuestas;

    public Service_Encuestas(Context context) {
        this.context = context;
    }

    public void setOnSuccessEncuestas(OnSuccessEncuestas onSuccessEncuestas) {
        this.onSuccessEncuestas = onSuccessEncuestas;
    }

    public void sendRequest(String idlocal,String idvariable) {
        Session_Manager session_manager = new Session_Manager(context);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token", session_manager.getSessionv2().getToken());
        asyncHttpClient.get(context,Opino_WS.WS_OBTENER_INFORMACION.replace("%",idlocal).replace("#",idvariable),null,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Opino_DTO opino_dto = new Opino_DTO();
                ArrayList<Encuesta_DTO> encuesta_dtos = opino_dto.getEncuesta_dtos(response);
                onSuccessEncuestas.onSuccessEncuestas(true,encuesta_dtos,context.getString(R.string.message_correcto));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                onSuccessEncuestas.onSuccessEncuestas(false,null,context.getString(R.string.message_api_error));
            }
        });
    }

    public void sendRequestOff(String idlocal,String idvariable) {
        Encuesta_CRUD encuesta_crud = new Encuesta_CRUD(context);
        ArrayList<Encuesta_DTO> encuesta_dtos = encuesta_crud.getEncuestas(idlocal,idvariable);
        onSuccessEncuestas.onSuccessEncuestas(true,encuesta_dtos,context.getString(R.string.message_correcto));
    }
}
