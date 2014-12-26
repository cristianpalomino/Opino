package com.capr.views;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.capr.beans.Presente_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 21/12/14.
 */
public class View_Presente extends View_Opino {

    private Presente_DTO presente_dto;
    private TextView btnpresente;
    private TextView nombre;

    private Point p;

    public View_Presente(Context context) {
        super(context, R.layout.view_presente);
    }

    public View_Presente(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_presente);
    }

    public View_Presente(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_presente);
    }

    public View_Presente(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_presente);
    }

    @Override
    protected void initView() {
        super.initView();

        nombre = (TextView) getView().findViewById(R.id.nombre_subvariable);
        nombre.setTypeface(Util_Fonts.setPNALight(getContext()));

        btnpresente = (TextView) getView().findViewById(R.id.btn_presente);
        btnpresente.setTypeface(Util_Fonts.setPNASemiBold(getContext()));
        btnpresente.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p != null)
                showPopup(p);
            }
        });

    }

    public Presente_DTO getPresente_dto() {
        return presente_dto;
    }

    public void setPresente_dto(Presente_DTO presente_dto) {
        this.presente_dto = presente_dto;
        nombre.setText(presente_dto.getRespuesta_dto().getVariable_nombre());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int[] location = new int[2];
        btnpresente.getLocationOnScreen(location);
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    /**
     * POPUP
     */
    private void showPopup(final Point p) {
        int popupWidth = 150;
        int popupHeight = 150;

        // Inflate the popup_layout.xml
        /*
        LinearLayout viewGroup = (LinearLayout)getOpino().findViewById(R.id.view_presente);
        LayoutInflater layoutInflater = (LayoutInflater)getOpino().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.view_presente,viewGroup);
        */
        View_Opcion view_opcion = new View_Opcion(getOpino());

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(getOpino());
        popup.setContentView(view_opcion);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(getView(), Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
    }
}
