package com.capr.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.capr.beans.Opcion_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

import java.util.ArrayList;

public class Adapter_Presente extends BaseAdapter {

    private Context mContext;
    private ArrayList<Opcion_DTO> opcion_dtos;
    private LayoutInflater inflater;

    public Adapter_Presente(Context mContext, ArrayList<Opcion_DTO> opcion_dtos) {
        this.mContext = mContext;
        this.opcion_dtos = opcion_dtos;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return opcion_dtos.size();
    }

    @Override
    public Object getItem(int item) {
        return opcion_dtos.get(item);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        Holder holder = null;
        Opcion_DTO opcion_dto = opcion_dtos.get(position);

        if (view == null) {
            view = inflater.inflate(R.layout.item_opcion, container, false);
            holder = new Holder();

            holder.nombrevariable = (TextView) view.findViewById(R.id.txtnombrevariable);
            holder.checkBox = (CheckBox) view.findViewById(R.id.checkBox);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.nombrevariable.setText(opcion_dto.getNombre());
        holder.nombrevariable.setTypeface(Util_Fonts.setPNASemiBold(mContext));
        holder.checkBox.setClickable(false);

        if(position == 0){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }

        return view;
    }

    static class Holder {
        TextView nombrevariable;
        CheckBox checkBox;
    }
}
