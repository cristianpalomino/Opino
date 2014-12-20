package com.capr.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.capr.beans.Local_DTO;
import com.capr.opino.R;

import java.util.ArrayList;

public class Adapter_Locales extends RecyclerView.Adapter<Adapter_Locales.ViewHolder> {

    private ArrayList<Local_DTO> local_dtos;

    public Adapter_Locales(ArrayList<Local_DTO> local_dtos) {
        this.local_dtos = local_dtos;
    }

    @Override
    public Adapter_Locales.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_local, parent, false);
        ViewHolder vh = new ViewHolder(v);

        vh.txtdireccionlocal = (TextView) v.findViewById(R.id.txtdireccionlocal);
        vh.txtnombrelocal = (TextView) v.findViewById(R.id.txtnombrelocal);
        vh.cvlocal = (CardView) v.findViewById(R.id.card_view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Local_DTO local_dto = local_dtos.get(position);
        holder.txtnombrelocal.setText(local_dto.getLocal_nombre().toUpperCase());
        holder.txtdireccionlocal.setText(local_dto.getLocal_direccion());
        holder.cvlocal.setBackgroundColor(Color.parseColor(local_dto.getLocal_canal()));
    }

    @Override
    public int getItemCount() {
        return local_dtos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtnombrelocal;
        public TextView txtdireccionlocal;
        public CardView cvlocal;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}