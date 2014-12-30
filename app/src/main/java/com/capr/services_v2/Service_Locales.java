package com.capr.services_v2;

import android.content.Context;
import android.util.Log;

import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Opino_DTO;
import com.capr.crud_v2.Local_CRUD;
import com.capr.dao.Local_DAO;
import com.capr.interfaces_v2.OnSuccessLocales;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public class Service_Locales {

    private Context context;
    private OnSuccessLocales onSuccessLocales;

    public Service_Locales(Context context) {
        this.context = context;
    }

    public void setOnSuccessLocales(OnSuccessLocales onSuccessLocales) {
        this.onSuccessLocales = onSuccessLocales;
    }

    public void sendRequest() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token",new Session_Manager(context).getSessionv2().getToken());
        asyncHttpClient.get(context,Opino_WS.WS_OBTENER_LOCALES,null,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Opino_DTO opino_dto = new Opino_DTO();
                opino_dto.setDataSource(response);
                onSuccessLocales.onSuccessLocales(true,opino_dto.getLocal_dtos(),context.getString(R.string.message_correcto));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                onSuccessLocales.onSuccessLocales(false,null,context.getString(R.string.message_api_error));
            }
        });
    }

    public void sendRequestOff(){
        Local_CRUD local_crud = new Local_CRUD(context);
        ArrayList<Local_DTO> local_dtos = local_crud.getLocales();
        onSuccessLocales.onSuccessLocales(true,local_dtos,context.getString(R.string.message_correcto));
    }
}
