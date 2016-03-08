package teachapps.gocheck;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Luis on 07/03/2016.
 */
public class ElegirEvaluacionActivity extends Activity {
    //TextViews
    private TextView mTextViewDebug;

    //ListView
    private ListView mListView_listaEvaluaciones;

    DatabaseHelper DBHelper;
    private boolean isCheckingEvaluacion;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_evaluacion);
        initElegirEvaluacion();
        DBHelper = new DatabaseHelper(this);

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        isCheckingEvaluacion = bundleRecibido.getBoolean("isCheckingEvaluacion");

        //SetTextDebug
        if(isCheckingEvaluacion){
            this.mTextViewDebug.setText("IsCheckingEvaluación");
        }
        else {
            this.mTextViewDebug.setText("IsNOTCheckingEvaluación");
        }

        //Get Evaluaciones
        Cursor cursorEvaluaciones = DBHelper.listEvaluaciones();
        ArrayList<String> evaluacionesArrayList = new ArrayList<String>();
        ArrayList<Long> idEvaluacionesArrayList = new ArrayList<Long>();
        while(cursorEvaluaciones.moveToNext()){
            //Set text
            StringBuffer buffer= new StringBuffer();
            buffer.append("Nombre: " + cursorEvaluaciones.getString(1) + "\n");
            buffer.append("Curso: " + cursorEvaluaciones.getString(2) + "\n");
            buffer.append("Asignatura: " + cursorEvaluaciones.getString(3) + "\n");
            evaluacionesArrayList.add(buffer.toString());

            //Set id
            idEvaluacionesArrayList.add(Long.parseLong(cursorEvaluaciones.getString(0)));
        }


        //Poblar listaEvaluaciones
        adapter = new ArrayAdapter<String>(this,R.layout.list_item_evaluacion, R.id.evaluacionlist_item,evaluacionesArrayList);
        mListView_listaEvaluaciones.setAdapter(adapter);
    }

    private void initElegirEvaluacion(){
        //TextViews
        this.mTextViewDebug             = (TextView) findViewById(R.id.textView_debug);

        //ExpandableListView
        this.mListView_listaEvaluaciones = (ListView) findViewById(R.id.listView_listaEvaluaciones);
    }

}
