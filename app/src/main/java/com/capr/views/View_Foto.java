package com.capr.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capr.beans.Foto_DTO;
import com.capr.beans.Imagen_DTO;
import com.capr.beans.Respuesta_DTO;
import com.capr.dialog.Dialog_Foto;
import com.capr.opino.R;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

import org.json.JSONObject;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Foto extends View_Opino implements PopupMenu.OnMenuItemClickListener {

    private Imagen_DTO imagen_dto;
    private Foto_DTO foto_dto;

    public View_Foto(Context context) {
        super(context,R.layout.view_foto);
    }

    public View_Foto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr,R.layout.view_foto);
    }

    public View_Foto(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes,R.layout.view_foto);
    }

    public View_Foto(Context context, AttributeSet attrs) {
        super(context, attrs,R.layout.view_foto);
    }

    @Override
    protected void initView() {
        super.initView();

        ImageButton imageButton = (ImageButton)getView().findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getOpino(),view);
                popupMenu.inflate(R.menu.menu_foto);
                popupMenu.setOnMenuItemClickListener(View_Foto.this);
                popupMenu.show();
            }
        });

        imageButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getOpino(),foto_dto.getRespuesta_dto().getVariable_nombre(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_tomar_foto:
                getOpino().dispatchTakePictureIntent(View_Foto.this);
                break;
            case R.id.menu_ver_foto:
                if(getImagen_dto() != null){
                    Dialog_Foto dialog_foto = new Dialog_Foto(getOpino(),getImagen_dto());
                    dialog_foto.show();
                    getFoto_dto().getRespuesta_dto().setRespuesta_string(getImagen_dto().toString());
                }
                break;
        }
        return false;
    }

    public void setImagen_dto(Imagen_DTO imagen_dto) {
        this.imagen_dto = imagen_dto;
    }

    public Imagen_DTO getImagen_dto() {
        return imagen_dto;
    }

    public void setFoto_dto(Foto_DTO foto_dto) {
        this.foto_dto = foto_dto;
    }

    public Foto_DTO getFoto_dto() {
        return foto_dto;
    }
}
