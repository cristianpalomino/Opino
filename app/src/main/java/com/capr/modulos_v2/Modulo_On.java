package com.capr.modulos_v2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;

import com.capr.actividades.Encuestas;
import com.capr.actividades.Local;
import com.capr.actividades.Variable;
import com.capr.adapter.Adapter_Locales_v2;
import com.capr.adapter.Adapter_Variables;
import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.interfaces_v2.OnSuccessEncuestas;
import com.capr.interfaces_v2.OnSuccessLocales;
import com.capr.interfaces_v2.OnSuccessVariables;
import com.capr.services_v2.Service_Encuestas;
import com.capr.services_v2.Service_Locales;
import com.capr.services_v2.Service_Variables;
import com.capr.views_v2.View_Encuesta_v2;

import java.util.ArrayList;


/**
 * Created by Gantz on 27/12/14.
 */
public class Modulo_On implements OnSuccessLocales, OnSuccessVariables, OnSuccessEncuestas {

    public Activity activity;
    private ProgressDialog dialog;

    public Modulo_On(Activity activity) {
        this.activity = activity;
    }

    public void startLocalesOn() {
        Local local = ((Local)activity);
        dialog = ProgressDialog.show(local,null,"Espere...!",true,false);
        dialog.show();

        /** Inicia el Request **/
        Service_Locales service_locales = new Service_Locales(local);
        service_locales.sendRequest();
        service_locales.setOnSuccessLocales(this);

        /** Se muestra el FlatButton **/
        local.flatbutton.setVisibility(View.GONE);
    }

    public void startVariablesOn() {
        Variable variable = ((Variable)activity);

        /** Inicia el Request **/
        Service_Variables service_variables = new Service_Variables(variable);
        service_variables.setOnSuccessVariables(this);
        service_variables.sendRequest();
    }

    public void startEncuestasOn(String idLocal, String idVariable) {
        Encuestas encuestas = ((Encuestas)activity);

        /** Inicia el Request **/
        Service_Encuestas service_encuestas = new Service_Encuestas(encuestas);
        service_encuestas.sendRequest(idLocal, idVariable);
        service_encuestas.setOnSuccessEncuestas(this);
    }

    @Override
    public void onSuccessLocales(boolean success, ArrayList<Local_DTO> local_dtos, String message) {
        dialog.hide();
        Local local = ((Local)activity);
        Adapter_Locales_v2 adapter_locales_v2 = new Adapter_Locales_v2(local, local_dtos);
        local.lista_locales.setAdapter(adapter_locales_v2);
    }

    @Override
    public void onSuccessVariables(boolean success, ArrayList<Variable_DTO> variable_dtos, String message) {
        Variable variable = ((Variable)activity);
        Adapter_Variables adapter_locales_v2 = new Adapter_Variables(variable, variable_dtos);
        variable.lista_variables.setAdapter(adapter_locales_v2);
    }

    @Override
    public void onSuccessEncuestas(boolean success, ArrayList<Encuesta_DTO> encuesta_dtos, String message) {
        Encuestas encuestas = ((Encuestas)activity);
        if (success) {
            try {
                /**
                 * Save General Opino
                 */
                encuestas.setEncuesta_dtos(encuesta_dtos);
                for (int i = 0; i < encuesta_dtos.size(); i++) {
                    View_Encuesta_v2 view_encuesta_v2 = new View_Encuesta_v2(encuestas,encuesta_dtos.get(i));
                    encuestas.container_encuestas.addView(view_encuesta_v2);
                    view_encuesta_v2.init();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
