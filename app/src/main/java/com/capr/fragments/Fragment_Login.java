package com.capr.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capr.beans.Usuario_DTO;
import com.capr.dialog.Dialog_Opino;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.utils.Util_Fonts;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by Gantz on 18/10/14.
 */
public class Fragment_Login extends Fragment_Opino implements View.OnClickListener {

    protected EditText edtusuario;
    protected EditText edtpassword;
    protected Button btnentrar;

    public static final Fragment_Login newInstance(){
        return new Fragment_Login();
    }

    public Fragment_Login() {
        super(R.layout.fragment_login,R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOpino().getSupportActionBar().setIcon(R.drawable.logo_opino);
        getOpino().getSupportActionBar().setTitle("Entrar");
    }

    @Override
    protected void initView() {
        super.initView();

        edtusuario = (EditText)getView().findViewById(R.id.edtusuario);
        edtpassword = (EditText)getView().findViewById(R.id.edtpassword);
        btnentrar = (Button)getView().findViewById(R.id.btnentrar);

        btnentrar.setOnClickListener(this);

        edtusuario.setTypeface(Util_Fonts.setPNALight(getOpino()));
        edtpassword.setTypeface(Util_Fonts.setPNALight(getOpino()));
        btnentrar.setTypeface(Util_Fonts.setPNASemiBold(getOpino()));
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        showDialog();

        RequestParams params = new RequestParams();
        params.put("username",edtusuario.getText().toString());
        params.put("password",edtpassword.getText().toString());

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(getOpino(),Opino_WS.WS_LOGIN_OPINO,params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Session_Manager session_manager = new Session_Manager(getOpino());
                Usuario_DTO usuario_dto = new Usuario_DTO();
                usuario_dto.setUsuario_json(response);
                session_manager.crearSession(usuario_dto);

                hideDialog();

                getOpino().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.abajo_arriba, R.animator.arriba_abajo).replace(R.id.container, Fragment_Locales.newInstance(),Fragment_Locales.class.getName()).commit();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(Fragment_Login.class.getName(),responseString);
                hideDialog();
            }
        });
    }
}
