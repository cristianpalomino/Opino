package com.capr.fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.capr.adapter.Adapter_Encuesta;
import com.capr.beans.Atencion_DTO;
import com.capr.beans.Comentario_DTO;
import com.capr.beans.Directo_Indirecto_DTO;
import com.capr.beans.Encuesta_DTO;
import com.capr.beans.Foto_DTO;
import com.capr.beans.Imagen_DTO;
import com.capr.beans.Local_DTO;
import com.capr.beans.Rango_DTO;
import com.capr.beans.Respuesta_DTO;
import com.capr.beans.Si_No_DTO;
import com.capr.beans.Sub_Comentario_DTO;
import com.capr.beans.Timer_DTO;
import com.capr.interfaces.Interface_Upload_Image;
import com.capr.modulos.Modulo_Upload_Image;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.utils.Util_Fonts;
import com.capr.views.View_Encuesta;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Gantz on 18/10/14.
 */
public class Fragment_Encuestas_v2 extends Fragment_Opino implements AdapterView.OnItemClickListener {

    private LinearLayout linearLayout;
    private ArrayList<Encuesta_DTO> encuesta_dtos = new ArrayList<Encuesta_DTO>();

    public static final Fragment_Encuestas_v2 newInstance() {
        return new Fragment_Encuestas_v2();
    }

