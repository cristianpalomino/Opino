package com.capr.actividades;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.widget.Toast;

import com.capr.application.Opino_Application;
import com.capr.beans.Imagen_DTO;
import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.opino.R;
import com.capr.session.Session_Manager;
import com.capr.utils.Connectivity;
import com.capr.views_v2.View_Foto_v2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gantz on 30/12/14.
 */
public class Opino extends ActionBarActivity {

    public static final String END_SESSION_TIME = "14:47:00";

    private Opino_Application opino_application;
    protected Imagen_DTO imagen_dto;
    protected View_Foto_v2 view_foto;

    static final int REQUEST_TAKE_PHOTO = 11111;
    private final static String CAPTURED_PHOTO_PATH_KEY = "mCurrentPhotoPath";
    private final static String CAPTURED_PHOTO_URI_KEY = "mCapturedImageURI";
    private String mCurrentPhotoPath = null;
    private Uri mCapturedImageURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opino_application = (Opino_Application) getApplication();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,getCurrentTime(),Toast.LENGTH_SHORT).show();
        if(getCurrentTime().equals(END_SESSION_TIME)){
            Session_Manager session_manager = new Session_Manager(this);
            session_manager.cerrarSessionv2();
            session_manager.setMode(true);

            Intent intent = new Intent(this, Entrar.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    public String getCurrentTime(){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        return today.format("%k:%M:%S");
    }

    /**
     * Start the camera by dispatching a camera intent.
     */
    public void dispatchTakePictureIntent(View_Foto_v2 view_foto) {
        PackageManager packageManager = getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false) {
            Toast.makeText(Opino.this, "This device does not have a camera.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Camera exists? Then proceed...
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go.
            // If you don't do this, you may get a crash in some devices.
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast toast = Toast.makeText(Opino.this, "There was a problem saving the photo...", Toast.LENGTH_SHORT);
                toast.show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri fileUri = Uri.fromFile(photoFile);
                setCapturedImageURI(fileUri);
                setCurrentPhotoPath(fileUri.getPath());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, getCapturedImageURI());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                this.view_foto = view_foto;
            }
        }
    }

    /**
     * The activity returns with the photo.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            addPhotoToGallery();

            Imagen_DTO imagen_dto = new Imagen_DTO(Opino.this);
            imagen_dto.setImagenData(getCurrentPhotoPath());
            imagen_dto.setImagenLocal(getLocal_dto().getId());
            view_foto.setImagen_dto(imagen_dto);
            view_foto.getImageButton().setBackgroundColor(getResources().getColor(R.color.verde_claro_emerald));

            Toast.makeText(Opino.this, "Imagen Guardada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Opino.this, "Image Capture Failed", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creates the image file to which the image must be saved.
     *
     * @return
     * @throws IOException
     */
    protected File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        setCurrentPhotoPath("file:" + image.getAbsolutePath());
        return image;
    }

    /**
     * Add the picture to the photo gallery.
     * Must be called on all camera images or they will
     * disappear once taken.
     */
    protected void addPhotoToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(getCurrentPhotoPath());
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (mCurrentPhotoPath != null) {
            savedInstanceState.putString(CAPTURED_PHOTO_PATH_KEY, mCurrentPhotoPath);
        }
        if (mCapturedImageURI != null) {
            savedInstanceState.putString(CAPTURED_PHOTO_URI_KEY, mCapturedImageURI.toString());
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(CAPTURED_PHOTO_PATH_KEY)) {
            mCurrentPhotoPath = savedInstanceState.getString(CAPTURED_PHOTO_PATH_KEY);
        }
        if (savedInstanceState.containsKey(CAPTURED_PHOTO_URI_KEY)) {
            mCapturedImageURI = Uri.parse(savedInstanceState.getString(CAPTURED_PHOTO_URI_KEY));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    public boolean isOnline(){
        return new Connectivity().isConnected(this);
    }

    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * Getters and setters.
     */

    public String getCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    public void setCurrentPhotoPath(String mCurrentPhotoPath) {
        this.mCurrentPhotoPath = mCurrentPhotoPath;
    }

    public Uri getCapturedImageURI() {
        return mCapturedImageURI;
    }

    public void setCapturedImageURI(Uri mCapturedImageURI) {
        this.mCapturedImageURI = mCapturedImageURI;
    }

    public Encuesta_DTO getEncuesta_dto() {
        return opino_application.getEncuesta_dto();
    }

    public Variable_DTO getVariable_dto() {
        return opino_application.getVariable_dto();
    }

    public Local_DTO getLocal_dto() {
        return opino_application.getLocal_dto();
    }

    public void setLocal_dto(Local_DTO local_dto) {
        opino_application.setLocal_dto(local_dto);
    }

    public void setVariable_dto(Variable_DTO variable_dto) {
        opino_application.setVariable_dto(variable_dto);
    }

    public void setEncuesta_dto(Encuesta_DTO encuesta_dto) {
        opino_application.setEncuesta_dto(encuesta_dto);
    }

    public ArrayList<Encuesta_DTO> getEncuesta_dtos() {
        return opino_application.getEncuesta_dtos();
    }

    public void setEncuesta_dtos(ArrayList<Encuesta_DTO> encuesta_dtos) {
        opino_application.setEncuesta_dtos(encuesta_dtos);
    }

    public Opino_Application getOpino_application() {
        return opino_application;
    }
}
