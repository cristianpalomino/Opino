package com.capr.actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capr.beans_v2.User_DTO;
import com.capr.dialog.Dialog_Opino;
import com.capr.interfaces_v2.OnSuccessLogin;
import com.capr.opino.R;
import com.capr.services_v2.Service_Login;
import com.capr.session.Session_Manager;
import com.capr.utils.Util_Fonts;

public class Entrar extends Opino implements View.OnClickListener {

    protected EditText edtusuario;
    protected EditText edtpassword;
    protected Button btnentrar;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        Session_Manager session_manager = new Session_Manager(this);
        if (session_manager.isLogin()) {
            Intent intent = new Intent(Entrar.this, Local.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            edtusuario = (EditText) findViewById(R.id.edtusuario);
            edtpassword = (EditText) findViewById(R.id.edtpassword);
            btnentrar = (Button) findViewById(R.id.btnentrar);

            btnentrar.setOnClickListener(this);

            edtusuario.setTypeface(Util_Fonts.setPNALight(this));
            edtpassword.setTypeface(Util_Fonts.setPNALight(this));
            btnentrar.setTypeface(Util_Fonts.setPNASemiBold(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entrar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        progressDialog = ProgressDialog.show(Entrar.this, null, "Validando ususario...!", true, false);
        progressDialog.show();

        String usuario = edtusuario.getText().toString();
        String password = edtpassword.getText().toString();

        Service_Login service_login = new Service_Login(Entrar.this);
        service_login.sendRequest(usuario, password);
        service_login.setOnSuccessLogin(new OnSuccessLogin() {
            @Override
            public void onSuccessLogin(boolean success, User_DTO user_dto, String message) {
                Toast.makeText(Entrar.this, message, Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                if (success) {
                    Session_Manager session_manager = new Session_Manager(Entrar.this);
                    session_manager.crearSessionv2(user_dto);
                    session_manager.setMode(true);
                }
            }
        });
    }
}
