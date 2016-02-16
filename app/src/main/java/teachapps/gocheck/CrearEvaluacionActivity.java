package teachapps.gocheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CrearEvaluacionActivity extends Activity {

    private static final String TAG = "CrearEvaluacion";
    private TextView mTextViewNombre;
    private TextView mTextViewCurso;
    private TextView mTextViewAsignatura;
    private TextView mTextViewExigencia;
    private TextView mTextViewCalificacionMinima;
    private TextView mTextViewCalificacionMaxima;
    private TextView mTextViewNotaAprobacion;
    private TextView mTextViewCantidadFormas;
    private EditText mTextFieldNombre;
    private EditText mTextFieldCurso;
    private EditText mTextFieldAsignatura;
    private EditText mTextFieldExigencia;
    private EditText mTextFieldCalificacionMinima;
    private EditText mTextFieldCalificacionMaxima;
    private EditText mTextFieldNotaAprobacion;
    private EditText mTextFieldCantidadFormas;
    private Button mNextButton;
    private Button mAtrasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evaluacion);
        initCrearEvaluacion();
    }

    private void initCrearEvaluacion() {
        this.mTextViewNombre             = (TextView) findViewById(R.id.textview_nombre);
        this.mTextViewCurso              = (TextView) findViewById(R.id.textview_curso);
        this.mTextViewAsignatura         = (TextView) findViewById(R.id.textview_nombre);
        this.mTextViewExigencia          = (TextView) findViewById(R.id.text_exigencia);
        this.mTextViewCalificacionMinima = (TextView) findViewById(R.id.textview_calificacion_min);
        this.mTextViewCalificacionMaxima = (TextView) findViewById(R.id.textview_calificacion_max);
        this.mTextViewNotaAprobacion     = (TextView) findViewById(R.id.textview_nota_aprobacion);
        this.mTextViewCantidadFormas     = (TextView) findViewById(R.id.textview_cantidad_formas);

        this.mNextButton = (Button) findViewById(R.id.button_next);
        this.mAtrasButton = (Button) findViewById(R.id.button_atras);
        this.mAtrasButton.setOnClickListener(new onClickmAtrasButton());

    }
    class onClickmAtrasButton implements View.OnClickListener {
        onClickmAtrasButton() {

        }

        public void onClick(View v){
            CrearEvaluacionActivity.this.startActivity(new Intent(CrearEvaluacionActivity.this, MainMenuActivity.class));
        }
    }
}
