package com.capr.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.capr.adapter.Adapter_Variables;
import com.capr.beans.Variable_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

import java.util.ArrayList;

/**
 * Created by Gantz on 18/10/14.
 */
public class Fragment_Variables extends Fragment_Opino implements AdapterView.OnItemClickListener {

    protected ListView lista_locales;
    protected Adapter_Variables adapter_variables;

    public static final Fragment_Variables newInstance() {
        return new Fragment_Variables();
    }

    public Fragment_Variables() {
        super(R.layout.fragment_variables,R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOpino().getSupportActionBar().setIcon(R.drawable.logo_opino);
        getOpino().getSupportActionBar().setTitle("Variables");
    }

    @Override
    protected void initView() {
        super.initView();

        TextView titulo = (TextView)getView().findViewById(R.id.txtvariableslocal);
        titulo.setTypeface(Util_Fonts.setPNASemiBold(getOpino()));

        lista_locales = (ListView) getView().findViewById(R.id.lista_variables);
        lista_locales.setOnItemClickListener(this);
        adapter_variables = new Adapter_Variables(getOpino(),variable_dtos());
        lista_locales.setAdapter(adapter_variables);
    }

    private ArrayList<Variable_DTO> variable_dtos(){
        ArrayList<Variable_DTO> variable_dtos = new ArrayList<Variable_DTO>();
        variable_dtos.add(new Variable_DTO("sku","Sku"));
        variable_dtos.add(new Variable_DTO("afiche","Afiche"));
        variable_dtos.add(new Variable_DTO("promocion","Promoción"));
        variable_dtos.add(new Variable_DTO("calidad","Calidad"));
        variable_dtos.add(new Variable_DTO("interaccion","Interacción"));
        return variable_dtos;
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
        getOpino().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.abajo_arriba, R.animator.arriba_abajo).add(R.id.container, Fragment_Encuestas_v2.newInstance(),Fragment_Encuestas.class.getName()).addToBackStack(null).commit();
        getOpino().getSupportActionBar().setTitle(variable_dto.getVariable_nombre());
    }
}