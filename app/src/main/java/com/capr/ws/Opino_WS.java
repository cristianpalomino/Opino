package com.capr.ws;

/**
 * Created by Gantz on 19/08/14.
 */
public class Opino_WS {

    /*
       @ = String
    */
    public static final String WS_LOGIN_OPINO = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/auth";
    public static final String WS_OBTENER_LOCALES = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/campanas/locales";
    public static final String WS_OBTENER_CAMPANAS = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/locales/%/campanas?seccion=@";
    public static final String WS_OBTENER_INFORMACION = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/campanas/locales/%/seccion?seccion=#";
    public static final String WS_OBTENER_VARIABLES = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/variables/";
    public static final String WS_SUBIR_INFORMACION = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/campanas/locales/%/responder?seccion=#";
    public static final String WS_UPDATE_LOCATION = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/locales/%";
    public static final String WS_SUBIR_IMAGENES = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/campanas/locales/%/responder/imagen";
    public static final String WS_SEND_ALERT = "http://dev-opino-websaurio-com-w2e6bowtidfy.runscope.net/api/alerta";

}