    public Fragment_Encuestas_v2() {
        super(R.layout.fragment_encuesta_v2, R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        getOpino().getSupportActionBar().setIcon(R.drawable.logo_opino);
    }

    @Override
    protected void initView() {
        super.initView();

        showDialog();

        Session_Manager session_manager = new Session_Manager(getOpino());
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token", session_manager.getSession().getUsuario_token());
        asyncHttpClient.get(getOpino(), Opino_WS.WS_OBTENER_INFORMACION.replace("%", getOpino().getLocal_dto().getLocal_id()).replace("#", getOpino().getVariable_dto().getVariable_id()), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    for (int i = 0; i < response.length(); i++) {
                        Encuesta_DTO encuesta_dto = new Encuesta_DTO(response.getJSONObject(i));

                        for (int j = 0; j < encuesta_dto.getEncuesta_respuesta_dtos().size(); j++) {
                            Respuesta_DTO respuesta_dto = encuesta_dto.getEncuesta_respuesta_dtos().get(j);
                            switch (Integer.parseInt(respuesta_dto.getRespuesta_tipo())){
                                case 0:
                                    Si_No_DTO si_no_dto = new Si_No_DTO();
                                    si_no_dto.setRespuesta_dto(respuesta_dto);
                                    si_no_dto.setSi_no_estado(false);
                                    encuesta_dto.addSi_No_DTO(si_no_dto);
                                    break;
                                case 1:
                                    Comentario_DTO comentario_dto = new Comentario_DTO();
                                    comentario_dto.setRespuesta_dto(respuesta_dto);
                                    comentario_dto.setcomentario_estado(false);
                                    encuesta_dto.addComentario_DTO(comentario_dto);
                                    break;
                                case 2:
                                    Sub_Comentario_DTO sub_comentario_dto = new Sub_Comentario_DTO();
                                    sub_comentario_dto.setRespuesta_dto(respuesta_dto);
                                    sub_comentario_dto.setsubcomentario_estado(false);
                                    encuesta_dto.addSub_Comentario_DTO(sub_comentario_dto);
                                    break;
                                case 3:
                                    Rango_DTO rango_dto = new Rango_DTO();
                                    rango_dto.setRespuesta_dto(respuesta_dto);
                                    rango_dto.setrango_estado(false);
                                    encuesta_dto.addRango_DTO(rango_dto);
                                    break;
                                case 4:
                                    Timer_DTO timer_dto = new Timer_DTO();
                                    timer_dto.setRespuesta_dto(respuesta_dto);
                                    timer_dto.settim_estado(false);
                                    encuesta_dto.setTimer_dto(timer_dto);
                                    break;
                                case 5:
                                    Directo_Indirecto_DTO directo_indirecto_dto = new Directo_Indirecto_DTO();
                                    directo_indirecto_dto.setRespuesta_dto(respuesta_dto);
                                    directo_indirecto_dto.setSi_no_estado(false);
                                    encuesta_dto.setDirecto_indirecto_dto(directo_indirecto_dto);
                                    break;
                                case 6:
                                    Atencion_DTO atencion_dto = new Atencion_DTO();
                                    atencion_dto.setRespuesta_dto(respuesta_dto);
                                    atencion_dto.setrango_estado(false);
                                    encuesta_dto.setAtencion_dto(atencion_dto);
                                    break;
                                case 7:
                                    Foto_DTO foto_dto = new Foto_DTO();
                                    foto_dto.setRespuesta_dto(respuesta_dto);
                                    foto_dto.setFoto_estado(false);
                                    encuesta_dto.setFoto_dto(foto_dto);
                                    break;
                            }
                        }

                        hideDialog();

                        encuesta_dtos.add(encuesta_dto);
                    }

                    linearLayout = (LinearLayout)getView().findViewById(R.id.containerencuesta);
                    for (int i = 0; i < encuesta_dtos.size(); i++) {
                        View_Encuesta view_encuesta = new View_Encuesta(getOpino(),encuesta_dtos.get(i));
                        view_encuesta.init();
                        linearLayout.addView(view_encuesta);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(Fragment_Locales.class.getName(), responseString);
                hideDialog();
            }
        });

        Button btnenviar = (Button)getView().findViewById(R.id.btnenviar);
        btnenviar.setTypeface(Util_Fonts.setPNASemiBold(getOpino()));
        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                try {
                    Interface_Upload_Image interface_upload_image = new Modulo_Upload_Image();
                    ArrayList<Imagen_DTO> imagen_dtos = new ArrayList<Imagen_DTO>();

                    for (int i = 0; i < encuesta_dtos.size(); i++) {
                        View_Encuesta view_encuesta = (View_Encuesta) linearLayout.getChildAt(i);
                        Imagen_DTO imagen_dto = view_encuesta.getView_foto().getImagen_dto();
                        if(imagen_dto != null){
                            imagen_dto.setImagenRecurso(encuesta_dtos.get(i).getEncuesta_recurso_id());

                            Log.e("IMAGEN",imagen_dto.getImagenFile().getAbsolutePath());
                            imagen_dtos.add(imagen_dto);
                        }
                    }

                    interface_upload_image.uploadImages(getOpino(),imagen_dtos);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < encuesta_dtos.size(); i++) {
                    View_Encuesta view_encuesta = (View_Encuesta) linearLayout.getChildAt(i);
                    Encuesta_DTO encuesta_dto = view_encuesta.getEncuesta_dto();
                    jsonArray.put(encuesta_dto.getEncuesta_json());
                }
                try {
                    uploadData(jsonArray, getOpino().getLocal_dto().getLocal_id(), getOpino().getVariable_dto().getVariable_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment_Encuestas_v2.this.getView().setFocusableInTouchMode(true);
        Fragment_Encuestas_v2.this.getView().requestFocus();
        Fragment_Encuestas_v2.this.getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction trans = manager.beginTransaction();
                        trans.setCustomAnimations(R.animator.izquierda_derecha, R.animator.derecha_izquierda);
                        trans.remove(Fragment_Encuestas_v2.this);
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
        getOpino().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.izquierda_derecha_b, R.animator.izquierda_derecha_b).add(R.id.container, Fragment_Encuestas_v2.newInstance(), Fragment_Encuestas_v2.class.getName()).addToBackStack(null).commit();
    }

    public void uploadData(final JSONArray jsonarray_encuesta_completa,String idLocal,String variable) throws JSONException {
        try {
            Session_Manager session_manager = new Session_Manager(getOpino());
            ByteArrayEntity entity = new ByteArrayEntity(jsonarray_encuesta_completa.toString().getBytes("UTF-8"));
            AsyncHttpClient asyncHttpClient_response = new AsyncHttpClient();
            asyncHttpClient_response.addHeader("Token", session_manager.getSession().getUsuario_token());
            asyncHttpClient_response.addHeader("Content-Type","application/json");

            asyncHttpClient_response.post(getOpino(), Opino_WS.WS_SUBIR_INFORMACION.replace("%", idLocal).replace("#", variable), entity, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Toast.makeText(getOpino(), response.toString(), Toast.LENGTH_SHORT).show();
                    hideDialog();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.e("ERROR", responseString.toString());
                    hideDialog();

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            hideDialog();
        }
    }
}