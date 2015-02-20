package com.capr.actividades;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.capr.beans.Imagen_DTO;
import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.crud_v2.Encuesta_CRUD;
import com.capr.crud_v2.Variable_CRUD;
import com.capr.interfaces.Interface_Upload_Image;
import com.capr.interfaces_v2.OnSuccessRespuesta;
import com.capr.modulos.Modulo_Upload_Image;
import com.capr.modulos_v2.Modulo_Off;
import com.capr.modulos_v2.Modulo_On;
import com.capr.opino.R;
import com.capr.services_v2.Service_Respuesta;
import com.capr.session.Session_Manager;
import com.capr.utils.Util_Fonts;
import com.capr.views_v2.View_Encuesta_v2;

import org.json.JSONArray;

import java.util.ArrayList;

public class Encuestas extends Opino {

    private Modulo_On modulo_on;
    private Modulo_Off modulo_off;
    public LinearLayout container_encuestas;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_encuesta_v2);
        try {
            setTitle(getVariable_dto().getNombre().toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }

        hideSoftKeyboard();
        container_encuestas = (LinearLayout) findViewById(R.id.containerencuesta);

        Session_Manager session_manager = new Session_Manager(Encuestas.this);
        modulo_on = new Modulo_On(this);
        modulo_off = new Modulo_Off(this);

        final String idLocal = getLocal_dto().getId();
        final String idVariable = getVariable_dto().getIdvariable();

        if (session_manager.getMode()) {
            modulo_on.startEncuestasOn(idLocal, idVariable);
        } else {
            modulo_off.startEncuestasOff(idLocal, idVariable);
        }

        Button btnenviar = (Button) findViewById(R.id.btnenviar);
        btnenviar.setTypeface(Util_Fonts.setPNASemiBold(Encuestas.this));
        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();

                /*
                Encuesta_CRUD encuesta_crud = new Encuesta_CRUD(Encuestas.this);
                ArrayList<Encuesta_DTO> encuesta_dtos = encuesta_crud.getEncuestas(idLocal, idVariable);
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < encuesta_dtos.size(); i++) {
                    Encuesta_DTO encuesta_dto = encuesta_dtos.get(i);
                    jsonArray.put(encuesta_dto.getDataSource());
                }

                Session_Manager session_manager = new Session_Manager(Encuestas.this);
                final ProgressDialog dialog = ProgressDialog.show(Encuestas.this, null, "Enviando Información...!", false, true);
                dialog.show();

                if (session_manager.getMode()) {

                } else {
                    Service_Respuesta service_respuesta = new Service_Respuesta(Encuestas.this);
                    service_respuesta.sendRequest(jsonArray, idLocal, idVariable);
                    service_respuesta.setOnSuccessRespuesta(new OnSuccessRespuesta() {
                        @Override
                        public void onSuccessRespuesta(boolean success, String message) {
                            dialog.hide();
                            if (success) {
                                Variable_CRUD variable_crud = new Variable_CRUD(Encuestas.this);
                                Variable_DTO variable_dto = getVariable_dto();
                                variable_dto.set_estado("SI");
                                variable_crud.updateVariable(variable_dto);
                                Toast.makeText(Encuestas.this, message, Toast.LENGTH_SHORT).show();

                                Encuesta_CRUD encuesta_crud = new Encuesta_CRUD(Encuestas.this);
                                ArrayList<Encuesta_DTO> encuesta_dtos = encuesta_crud.getEncuestas(idLocal,idVariable);
                                ArrayList<Imagen_DTO> imagen_dtos = new ArrayList<Imagen_DTO>();
                                for (int i = 0; i < encuesta_dtos.size(); i++) {
                                    if (!encuesta_dtos.get(i).get_uri().equals("NONE")) {
                                        Imagen_DTO imagen_dto = new Imagen_DTO(Encuestas.this);
                                        imagen_dto.setImagenId(encuesta_dtos.get(i).get_uri());
                                        imagen_dto.setImagenData(encuesta_dtos.get(i).get_uri());
                                        imagen_dto.setImagenRecurso(encuesta_dtos.get(i).getRecurso_id());
                                        imagen_dto.setImagenLocal(getLocal_dto().getId());

                                        imagen_dtos.add(imagen_dto);
                                    }
                                }

                                if (imagen_dtos.size() > 0) {
                                    Interface_Upload_Image interface_upload_image = new Modulo_Upload_Image();
                                    interface_upload_image.uploadImages(Encuestas.this, imagen_dtos);
                                }
                            }
                        }
                    });
                }
                */
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveChanges();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveChanges();
    }

    private void saveChanges() {
        /**
         * Enviar Informacion
         */
        int count = container_encuestas.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = container_encuestas.getChildAt(i);
            if (child instanceof View_Encuesta_v2) {
                Encuesta_DTO encuesta_dto = ((View_Encuesta_v2) child).getEncuesta_dto();
                Encuesta_CRUD encuesta_crud = new Encuesta_CRUD(Encuestas.this);
                encuesta_crud.updateEncuesta(encuesta_dto);
            }
        }

        Toast.makeText(Encuestas.this, getString(R.string.message_correcto), Toast.LENGTH_SHORT).show();
    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
