package com.capr.actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.format.Time;
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
import com.capr.opino.UserService;
import com.capr.services_v2.Service_Login;
import com.capr.session.Session_Manager;
import com.capr.utils.Util_Fonts;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Required;

public class Entrar extends Opino implements View.OnClickListener {

    @Required(order = 1, message = "Ingrese su Usuario")
    protected EditText edtusuario;
    @Required(order = 1, message = "Ingrese su Contraseña")
    protected EditText edtpassword;
    protected Button btnentrar;

    private ProgressDialog progressDialog;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        validator = new Validator(this);

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

            edtusuario.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
            edtpassword.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
            btnentrar.setOnClickListener(this);

            edtusuario.setTypeface(Util_Fonts.setPNALight(this));
            edtpassword.setTypeface(Util_Fonts.setPNALight(this));
            btnentrar.setTypeface(Util_Fonts.setPNASemiBold(this));

            startService(new Intent(this, UserService.class));
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                validate();
            }

            @Override
            public void onValidationFailed(View failedView, Rule<?> failedRule) {
                ((EditText) failedView).setError(failedRule.getFailureMessage());
            }
        });
        validator.validate();
    }

    private void validate() {
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
