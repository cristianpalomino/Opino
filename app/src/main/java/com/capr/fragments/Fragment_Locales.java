package com.capr.fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.capr.adapter.Adapter_Locales;
import com.capr.adapter.Adapter_Locales_v2;
import com.capr.beans.Local_DTO;
import com.capr.interfaces.Interface_Notification;
import com.capr.modulos.Modulo_Notificacion;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 18/10/14.
 */
public class Fragment_Locales extends Fragment_Opino implements AdapterView.OnItemClickListener {

    protected ListView lista_locales;
    protected Adapter_Locales_v2 adapter_locales;

    Interface_Notification interface_notification = new Modulo_Notificacion();


    public static final Fragment_Locales newInstance() {
        return new Fragment_Locales();
    }

    public Fragment_Locales() {
        super(R.layout.fragment_locales,R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOpino().getSupportActionBar().setIcon(R.drawable.logo_opino);
        getOpino().getSupportActionBar().setTitle("Locales");
    }

    @Override
    protected void initView() {
        super.initView();

        lista_locales = (ListView) getView().findViewById(R.id.lista_locales);
        lista_locales.setOnItemClickListener(this);

        showDialog();

        Session_Manager session_manager = new Session_Manager(getOpino());
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token", session_manager.getSession().getUsuario_token());
        asyncHttpClient.get(getOpino(), Opino_WS.WS_OBTENER_LOCALES, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(Fragment_Locales.class.getName(), response.toString());

                ArrayList<Local_DTO> local_dtos = new ArrayList<Local_DTO>();
                try {
                    JSONArray array_locales = response.getJSONArray("result");
                    for (int i = 0; i < array_locales.length(); i++) {
                        JSONObject local_json = array_locales.getJSONObject(i);
                        Local_DTO local_dto = new Local_DTO();
                        local_dto.setLocal_json(local_json);
                        local_dtos.add(local_dto);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                hideDialog();

                adapter_locales = new Adapter_Locales_v2(getOpino(), local_dtos);
                lista_locales.setAdapter(adapter_locales);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(Fragment_Locales.class.getName(), responseString);
                hideDialog();
            }
        });
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Local_DTO local_dto = (Local_DTO) parent.getItemAtPosition(position);
        getOpino().setLocal_dto(local_dto);
        getOpino().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.izquierda_derecha_b,R.animator.izquierda_derecha_b).add(R.id.container, Fragment_Variables.newInstance(),Fragment_Variables.class.getName()).addToBackStack(null).commit();
    }
}
        /*
        Bitmap bitmap = BitmapFactory.decodeResource(getOpino().getResources(),R.drawable.ic_launcher);
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                for (int i = 0; i < palette.getSwatches().size(); i++) {
                    Palette.Swatch swatch = palette.getSwatches().get(i);
                    Log.e("SWATH", "" + swatch);
                }
            }
        });
        */