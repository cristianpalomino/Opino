package com.capr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.capr.dialog.Dialog_Opino;
import com.capr.opino.Opino;
import com.capr.opino.R;

/**
 * Created by Gantz on 18/10/14.
 */
public class Fragment_Opino extends Fragment {

    private int id_container;
    private int id_layout;
    private Dialog_Opino dialog_opino;

    private Opino opino;

    public Fragment_Opino(int id_layout,int id_container) {
        this.id_container = id_container;
        this.id_layout = id_layout;
    }

    protected void initView() {
    }

    protected void initActionBar() {
    }

    protected void onBackPressed(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View localView = inflater.inflate(this.id_layout, viewGroup, false);
        localView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        return localView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        onBackPressed();
    }

    public Opino getOpino() {
        return (Opino) getActivity();
    }

    public Dialog_Opino showDialog(){
        dialog_opino = new Dialog_Opino(getOpino());
        dialog_opino.getWindow().setWindowAnimations(R.style.Dialog_Animation_UP_DOWN);

        return dialog_opino;
    }

    public void hideDialog(){
        dialog_opino.hide();
    }
}
