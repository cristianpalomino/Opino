package com.capr.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.capr.beans.Usuario_DTO;

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

    public void crearSession(Usuario_DTO usuario_dto) {
        editor.putBoolean(USER_LOGIN, true);
        editor.putString(USER_DATA, usuario_dto.getUsuario_json().toString());
        editor.commit();
    }

    public void cerrarSession() {
        editor.putBoolean(USER_LOGIN, false);
        editor.putString(USER_DATA, null);
        editor.commit();
        Toast.makeText(context, "Cerrando sessión", Toast.LENGTH_SHORT).show();
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
}