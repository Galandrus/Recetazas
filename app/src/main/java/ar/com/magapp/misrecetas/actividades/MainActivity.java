package ar.com.magapp.misrecetas.actividades;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.com.magapp.misrecetas.R;
import ar.com.magapp.misrecetas.sqlite.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);

    }
}


/*
 Falta

 Llenar las listas con la db
 Guardar bien en la db
 guardar las categorias si son nuevas

 foto expandible
 manjear los checbox
 Probando Github

 */
