package com.capr.services_v2;

import android.content.Context;

import com.capr.beans_v2.User_DTO;
import com.capr.interfaces_v2.OnSuccessLogin;
import com.capr.opino.R;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by Gantz on 26/12/14.
 */
public class Service_Login {

    private Context context;
    private OnSuccessLogin onSuccessLogin;

    public Service_Login(Context context) {
        this.context = context;
    }

    public void setOnSuccessLogin(OnSuccessLogin onSuccessLogin) {
        this.onSuccessLogin = onSuccessLogin;
    }

    public void sendRequest(String username, String password) {
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, Opino_WS.WS_LOGIN_OPINO, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                User_DTO user_dto = new User_DTO();
                user_dto.setDataSource(response);
                onSuccessLogin.onSuccessLogin(true,user_dto,context.getString(R.string.message_correcto));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                onSuccessLogin.onSuccessLogin(false,null,context.getString(R.string.message_api_error));
            }
        });
    }
}
