package com.capr.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.capr.adapter.Adapter_Encuesta;
import com.capr.adapter.Adapter_Locales_v2;
import com.capr.beans.Comentario_DTO;
import com.capr.beans.Encuesta_DTO;
import com.capr.beans.Foto_DTO;
import com.capr.beans.Local_DTO;
import com.capr.beans.Rango_DTO;
import com.capr.beans.Respuesta_DTO;
import com.capr.beans.Si_No_DTO;
import com.capr.beans.Sub_Comentario_DTO;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.views.View_Foto;
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
public class Fragment_Encuestas extends Fragment_Opino implements AdapterView.OnItemClickListener {

    protected RecyclerView recyclerView;
    protected Adapter_Encuesta adapter_encuesta;

    public static final Fragment_Encuestas newInstance() {
        return new Fragment_Encuestas();
    }

    public Fragment_Encuestas() {
        super(R.layout.fragment_encuesta, R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        getOpino().getSupportActionBar().setIcon(R.drawable.logo_opino);
        getOpino().getSupportActionBar().setTitle("Encuesta");
    }

    @Override
    protected void initView() {
        super.initView();

        recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        Session_Manager session_manager = new Session_Manager(getOpino());
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token", session_manager.getSession().getUsuario_token());
        asyncHttpClient.get(getOpino(), Opino_WS.WS_OBTENER_INFORMACION.replace("%", getOpino().getLocal_dto().getLocal_id()).replace("#", getOpino().getVariable_dto().getVariable_id()), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    ArrayList<Encuesta_DTO> encuesta_dtos = new ArrayList<Encuesta_DTO>();
                    for (int i = 0; i < response.length(); i++) {
                        Encuesta_DTO encuesta_dto = new Encuesta_DTO(response.getJSONObject(i));

                        for (int j = 0; j < encuesta_dto.getEncuesta_respuesta_dtos().size(); j++) {
                            Respuesta_DTO respuesta_dto = encuesta_dto.getEncuesta_respuesta_dtos().get(j);
                            switch (Integer.parseInt(respuesta_dto.getRespuesta_tipo())){
                                case 0:
                                    Si_No_DTO si_no_dto = new Si_No_DTO();
                                    si_no_dto.setRespuesta_dto(respuesta_dto);
                                    si_no_dto.setSi_no_estado(false);
                                    //encuesta_dto.setSi_no_dto(si_no_dto);
                                    break;
                                case 1:
                                    Comentario_DTO comentario_dto = new Comentario_DTO();
                                    comentario_dto.setRespuesta_dto(respuesta_dto);
                                    comentario_dto.setcomentario_estado(false);
                                    //encuesta_dto.setComentario_dto(comentario_dto);
                                    break;
                                case 2:
                                    Sub_Comentario_DTO sub_comentario_dto = new Sub_Comentario_DTO();
                                    sub_comentario_dto.setRespuesta_dto(respuesta_dto);
                                    sub_comentario_dto.setsubcomentario_estado(false);
                                    //encuesta_dto.setSub_comentario_dto(sub_comentario_dto);
                                    break;
                                case 3:
                                    Rango_DTO rango_dto = new Rango_DTO();
                                    rango_dto.setRespuesta_dto(respuesta_dto);
                                    rango_dto.setrango_estado(false);
                                    //encuesta_dto.setRango_dto(rango_dto);
                                    break;
                                case 7:
                                    Foto_DTO foto_dto = new Foto_DTO();
                                    foto_dto.setRespuesta_dto(respuesta_dto);
                                    foto_dto.setFoto_estado(false);
                                    encuesta_dto.setFoto_dto(foto_dto);
                                    break;
                            }
                        }

                        encuesta_dtos.add(encuesta_dto);
                    }

                    adapter_encuesta = new Adapter_Encuesta(encuesta_dtos);
                    recyclerView.setAdapter(adapter_encuesta);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(Fragment_Locales.class.getName(), responseString);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment_Encuestas.this.getView().setFocusableInTouchMode(true);
        Fragment_Encuestas.this.getView().requestFocus();
        Fragment_Encuestas.this.getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction trans = manager.beginTransaction();
                        trans.setCustomAnimations(R.animator.izquierda_derecha, R.animator.derecha_izquierda);
                        trans.remove(Fragment_Encuestas.this);
                        trans.commit();
                        manager.popBackStack();
                        return true;
                    }
                }
                return false;
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
        getOpino().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.izquierda_derecha_b, R.animator.izquierda_derecha_b).add(R.id.container, Fragment_Encuestas.newInstance(), Fragment_Encuestas.class.getName()).addToBackStack(null).commit();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_update_location).setVisible(false).setEnabled(false);
        super.onPrepareOptionsMenu(menu);
    }
}