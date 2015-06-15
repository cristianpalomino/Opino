package com.capr.views_v2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.capr.beans.Precio_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;
import com.capr.views.View_Opino;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Precio_v2 extends View_Opino implements TextWatcher {

    private EditText edtcomentario;
    private TextView nombre;
    private String current = "";

    public View_Precio_v2(Context context) {
        super(context, R.layout.view_precio);
    }

    public View_Precio_v2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_precio);
    }

    public View_Precio_v2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_precio);
    }

    public View_Precio_v2(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_precio);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    public void init() {
        nombre = (TextView) getView().findViewById(R.id.nombre_subvariable);
        nombre.setTypeface(Util_Fonts.setPNALight(getOpino()));
        nombre.setText(getRespuesta_dto().getVariable_nombre());

        edtcomentario = (EditText) getView().findViewById(R.id.edtcomentario);
        edtcomentario.setTypeface(Util_Fonts.setPNALight(getOpino()));
        edtcomentario.addTextChangedListener(this);

        if (!getRespuesta_dto().getRespuesta().equals("0")) {
            edtcomentario.setText(getRespuesta_dto().getRespuesta());
        }
    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * are about to be replaced by new text with length <code>after</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * have just replaced old text that had length <code>before</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edtcomentario.removeTextChangedListener(this);
        String clean = s.toString().replace(",", "").trim();
        String string = "0";
        if (!clean.toString().equals(current)) {
            try {
                int your_integer = Integer.parseInt(clean.toString());
                string = String.valueOf(your_integer);
                if (String.valueOf(your_integer).trim().length() > 2) {
                    string = String.format("%6.2f", your_integer / 100.0);
                }
            } catch (Exception e) {} finally {
                current = string;
                edtcomentario.setText(string);
                edtcomentario.setSelection(string.length());
                edtcomentario.addTextChangedListener(this);
            }
        }

        getRespuesta_dto().setRespuesta(edtcomentario.getText().toString());
    }

    /**
     * This method is called to notify you that, somewhere within
     * <code>s</code>, the text has been changed.
     * It is legitimate to make further changes to <code>s</code> from
     * this callback, but be careful not to get yourself into an infinite
     * loop, because any changes you make will cause this method to be
     * called again recursively.
     * (You are not told where the change took place because other
     * afterTextChanged() methods may already have made other changes
     * and invalidated the offsets.  But if you need to know here,
     * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
     * to mark your place and then look up from here where the span
     * ended up.
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {

    }
}
