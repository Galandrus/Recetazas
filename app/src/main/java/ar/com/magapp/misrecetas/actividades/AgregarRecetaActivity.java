package ar.com.magapp.misrecetas.actividades;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

import ar.com.magapp.misrecetas.R;
import ar.com.magapp.misrecetas.entidades.CustomEditText;
import ar.com.magapp.misrecetas.sqlite.ConexionSQLiteHelper;
import ar.com.magapp.misrecetas.utilidades.Utilidades;

public class AgregarRecetaActivity extends AppCompatActivity {

    ImageView imagen;
    Bitmap bitmapFoto;
    Uri rutaFoto;
    EditText nombre,desc,ing1,cant1,paso1,tip1;
    AutoCompleteTextView categoria;
    boolean seCargoImagen = false;
    ArrayList<String> listaCategorias;


    private static int idIngredientes=100;
    private static final int idIngredientesStart=100;
    private static int idCantidad=120;
    private static final int idCantidadStart=120;
    private static int idPasos=140;
    private static final int idPasosStart=140;
    private static int idTips=160;
    private static final int idTipsStart=160;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_receta);
        imagen = findViewById(R.id.idAgregarFoto);
        nombre=findViewById(R.id.idNombreAgregar);
        desc=findViewById(R.id.idDescAgregar);
        categoria=findViewById(R.id.idCategoriaAgregar);
        ing1=findViewById(R.id.idIngredienteAgregar);
        cant1=findViewById(R.id.idCantidadAgregar);
        paso1=findViewById(R.id.idPasoAgregar);
        tip1=findViewById(R.id.idTipAgregar);

        listaCategorias=new ArrayList<>();
        recuperarListaCategorias();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaCategorias);
        categoria.setAdapter(adapter);



    }

    private void recuperarListaCategorias() {

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(Utilidades.RECUPERAR_CATEGORIAS,null);

        while (cursor.moveToNext()){
            listaCategorias.add(cursor.getString(0));
        }

        db.close();
    }


    public void cargarFoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Seleccione la Aplicación"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            rutaFoto = data.getData();
            imagen.setImageURI(rutaFoto);
            seCargoImagen = true;
