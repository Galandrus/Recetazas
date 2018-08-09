package ar.com.magapp.misrecetas.utilidades;

public class Utilidades {


    //TABLA CATEGORIA
    public static final String TABLA_CATEGORIAS="categorias";
    public static final String CATEGORIA_ID="_idCategoria";
    public static final String CATEGORIAS_NOMBRE="nombreCategoria";

    public static final String CREAR_TABLA_CATEGORIA="CREATE TABLE "+TABLA_CATEGORIAS+" ("
            +CATEGORIA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +CATEGORIAS_NOMBRE+" TEXT)";

    // TABLA RECETA
    public static final String TABLA_RECETAS="recetas";
    public static final String RECETA_ID ="_idReceta";
    public static final String RECETAS_NOMBRE="nombreReceta";
    public static final String RECETA_DESCRIPCION="descripcion";
    public static final String RECETA_FOTO="foto";

    public static final String CREAR_TABLA_RECETA="CREATE TABLE "+TABLA_RECETAS+" ("
            +RECETA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +RECETAS_NOMBRE+" TEXT, "
            +RECETA_DESCRIPCION+" TEXT, "
            +RECETA_FOTO+" INTEGER, "
            +CATEGORIA_ID+" INTEGER, FOREIGN KEY("+ CATEGORIA_ID +") REFERENCES "+ TABLA_CATEGORIAS +"("+ CATEGORIA_ID +"))";

    //TABLA INGREDIENTES
    public static final String TABLA_INGREDIENTES="ingredientes";
    public static final String ING_ID="_idIngrediente";
    public static final String ING_NOMBRE="nombreIngrediente";
    public static final String ING_CANT="cantidad";

    public static final String CREAR_TABLA_INGREDIENTES="CREATE TABLE "+TABLA_INGREDIENTES+" ("
            +ING_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +ING_NOMBRE+" TEXT,"
            +ING_CANT+" TEXT,"
            +RECETA_ID+" INTEGER, FOREIGN KEY("+ RECETA_ID +") REFERENCES "+ TABLA_RECETAS +"("+ RECETA_ID +"))";


    //TABLA PREPARACION
    public static final String TABLA_PREPARACION="preparacion";
    public static final String PREPARACION_ID="_idPreparcion";
    public static final String PREPARACION_PASO="paso";

    public static final String CREAR_TABLA_PREPARACION="CREATE TABLE "+TABLA_PREPARACION+" ("
            +PREPARACION_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +PREPARACION_PASO+" TEXT,"
            +RECETA_ID+" INTEGER, FOREIGN KEY("+ RECETA_ID +") REFERENCES "+ TABLA_RECETAS +"("+ RECETA_ID +"))";

    //TABLA TIPS
    public static final String TABLA_TIPS="tips";
    public static final String TIP_ID="_idTip";
    public static final String TIPS_TIP="tip";

    public static final String CREAR_TABLA_TIPS="CREATE TABLE "+TABLA_TIPS+" ("
            +TIP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +TIPS_TIP+" TEXT,"
            +RECETA_ID+" INTEGER, FOREIGN KEY("+ RECETA_ID +") REFERENCES "+ TABLA_RECETAS +"("+ RECETA_ID +"))";



    // CONSULTAS

    public static final String RECUPERAR_CATEGORIAS = "select "+ CATEGORIAS_NOMBRE + " from "+TABLA_CATEGORIAS+" order by " + CATEGORIAS_NOMBRE;

    public static String recuperarReceta(String categoria) {
        return  "select " + RECETA_ID + " , " + RECETAS_NOMBRE + " , " + RECETA_DESCRIPCION + " , " + RECETA_FOTO +
                " from " + TABLA_RECETAS +
                " where " + CATEGORIA_ID + " = '"+ categoria+"'" +
                " order by " + RECETAS_NOMBRE;
    }


    public static String seleccionarCategoria(String categoria) {
        return  "select " + CATEGORIA_ID +
                " from " + TABLA_CATEGORIAS +
                " where " + CATEGORIAS_NOMBRE +" = '"+categoria+"'";

    }

    public static String recuperarIngredientes(String id) {
        return "select " + ING_NOMBRE + " , " + ING_CANT +
                " from " + TABLA_INGREDIENTES +
                " where " + RECETA_ID +" = '"+id+"'";

    }

    public static String recuperarPreparacion(String id) {
        return "select " + PREPARACION_PASO +
                " from " + TABLA_PREPARACION +
                " where " + RECETA_ID +" = '"+id+"'";
    }

    public static String recuperarTips(String id) {
        return "select " + TIPS_TIP +
                " from " + TABLA_TIPS +
                " where " + RECETA_ID +" = '"+id+"'";
    }
}


/*
receta=(idreceta,nnombre,desc,foto,idCategoria)
categoria(idcategoira,nombre)
ingredietne=(idingreidnte,nombre,cant,idReceta)
preparacion=(paso,idReceta)
tips=(tip,idReceta)
 */