package com.capr.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.capr.adapter.Adapter_Locales_v2;
import com.capr.beans.Local_DTO;
import com.capr.beans.Variable_DTO;
import com.capr.dialog.Dialog_Opino;
import com.capr.dialog.Dialog_Opino_Aviso;
import com.capr.modulos.Modulo_Off_Line;
import com.capr.opino.R;
import com.capr.service.Local_Service;
import com.capr.session.Session_Manager;
import com.capr.utils.Connectivity;
import com.capr.utils.Util_Fonts;
import com.capr.ws.Opino_WS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.shamanland.fab.FloatingActionButton;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 18/10/14.
 */
public class Fragment_Locales extends Fragment_Opino implements AdapterView.OnItemClickListener, Local_Service.Interface_Service_Locales, Dialog_Opino_Aviso.I_Aviso, CompoundButton.OnCheckedChangeListener {

    protected ListView lista_locales;
    private FloatingActionButton flatbutton;
    private ToggleButton modo;

    public static final Fragment_Locales newInstance() {
        return new Fragment_Locales();
    }

    public Fragment_Locales() {
        super(R.layout.fragment_locales, R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getOpino().getSupportActionBar().setIcon(R.drawable.logo_opino);
        getOpino().getSupportActionBar().setTitle("Locales");
    }

    @Override
    protected void initView() {
        super.initView();

        lista_locales = (ListView) getView().findViewById(R.id.lista_locales);
        lista_locales.setOnItemClickListener(this);

        /**
         * Flatbutton
         */
        flatbutton = (FloatingActionButton) getView().findViewById(R.id.flatbutton);
        flatbutton.setVisibility(View.GONE);
        flatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Connectivity.isConnected(getOpino())) {
                    Modulo_Off_Line modulo_offLine = new Modulo_Off_Line(getOpino());
                    modulo_offLine.initModuloOffLine(Fragment_Locales.this);
                } else {
                    Toast.makeText(getOpino(), "Conectese a Internet", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (Connectivity.isConnected(getOpino())) {
            TextView aviso = (TextView) getView().findViewById(android.R.id.empty);
            aviso.setText(getString(R.string.aviso_lista));
            aviso.setTypeface(Util_Fonts.setPNASemiBold(getOpino()));
            lista_locales.setEmptyView(aviso);
            initRequest();
            getOpino().setOnline(true);
        } else {
            /**
             * Save Local to Db
             */
            Local_Service local_service = new Local_Service(getOpino(), true);
            local_service.setInterface_service_locales(Fragment_Locales.this);
            local_service.getLocales();
        }
    }

    @Override
    public void onFinish(boolean state, ArrayList<Local_DTO> local_dtos) {
        if (state) {
            /**
             * Get Locales from Db
             */
            Adapter_Locales_v2 adapter_locales = new Adapter_Locales_v2(getOpino(), local_dtos);
            lista_locales.setAdapter(adapter_locales);
        }
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Local_DTO local_dto = (Local_DTO) parent.getItemAtPosition(position);

        getOpino().setLocal_dto(local_dto);
        getOpino().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.izquierda_derecha_b, R.animator.izquierda_derecha_b).add(R.id.container, Fragment_Variables.newInstance(), Fragment_Variables.class.getName()).addToBackStack(null).commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_usuario, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        modo = (ToggleButton) menu.findItem(R.id.action_modo).getActionView();
        modo.setOnCheckedChangeListener(this);

        if (!Connectivity.isConnected(getOpino())) {
            modo.setChecked(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Session_Manager session_manager = new Session_Manager(getOpino());
            session_manager.cerrarSession();
            getOpino().clearHistory();

            if (session_manager.isLogin()) {
                getOpino().getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Locales.newInstance()).commit();
            } else {
                getOpino().getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Login.newInstance()).commit();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void initRequest() {
        final Dialog_Opino dialog_opino = showDialog();
        dialog_opino.setText("Obteniendo Locales...!");
        dialog_opino.show();

        Session_Manager session_manager = new Session_Manager(getOpino());
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Token", session_manager.getSession().getUsuario_token());
        asyncHttpClient.get(getOpino(), Opino_WS.WS_OBTENER_LOCALES, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(Fragment_Locales.class.getName(), response.toString());

                ArrayList<Local_DTO> local_dtos = new ArrayList<Local_DTO>();
                try {
                    JSONArray array_locales = response.getJSONArray("result");
                    for (int i = 0; i < array_locales.length(); i++) {
                        JSONObject local_json = array_locales.getJSONObject(i);
                        Local_DTO local_dto = new Local_DTO();
                        local_dto.setLocal_json(local_json);

                        /**
                         * Save variables
                         */
                        String id_local = local_dto.getLocal_json().getString("id");

                        ArrayList<Variable_DTO> variable_dtos = new ArrayList<Variable_DTO>();
                        variable_dtos.add(new Variable_DTO(id_local,"sku",false, "Sku"));
                        variable_dtos.add(new Variable_DTO(id_local,"afiche", false, "Afiche"));
                        variable_dtos.add(new Variable_DTO(id_local,"promocion",true, "Promoción"));
                        variable_dtos.add(new Variable_DTO(id_local,"calidad", false, "Calidad"));
                        local_dto.setVariable_dtos(variable_dtos,getOpino(),false);

                        local_dtos.add(local_dto);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /**
                 * Save Local to Db
                 */
                Local_Service local_service = new Local_Service(getOpino(), true);
                local_service.setInterface_service_locales(Fragment_Locales.this);
                local_service.addLocales(local_dtos);

                dialog_opino.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dialog_opino.dismiss();
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(Fragment_Locales.class.getName(), responseString);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog_opino.dismiss();
            }
        });
    }

    @Override
    public void onAvisoResult(boolean accept, Dialog_Opino_Aviso dialog_opino_aviso) {
        if(accept){
            lista_locales.setAdapter(null);
            Toast.makeText(getOpino(),"Modo ON-LINE", Toast.LENGTH_SHORT).show();
            flatbutton.setVisibility(View.GONE);
            modo.setChecked(false);
            getOpino().setOnline(true);
            initRequest();
        }else{
            modo.setOnCheckedChangeListener(null);
            modo.setChecked(true);
            getOpino().setOnline(false);
            modo.setOnCheckedChangeListener(Fragment_Locales.this);
        }
        dialog_opino_aviso.hide();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            //lista_locales.setAdapter(null);

            modo.setOnCheckedChangeListener(null);
            modo.setChecked(true);
            getOpino().setOnline(false);
            modo.setOnCheckedChangeListener(Fragment_Locales.this);

            Toast.makeText(getOpino(), "Modo OFF-LINE", Toast.LENGTH_SHORT).show();
            flatbutton.setVisibility(View.VISIBLE);

            Local_Service local_service = new Local_Service(getOpino(), true);
            local_service.setInterface_service_locales(Fragment_Locales.this);
            local_service.getLocales();
        } else {
            Dialog_Opino_Aviso dialog_opino_aviso = new Dialog_Opino_Aviso(getOpino());
            dialog_opino_aviso.setI_aviso(Fragment_Locales.this);
            dialog_opino_aviso.show();
        }
    }
}


        /*
        Bitmap bitmap = BitmapFactory.decodeResource(getOpino().getResources(),R.drawable.ic_launcher);
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                for (int i = 0; i < palette.getSwatches().size(); i++) {
                    Palette.Swatch swatch = palette.getSwatches().get(i);
                    Log.e("SWATH", "" + swatch);
                }
            }
        });
        */