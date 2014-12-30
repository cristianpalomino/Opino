package com.capr.interfaces;

import android.content.Context;

import com.capr.beans.Foto_DTO;
import com.capr.beans.Imagen_DTO;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Gantz on 3/11/14.
 */
public interface Interface_Upload_Image {

    public void uploadImages(Context context,ArrayList<Imagen_DTO> imagen_dtos);
}
