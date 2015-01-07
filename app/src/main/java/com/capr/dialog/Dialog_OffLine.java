package com.capr.dialog;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 7/08/14.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Dialog_OffLine extends AlertDialog {

    private TextView txtavisodialog;
    private TextView progreso;
    private ProgressBar progressBar;

    public Dialog_OffLine(Context context) {
        super(context);
        initDialog();
    }

    public Dialog_OffLine(Context context, int theme) {
        super(context, theme);
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_downloader,null);
        setView(view);

        txtavisodialog = (TextView)view.findViewById(R.id.txtavisodialogo);
        txtavisodialog.setTypeface(Util_Fonts.setPNALight(getContext()));
        progreso = (TextView)view.findViewById(R.id.progreso);
        progreso.setTypeface(Util_Fonts.setPNALight(getContext()));
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        setCancelable(false);
    }

    public void setText(String title){
        txtavisodialog.setText(title);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public TextView getProgreso() {
        return progreso;
    }
}