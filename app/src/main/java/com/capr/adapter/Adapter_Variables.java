package com.capr.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.capr.beans_v2.Variable_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

import java.util.ArrayList;

public class Adapter_Variables extends BaseAdapter {

    private Context mContext;
    private ArrayList<Variable_DTO> variable_dtos;
    private LayoutInflater inflater;

    public Adapter_Variables(Context mContext, ArrayList<Variable_DTO> variable_dtos) {
        this.mContext = mContext;
        this.variable_dtos = variable_dtos;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return variable_dtos.size();
    }

    @Override
    public Object getItem(int item) {
        return variable_dtos.get(item);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        Holder holder = null;
        Variable_DTO variable_dto = variable_dtos.get(position);

        if (view == null) {
            view = inflater.inflate(R.layout.item_variable, container, false);
            holder = new Holder();

            holder.nombrevariable = (TextView) view.findViewById(R.id.txtnombrevariable);
            holder.checkBox = (CheckBox) view.findViewById(R.id.checkBox);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.nombrevariable.setTypeface(Util_Fonts.setPNASemiBold(mContext));
        holder.nombrevariable.setText(variable_dto.getNombre());
        holder.checkBox.setClickable(false);

        if(variable_dto.get_estado() != null){
            if(variable_dto.get_estado().equals("NO")){
                holder.checkBox.setChecked(false);
            }else{
                holder.checkBox.setChecked(true);
            }
        }

        return view;
    }

    static class Holder {
        TextView nombrevariable;
        CheckBox checkBox;
    }
}
