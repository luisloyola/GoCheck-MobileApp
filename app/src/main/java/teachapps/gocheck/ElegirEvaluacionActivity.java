package teachapps.gocheck;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Luis on 07/03/2016.
 */
public class ElegirEvaluacionActivity extends Activity {
    //TextViews
    private TextView mTextViewDebug;


    DatabaseHelper DBHelper;
    private boolean isCheckingEvaluacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_evaluacion);
        DBHelper = new DatabaseHelper(this);

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        isCheckingEvaluacion = bundleRecibido.getBoolean("isCheckingEvaluacion");

        initElegirEvaluacion();
    }

    private void initElegirEvaluacion(){
        //TextViews
        this.mTextViewDebug             = (TextView) findViewById(R.id.textView_debug);
        if(isCheckingEvaluacion){
            this.mTextViewDebug.setText("IsCheckingEvaluación");
        }
        else {
            this.mTextViewDebug.setText("IsNOTCheckingEvaluación");
        }
    }

}
