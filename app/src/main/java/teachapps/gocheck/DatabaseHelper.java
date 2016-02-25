package teachapps.gocheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luis on 12/02/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    //CONSTANTS
    public static final long ALTERNATIVA = 1;

    //DB
    public static final String DATABASE_NAME = "GoCheck.db";
    public static final int DATABASE_VERSION = 1;

    //Tabla Evaluación
    public static final String EvaluacionTableName = "evaluacion_table";
    public static final String EvaluacionCol1 = "ID";
    public static final String EvaluacionCol2 = "Name";
    public static final String EvaluacionCol3 = "Curso";
    public static final String EvaluacionCol4 = "Asignatura";
    public static final String EvaluacionCol5 = "NivelExigencia";
    public static final String EvaluacionCol6 = "CalificacionMinima";
    public static final String EvaluacionCol7 = "CalificacionMaxima";
    public static final String EvaluacionCol8 = "NotaAprobacion";
    public static final String EvaluacionCol9 = "CantidadFormas";

    //Tabla TipoPregunta
    public static final String TipoPreguntaTableName = "tipo_pregunta_table";
    public static final String TipoPreguntaCol1 = "ID";
    public static final String TipoPreguntaCol2 = "Tipo";

    //Tabla Sección
    public static final String SeccionTableName = "seccion_table";
    public static final String SeccionCol1 = "ID";
    public static final String SeccionCol2 = "Nombre";
    public static final String SeccionCol3 = "Instrucciones";
    public static final String SeccionCol4 = "EvaluacionID";
    public static final String SeccionCol5 = "TipoPreguntaID";

    //Tabla Pregunta
    public static final String PreguntaTableName = "pregunta_table";
    public static final String PreguntaCol1 = "ID";
    public static final String PreguntaCol2 = "Enunciado";
    public static final String PreguntaCol3 = "Alternativas";
    public static final String PreguntaCol4 = "AlternativaCorrecta";
    public static final String PreguntaCol5 = "Puntaje";
    public static final String PreguntaCol6 = "SeccionID";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Evaluacion Table
        db.execSQL("DROP TABLE IF EXISTS " + EvaluacionTableName);
        db.execSQL("create table " + EvaluacionTableName + "(" +
                        EvaluacionCol1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        EvaluacionCol2 + " TEXT " +
                        EvaluacionCol2 + " TEXT, " +
                        EvaluacionCol3 + " TEXT, " +
                        EvaluacionCol4 + " TEXT, " +
                        EvaluacionCol5 + " REAL, " +
                        EvaluacionCol6 + " REAL, " +
                        EvaluacionCol7 + " REAL, " +
                        EvaluacionCol8 + " REAL, " +
                        EvaluacionCol9 + " INTEGER" +
                        ")"
        );

        //Create TipoPregunta Table
        db.execSQL("DROP TABLE IF EXISTS " + TipoPreguntaTableName);
        db.execSQL("create table " + TipoPreguntaTableName + "(" +
                        TipoPreguntaCol1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TipoPreguntaCol2 + " TEXT" +
                        ")"
        );

        //Create Seccion Table
        db.execSQL("DROP TABLE IF EXISTS " + SeccionTableName);
        db.execSQL("create table " + SeccionTableName + "(" +
                        SeccionCol1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        SeccionCol2 + " TEXT, " +
                        SeccionCol3 + " TEXT, " +
                        SeccionCol4 + " INTEGER REFERENCES" + EvaluacionTableName + ", " +
                        SeccionCol5 + " INTEGER REFERENCES" + TipoPreguntaTableName +
                        ")"
        );


        //Create Pregunta Table
        db.execSQL("DROP TABLE IF EXISTS " + PreguntaTableName);
        db.execSQL("create table " + PreguntaTableName + "(" +
                        PreguntaCol1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PreguntaCol2 + " TEXT, " +
                        PreguntaCol3 + " TEXT, " +
                        PreguntaCol4 + " TEXT, " +
                        PreguntaCol5 + " REAL, " +
                        PreguntaCol6 + " INTEGER, " +
                        "FOREIGN KEY(" + PreguntaCol6 + ") REFERENCES " + SeccionTableName + "(" + SeccionCol1 + ") " +
                        ")"
        );

        //Poblado Inicial
        //Poblado TipoPregunta
        this.insertTipoPregunta(db, "Alternativa");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EvaluacionTableName);
        onCreate(db);
    }

    //Inserts
    public long insertEvaluacion(String nombre,
                                 String curso,
                                 String asignatura,
                                 String nivelExigencia,
                                 String calificacionMin,
                                 String calificacionMax,
                                 String notaAprobacion,
                                 String cantidadFormas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EvaluacionCol2, nombre);
        contentValues.put(EvaluacionCol3, curso);
        contentValues.put(EvaluacionCol4, asignatura);
        contentValues.put(EvaluacionCol5, nivelExigencia);
        contentValues.put(EvaluacionCol6, calificacionMin);
        contentValues.put(EvaluacionCol7, calificacionMax);
        contentValues.put(EvaluacionCol8, notaAprobacion);
        contentValues.put(EvaluacionCol9, cantidadFormas);
        return db.insert(EvaluacionTableName, null, contentValues); //-1 if insertion fails.
    }

    public long insertSeccion(String nombre,
                              String instrucciones,
                              long EvaluacionID,
                              long TipoPreguntaID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SeccionCol2, nombre);
        contentValues.put(SeccionCol3, instrucciones);
        contentValues.put(SeccionCol4, EvaluacionID);
        contentValues.put(SeccionCol5, TipoPreguntaID);
        return db.insert(SeccionTableName, null, contentValues); //-1 if insertion fails.
    }

    private long insertTipoPregunta(String tipo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TipoPreguntaCol2, tipo);
        return db.insert(TipoPreguntaTableName, null, contentValues);
    }

    private long insertTipoPregunta(SQLiteDatabase db, String tipo){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TipoPreguntaCol2, tipo);
        return db.insert(TipoPreguntaTableName, null, contentValues);
    }

    public long insertPregunta(String enunciado,
                               String alternativas,
                               String alternativaCorrecta,
                               float puntaje,
                               long SeccionID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PreguntaCol2, enunciado);
        contentValues.put(PreguntaCol3, alternativas);
        contentValues.put(PreguntaCol4, alternativaCorrecta);
        contentValues.put(PreguntaCol5, puntaje);
        contentValues.put(PreguntaCol6, SeccionID);
        return db.insert(PreguntaTableName, null, contentValues);
    }

    public long insertPreguntaPauta(int alternativaCorrecta, long SeccionID){
        String alternativaCorrectaChar = getCharForNumber(alternativaCorrecta);
        return insertPregunta("", "", alternativaCorrectaChar, 1, SeccionID);
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 97)) : null;
    }
}
