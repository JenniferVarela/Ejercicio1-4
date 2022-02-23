package com.example.ejercicio1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ejercicio1_4.Clases.Persona;
import com.example.ejercicio1_4.Condiguraciones.SQLiteConexion;
import com.example.ejercicio1_4.Condiguraciones.Transacciones;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ActivityListaFotos extends AppCompatActivity {
    SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
    ImageView imageViewFoto;
    ArrayList<String> Arreglopersona;
    private final ArrayList<Persona> listapersonas = new ArrayList<Persona>();
    ImageView imageView1;
    AppCompatActivity appCompatActivity;
    ListView list1;
    ArrayList<Persona> listaPersonas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fotos);

        SQLiteDatabase db = conexion.getWritableDatabase();
        String sql = "SELECT * FROM personas";
        Cursor cursor = db.rawQuery(sql, new String[] {});

        while (cursor.moveToNext()){
            listapersonas.add(new Persona(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2), cursor.getBlob(3)));
        }


        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();



        AdaptadorPersonas adaptador = new AdaptadorPersonas(this);
        ListView lv1 = findViewById(R.id.list1);
        lv1.setAdapter(adaptador);

        list1 = findViewById(R.id.list1);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(i);
                obtenerFotos(i);



            }
        });


    }

    private void obtenerFotos( int id) {
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
        Persona persona = listaPersonas.get(id);

        Intent intent = new Intent(getApplicationContext(),ActivityMostrarRegistro.class);

        intent.putExtra("codigo", persona.getId()+"");
        intent.putExtra("nombre",persona.getNombre());
        intent.putExtra("descripcion",persona.getDescripcion());
        //intent.putExtra("foto", (Parcelable) persona.getFoto());

        startActivity(intent);

    }



    class AdaptadorPersonas extends ArrayAdapter<Persona> {

        AppCompatActivity appCompatActivity;

        AdaptadorPersonas(AppCompatActivity context) {
            super(context, R.layout.persona, listapersonas);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.persona, null);

            imageView1 = item.findViewById(R.id.imageView);

            SQLiteDatabase db = conexion.getWritableDatabase();

            String sql = "SELECT * FROM personas";
            Persona lista_Personas = null;


            Cursor cursor = db.rawQuery(sql, new String[] {});
            Bitmap bitmap = null;
            TextView textView1 = item.findViewById(R.id.textView);

            if (cursor.moveToNext()){
                textView1.setText(listapersonas.get(position).getNombre());
                byte[] blob = listapersonas.get(position).getFoto();
                ByteArrayInputStream bais = new ByteArrayInputStream(blob);
                bitmap = BitmapFactory.decodeStream(bais);
                imageView1.setImageBitmap(bitmap);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            db.close();

            return(item);
        }
    }


}