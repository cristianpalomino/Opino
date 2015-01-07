package com.capr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.capr.beans_v2.Local_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

import java.util.ArrayList;

public class Adapter_Locales_v2 extends BaseAdapter {

    private Context mContext;
    private ArrayList<Local_DTO> locales;
    private LayoutInflater inflater;

    public Adapter_Locales_v2(Context mContext, ArrayList<Local_DTO> locales) {
        this.mContext = mContext;
        this.locales = locales;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return locales.size();
    }

    @Override
    public Object getItem(int item) {
        return locales.get(item);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        Holder holder = null;
        Local_DTO local_dto = locales.get(position);

        if (view == null) {
            view = inflater.inflate(R.layout.item_local_v2, container, false);
            holder = new Holder();

            holder.nombrelocal = (TextView) view.findViewById(R.id.txtnombrelocal);
            holder.direccionlocal = (TextView) view.findViewById(R.id.txtdireccionlocal);
            holder.containeritemlocal = (FrameLayout) view.findViewById(R.id.containeritemlocal);
            holder.checkBox = (CheckBox) view.findViewById(R.id.checkBox);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.nombrelocal.setTypeface(Util_Fonts.setPNASemiBold(mContext));
        holder.direccionlocal.setTypeface(Util_Fonts.setPNALight(mContext));

        holder.nombrelocal.setText(local_dto.getNombre().toUpperCase());
        holder.direccionlocal.setText(local_dto.getDireccion() + ", " + local_dto.getDistrito());
        holder.containeritemlocal.setBackgroundColor(mContext.getResources().getColor(R.color.rojo_alizarin));
        holder.checkBox.setClickable(false);

        if(local_dto.get_estado() != null){
            if(local_dto.get_estado().equals("NO")){
                holder.checkBox.setChecked(false);
                holder.containeritemlocal.setBackgroundColor(mContext.getResources().getColor(R.color.rojo_alizarin));
            }else{
                holder.checkBox.setChecked(true);
                holder.containeritemlocal.setBackgroundColor(mContext.getResources().getColor(R.color.color_verde_boton));
            }
        }

        return view;
    }

    static class Holder {
        TextView nombrelocal;
        TextView direccionlocal;
        FrameLayout containeritemlocal;
        CheckBox checkBox;
    }
}
