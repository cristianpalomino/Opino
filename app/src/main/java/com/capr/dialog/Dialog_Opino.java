package com.capr.dialog;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 7/08/14.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Dialog_Opino extends AlertDialog {

    public Dialog_Opino(Context context) {
        super(context);
        initDialog();
    }

    public Dialog_Opino(Context context, int theme) {
        super(context, theme);
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_opino,null);
        setView(view);

        TextView txtavisodialog= (TextView)view.findViewById(R.id.txtavisodialogo);
        txtavisodialog.setTypeface(Util_Fonts.setPNALight(getContext()));

        setCancelable(false);
    }
}