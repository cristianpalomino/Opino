package com.capr.services_v2;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Local_DTO;
import com.capr.crud_v2.Encuesta_CRUD;
import com.capr.crud_v2.Local_CRUD;
import com.capr.crud_v2.Main_CRUD;
import com.capr.crud_v2.Variable_CRUD;
import com.capr.dialog.Dialog_OffLine;
import com.capr.interfaces_v2.OnSuccessEncuestas;
import com.capr.interfaces_v2.OnSuccessLocales;
import com.capr.modulos_v2.Modulo_Off;
import com.capr.opino.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public class Downloader {

    private Context context;
    private Dialog_OffLine dialog_offLine;

    private int max_local = 0;
    private int counter_local = 0;
    private int counter_variable = 0;

    public Downloader(Context context) {
        this.context = context;
        new Main_CRUD(context).cleanAllTables();
    }

    public void initDownloader(final Activity activity) {
        dialog_offLine = new Dialog_OffLine(context);
        dialog_offLine.setText("Calculando...");
        dialog_offLine.show();

        final Service_Locales service_locales = new Service_Locales(context);
        service_locales.sendRequest();
        service_locales.setOnSuccessLocales(new OnSuccessLocales() {
            @Override
            public void onSuccessLocales(boolean success, ArrayList<Local_DTO> local_dtos, String message) {
                max_local = local_dtos.size();
                dialog_offLine.setText("Se han encontrado " + max_local + " locales.");

                Local_CRUD local_crud = new Local_CRUD(context);
                for (int i = 0; i < local_dtos.size(); i++) {
                    String idlocal = local_dtos.get(i).getId();
                    String estado = "NO";
                    JSONObject json_local = local_dtos.get(i).getDataSource();
                    Local_DTO local_dto = new Local_DTO(idlocal, estado, json_local);
                    local_crud.addLocal(local_dto);

                    Service_Variables service_variables = new Service_Variables(context);
                    service_variables.sendRequestOff(local_dto);
                }

                Modulo_Off modulo_off = new Modulo_Off(activity);
                modulo_off.startLocalesOff();

                downloadCore(local_dtos);
            }
        });
    }

    private void downloadCore(final ArrayList<Local_DTO> local_dtos) {
        final Variable_CRUD variable_crud = new Variable_CRUD(context);
        final String id_local = local_dtos.get(counter_local).getId();
        final String id_variable = variable_crud.getVariables(local_dtos.get(counter_local)).get(counter_variable).get_idVariable();

        dialog_offLine.setText("Descargando información \n'"
                + local_dtos.get(counter_local).getNombre().toUpperCase() + "'\n"
                + "Obteniendo Variable \n'"
                + variable_crud.getVariables(local_dtos.get(counter_local)).get(counter_variable).getNombre().toUpperCase() + "'");

        Service_Encuestas service_encuestas = new Service_Encuestas(context);
        service_encuestas.sendRequest(id_local, id_variable);
        service_encuestas.setOnSuccessEncuestas(new OnSuccessEncuestas() {
            @Override
            public void onSuccessEncuestas(boolean success, ArrayList<Encuesta_DTO> encuesta_dtos, String message) {
                if (success) {

                    for (int i = 0; i < encuesta_dtos.size(); i++) {
                        Encuesta_DTO encuesta_dto = new Encuesta_DTO();
                        encuesta_dto.set_idlocal(id_local);
                        encuesta_dto.set_idvariable(id_variable);
                        encuesta_dto.set_data(encuesta_dtos.get(i).getDataSource());

                        Encuesta_CRUD encuesta_crud = new Encuesta_CRUD(context);
                        encuesta_crud.addEncuesta(encuesta_dto);
                    }

                    counter_variable++;
                    if(counter_variable < variable_crud.getVariables(local_dtos.get(counter_local)).size()){
                        downloadCore(local_dtos);
                    }else{
                        counter_variable = 0;
                        counter_local++;
                        if(counter_local < local_dtos.size()){
                            downloadCore(local_dtos);
                        }else{
                            dialog_offLine.hide();
                            Toast.makeText(context,context.getString(R.string.message_correcto),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    /*

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
                Log.e(Downloader.class.getName(), response.toString());

                ArrayList<Local_DTO> local_dtos = new ArrayList<Local_DTO>();
                try {
                    JSONArray array_locales = response.getJSONArray("result");

                    dialog_offLine.setText("Se han encontrado " + array_locales.length() + " locales.");

                    for (int i = 0; i < array_locales.length(); i++) {
                        JSONObject local_json = array_locales.getJSONObject(i);
                        Local_DTO local_dto = new Local_DTO();
                        local_dto.setLocal_json(local_json);
                        String id_local = local_json.getString("id");

                        ArrayList<Variable_DTO> variable_dtos = new ArrayList<Variable_DTO>();
                        variable_dtos.add(new Variable_DTO(id_local, "sku", false, "Sku"));
                        variable_dtos.add(new Variable_DTO(id_local, "afiche", false, "Afiche"));
                        variable_dtos.add(new Variable_DTO(id_local, "promocion", false, "Promoción"));
                        variable_dtos.add(new Variable_DTO(id_local, "calidad", false, "Calidad"));
                        local_dto.setVariable_dtos(variable_dtos, context,false);

                        local_dtos.add(local_dto);
                    }
                    Local_Service local_service = new Local_Service(context, true);
                    local_service.setInterface_service_locales(interface_service_locales);
                    local_service.addLocales(local_dtos);
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
                Log.e(Downloader.class.getName(), responseString);
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
                dialog_offLine.setText("Ocurrio un error, intentelo nuevamente");
            }
        });
    }
        */
}
