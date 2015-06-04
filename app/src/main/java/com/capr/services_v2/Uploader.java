package com.capr.services_v2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.capr.actividades.Variable;
import com.capr.beans.Imagen_DTO;
import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Sender_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.crud_v2.Encuesta_CRUD;
import com.capr.crud_v2.Local_CRUD;
import com.capr.crud_v2.Main_CRUD;
import com.capr.crud_v2.Variable_CRUD;
import com.capr.dialog.Dialog_OffLine;
import com.capr.interfaces.Interface_Upload_Image;
import com.capr.interfaces_v2.OnSendAlert;
import com.capr.interfaces_v2.OnSuccessEncuestas;
import com.capr.interfaces_v2.OnSuccessLocales;
import com.capr.interfaces_v2.OnSuccessRespuesta;
import com.capr.modulos.Modulo_Upload_Image;
import com.capr.modulos_v2.Modulo_Off;
import com.capr.opino.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public class Uploader {

    private Context context;
    private Dialog_OffLine dialog_offLine;

    private int max_local = 0;
    private int counter_local = 0;
    private int counter_variable = 0;
    private int counter_general = 0;

    public Uploader(Context context) {
        this.context = context;
        //new Main_CRUD(context).cleanAllTables();
    }

    public void initUploader(final Activity activity) {

        Encuesta_CRUD encuesta_crud = new Encuesta_CRUD(context);
        Local_CRUD local_crud = new Local_CRUD(context);

        ArrayList<Sender_DTO> sender_dtos = new ArrayList<Sender_DTO>();
        ArrayList<Encuesta_DTO> encuesta_dtos = new ArrayList<Encuesta_DTO>();
        ArrayList<Imagen_DTO> imagen_dtos = new ArrayList<Imagen_DTO>();
        ArrayList<Local_DTO> local_dtos = local_crud.getLocales();

        String[] vars = {"sku", "pop", "promocion", "calidad"};


        for (int x = 0; x < local_dtos.size(); x++) {
            Local_DTO local_dto = local_dtos.get(x);

            for (int i = 0; i < vars.length; i++) {
                encuesta_dtos = encuesta_crud.getEncuestas(local_dto.getId(), vars[i]);
                JSONArray jsonArray = new JSONArray();

                for (int j = 0; j < encuesta_dtos.size(); j++) {

                    String uri = encuesta_dtos.get(j).get_uri();
                    if (!uri.equals("NONE")) {
                        Imagen_DTO imagen_dto = new Imagen_DTO(context);
                        imagen_dto.setImagenId(encuesta_dtos.get(j).get_uri());
                        imagen_dto.setImagenData(encuesta_dtos.get(j).get_uri());
                        imagen_dto.setImagenRecurso(encuesta_dtos.get(j).getRecurso_id());
                        imagen_dto.setImagenLocal(local_dtos.get(x).getId());
                        imagen_dtos.add(imagen_dto);
                    }

                    jsonArray.put(encuesta_dtos.get(j).getDataSource());
                }

                Sender_DTO sender_dto = new Sender_DTO();
                sender_dto.setLocal_dto(local_dto);
                sender_dto.setIdVariable(vars[i]);
                sender_dto.setRespuestas(jsonArray);
                sender_dtos.add(sender_dto);
            }
        }

        max_local = sender_dtos.size();

        if(sender_dtos.isEmpty()){
            Toast.makeText(activity,"No hay locales =(",Toast.LENGTH_SHORT).show();
        }else{
            /**
             * ENVIANDO ALERTAS
             */
            Service_Alerta service_alerta = new Service_Alerta(activity);
            service_alerta.sendRequest();
            service_alerta.setOnSendAlert(new OnSendAlert() {
                @Override
                public void onSuccessAlert(boolean success, String message) {
                    Log.e("CALLBACK",message);
                }
            });

            dialog_offLine = new Dialog_OffLine(context);
            dialog_offLine.setText("Obteniendo Resultados...!");
            dialog_offLine.show();

            uploadCore(sender_dtos);
        }

        if(!imagen_dtos.isEmpty()){
            Interface_Upload_Image interface_upload_image = new Modulo_Upload_Image();
            interface_upload_image.uploadImages(context, imagen_dtos);
        }

    }

    private void uploadCore(final ArrayList<Sender_DTO> sender_dtos) {
        Sender_DTO sender_dto = sender_dtos.get(counter_general);
        dialog_offLine.setText("Eviando información \n'"
                + sender_dto.getLocal_dto().getNombre().toUpperCase() + "'\n"
                + "Enviando Variable \n'"
                + sender_dto.getIdVariable().toUpperCase() + "'");

        Service_Respuesta service_respuesta = new Service_Respuesta(context);
        service_respuesta.sendRequest(sender_dto.getRespuestas(), sender_dto.getLocal_dto().getId(), sender_dto.getIdVariable());
        service_respuesta.setOnSuccessRespuesta(new OnSuccessRespuesta() {
            @Override
            public void onSuccessRespuesta(boolean success, String message) {
                if (success) {
                    dialog_offLine.getProgressBar().setProgress(100 * counter_general / max_local);
                    dialog_offLine.getProgreso().setText(String.valueOf(100 * counter_general / max_local) + "%");
                    counter_general++;
                    if (counter_general < sender_dtos.size()) {
                        uploadCore(sender_dtos);
                    } else {
                        dialog_offLine.hide();
                    }
                }else{
                    dialog_offLine.hide();
                }
            }
        });
    }
}
