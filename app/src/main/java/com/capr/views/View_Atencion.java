package com.capr.views;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.capr.beans.Atencion_DTO;
import com.capr.beans.Respuesta_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Atencion extends View_Opino implements PopupMenu.OnMenuItemClickListener {

    private Atencion_DTO atencion_dto;
    private TextView btnrango;


    public View_Atencion(Context context) {
        super(context, R.layout.view_rango);
    }

    public View_Atencion(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_rango);
    }

    public View_Atencion(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_rango);
    }

    public View_Atencion(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_rango);
    }


    @Override
    protected void initView() {
        super.initView();
        btnrango = (TextView) getView().findViewById(R.id.btnrango);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        btnrango.setLayoutParams(params);

        btnrango.setTypeface(Util_Fonts.setPNASemiBold(getContext()));
        btnrango.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getOpino(),view);
                popupMenu.inflate(R.menu.menu_rango);
                popupMenu.setOnMenuItemClickListener(View_Atencion.this);
                popupMenu.show();
            }
        });

        btnrango.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getOpino(), atencion_dto.getRespuesta_dto().getVariable_nombre(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public Atencion_DTO getatencion_dto() {
        return atencion_dto;
    }

    public void setatencion_dto(Atencion_DTO atencion_dto) {
        this.atencion_dto = atencion_dto;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Respuesta_DTO respuesta_dto = getatencion_dto().getRespuesta_dto();
        switch (menuItem.getItemId()){
            case R.id.menu_rango_1:
                respuesta_dto.setRespuesta_int(1);
                btnrango.setText(String.valueOf(1));
                break;
            case R.id.menu_rango_2:
                respuesta_dto.setRespuesta_int(2);
                btnrango.setText(String.valueOf(2));
                break;
            case R.id.menu_rango_3:
                respuesta_dto.setRespuesta_int(3);
                btnrango.setText(String.valueOf(3));
                break;
            case R.id.menu_rango_4:
                respuesta_dto.setRespuesta_int(4);
                btnrango.setText(String.valueOf(4));
                break;
            case R.id.menu_rango_5:
                respuesta_dto.setRespuesta_int(5);
                btnrango.setText(String.valueOf(5));
                break;
        }
        return false;
    }
}
