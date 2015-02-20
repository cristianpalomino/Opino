package com.capr.services_v2;

import android.content.Context;

import com.capr.actividades.Variable;
import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.crud_v2.Variable_CRUD;
import com.capr.interfaces_v2.OnSuccessVariables;
import com.capr.opino.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public class Service_Variables {

    private Context context;
    private OnSuccessVariables onSuccessVariables;

    public Service_Variables(Context context) {
        this.context = context;
    }

    public void setOnSuccessVariables(OnSuccessVariables onSuccessVariables) {
        this.onSuccessVariables = onSuccessVariables;
    }

    public void sendRequest() {
        Variable variable = ((Variable) context);
        ArrayList<Variable_DTO> variable_dtos = new ArrayList<Variable_DTO>();

        try {
            for (int i = 0; i < names().size(); i++) {
                Variable_DTO variable_dto = new Variable_DTO();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", i);
                jsonObject.put("idlocal", variable.getLocal_dto().getId());
                jsonObject.put("nombre", names().get(i));
                jsonObject.put("idvariable", codes().get(i));
                jsonObject.put("estado", false);


                variable_dto.setDataSource(jsonObject);
                variable_dtos.add(variable_dto);
            }
            onSuccessVariables.onSuccessVariables(true, variable_dtos, context.getString(R.string.message_correcto));
        } catch (JSONException e) {
            e.printStackTrace();
            onSuccessVariables.onSuccessVariables(false, null, context.getString(R.string.message_api_error));
        }
    }

    public void sendRequestOff(Local_DTO local_dto) {
        ArrayList<Variable_DTO> variable_dtos = new ArrayList<Variable_DTO>();
        Variable_CRUD variable_crud = new Variable_CRUD(context);

        try {
            if (onSuccessVariables != null) {
                Local_DTO local_activo = ((Variable) context).getLocal_dto();
                ArrayList<Variable_DTO> mvariable_dtos = variable_crud.getVariables(local_activo);
                onSuccessVariables.onSuccessVariables(true, mvariable_dtos, context.getString(R.string.message_correcto));
            } else {
                for (int i = 0; i < names().size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", i);
                    jsonObject.put("idlocal", local_dto.get_idLocal());
                    jsonObject.put("nombre", names().get(i));
                    jsonObject.put("idvariable", codes().get(i));
                    jsonObject.put("estado", false);

                    Variable_DTO variable_dto = new Variable_DTO();
                    variable_dto.set_idLocal(local_dto.get_idLocal());
                    variable_dto.set_idVariable(codes().get(i));
                    variable_dto.set_estado("NO");
                    variable_dto.set_data(jsonObject);
                    variable_dtos.add(variable_dto);

                    variable_crud.addVariable(variable_dto);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onSuccessVariables.onSuccessVariables(false, null, context.getString(R.string.message_api_error));
        }
    }

    private ArrayList<String> names() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Sku");
        names.add("Pop");
        names.add("Promoción");
        names.add("Calidad");
        return names;
    }

    private ArrayList<String> codes() {
        ArrayList<String> codes = new ArrayList<String>();
        codes.add("sku");
        codes.add("pop");
        codes.add("promocion");
        codes.add("calidad");
        return codes;
    }
}
