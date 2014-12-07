package com.capr.modulos;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.capr.interfaces.Interface_Update_Location;
import com.capr.session.Session_Manager;
import com.capr.utils.Locator;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Gantz on 4/11/14.
 */
public class Modulo_Update_Location implements Interface_Update_Location {

    @Override
    public void updateLocation(final Context context, final String idLocal) {
        new Locator(context).getLocation(Locator.Method.NETWORK_THEN_GPS,new Locator.Listener() {
            @Override
            public void onLocationFound(Location location) {
                try {
                    Session_Manager session_manager = new Session_Manager(context);
                    String params = "latitud=" + location.getLatitude() + "&longitud=" + location.getLongitude();
                    Log.e("params", params);

                    ByteArrayEntity entity = new ByteArrayEntity(params.toString().getBytes("UTF-8"));
                    AsyncHttpClient asyncHttpClient_response = new AsyncHttpClient();
                    asyncHttpClient_response.addHeader("Token", session_manager.getSession().getUsuario_token());
                    asyncHttpClient_response.addHeader("Content-Type", "multipart/form-data");

                    asyncHttpClient_response.put(context, Opino_WS.WS_UPDATE_LOCATION.replace("%", idLocal), entity, null, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            Log.e("ERROR", responseString.toString());
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onLocationNotFound() {
                Toast.makeText(context,"Active su GPS",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
