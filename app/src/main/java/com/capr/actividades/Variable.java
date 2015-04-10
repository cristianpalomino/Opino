package com.capr.actividades;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.crud_v2.Local_CRUD;
import com.capr.crud_v2.Variable_CRUD;
import com.capr.modulos.Modulo_Update_Location;
import com.capr.modulos_v2.Modulo_Off;
import com.capr.modulos_v2.Modulo_On;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.utils.Util_Fonts;

import java.util.ArrayList;

public class Variable extends Opino implements AdapterView.OnItemClickListener {

    public ListView lista_variables;
    //private Modulo_On modulo_on;
    private Modulo_Off modulo_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_variables);
        try {
            setTitle(getLocal_dto().getNombre().toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView titulo = (TextView) findViewById(R.id.txtvariableslocal);
        titulo.setTypeface(Util_Fonts.setPNASemiBold(Variable.this));

        lista_variables = (ListView) findViewById(R.id.lista_variables);
        lista_variables.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Session_Manager session_manager = new Session_Manager(Variable.this);
        //modulo_on = new Modulo_On(this);
        modulo_off = new Modulo_Off(this);
        if (session_manager.getMode()) {
            //modulo_on.startVariablesOn();
            //modulo_off.startVariablesOff();
        } else {
            modulo_off.startVariablesOff();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_variable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_check) {
            Modulo_Update_Location modulo_update_location = new Modulo_Update_Location();
            modulo_update_location.updateLocation(Variable.this, getLocal_dto().getId());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Variable_DTO variable_dto = (Variable_DTO) parent.getItemAtPosition(position);
        setVariable_dto(variable_dto);

        Intent intent = new Intent(Variable.this, Encuestas.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Local_CRUD local_crud = new Local_CRUD(this);
        ArrayList<Local_DTO> local_dtos = local_crud.getLocales();

        for (int i = 0; i < local_dtos.size(); i++) {
            Local_DTO local_dto = local_dtos.get(i);
            Variable_CRUD variable_crud = new Variable_CRUD(this);
            ArrayList<Variable_DTO> variable_dtos = variable_crud.getVariables(local_dto);
            if (variable_dtos.get(0).get_estado().equals("SI") &&
                    variable_dtos.get(1).get_estado().equals("SI") &&
                    variable_dtos.get(2).get_estado().equals("SI") &&
                    variable_dtos.get(3).get_estado().equals("SI")) {
                local_dto.set_estado("SI");
                local_crud.updateLocal(local_dto);
            }
        }
    }
}
