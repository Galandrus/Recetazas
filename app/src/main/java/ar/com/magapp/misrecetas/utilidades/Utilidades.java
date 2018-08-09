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
            +CATEGORIA_ID+" INTEGER)";

    //TABLA INGREDIENTES
    public static final String TABLA_INGREDIENTES="ingredientes";
    public static final String ING_NOMBRE="nombreIngrediente";
    public static final String ING_CANT="cantidad";

    public static final String CREAR_TABLA_INGREDIENTES="CREATE TABLE "+TABLA_INGREDIENTES+" ("
            +ING_NOMBRE+" TEXT,"
            +ING_CANT+" TEXT,"
            +RECETA_ID+" INTEGER)";


    //TABLA PREPARACION
    public static final String TABLA_PREPARACION="preparacion";
    public static final String PREPARACION_PASO="paso";

    public static final String CREAR_TABLA_PREPARACION="CREATE TABLE "+TABLA_PREPARACION+" ("
            +PREPARACION_PASO+" TEXT,"
            +RECETA_ID+" INTEGER)";

    //TABLA TIPS
    public static final String TABLA_TIPS="tips";
    public static final String TIPS_TIP="tip";

    public static final String CREAR_TABLA_TIPS="CREATE TABLE "+TABLA_TIPS+" ("
            +TIPS_TIP+" TEXT,"
            +RECETA_ID+" INTEGER)";


}


/*
receta=(idreceta,nnombre,desc,foto,idCategoria)
categoria(idcategoira,nombre)
ingredietne=(idingreidnte,nombre,cant,idReceta)
preparacion=(paso,idReceta)
tips=(tip,idReceta)
 */