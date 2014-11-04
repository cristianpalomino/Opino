package com.capr.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.capr.beans.Imagen_DTO;
import com.capr.opino.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Gantz on 22/10/14.
 */
public class Dialog_Foto extends AlertDialog {

    private Imagen_DTO imagen_dto;

    public Dialog_Foto(Context context, Imagen_DTO imagen_dto) {
        super(context);
        this.imagen_dto = imagen_dto;
        initDialog();
    }

    /**
     * Construct an AlertDialog that uses an explicit theme.  The actual style
     * that an AlertDialog uses is a private implementation, however you can
     * here supply either the name of an attribute in the theme from which
     * to get the dialog's style (such as {@link android.R.attr#alertDialogTheme}
     * or one of the constants {@link #THEME_TRADITIONAL},
     * {@link #THEME_HOLO_DARK}, or {@link #THEME_HOLO_LIGHT}.
     *
     * @param context
     * @param theme
     */
    public Dialog_Foto(Context context, int theme, Imagen_DTO imagen_dto) {
        super(context, theme);
        this.imagen_dto = imagen_dto;
        initDialog();
    }

    public Dialog_Foto(Context context, boolean cancelable, OnCancelListener cancelListener, Imagen_DTO imagen_dto) {
        super(context, cancelable, cancelListener);
        this.imagen_dto = imagen_dto;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_foto, null);
        setView(view);

        ImageView imgfoto = (ImageView)view.findViewById(R.id.imgfoto);
        Picasso.with(getContext()).load("file://" + Uri.parse(imagen_dto.getImagenData())).into(imgfoto);
    }
}
