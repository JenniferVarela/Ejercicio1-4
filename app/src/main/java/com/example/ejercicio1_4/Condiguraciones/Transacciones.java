package com.example.ejercicio1_4.Condiguraciones;

public class Transacciones {

    public static final String NameDatabase = "PM01DB";

    public static String tblPersonas = "personas";

    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String descripcion = "descripcion";
    public static final String foto = "foto";

    public static final String CreateTablePersonas = "CREATE TABLE " + tblPersonas +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"nombre TEXT,descripcion TEXT,foto BLOB)";

    public static final String DropTablePersonas = "DROP TABLE " + tblPersonas;

}
