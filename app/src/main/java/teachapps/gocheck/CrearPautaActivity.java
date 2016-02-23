package teachapps.gocheck;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Luis on 18/02/2016.
 */
public class CrearPautaActivity extends Activity {

    private static final String TAG = "CrearPauta";
    private static final int numberOfRows = 20;
    private static final int numberOfAlternatives = 4;

    private TextView mTextViewPauta;
    private EditText mEditTextNumeroPreguntas;
    private Button mButtonAgregarPreguntas;
    private Button mButtonGuardarPauta;
    private PautaRadioButtonHandler radioButtonHandler;

    private Long EvaluacionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pauta);
        initCrearPauta();

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        EvaluacionID = bundleRecibido.getLong("EvaluacionID");

    }

    private void initCrearPauta() {
        //TextViews
        this.mTextViewPauta             = (TextView) findViewById(R.id.textview_pauta);

        //EditTexts
        this.mEditTextNumeroPreguntas   = (EditText) findViewById(R.id.text_cantidad_preguntas);

        //Buttons
        this.mButtonAgregarPreguntas    = (Button) findViewById(R.id.button_agregar_preguntas);
        this.mButtonGuardarPauta        = (Button) findViewById(R.id.button_guardarPauta);

        //PautaRadioButtons in RadioButtonHandler
        this.radioButtonHandler = new PautaRadioButtonHandler(this, numberOfRows, numberOfAlternatives);

        //Set Buttons' OnClickListener
        this.mButtonAgregarPreguntas.setOnClickListener(new onClickAgregarPreguntaButton());
        this.mButtonGuardarPauta.setOnClickListener(new onClickGuardarPautaButton());
    }

    class onClickAgregarPreguntaButton implements View.OnClickListener{
        onClickAgregarPreguntaButton(){

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(CrearPautaActivity.this, "Funcionalidad aun no disponible", Toast.LENGTH_LONG).show();
        }
    }

    class onClickGuardarPautaButton implements View.OnClickListener{
        onClickGuardarPautaButton(){

        }

        @Override
        public void onClick(View v) {
            //TODO guardar pauta en DB
            Toast.makeText(CrearPautaActivity.this, "Guardando", Toast.LENGTH_LONG).show();
        }
    }


}
