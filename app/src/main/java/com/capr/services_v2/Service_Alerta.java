package com.capr.services_v2;

import android.content.Context;
import android.util.Log;

import com.capr.beans_v2.Opino_DTO;
import com.capr.interfaces_v2.OnSendAlert;
import com.capr.interfaces_v2.OnSuccessLocales;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by Gantz on 26/12/14.
 */
public class Service_Alerta {

    private Context context;
    private OnSendAlert onSendAlert;

    public void setOnSendAlert(OnSendAlert onSendAlert) {
        this.onSendAlert = onSendAlert;
    }

    public Service_Alerta(Context context) {
        this.context = context;
    }

    public void sendRequest() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token", new Session_Manager(context).getSessionv2().getToken());
        asyncHttpClient.get(context, Opino_WS.WS_SEND_ALERT, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("RESULTADO",response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                onSendAlert.onSuccessAlert(false, context.getString(R.string.message_api_error));
            }
        });
    }
}
