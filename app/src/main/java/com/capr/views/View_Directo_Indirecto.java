package com.capr.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.capr.beans.Directo_Indirecto_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Directo_Indirecto extends View_Opino implements CompoundButton.OnCheckedChangeListener {


    public View_Directo_Indirecto(Context context) {
        super(context, R.layout.view_si_no);
    }

    public View_Directo_Indirecto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_si_no);
    }

    public View_Directo_Indirecto(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_si_no);
    }

    public View_Directo_Indirecto(Context context, AttributeSet attrs) {
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
                //Toast.makeText(getOpino(), directo_indirecto_dto.getRespuesta_dto().getVariable_nombre(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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
        } else {
            ((TextView)getView().findViewById(R.id.txtsino)).setText("No");
        }
    }
}
