package com.capr.modulos;

import android.content.Context;
import android.util.Log;

import com.capr.adapter.Adapter_Locales_v2;
import com.capr.beans.Imagen_DTO;
import com.capr.beans.Local_DTO;
import com.capr.interfaces.Interface_Notification;
import com.capr.interfaces.Interface_Upload_Image;
import com.capr.session.Session_Manager;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Gantz on 3/11/14.
 */
public class Modulo_Upload_Image implements Interface_Upload_Image {

    private int COUNT = 0;
    private Interface_Notification interface_notification = new Modulo_Notificacion();

    @Override
    public void uploadImages(Context context, ArrayList<Imagen_DTO> imagen_dtos) throws Exception {
        if (imagen_dtos.size() > 0) {
            interface_notification.createNotification(context);
            interface_notification.getBuilder().setContentTitle("Subiendo " + (COUNT + 1) + " de " + imagen_dtos.size());
            interface_notification.setProgress(imagen_dtos.size(), 0);
            upload(context, imagen_dtos);
        }
    }

    public void upload(final Context context, final ArrayList<Imagen_DTO> imagen_dtos) throws Exception {
        Session_Manager session_manager = new Session_Manager(context);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setTimeout(9000000);
        RequestParams params = new RequestParams();

        asyncHttpClient.addHeader("Token", session_manager.getSession().getUsuario_token());
        params.put("file", imagen_dtos.get(COUNT).getImagenFile());
        params.put("recurso_id", imagen_dtos.get(COUNT).getImagenRecurso());

        asyncHttpClient.post(context, Opino_WS.WS_SUBIR_IMAGENES.replace("%", imagen_dtos.get(COUNT).getImagenLocal()), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(Modulo_Upload_Image.class.getName(), response.toString());

                COUNT++;

                if (COUNT < imagen_dtos.size()) {
                    try {
                        upload(context, imagen_dtos);
                        interface_notification.getBuilder().setContentTitle("Subiendo " + (COUNT + 1) + " de " + imagen_dtos.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    interface_notification.getBuilder().setContentTitle("Listo").setProgress(0,0,false);
                    interface_notification.invalidate();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(Modulo_Upload_Image.class.getName(), responseString);

                COUNT++;
                interface_notification.getBuilder().setContentTitle("Subiendo " + (COUNT + 1) + " de " + imagen_dtos.size());

                if (COUNT <= imagen_dtos.size()) {
                    try {
                        upload(context, imagen_dtos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    interface_notification.getBuilder().setContentTitle("Listo").setProgress(0,0,false);
                    interface_notification.invalidate();
                }
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                super.onProgress(bytesWritten, totalSize);

                int progressPercentage = 100 * bytesWritten / totalSize;
                if (progressPercentage > 95 && progressPercentage < 101) {
                    interface_notification.getBuilder().setProgress(0, 0, true);
                    interface_notification.getManager().notify(1, interface_notification.getBuilder().build());
                } else {
                    interface_notification.setProgress(100, progressPercentage);
                }
            }
        });
    }
}
