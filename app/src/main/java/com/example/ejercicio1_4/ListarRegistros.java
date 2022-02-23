package com.example.ejercicio1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ejercicio1_4.Clases.Persona;
import com.example.ejercicio1_4.Condiguraciones.SQLiteConexion;
import com.example.ejercicio1_4.Condiguraciones.Transacciones;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListarRegistros extends AppCompatActivity {

    SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

    FloatingActionButton btnAtras;
    ListView lista;
    ArrayList<String> Arreglopersona;
    ArrayList<Persona> listaPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_registros);

        btnAtras = (FloatingActionButton) findViewById(R.id.albtnatras);
        lista = (ListView) findViewById(R.id.lista);
        ObternerListaRegistros();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_checked,Arreglopersona);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                obtenerFotos(i);

            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void obtenerFotos( int id) {

        Persona persona = listaPersonas.get(id);

        Intent intent = new Intent(getApplicationContext(),ActivityMostrarRegistro.class);

        intent.putExtra("codigo", persona.getId()+"");
        intent.putExtra("nombre",persona.getNombre());
        intent.putExtra("descripcion",persona.getDescripcion());
        //intent.putExtra("foto", (Parcelable) persona.getFoto());

        startActivity(intent);

    }

    private void ObternerListaRegistros() {

        SQLiteDatabase db = conexion.getReadableDatabase();

        Persona lista_Personas = null;
        listaPersonas = new ArrayList<Persona>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tblPersonas,null);

        while (cursor.moveToNext())
        {
            lista_Personas = new Persona();
            lista_Personas.setId(cursor.getInt(0));
            lista_Personas.setNombre(cursor.getString(1));
            lista_Personas.setDescripcion(cursor.getString(2));
            listaPersonas.add(lista_Personas);
        }
        cursor.close();
        llenarlista();
    }

    private void llenarlista() {

        Arreglopersona = new ArrayList<String>();

        for (int i=0; i<listaPersonas.size();i++)
        {
            Arreglopersona.add(listaPersonas.get(i).getNombre()+" | "+ listaPersonas.get(i).getDescripcion());
        }
    }
}