package com.capr.modulos;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.capr.beans.Core_DTO;
import com.capr.beans.Local_DTO;
import com.capr.beans.Variable_DTO;
import com.capr.dialog.Dialog_OffLine;
import com.capr.fragments.Fragment_Locales;
import com.capr.service.Core_Service;
import com.capr.service.Local_Service;
import com.capr.service.Main_Service;
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
 * Created by Gantz on 17/12/14.
 */
public class Modulo_Off_Line {

    private Context context;

    private int max_local = 0;
    private int counter_local = 0;
    private int counter_variable = 0;

    public Modulo_Off_Line(Context context) {
        this.context = context;
        new Main_Service(context).cleanAllTables();
    }

    public void initModuloOffLine(Local_Service.Interface_Service_Locales interface_service_locales) {
        initRequest(interface_service_locales);
    }

    private void initRequest(final Local_Service.Interface_Service_Locales interface_service_locales) {
        final Dialog_OffLine dialog_offLine = new Dialog_OffLine(context);
        dialog_offLine.setText("Calculando...");
        dialog_offLine.show();

        Session_Manager session_manager = new Session_Manager(context);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token", session_manager.getSession().getUsuario_token());
        asyncHttpClient.get(context, Opino_WS.WS_OBTENER_LOCALES, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(Modulo_Off_Line.class.getName(), response.toString());

                ArrayList<Local_DTO> local_dtos = new ArrayList<Local_DTO>();
                try {
                    /**
                     * Guardando Locales
                     */

                    JSONArray array_locales = response.getJSONArray("result");

                    dialog_offLine.setText("Se han encontrado " + array_locales.length() + " locales.");

                    for (int i = 0; i < array_locales.length(); i++) {
                        JSONObject local_json = array_locales.getJSONObject(i);
                        Local_DTO local_dto = new Local_DTO();
                        local_dto.setLocal_json(local_json);

                        /**
                         * Save variables
                         */
                        String id_local = local_json.getString("id");

                        ArrayList<Variable_DTO> variable_dtos = new ArrayList<Variable_DTO>();
                        variable_dtos.add(new Variable_DTO(id_local, "sku", false, "Sku"));
                        variable_dtos.add(new Variable_DTO(id_local, "afiche", false, "Afiche"));
                        variable_dtos.add(new Variable_DTO(id_local, "promocion", false, "Promoción"));
                        variable_dtos.add(new Variable_DTO(id_local, "calidad", false, "Calidad"));
                        local_dto.setVariable_dtos(variable_dtos, context,false);

                        local_dtos.add(local_dto);
                    }
                    /**
                     * Save Local to Db
                     */
                    Local_Service local_service = new Local_Service(context, true);
                    local_service.setInterface_service_locales(interface_service_locales);
                    local_service.addLocales(local_dtos);

                    /**
                     * Guardando Variables por local
                     */
                    max_local = local_dtos.size();
                    getCore(context, local_dtos, dialog_offLine);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dialog_offLine.hide();
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(Modulo_Off_Line.class.getName(), responseString);
            }
        });
    }

    private void getCore(final Context context, final ArrayList<Local_DTO> local_dtos, final Dialog_OffLine dialog_offLine) {
        dialog_offLine.setText("Descargando información \n'"
                + local_dtos.get(counter_local).getLocal_nombre().toUpperCase() + "'\n"
                + "Obteniendo Variable \n'"
                + local_dtos.get(counter_local).getVariable_dtos().get(counter_variable).getVariable_nombre().toUpperCase() + "'");

        Session_Manager session_manager = new Session_Manager(context);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token", session_manager.getSession().getUsuario_token());
        asyncHttpClient.get(context, Opino_WS.WS_OBTENER_INFORMACION.replace("%", local_dtos.get(counter_local).getLocal_id()).replace("#", local_dtos.get(counter_local).getVariable_dtos().get(counter_variable).getVariable_id()), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                /**
                 * Save Core to DB
                 */
                Core_DTO core_dto = new Core_DTO();
                core_dto.setId_local(local_dtos.get(counter_local).getLocal_id());
                core_dto.setId_variable(local_dtos.get(counter_local).getVariable_dtos().get(counter_variable).getVariable_id());
                core_dto.setJson_core(response);

                Core_Service core_service = new Core_Service(context);
                core_service.addCore(core_dto);

                counter_variable++;

                if (counter_variable < local_dtos.get(counter_local).getVariable_dtos().size()) {
                    getCore(context, local_dtos, dialog_offLine);
                } else {
                    counter_variable = 0;
                    counter_local++;
                    if (counter_local < max_local) {
                        getCore(context, local_dtos, dialog_offLine);
                    }else{
                        dialog_offLine.hide();
                        Toast.makeText(context,"Descarga Completa",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(Fragment_Locales.class.getName(), responseString);
                dialog_offLine.setText("Ocurrio un error, intentelo nuevamente");
            }
        });
    }
}
