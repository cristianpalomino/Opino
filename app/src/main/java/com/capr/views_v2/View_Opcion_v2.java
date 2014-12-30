package com.capr.views_v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.capr.adapter.Adapter_Presente;
import com.capr.beans.Opcion_DTO;
import com.capr.opino.R;
import com.capr.views.View_Opino;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/12/14.
 */
public class View_Opcion_v2 extends View_Opino implements AdapterView.OnItemClickListener {

    private ListView lista_opcion;

    public View_Opcion_v2(Context context) {
        super(context, R.layout.view_opcion);
    }

    public View_Opcion_v2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_opcion);
    }

    public View_Opcion_v2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_opcion);
    }

    public View_Opcion_v2(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_opcion);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    public void init(){
        lista_opcion = (ListView) getView().findViewById(R.id.lista_opcion);
        lista_opcion.setAdapter(new Adapter_Presente(getOpino(),getOpciones()));
        lista_opcion.setOnItemClickListener(this);
    }
    
    private ArrayList<Opcion_DTO> getOpciones(){
        Opcion_DTO presente = new Opcion_DTO(true,"Presente",false,false);
        Opcion_DTO daniado = new Opcion_DTO(false,"Dañado",false,false);
        Opcion_DTO invadido = new Opcion_DTO(false,"Invadido",false,false);

        ArrayList<Opcion_DTO> opcion_dtos = new ArrayList<Opcion_DTO>();
        opcion_dtos.add(presente);
        opcion_dtos.add(daniado);
        opcion_dtos.add(invadido);

        return opcion_dtos;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        if(position == 0){
            if(checkBox.isActivated()){
                checkBox.setChecked(false);
            }else{
                checkBox.setChecked(true);
            }
        }
    }
}
