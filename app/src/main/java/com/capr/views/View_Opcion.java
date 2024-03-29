package com.capr.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.capr.adapter.Adapter_Presente;
import com.capr.beans.Opcion_DTO;
import com.capr.beans.Presente_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/12/14.
 */
public class View_Opcion extends View_Opino implements AdapterView.OnItemClickListener {
    
    private ListView lista_opcion;
    private OnItemClick onItemClick;

    public View_Opcion(Context context) {
        super(context, R.layout.view_opcion);
    }

    public View_Opcion(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_opcion);
    }

    public View_Opcion(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_opcion);
    }

    public View_Opcion(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_opcion);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    public void init(){
        lista_opcion = (ListView) getView().findViewById(R.id.lista_opcion);
        lista_opcion.setAdapter(new Adapter_Presente(getOpino(), getOpciones()));
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
        onItemClick.onItemClick(parent,view,position,id);

        View presente = parent.getChildAt(0);
        View daniado = parent.getChildAt(1);
        View invadido = parent.getChildAt(2);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        if(position == 0){
            if(checkBox.isChecked()){
                checkBox.setChecked(false);

                daniado.setVisibility(GONE);
                invadido.setVisibility(GONE);
            }else{
                checkBox.setChecked(true);
                daniado.setVisibility(VISIBLE);
                invadido.setVisibility(VISIBLE);
            }

            ((CheckBox)daniado.findViewById(R.id.checkBox)).setChecked(false);
            ((CheckBox)invadido.findViewById(R.id.checkBox)).setChecked(false);
        }else{
            if(checkBox.isChecked()){
                checkBox.setChecked(false);
            }else{
                checkBox.setChecked(true);
            }
        }

        /*
        if(position == 0){
            if(checkBox.isActivated()){
                checkBox.setChecked(false);
            }else{
                checkBox.setChecked(true);
            }
        }
        */
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }
}
