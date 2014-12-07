package com.capr.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.capr.beans.Comentario_DTO;
import com.capr.beans.Sub_Comentario_DTO;
import com.capr.opino.R;
import com.capr.utils.Util_Fonts;

/**
 * Created by Gantz on 21/10/14.
 */
public class View_Sub_Comentario extends View_Opino implements TextWatcher {

    private Sub_Comentario_DTO sub_comentario_dto;
    private EditText edtsubcomentario;


    public View_Sub_Comentario(Context context) {
        super(context, R.layout.view_sub_comentario);
    }

    public View_Sub_Comentario(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.view_sub_comentario);
    }

    public View_Sub_Comentario(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, R.layout.view_sub_comentario);
    }

    public View_Sub_Comentario(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.view_sub_comentario);
    }


    @Override
    protected void initView() {
        super.initView();
        edtsubcomentario = (EditText) getView().findViewById(R.id.edtsubcomentario);
        edtsubcomentario.setTypeface(Util_Fonts.setPNALight(getOpino()));
        edtsubcomentario.addTextChangedListener(this);

        edtsubcomentario.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getOpino(), sub_comentario_dto.getRespuesta_dto().getVariable_nombre(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public Sub_Comentario_DTO getSub_comentario_dto() {
        return sub_comentario_dto;
    }

    public void setSub_comentario_dto(Sub_Comentario_DTO sub_comentario_dto) {
        this.sub_comentario_dto = sub_comentario_dto;
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
        getSub_comentario_dto().getRespuesta_dto().setRespuesta_string(edtsubcomentario.getText().toString());
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
