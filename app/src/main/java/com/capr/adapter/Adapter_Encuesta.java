package com.capr.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capr.beans.Encuesta_DTO;
import com.capr.opino.R;
import com.capr.views.View_Comentario;
import com.capr.views.View_Foto;
import com.capr.views.View_Rango;
import com.capr.views.View_Si_No;
import com.capr.views.View_Sub_Comentario;

import java.util.ArrayList;

public class Adapter_Encuesta extends RecyclerView.Adapter<Adapter_Encuesta.ViewHolder> {

    private ArrayList<Encuesta_DTO> encuesta_dtos;

    public Adapter_Encuesta(ArrayList<Encuesta_DTO> encuesta_dtos) {
        this.encuesta_dtos = encuesta_dtos;
    }

    @Override
    public Adapter_Encuesta.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_encuesta,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Encuesta_DTO encuesta_dto = encuesta_dtos.get(position);
        holder.nombreencuesta.setText(encuesta_dto.getEncuesta_descripcion());

        /*
        if(encuesta_dto.getFoto_dto() != null){
            holder.view_foto.setFoto_dto(encuesta_dto.getFoto_dto());
            holder.view_foto.setVisibility(View.VISIBLE);
        }

        if(encuesta_dto.getSi_no_dto() != null){
            holder.view_si_no.setSi_no_dto(encuesta_dto.getSi_no_dto());
            holder.view_si_no.setVisibility(View.VISIBLE);
        }

        if(encuesta_dto.getComentario_dto() != null){
            holder.view_comentario.setComentario_dto(encuesta_dto.getComentario_dto());
            holder.view_comentario.setVisibility(View.VISIBLE);
        }

        if(encuesta_dto.getSub_comentario_dto() != null){
            holder.view_sub_comentario.setSub_comentario_dto(encuesta_dto.getSub_comentario_dto());
            holder.view_sub_comentario.setVisibility(View.VISIBLE);
        }

        if(encuesta_dto.getRango_dto() != null){
            //holder.view_rango.setRango_dto(encuesta_dto.getRango_dto());
            holder.view_rango.setVisibility(View.VISIBLE);
        }
        */
    }

    @Override
    public int getItemCount() {
        return encuesta_dtos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public  TextView nombreencuesta;
        public  LinearLayout containeritemencuesta;
        public View_Foto view_foto;
        public View_Si_No view_si_no;
        public View_Comentario view_comentario;
        public View_Sub_Comentario view_sub_comentario;
        public View_Rango view_rango;

        public ViewHolder(View view) {
            super(view);
            nombreencuesta = (TextView)view.findViewById(R.id.txtnombreencuesta);
            containeritemencuesta = (LinearLayout)view.findViewById(R.id.containerencuesta);
            //view_foto = (View_Foto)view.findViewById(R.id.view_foto);

            /*
            view_si_no = (View_Si_No)view.findViewById(R.id.view_si_no);
            view_comentario = (View_Comentario)view.findViewById(R.id.view_comentario);
            view_sub_comentario = (View_Sub_Comentario)view.findViewById(R.id.view_sub_comentario);
            view_rango = (View_Rango)view.findViewById(R.id.view_rango);
            */
        }
    }
}
