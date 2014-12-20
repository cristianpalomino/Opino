package com.capr.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.capr.adapter.Adapter_Variables;
import com.capr.beans.Local_DTO;
import com.capr.beans.Variable_DTO;
import com.capr.interfaces.Interface_Update_Location;
import com.capr.modulos.Modulo_Update_Location;
import com.capr.opino.R;
import com.capr.service.Variable_Service;
import com.capr.utils.Util_Fonts;

import java.util.ArrayList;

/**
 * Created by Gantz on 18/10/14.
 */
public class Fragment_Variables extends Fragment_Opino implements AdapterView.OnItemClickListener {

    protected ListView lista_variables;
    protected Adapter_Variables adapter_variables;

    public static final Fragment_Variables newInstance() {
        return new Fragment_Variables();
    }

    public Fragment_Variables() {
        super(R.layout.fragment_variables, R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getOpino().getSupportActionBar().setIcon(R.drawable.logo_opino);
        getOpino().getSupportActionBar().setTitle("Variables");
    }

    @Override
    protected void initView() {
        super.initView();

        TextView titulo = (TextView) getView().findViewById(R.id.txtvariableslocal);
        titulo.setTypeface(Util_Fonts.setPNASemiBold(getOpino()));

        lista_variables = (ListView) getView().findViewById(R.id.lista_variables);
        lista_variables.setOnItemClickListener(this);
        ArrayList<Variable_DTO> variable_dtos = null;

        if (getOpino().isOnline()) {
            /**
             * Get from Db
             */
            Variable_Service variable_service = new Variable_Service(getOpino());
            variable_dtos = variable_service.getVariables(getOpino().getLocal_dto().getLocal_id());
        } else {
            /**
             * Get from Db
             */
            Variable_Service variable_service = new Variable_Service(getOpino());
            variable_dtos = variable_service.getVariables(getOpino().getLocal_dto().getLocal_id());
        }

        adapter_variables = new Adapter_Variables(getOpino(),variable_dtos);
        lista_variables.setAdapter(adapter_variables);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment_Variables.this.getView().setFocusableInTouchMode(true);
        Fragment_Variables.this.getView().requestFocus();
        Fragment_Variables.this.getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction trans = manager.beginTransaction();
                        trans.setCustomAnimations(R.animator.izquierda_derecha, R.animator.derecha_izquierda);
                        trans.remove(Fragment_Variables.this);
                        trans.commit();
                        manager.popBackStack();
                        return true;
                    }
                }
                return false;
            }
        });
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
        getOpino().setVariable_dto(variable_dto);
        getOpino().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.abajo_arriba, R.animator.arriba_abajo).add(R.id.container, Fragment_Encuestas_v2.newInstance(), Fragment_Encuestas.class.getName()).addToBackStack(null).commit();
        getOpino().getSupportActionBar().setTitle(variable_dto.getVariable_nombre());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        /**
         * Validate if need location
        Local_DTO local_dto = getOpino().getLocal_dto();
        if (local_dto.getLocal_latitud().equals("") || local_dto.getLocal_latitud() == null) {
            inflater.inflate(R.menu.menu_local, menu);
        }
         */
        inflater.inflate(R.menu.menu_local, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update_location:
                Interface_Update_Location interface_update_location = new Modulo_Update_Location();
                interface_update_location.updateLocation(getOpino(),getOpino().getLocal_dto().getLocal_id());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_logout).setVisible(false).setEnabled(false);
        menu.findItem(R.id.action_modo).setVisible(false).setEnabled(false);
        super.onPrepareOptionsMenu(menu);
    }
}