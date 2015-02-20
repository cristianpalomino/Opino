package com.capr.views_v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.capr.beans.Presente_DTO;
import com.capr.beans_v2.Child_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;
import com.capr.views.View_Opcion;
import com.capr.views.View_Opino;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/12/14.
 */
public class View_Presente_v2 extends View_Opino implements View_Opcion.OnItemClick {

    private TextView btnpresente;
    private TextView nombre;

    private boolean flag_presente = true;
    private boolean flag_daniado = true;
    private boolean flag_invadido = true;

    private Point p;

    public View_Presente_v2(Context context) {
        super(context, R.layout.view_presente);
    }

    public View_Presente_v2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_presente);
    }

    public View_Presente_v2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_presente);
    }

    public View_Presente_v2(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_presente);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    public void init() {
        nombre = (TextView) getView().findViewById(R.id.nombre_subvariable);
        nombre.setTypeface(Util_Fonts.setPNALight(getContext()));
        nombre.setText(getRespuesta_dto().getVariable_nombre().toUpperCase());

        btnpresente = (TextView) getView().findViewById(R.id.btn_presente);
        btnpresente.setTypeface(Util_Fonts.setPNASemiBold(getContext()));

        getRespuesta_dto().updateChild();

        String r_presente = getRespuesta_dto().getRespuesta();
        String r_daniado = getRespuesta_dto().getRespuestaDaniado();
        String r_invadido = getRespuesta_dto().getRespuestaInvadido();

        Log.e("RESPUESTAS", r_presente + " * " + r_daniado + " * " + r_invadido);

        if (r_presente.equals("1")) {
            btnpresente.setText("Presente");

            if (r_daniado.equals("1")) {
                btnpresente.setText("Dañado");
            }

            if (r_invadido.equals("1")) {
                btnpresente.setText("Invadido");
            }

        }else{
            btnpresente.setText("Opciones");
        }



        /*
        switch (Integer.parseInt(rpta)) {
            case 0:
                btnpresente.setText("Presente");
                break;
            case 1:
                btnpresente.setText("Dañado");
                break;
            case 2:
                btnpresente.setText("Invadido");
                break;
        }
        */

        btnpresente.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                btnpresente.getLocationOnScreen(location);
                p = new Point();
                p.x = location[0];
                p.y = location[1];

                showPopup(p);
            }
        });

        Log.e("DATASOURCE PRESENTE", getRespuesta_dto().getDataSource().toString());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (flag_presente) {
            if (position == 0) {
                flag_presente = false;
                btnpresente.setText("Presente");
                getRespuesta_dto().setRespuestaPop("1");
            }
        } else {
            if (position == 0) {
                flag_presente = true;
                btnpresente.setText("Opciones");
                getRespuesta_dto().setRespuestaPop("0");

                /**
                 * 0 - Child
                 */
                Child_DTO c0 = getRespuesta_dto().getHijos_reales().get(0);
                c0.setRespuesta("1");
                getRespuesta_dto().getHijos_reales().set(0, c0);
            }
        }

        if (flag_daniado) {
            if (position == 1) {
                flag_daniado = false;
                btnpresente.setText("Dañado");

                Child_DTO c0 = getRespuesta_dto().getHijos_reales().get(0);
                c0.setRespuesta("1");
                getRespuesta_dto().getHijos_reales().set(0, c0);


                Log.e("DAÑADO A", getRespuesta_dto().getHijos_reales().get(0).getDataSourceRespuesta().toString());
            }
        } else {
            if (position == 1) {
                flag_daniado = true;
                btnpresente.setText("Presente");

                Child_DTO c0 = getRespuesta_dto().getHijos_reales().get(0);
                c0.setRespuesta("0");
                getRespuesta_dto().getHijos_reales().set(0, c0);

                Child_DTO c1 = getRespuesta_dto().getHijos_reales().get(1);
                c1.setRespuesta("0");
                getRespuesta_dto().getHijos_reales().set(1, c1);

                Log.e("DAÑADO A", getRespuesta_dto().getHijos_reales().get(0).getDataSourceRespuesta().toString());
            }
        }


        if (flag_invadido) {
            if (position == 2) {
                flag_invadido = false;
                btnpresente.setText("Invadido");

                Child_DTO c1 = getRespuesta_dto().getHijos_reales().get(1);
                c1.setRespuesta("1");
                getRespuesta_dto().getHijos_reales().set(1, c1);

                Log.e("DAÑADO A", getRespuesta_dto().getHijos_reales().get(1).getDataSourceRespuesta().toString());
            }
        } else {
            if (position == 2) {
                flag_invadido = true;
                btnpresente.setText("Presente");

                Child_DTO c1 = getRespuesta_dto().getHijos_reales().get(1);
                c1.setRespuesta("0");
                getRespuesta_dto().getHijos_reales().set(1, c1);

                Log.e("DAÑADO A", getRespuesta_dto().getHijos_reales().get(1).getDataSourceRespuesta().toString());
            }
        }
    }

    /**
     * POPUP
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showPopup(final Point p) {
        int popupWidth = btnpresente.getWidth();
        int popupHeight = btnpresente.getHeight() * 4;

        // Inflate the popup_layout.xml
        /*
        LinearLayout viewGroup = (LinearLayout)getOpino().findViewById(R.id.view_presente);
        LayoutInflater layoutInflater = (LayoutInflater)getOpino().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.view_presente,viewGroup);
        */
        View_Opcion view_opcion = new View_Opcion(getOpino());
        view_opcion.setOnItemClick(this);
        view_opcion.init();

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(getOpino());
        popup.setContentView(view_opcion);
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
}