/*    try {
        bitmapFoto = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(rutaFoto));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }*/
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void GuardarReceta(View view) {

        //instancio la bd
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);

        //abro la base para poder editarla
        SQLiteDatabase db=conn.getWritableDatabase();

        //Agrego Tabla Categorias
        Cursor cursor = db.rawQuery(Utilidades.seleccionarCategoria(categoria.getText().toString()),null);
        long categoriaId;
        long recetaId;

        if (cursor.moveToFirst()){
            categoriaId = cursor.getInt(0);
        }
        else {
            ContentValues valoresCategoria =new ContentValues();
            valoresCategoria.put(Utilidades.CATEGORIAS_NOMBRE, categoria.getText().toString());
            categoriaId = db.insert(Utilidades.TABLA_CATEGORIAS,null,valoresCategoria);
        }

        //Agrego Tabla Receta
        ContentValues valoresReceta =new ContentValues();
        valoresReceta.put(Utilidades.RECETAS_NOMBRE, nombre.getText().toString());
        valoresReceta.put(Utilidades.RECETA_DESCRIPCION, desc.getText().toString());
        if (seCargoImagen)
            valoresReceta.put(Utilidades.RECETA_FOTO, rutaFoto.toString());
        valoresReceta.put(Utilidades.CATEGORIA_ID, categoriaId);

        try {
            recetaId = db.insert(Utilidades.TABLA_RECETAS,null,valoresReceta);
        } catch (IOError e){
            Toast.makeText(this,"Fallo la Detales", Toast.LENGTH_SHORT).show();
            recetaId = Long.parseLong(null);
        }


        //Agrego Tabla Ingredientes
        ContentValues valoresIngredientes =new ContentValues();
        valoresIngredientes.put(Utilidades.ING_NOMBRE, ing1.getText().toString());
        valoresIngredientes.put(Utilidades.ING_CANT, cant1.getText().toString());
        valoresIngredientes.put(Utilidades.RECETA_ID, recetaId);

        try {
            db.insert(Utilidades.TABLA_INGREDIENTES,null,valoresIngredientes);
        } catch (IOError e){
            Toast.makeText(this,"Fallo la Ingredientes", Toast.LENGTH_SHORT).show();
        }

        for (int i=0;i<idIngredientes-idIngredientesStart;i++){
            valoresIngredientes= new ContentValues();
            EditText ingAux=findViewById(idIngredientesStart+i);
            EditText cantAux=findViewById(idCantidadStart+i);

            valoresIngredientes.put(Utilidades.ING_NOMBRE,ingAux.getText().toString());
            valoresIngredientes.put(Utilidades.ING_CANT,cantAux.getText().toString());
            valoresIngredientes.put(Utilidades.RECETA_ID, recetaId);

           try {
               db.insert(Utilidades.TABLA_INGREDIENTES,null,valoresIngredientes);
           } catch (IOError e){
               Toast.makeText(this,"Fallo la Ingredientes", Toast.LENGTH_SHORT).show();
           }
        }

        //Agrego Tabla Preparacion
        ContentValues valoresPreparacion =new ContentValues();
        valoresPreparacion.put(Utilidades.PREPARACION_PASO, paso1.getText().toString());
        valoresPreparacion.put(Utilidades.RECETA_ID, recetaId);

        try {
            db.insert(Utilidades.TABLA_PREPARACION,null,valoresPreparacion);
        } catch (IOError e){
            Toast.makeText(this,"Fallo la preparacion", Toast.LENGTH_SHORT).show();
        }

        for (int i=idPasosStart;i<idPasos;i++){
            valoresPreparacion= new ContentValues();
            EditText pasoAux = findViewById(i);

            valoresPreparacion.put(Utilidades.PREPARACION_PASO,pasoAux.getText().toString());
            valoresPreparacion.put(Utilidades.RECETA_ID, recetaId);

            try {
                db.insert(Utilidades.TABLA_PREPARACION,null,valoresPreparacion);
            } catch (IOError e){
                Toast.makeText(this,"Fallo la preparacion", Toast.LENGTH_SHORT).show();
            }
        }

        //Agrego Tabla Tips
        ContentValues valoresTips =new ContentValues();
        valoresTips.put(Utilidades.TIPS_TIP, tip1.getText().toString());
        valoresTips.put(Utilidades.RECETA_ID, recetaId);

        try {
            db.insert(Utilidades.TABLA_TIPS,null,valoresTips);
        } catch (IOError e){
            Toast.makeText(this,"Fallo Tips", Toast.LENGTH_SHORT).show();
        }

        for (int i=idTipsStart;i<idTips;i++){
            valoresTips= new ContentValues();
            EditText tipAux = findViewById(i);

            valoresTips.put(Utilidades.TIPS_TIP,tipAux.getText().toString());
            valoresTips.put(Utilidades.RECETA_ID, recetaId);

            try {
                db.insert(Utilidades.TABLA_TIPS,null,valoresTips);
            } catch (IOError e){
                Toast.makeText(this,"Fallo Tips", Toast.LENGTH_SHORT).show();
            }
        }
        //Aviso que se creo bien
       Toast.makeText(this,"Se guardo exitosamente", Toast.LENGTH_SHORT).show();

        //Cierro bd
        db.close();

        Intent intent = new Intent(this, VerRecetaActivity.class);
        startActivityForResult(intent,0);

    }

    private byte[] convertirImagen() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmapFoto.compress(Bitmap.CompressFormat.PNG, 0, baos);
        byte[] photo = baos.toByteArray();
        return photo;
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void agregarIngrediente(View view) {
        LinearLayout rootLayout=  findViewById(R.id.idLLIngredientes);
        LinearLayout llReference= findViewById(R.id.idLLReference);

        CustomEditText ing=new CustomEditText(this);
        ing.setIngredientes();
        ing.setHint(R.string.ingredientes);
        ing.setId(idIngredientes); idIngredientes++;

        CustomEditText cant=new CustomEditText(this);
        cant.setCantidad();
        cant.setHint(R.string.cantidad);
        cant.setId(idCantidad); idCantidad++;

        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(llReference.getLayoutParams());
        ll.addView(ing);
        ll.addView(cant);

        rootLayout.addView(ll);

    }


    public void agregarPaso(View view) {
        LinearLayout rootLayout= findViewById(R.id.idLLPreparacion);
        CustomEditText paso=new CustomEditText(this);
        paso.setPreparacion();
        paso.setHint(R.string.pasos);
        paso.setId(idPasos); idPasos++;
        rootLayout.addView(paso);
    }

    public void agregarTip(View view) {
        LinearLayout rootLayout=  findViewById(R.id.idLLTips);
        CustomEditText tip=new CustomEditText(this);
        tip.setTips();
        tip.setHint(this.getString(R.string.tip));
        tip.setId(idTips); idTips++;
        rootLayout.addView(tip);
    }
}