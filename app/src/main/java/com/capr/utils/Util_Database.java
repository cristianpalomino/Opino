package com.capr.utils;

/**
 * Created by Gantz on 7/12/14.
 */
public class Util_Database {

    public static final String TABLE_USUARIO = "USUARIO";
    public static final String TABLE_LOCAL = "LOCAL";
    public static final String TABLE_SKU = "SKU";
    public static final String TABLE_AFICHE = "AFICHE";
    public static final String TABLE_PROMOCION = "PROMOCION";
    public static final String TABLE_CALIDAD = "CALIDAD";

    public static final String DB_NAME = "OPINO_DB";
    public static final int DB_VERSION = 1;

    public static String createTableUsuario() {
        return "CREATE TABLE `USUARIO` (\n" +
                "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`usuario_id`\tTEXT NOT NULL,\n" +
                "\t`fexpiracion`\tTEXT NOT NULL\n" +
                ");";
    }

    public static String createTableLocal() {
        return "CREATE TABLE `LOCAL` (\n" +
                "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`id_local`\tTEXT NOT NULL,\n" +
                "\t`nombre`\tTEXT NOT NULL,\n" +
                "\t`direccion`\tTEXT NOT NULL,\n" +
                "\t`distrito`\tTEXT NOT NULL,\n" +
                "\t`canal`\tTEXT NOT NULL,\n" +
                "\t`latitud`\tTEXT NOT NULL,\n" +
                "\t`longitud`\tTEXT NOT NULL\n" +
                ");";
    }

    public static String createTableSKU() {
        return "CREATE TABLE `SKU` (\n" +
                "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`id_local`\tTEXT NOT NULL,\n" +
                "\t`json_sku`\tTEXT NOT NULL\n" +
                ");";
    }

    public static String createTableAfiche() {
        return "CREATE TABLE `AFICHE` (\n" +
                "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`id_local`\tTEXT NOT NULL,\n" +
                "\t`json_afiche`\tTEXT NOT NULL\n" +
                ");";
    }

    public static String createTablePromocion() {
        return "CREATE TABLE `PROMOCION` (\n" +
                "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`id_local`\tTEXT NOT NULL,\n" +
                "\t`json_promocion`\tTEXT NOT NULL\n" +
                ");";
    }

    public static String createTableCalidad() {
        return "CREATE TABLE `CALIDAD` (\n" +
                "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`id_local`\tTEXT NOT NULL,\n" +
                "\t`json_calidad`\tTEXT NOT NULL\n" +
                ");";
    }

    public static String dropTableUsuario() {
        return "DROP TABLE IF EXISTS " + TABLE_USUARIO;
    }

    public static String dropTableLocal() {
        return "DROP TABLE IF EXISTS " + TABLE_LOCAL;
    }

    public static String dropTableSKU() {
        return "DROP TABLE IF EXISTS " + TABLE_SKU;
    }

    public static String dropTableAfiche() {
        return "DROP TABLE IF EXISTS " + TABLE_AFICHE;
    }

    public static String dropTablePromocion() {
        return "DROP TABLE IF EXISTS " + TABLE_PROMOCION;
    }

    public static String dropTableCalidad() {
        return "DROP TABLE IF EXISTS " + TABLE_CALIDAD;
    }
}
