package com.example.ejercicio1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ejercicio1_4.Clases.Persona;
import com.example.ejercicio1_4.Condiguraciones.SQLiteConexion;
import com.example.ejercicio1_4.Condiguraciones.Transacciones;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ActivityListaFotos extends AppCompatActivity {
    SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
    ImageView imageViewFoto;
    ArrayList<String> Arreglopersona;
    ArrayList<Persona> listaPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fotos);


    }



}