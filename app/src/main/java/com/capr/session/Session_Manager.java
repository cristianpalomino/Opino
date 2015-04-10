package com.capr.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.capr.actividades.Entrar;
import com.capr.actividades.Local;
import com.capr.beans.Usuario_DTO;
import com.capr.beans_v2.User_DTO;
import com.capr.opino.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gantz on 23/06/14.
 */
public class Session_Manager {

    private static final String PREFERENCE_NAME = "opino_preferences";
    private int PRIVATE_MODE = 0;

    /*
    USUARIO DATA SESSION - JSON
     */
    public static final String USER_DATA = "userData";
    public static final String USER_LOGIN = "userLogin";
    public static final String USER_MODE = "userMode";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Session_Manager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean isLogin() {
        return preferences.getBoolean(USER_LOGIN, false);
    }

    public boolean getMode() {
        return preferences.getBoolean(USER_MODE,false);
    }

    public void setMode(boolean mode){
        editor.putBoolean(USER_MODE,mode);
        editor.commit();
    }

    public Usuario_DTO getSession() {
        try {
            if (isLogin()) {
                String userData = preferences.getString(USER_DATA, null);
                Usuario_DTO usuario_dto = new Usuario_DTO(new JSONObject(userData));
                return usuario_dto;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void crearSessionv2(User_DTO user_dto) {
        editor.putBoolean(USER_LOGIN, true);
        editor.putBoolean(USER_MODE, true);
        editor.putString(USER_DATA,user_dto.getDataSource().toString());
        editor.commit();

        Intent intent = new Intent(context,Local.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Entrar)context).finish();
    }

    public void cerrarSessionv2() {
        editor.putBoolean(USER_LOGIN, false);
        editor.putBoolean(USER_MODE, false);
        editor.putString(USER_DATA, null);
        editor.commit();

        Intent intent = new Intent(context,Entrar.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Local)context).finish();

        Toast.makeText(context, "Cerrando sessión", Toast.LENGTH_SHORT).show();
    }

    public User_DTO getSessionv2() {
        try {
            if (isLogin()) {
                String userData = preferences.getString(USER_DATA, null);
                User_DTO user_dto = new User_DTO();
                user_dto.setDataSource(new JSONObject(userData));
                return user_dto;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}