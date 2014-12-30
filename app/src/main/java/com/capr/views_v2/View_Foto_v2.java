package com.capr.views_v2;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.capr.beans.Imagen_DTO;
import com.capr.beans_v2.Encuesta_DTO;
import com.capr.crud_v2.Encuesta_CRUD;
import com.capr.dialog.Dialog_Foto;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.utils.Util_Fonts;
import com.capr.views.View_Opino;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Foto_v2 extends View_Opino implements PopupMenu.OnMenuItemClickListener {

    private Imagen_DTO imagen_dto;
    private TextView nombre;
    private Encuesta_DTO encuesta_dto;

    public View_Foto_v2(Context context) {
        super(context, R.layout.view_foto);
    }

    public View_Foto_v2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_foto);
    }

    public View_Foto_v2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_foto);
    }

    public View_Foto_v2(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_foto);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    public void init() {
        nombre = (TextView) getView().findViewById(R.id.nombre_subvariable);
        nombre.setTypeface(Util_Fonts.setPNALight(getOpino()));
        nombre.setText(getRespuesta_dto().getVariable_nombre());

        ImageButton imageButton = (ImageButton) getView().findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getOpino(), view);
                popupMenu.inflate(R.menu.menu_foto);
                popupMenu.setOnMenuItemClickListener(View_Foto_v2.this);
                popupMenu.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_tomar_foto:
                getOpino().dispatchTakePictureIntent(View_Foto_v2.this);
                break;
            case R.id.menu_ver_foto:
                if (getImagen_dto() != null) {
                    Dialog_Foto dialog_foto = new Dialog_Foto(getOpino(), getImagen_dto());
                    dialog_foto.show();
                }
                break;
        }
        return false;
    }

    public void setImagen_dto(Imagen_DTO imagen_dto) {
        this.imagen_dto = imagen_dto;
        getEncuesta_dto().set_uri(imagen_dto.getImagenData());
        Encuesta_CRUD encuesta_crud = new Encuesta_CRUD(getContext());
        encuesta_crud.updatePhotoEncuesta(encuesta_dto);
    }

    public Imagen_DTO getImagen_dto() {
        Session_Manager session_manager = new Session_Manager(getContext());
        if(session_manager.getMode()){
            return imagen_dto;
        }else{
            Encuesta_CRUD encuesta_crud = new Encuesta_CRUD(getContext());
            Encuesta_DTO encuestadb = encuesta_crud.getEncuesta(encuesta_dto);

            Imagen_DTO mimagen = new Imagen_DTO(encuestadb.get_uri(),encuestadb.get_uri());
            return mimagen;
        }
    }

    public void setEncuesta_dto(Encuesta_DTO encuesta_dto) {
        this.encuesta_dto = encuesta_dto;
    }

    public Encuesta_DTO getEncuesta_dto() {
        return encuesta_dto;
    }
}
