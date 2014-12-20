package com.capr.dialog;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.capr.fragments.Fragment_Locales;
import com.capr.opino.Opino;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;
import com.shamanland.fab.FloatingActionButton;

/**
 * Created by Gantz on 7/08/14.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Dialog_Opino_Aviso extends AlertDialog implements View.OnClickListener{

    private TextView txtavisodialog;
    private Button btncancelar;
    private Button btnaceptar;
    private I_Aviso i_aviso;

    public Dialog_Opino_Aviso(Context context) {
        super(context);
        initDialog();
    }

    public Dialog_Opino_Aviso(Context context, int theme) {
        super(context, theme);
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_opino_aviso, null);
        setView(view);

        txtavisodialog = (TextView) view.findViewById(R.id.txtavisodialogo);
        txtavisodialog.setTypeface(Util_Fonts.setPNALight(getContext()));

        btnaceptar = (Button)view.findViewById(R.id.btn_aceptar);
        btncancelar = (Button)view.findViewById(R.id.btn_cancelar);

        btnaceptar.setOnClickListener(this);
        btncancelar.setOnClickListener(this);

        btnaceptar.setTypeface(Util_Fonts.setPNASemiBold(getContext()));
        btncancelar.setTypeface(Util_Fonts.setPNASemiBold(getContext()));

        setCancelable(false);
    }

    public void setText(String title) {
        txtavisodialog.setText(title);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnaceptar)){
            i_aviso.onAvisoResult(true,Dialog_Opino_Aviso.this);
        }else{
            i_aviso.onAvisoResult(false,Dialog_Opino_Aviso.this);
        }
    }

    public void setI_aviso(I_Aviso i_aviso) {
        this.i_aviso = i_aviso;
    }

    public I_Aviso getI_aviso() {
        return i_aviso;
    }

    public interface I_Aviso{
        void onAvisoResult(boolean accept,Dialog_Opino_Aviso dialog_opino_aviso);
    }
}