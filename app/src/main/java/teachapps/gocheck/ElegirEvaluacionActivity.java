package teachapps.gocheck;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Luis on 07/03/2016.
 */
public class ElegirEvaluacionActivity extends ListActivity {
    //TextViews
    TextView mTextViewMensaje;

    //ListView
    private ListView mListView_listaEvaluaciones;

    DatabaseHelper DBHelper;
    private boolean isCheckingEvaluacion;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> evaluacionesArrayList = new ArrayList<String>();
    private ArrayList<Long> idEvaluacionesArrayList = new ArrayList<Long>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_evaluacion);
        initElegirEvaluacion();
        DBHelper = new DatabaseHelper(this);

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        isCheckingEvaluacion = bundleRecibido.getBoolean("isCheckingEvaluacion");

        //Get Evaluaciones
        Cursor cursorEvaluaciones = DBHelper.listEvaluaciones();

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

        if(idEvaluacionesArrayList.size() == 0){
            this.mTextViewMensaje.setText("No hay evaluaciones disponibles.");
        }
        else{
            this.mTextViewMensaje.setText("Seleccione la evaluación que desea corregir.");
        }
    }

    private void initElegirEvaluacion(){
        //TextViews
        this.mTextViewMensaje = (TextView) findViewById(R.id.textViewElegirEvaluacionMensaje);

        //ListView
        this.mListView_listaEvaluaciones = this.getListView();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        //id es la posición seleccionada partiendo desde 0.
        long evaluacionID = idEvaluacionesArrayList.get(position);

        //Cargar siguiente activity
        Intent intent;
        if (isCheckingEvaluacion) {
            intent = new Intent(ElegirEvaluacionActivity.this, DummyCamaraActivity.class);

            //Pasar datos a la activity CamaraActivity
            Bundle bundle = new Bundle();
            bundle.putLong("EvaluacionID", evaluacionID);
            intent.putExtras(bundle);

        }
        else {//isNOTCheckingEvaluacion
            Toast.makeText(ElegirEvaluacionActivity.this, "Aun no se pueden editar las evaluaciones", Toast.LENGTH_LONG).show();
            //TODO implementar EditarEvaluacionActivity, por mientras redirige a MainMenuActivity
            //intent = new Intent(ElegirEvaluacionActivity.this, EditarEvaluacionActivity.class);
            intent = new Intent(ElegirEvaluacionActivity.this, MainMenuActivity.class);
        }

        //Iniciar Activity
        ElegirEvaluacionActivity.this.startActivity(intent);

    }
}
