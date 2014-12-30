package com.capr.views_v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.capr.opino.R;
import com.capr.utils.Util_Fonts;
import com.capr.views.View_Opcion_Directo;
import com.capr.views.View_Opcion_Rango;
import com.capr.views.View_Opino;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Directa extends View_Opino implements View_Opcion_Directo.OnItemClick {

    private TextView btnrango;
    private TextView nombre;
    private PopupWindow popup ;
    private Point p;

    public View_Directa(Context context) {
        super(context, R.layout.view_directo);
    }

    public View_Directa(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_directo);
    }

    public View_Directa(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_directo);
    }

    public View_Directa(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_directo);
    }


    @Override
    protected void initView() {
        super.initView();
    }

    public void init(){
        nombre = (TextView)getView().findViewById(R.id.nombre_subvariable);
        nombre.setTypeface(Util_Fonts.setPNALight(getOpino()));
        nombre.setText(getRespuesta_dto().getVariable_nombre());
        nombre.setVisibility(GONE);

        btnrango = (TextView) getView().findViewById(R.id.btnrango);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        btnrango.setLayoutParams(params);
        btnrango.setTypeface(Util_Fonts.setPNASemiBold(getContext()));

        String rpta = getRespuesta_dto().getRespuesta();
        switch (Integer.parseInt(rpta)){
            case 0: btnrango.setText("Indirecta");
                break;
            case 1: btnrango.setText("Mixta");
                break;
            case 2: btnrango.setText("Directa");
                break;
        }

        btnrango.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                btnrango.getLocationOnScreen(location);
                p = new Point();
                p.x = location[0];
                p.y = location[1];

                showPopup(p);
            }
        });
    }

    /**
     * POPUP
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showPopup(final Point p) {
        int popupWidth = btnrango.getWidth();
        int popupHeight = btnrango.getHeight() * 3;

        // Inflate the popup_layout.xml
        /*
        LinearLayout viewGroup = (LinearLayout)getOpino().findViewById(R.id.view_presente);
        LayoutInflater layoutInflater = (LayoutInflater)getOpino().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.view_presente,viewGroup);
        */
        View_Opcion_Directo view_opcion_directo = new View_Opcion_Directo(getOpino());
        view_opcion_directo.setOnItemClick(this);
        view_opcion_directo.init();

        // Creating the PopupWindow
        popup = new PopupWindow(getOpino());
        popup.setContentView(view_opcion_directo);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(getView(), Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        popup.dismiss();
        switch (position){
            case 0: getRespuesta_dto().setRespuestaCalidad("0");
                btnrango.setText("Indirecta");
                break;
            case 1: getRespuesta_dto().setRespuestaCalidad("1");
                btnrango.setText("Mixta");
                break;
            case 2: getRespuesta_dto().setRespuestaCalidad("2");
                btnrango.setText("Directa");
                break;
        }
    }
}
