package com.capr.utils;

/**
 * Created by Gantz on 7/12/14.
 */
public class Util_Database {

    public static final String TABLE_USUARIO = "USUARIO";
    public static final String TABLE_LOCAL = "LOCAL";
    public static final String TABLE_CORE = "CORE";
    public static final String TABLE_VARIABLES = "VARIABLES";

    public static final String DB_NAME = "OPINO_DB";
    public static final int DB_VERSION = 1;

    public static String createTableVariables() {
        return "CREATE TABLE `VARIABLES` (\n" +
                "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`codigo_local`\tTEXT NOT NULL,\n" +
                "\t`nombre_variable`\tTEXT NOT NULL,\n" +
                "\t`codigo_variable`\tTEXT NOT NULL,\n" +
                "\t`estado_variable`\tINTEGER NOT NULL\n" +
                ");";
    }

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
                "\t`json_local`\tTEXT NOT NULL\n" +
                ");";
    }

    public static String createTableCore() {
        return "CREATE TABLE `CORE` (\n" +
                "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`id_local`\tTEXT NOT NULL,\n" +
                "\t`id_variable`\tTEXT NOT NULL,\n" +
                "\t`json_variable`\tTEXT NOT NULL\n" +
                ");";
    }

    public static String dropTableUsuario() {
        return "DROP TABLE IF EXISTS " + TABLE_USUARIO;
    }

    public static String dropTableLocal() {
        return "DROP TABLE IF EXISTS " + TABLE_LOCAL;
    }

    public static String dropTableCore() {
        return "DROP TABLE IF EXISTS " + TABLE_CORE;
    }

    public static String dropTableVariables() {
        return "DROP TABLE IF EXISTS " + TABLE_VARIABLES;
    }
}
