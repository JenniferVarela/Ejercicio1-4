package com.example.ejercicio1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fotos);

        SQLiteDatabase db = conexion.getWritableDatabase();
        String sql = "SELECT * FROM personas";
        Cursor cursor = db.rawQuery(sql, new String[] {});

        /*
        LayoutInflater inflater = appCompatActivity.getLayoutInflater();
        View item = inflater.inflate(R.layout.persona, null);
        imageView1 = item.findViewById(R.id.imageView);
        Bitmap bitmap = null;

        */

        while (cursor.moveToNext()){
            listapersonas.add(new Persona(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2),null));

            /*
            byte[] blob = cursor.getBlob(3);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            bitmap = BitmapFactory.decodeStream(bais);

            imageView1.setImageBitmap(bitmap);

             */
        }


        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();



        AdaptadorPersonas adaptador = new AdaptadorPersonas(this);
        ListView lv1 = findViewById(R.id.list1);
        lv1.setAdapter(adaptador);
        lv1.setAdapter(adaptador);

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


            if(cursor.moveToNext()){
                textView1.setText(listapersonas.get(position).getNombre());
                byte[] blob = cursor.getBlob(3);
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