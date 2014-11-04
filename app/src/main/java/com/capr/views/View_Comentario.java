package com.capr.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.capr.beans.Comentario_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Comentario extends View_Opino implements TextWatcher {

    private Comentario_DTO comentario_dto;
    private EditText edtcomentario;


    public View_Comentario(Context context) {
        super(context, R.layout.view_comentario);
    }

    public View_Comentario(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_comentario);
    }

    public View_Comentario(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_comentario);
    }

    public View_Comentario(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_comentario);
    }


    @Override
    protected void initView() {
        super.initView();
        edtcomentario = (EditText) getView().findViewById(R.id.edtcomentario);
        edtcomentario.setTypeface(Util_Fonts.setPNALight(getOpino()));
        edtcomentario.addTextChangedListener(this);
    }

    public Comentario_DTO getComentario_dto() {
        return comentario_dto;
    }

    public void setComentario_dto(Comentario_DTO comentario_dto) {
        this.comentario_dto = comentario_dto;
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
        getComentario_dto().getRespuesta_dto().setRespuesta_string(edtcomentario.getText().toString());
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
