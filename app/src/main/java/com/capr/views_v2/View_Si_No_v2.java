package com.capr.views_v2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.capr.beans.Si_No_DTO;
import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Respuesta_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;
import com.capr.views.View_Opino;
import com.capr.views.View_Si_No;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Si_No_v2 extends View_Opino implements CompoundButton.OnCheckedChangeListener {

    private TextView nombre_variable;
    private LinearLayout padre;
    private ToggleButton switch_si_no;

    public View_Si_No_v2(Context context) {
        super(context, R.layout.view_si_no);
    }

    public View_Si_No_v2(Context context, AttributeSet attrs, int id_layout) {
        super(context, attrs, R.layout.view_si_no);
    }

    public View_Si_No_v2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_si_no);
    }

    public LinearLayout getPadre() {
        return padre;
    }

    public void setPadre(LinearLayout padre) {
        this.padre = padre;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    public void init() {
        nombre_variable = (TextView) getView().findViewById(R.id.nombre_subvariable);
        nombre_variable.setTypeface(Util_Fonts.setPNALight(getContext()));
        nombre_variable.setText(getRespuesta_dto().getVariable_nombre().toUpperCase());

        switch_si_no = (ToggleButton) getView().findViewById(R.id.switch_si_no);
        String rpta = getRespuesta_dto().getRespuesta();
        if (rpta.equals("1")) {
            switch_si_no.setChecked(true);
        } else if (rpta.equals("0")) {
            switch_si_no.setChecked(false);
        }
        switch_si_no.setOnCheckedChangeListener(this);

        if (getRespuesta_dto().getVariable_nombre().equals("EXHIBIDO")) {
            View_Si_No_v2.this.setVisibility(GONE);
            getRespuesta_dto().setRespuesta("0");
            switch_si_no.setChecked(false);
        }
    }

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            ((TextView) getView().findViewById(R.id.txtsino)).setText("Si");
            getRespuesta_dto().setRespuesta("1");
        } else {
            ((TextView) getView().findViewById(R.id.txtsino)).setText("No");
            getRespuesta_dto().setRespuesta("0");
        }


        if (getRespuesta_dto().getVariable_nombre().equals("STOCK")) {
            if (getRespuesta_dto().getRespuesta().equals("0")) {
                for (int i = 0; i < getPadre().getChildCount(); i++) {
                    View v = getPadre().getChildAt(i);
                    if (v instanceof View_Si_No_v2) {
                        String name = ((View_Si_No_v2) v).getRespuesta_dto().getVariable_nombre();
                        if (name.equals("EXHIBIDO")) {
                            v.setVisibility(GONE);
                            getRespuesta_dto().setRespuesta("0");
                            switch_si_no.setChecked(false);
                        }
                    }
                }
            } else {
                for (int i = 0; i < getPadre().getChildCount(); i++) {
                    View v = getPadre().getChildAt(i);
                    if (v instanceof View_Si_No_v2) {
                        String name = ((View_Si_No_v2) v).getRespuesta_dto().getVariable_nombre();
                        if (name.equals("EXHIBIDO")) {
                            v.setVisibility(VISIBLE);
                        }
                    }
                }
            }
        }
    }
}
