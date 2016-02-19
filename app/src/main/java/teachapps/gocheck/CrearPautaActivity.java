package teachapps.gocheck;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Luis on 18/02/2016.
 */
public class CrearPautaActivity extends Activity {

    private static final String TAG = "CrearPauta";

    private TextView mTextViewPauta;
    private Long EvaluacionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pauta);
        initCrearPauta();

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        EvaluacionID = bundleRecibido.getLong("EvaluacionID");
        mTextViewPauta.setText("Evaluación recien creada: " + EvaluacionID.toString());
    }

    private void initCrearPauta() {
        //TODO completar esto

        //TextViews
        this.mTextViewPauta             = (TextView) findViewById(R.id.textview_pauta);
    }


}
