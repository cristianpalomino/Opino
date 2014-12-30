package com.capr.actividades;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.crud_v2.Local_CRUD;
import com.capr.crud_v2.Variable_CRUD;
import com.capr.dialog.Dialog_Opino_Aviso;
import com.capr.modulos_v2.Modulo_Off;
import com.capr.modulos_v2.Modulo_On;
import com.capr.opino.R;
import com.capr.services_v2.Downloader;
import com.capr.session.Session_Manager;
import com.shamanland.fab.FloatingActionButton;

import java.util.ArrayList;

public class Local extends Opino implements AdapterView.OnItemClickListener,
        Dialog_Opino_Aviso.I_Aviso,
        CompoundButton.OnCheckedChangeListener {

    public ListView lista_locales;
    public FloatingActionButton flatbutton;
    public ToggleButton modo;
    private Modulo_On modulo_on;
    private Modulo_Off modulo_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_locales);
        setTitle("LOCALES");

        lista_locales = (ListView) findViewById(R.id.lista_locales);
        lista_locales.setOnItemClickListener(Local.this);

        /**
         * Flatbutton
         */
        flatbutton = (FloatingActionButton) findViewById(R.id.flatbutton);
        flatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Downloader downloader = new Downloader(Local.this);
                downloader.initDownloader(Local.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Session_Manager session_manager = new Session_Manager(Local.this);
        modulo_on = new Modulo_On(Local.this);
        modulo_off = new Modulo_Off(Local.this);

        if (session_manager.getMode()) {
            modulo_on.startLocalesOn();
        } else {
            modulo_off.startLocalesOff();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        modo = (ToggleButton) menu.findItem(R.id.action_modo).getActionView();
        if (new Session_Manager(Local.this).getMode()) {
            modo.setChecked(true);
        } else {
            modo.setChecked(false);
        }
        modo.setOnCheckedChangeListener(this);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Session_Manager session_manager = new Session_Manager(Local.this);
            session_manager.cerrarSessionv2();
            session_manager.setMode(true);
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
        Local_DTO local_dto = (Local_DTO) parent.getItemAtPosition(position);
        setLocal_dto(local_dto);

        Intent intent = new Intent(Local.this, Variable.class);
        startActivity(intent);
    }

    @Override
    public void onAvisoResult(boolean accept, Dialog_Opino_Aviso dialog_opino_aviso) {
        dialog_opino_aviso.hide();
        if (accept) {
            Session_Manager session_manager = new Session_Manager(Local.this);
            session_manager.setMode(true);
            modulo_on.startLocalesOn();

            modo.setOnCheckedChangeListener(null);
            modo.setChecked(true);
            modo.setOnCheckedChangeListener(Local.this);
        } else {
            Session_Manager session_manager = new Session_Manager(Local.this);
            session_manager.setMode(false);

            modo.setOnCheckedChangeListener(null);
            modo.setChecked(false);
            modo.setOnCheckedChangeListener(Local.this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Dialog_Opino_Aviso dialog_opino_aviso = new Dialog_Opino_Aviso(Local.this);
            dialog_opino_aviso.setI_aviso(Local.this);
            dialog_opino_aviso.show();
        } else {
            Session_Manager session_manager = new Session_Manager(Local.this);
            session_manager.setMode(false);

            modulo_off.startLocalesOff();
        }
    }
}