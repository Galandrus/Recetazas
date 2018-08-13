package ar.com.magapp.misrecetas.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ar.com.magapp.misrecetas.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper{

    private static final String DB_NAME= "recetazas.sqlite";
    private static final int DB_VERSION = 2;

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ConexionSQLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_CATEGORIA);
        db.execSQL(Utilidades.CREAR_TABLA_RECETA);
        db.execSQL(Utilidades.CREAR_TABLA_INGREDIENTES);
        db.execSQL(Utilidades.CREAR_TABLA_PREPARACION);
        db.execSQL(Utilidades.CREAR_TABLA_TIPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_CATEGORIAS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RECETAS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_INGREDIENTES);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PREPARACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_TIPS);
        onCreate(db);
    }

     /*
    //instancion la bd
    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);

    //abro la base para poder editarla
    SQLiteDatabase db=conn.getWritableDatabase();

    ContentValues values =new ContentValues();
    values.put(campo, dato);
    db.insert(nombreTabla,null,values);  //si en nul le pones que queres uqe te devuelva, podes poner el id y ya tenes el id apra podener en las otras talbas.
    db.close();
    */
}




