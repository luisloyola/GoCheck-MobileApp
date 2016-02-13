package teachapps.gocheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luis on 12/02/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "GoCheck.db";
    public static final int DATABASE_VERSION = 1;
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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EvaluacionTableName);
        onCreate(db);
    }

    public boolean insertEvaluacion(String nombre,
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
        long isInserted = db.insert(EvaluacionTableName, null, contentValues);

        if (isInserted == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
