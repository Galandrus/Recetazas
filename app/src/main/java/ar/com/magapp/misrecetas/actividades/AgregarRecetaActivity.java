package ar.com.magapp.misrecetas.actividades;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import ar.com.magapp.misrecetas.R;
import ar.com.magapp.misrecetas.sqlite.ConexionSQLiteHelper;
import ar.com.magapp.misrecetas.utilidades.Utilidades;

public class AgregarRecetaActivity extends AppCompatActivity {

    ImageView imagen;
    EditText nombre,desc,categoria,ing1,cant1,paso1,tip1;
    boolean seCargoImagen = false;


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



    }


    public void cargarFoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri path = data.getData();
            imagen.setImageURI(path);
            seCargoImagen = true;
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void GuardarReceta(View view) {

        //instancio la bd
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);

        //abro la base para poder editarla
        SQLiteDatabase db=conn.getWritableDatabase();

        //Agrego Tabla Categorias
        ContentValues valoresCategoria =new ContentValues();
        valoresCategoria.put(Utilidades.CATEGORIAS_NOMBRE, categoria.getText().toString());
        long categoriaId = db.insert(Utilidades.TABLA_CATEGORIAS,null,valoresCategoria);

        //Agrego Tabla Receta
        ContentValues valoresReceta =new ContentValues();
        valoresReceta.put(Utilidades.RECETAS_NOMBRE, nombre.getText().toString());
        valoresReceta.put(Utilidades.RECETA_DESCRIPCION, desc.getText().toString());
        //if (seCargoImagen)
            valoresReceta.put(Utilidades.RECETA_FOTO, imagen.getImageAlpha());
        valoresReceta.put(Utilidades.CATEGORIA_ID, categoriaId);

        long recetaId = db.insert(Utilidades.TABLA_RECETAS,null,valoresReceta);

        //Agrego Tabla Ingredientes
        ContentValues valoresIngredientes =new ContentValues();
        valoresIngredientes.put(Utilidades.ING_NOMBRE, ing1.getText().toString());
        valoresIngredientes.put(Utilidades.ING_CANT, cant1.getText().toString());
        valoresIngredientes.put(Utilidades.RECETA_ID, recetaId);

        db.insert(Utilidades.TABLA_INGREDIENTES,null,valoresIngredientes);

        for (int i=0;i<idIngredientes-idIngredientesStart;i++){
            valoresIngredientes= new ContentValues();
            EditText ingAux=findViewById(idIngredientesStart+i);
            EditText cantAux=findViewById(idCantidadStart+i);

            valoresIngredientes.put(Utilidades.ING_NOMBRE,ingAux.getText().toString());
            valoresIngredientes.put(Utilidades.ING_CANT,cantAux.getText().toString());
            valoresIngredientes.put(Utilidades.RECETA_ID, recetaId);

            db.insert(Utilidades.TABLA_INGREDIENTES,null,valoresIngredientes);
        }

        //Agrego Tabla Preparacion
        ContentValues valoresPreparacion =new ContentValues();
        valoresPreparacion.put(Utilidades.PREPARACION_PASO, paso1.getText().toString());
        valoresPreparacion.put(Utilidades.RECETA_ID, recetaId);

        db.insert(Utilidades.TABLA_PREPARACION,null,valoresPreparacion);

        for (int i=idPasosStart;i<idPasos;i++){
            valoresPreparacion= new ContentValues();
            EditText pasoAux = findViewById(i);

            valoresPreparacion.put(Utilidades.PREPARACION_PASO,pasoAux.getText().toString());
            valoresPreparacion.put(Utilidades.RECETA_ID, recetaId);

            db.insert(Utilidades.TABLA_PREPARACION,null,valoresPreparacion);
        }

        //Agrego Tabla Tips
        ContentValues valoresTips =new ContentValues();
        valoresTips.put(Utilidades.TIPS_TIP, tip1.getText().toString());
        valoresTips.put(Utilidades.RECETA_ID, recetaId);

        db.insert(Utilidades.TABLA_TIPS,null,valoresTips);

        for (int i=idTipsStart;i<idTips;i++){
            valoresPreparacion= new ContentValues();
            EditText tipAux = findViewById(i);

            valoresPreparacion.put(Utilidades.TIPS_TIP,tipAux.getText().toString());
            valoresPreparacion.put(Utilidades.RECETA_ID, recetaId);

            db.insert(Utilidades.TABLA_PREPARACION,null,valoresTips);
        }
        //Aviso que se creo bien
       Toast.makeText(this,"Se guardo exitosamente", Toast.LENGTH_SHORT).show();

        //Cierro bd
        db.close();

        Intent intent = new Intent(this, VerRecetaActivity.class);
        startActivityForResult(intent,0);

    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void agregarIngrediente(View view) {
        LinearLayout rootLayout=  findViewById(R.id.idLLIngredientes);
        LinearLayout llReference= findViewById(R.id.idLLReference);

        EditText ing=new EditText(this);
        ing.setLayoutParams(ing1.getLayoutParams());
        ing.setHint(R.string.ingredientes);
        ing.setHintTextColor(Color.GRAY);
        ing.setId(idIngredientes); idIngredientes++;

        EditText cant=new EditText(this);
        cant.setLayoutParams(cant1.getLayoutParams());
        cant.setHint(R.string.cantidad);
        cant.setHintTextColor(Color.GRAY);
        cant.setId(idCantidad); idCantidad++;

        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(llReference.getLayoutParams());
        ll.addView(ing);
        ll.addView(cant);

        rootLayout.addView(ll);

    }


    public void agregarPaso(View view) {
        LinearLayout rootLayout= findViewById(R.id.idLLPreparacion);
        EditText paso=new EditText(this);
        paso.setLayoutParams(paso1.getLayoutParams());
        paso.setHint(R.string.pasos);
        paso.setHintTextColor(Color.GRAY);
        paso.setId(idPasos); idPasos++;
        rootLayout.addView(paso);
    }

    public void agregarTip(View view) {
        LinearLayout rootLayout=  findViewById(R.id.idLLTips);
        EditText tip=new EditText(this);
        tip.setLayoutParams(tip1.getLayoutParams());
        tip.setHint(this.getString(R.string.tip));
        tip.setHintTextColor(Color.GRAY);
        tip.setId(idTips); idTips++;
        rootLayout.addView(tip);
    }
}