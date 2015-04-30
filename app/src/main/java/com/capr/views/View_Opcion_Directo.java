package com.capr.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.capr.adapter.Adapter_Opcion;
import com.capr.beans.Opcion_DTO;
import com.capr.opino.R;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/12/14.
 */
public class View_Opcion_Directo extends View_Opino implements AdapterView.OnItemClickListener {

    private ListView lista_opcion;
    private OnItemClick onItemClick;

    public View_Opcion_Directo(Context context) {
        super(context, R.layout.view_opcion);
    }

    public View_Opcion_Directo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_opcion);
    }

    public View_Opcion_Directo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_opcion);
    }

    public View_Opcion_Directo(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_opcion);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    public void init(){
        lista_opcion = (ListView) getView().findViewById(R.id.lista_opcion);
        lista_opcion.setOnItemClickListener(this);
        lista_opcion.setAdapter(new Adapter_Opcion(getOpino(),getOpciones()));
    }

    private ArrayList<Opcion_DTO> getOpciones(){
        Opcion_DTO no_aplica = new Opcion_DTO(true,"No Aplica",false,false);
        Opcion_DTO presente = new Opcion_DTO(true,"Indirecta",false,false);
        Opcion_DTO daniado = new Opcion_DTO(false,"Mixta",false,false);
        Opcion_DTO invadido = new Opcion_DTO(false,"Directa",false,false);

        ArrayList<Opcion_DTO> opcion_dtos = new ArrayList<Opcion_DTO>();
        opcion_dtos.add(no_aplica);
        opcion_dtos.add(presente);
        opcion_dtos.add(daniado);
        opcion_dtos.add(invadido);

        return opcion_dtos;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onItemClick.onItemClick(parent, view, position, id);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }
}
