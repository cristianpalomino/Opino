package com.capr.views;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.capr.beans.Imagen_DTO;
import com.capr.beans.Si_No_DTO;
import com.capr.dialog.Dialog_Foto;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Si_No extends View_Opino implements CompoundButton.OnCheckedChangeListener {

    private Si_No_DTO si_no_dto;


    public View_Si_No(Context context) {
        super(context, R.layout.view_si_no);
    }

    public View_Si_No(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_si_no);
    }

    public View_Si_No(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_si_no);
    }

    public View_Si_No(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_si_no);
    }


    @Override
    protected void initView() {
        super.initView();

        TextView sino = (TextView)getView().findViewById(R.id.txtsino);
        sino.setTypeface(Util_Fonts.setPNALight(getContext()));

        final ToggleButton switch_si_no = (ToggleButton) getView().findViewById(R.id.switch_si_no);
        switch_si_no.setOnCheckedChangeListener(this);

        switch_si_no.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getOpino(),si_no_dto.getRespuesta_dto().getVariable_nombre(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public Si_No_DTO getSi_no_dto() {
        return si_no_dto;
    }

    public void setSi_no_dto(Si_No_DTO si_no_dto,int visibility) {
        this.si_no_dto = si_no_dto;
        TextView nombre = (TextView)getView().findViewById(R.id.nombre_subvariable);
        nombre.setVisibility(visibility);
        nombre.setTypeface(Util_Fonts.setPNALight(getOpino()));
        nombre.setText(si_no_dto.getRespuesta_dto().getVariable_nombre());
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
            ((TextView)getView().findViewById(R.id.txtsino)).setText("Si");
            getSi_no_dto().getRespuesta_dto().setRespuesta_string("1");
        } else {
            ((TextView)getView().findViewById(R.id.txtsino)).setText("No");
            getSi_no_dto().getRespuesta_dto().setRespuesta_string("0");
        }
    }
}
