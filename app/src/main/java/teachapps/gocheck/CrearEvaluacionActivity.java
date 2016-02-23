package teachapps.gocheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    DatabaseHelper DBHelper;
    private Boolean isCreatingPauta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evaluacion);
        initCrearEvaluacion();
        DBHelper = new DatabaseHelper(this);

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        isCreatingPauta = bundleRecibido.getBoolean("isCreatingPauta");
    }

    private void initCrearEvaluacion() {
        //TextViews
        this.mTextViewNombre             = (TextView) findViewById(R.id.textview_nombre);
        this.mTextViewCurso              = (TextView) findViewById(R.id.textview_curso);
        this.mTextViewAsignatura         = (TextView) findViewById(R.id.textview_nombre);
        this.mTextViewExigencia          = (TextView) findViewById(R.id.text_exigencia);
        this.mTextViewCalificacionMinima = (TextView) findViewById(R.id.textview_calificacion_min);
        this.mTextViewCalificacionMaxima = (TextView) findViewById(R.id.textview_calificacion_max);
        this.mTextViewNotaAprobacion     = (TextView) findViewById(R.id.textview_nota_aprobacion);
        this.mTextViewCantidadFormas     = (TextView) findViewById(R.id.textview_cantidad_formas);

        //TextFields (EditText)
        this.mTextFieldNombre               = (EditText) findViewById(R.id.text_nombre);
        this.mTextFieldCurso                = (EditText) findViewById(R.id.text_curso);
        this.mTextFieldAsignatura           = (EditText) findViewById(R.id.text_asignatura);
        this.mTextFieldExigencia            = (EditText) findViewById(R.id.text_exigencia);
        this.mTextFieldCalificacionMinima   = (EditText) findViewById(R.id.text_calificacion_min);
        this.mTextFieldCalificacionMaxima   = (EditText) findViewById(R.id.text_calificacion_max);
        this.mTextFieldNotaAprobacion       = (EditText) findViewById(R.id.text_nota_aprobacion);
        this.mTextFieldCantidadFormas       = (EditText) findViewById(R.id.text_cantidad_formas);

        //Buttons
        this.mNextButton = (Button) findViewById(R.id.button_next);
        this.mAtrasButton = (Button) findViewById(R.id.button_atras);

        //Set Buttons' OnClickListener
        this.mNextButton.setOnClickListener(new onClickNextButton());
        this.mAtrasButton.setOnClickListener(new onClickAtrasButton());

    }

    class onClickNextButton implements View.OnClickListener {
        onClickNextButton() {

        }

        @Override
        public void onClick(View v){
            //Insertar evaluación en DB
            //get values
            String Nombre = mTextFieldNombre.getText().toString();
            String Curso = mTextFieldCurso.getText().toString();
            String Asignatura = mTextFieldAsignatura.getText().toString();
            String Exigencia = mTextFieldExigencia.getText().toString();
            String CalificacionMin = mTextFieldCalificacionMinima.getText().toString();
            String CalificacionMax = mTextFieldCalificacionMaxima.getText().toString();
            String NotaAprobacion = mTextFieldNotaAprobacion.getText().toString();
            String CantidadFormas = mTextFieldCantidadFormas.getText().toString();

            //verification of the values
            if(Nombre.equals("")){
                Nombre = mTextFieldNombre.getHint().toString();
            }
            if(Curso.equals("")){
                Curso = mTextFieldCurso.getHint().toString();
            }
            if(Asignatura.equals("")){
                Asignatura = mTextFieldAsignatura.getHint().toString();
            }
            if(Exigencia.equals("")){
                Exigencia = mTextFieldExigencia.getHint().toString();
            }
            if(CalificacionMin.equals("")){
                CalificacionMin = mTextFieldCalificacionMinima.getHint().toString();
            }
            if(CalificacionMax.equals("")){
                CalificacionMax= mTextFieldCalificacionMaxima.getHint().toString();
            }
            if(NotaAprobacion.equals("")){
                NotaAprobacion= mTextFieldNotaAprobacion.getHint().toString();
            }
            if(CantidadFormas.equals("")){
                CantidadFormas = mTextFieldCantidadFormas.getHint().toString();
            }

            //Insert values
            Long insertedID = DBHelper.insertEvaluacion(
                    Nombre,
                    Curso,
                    Asignatura,
                    Exigencia,
                    CalificacionMin,
                    CalificacionMax,
                    NotaAprobacion,
                    CantidadFormas
            );
            if(insertedID == -1) { //Error al insertar evaluación en la DB.
                Toast.makeText(CrearEvaluacionActivity.this, "Error: No se pudo crear la evaluación", Toast.LENGTH_LONG).show();
            }
            else { //Evaluación insertada en la DB.
                Toast.makeText(CrearEvaluacionActivity.this, "Evaluación creada", Toast.LENGTH_LONG).show();
                //Cargar siguiente activity
                Intent intent;
                if(isCreatingPauta) {
                    intent = new Intent(CrearEvaluacionActivity.this, CrearPautaActivity.class);
                }
                else{//isCreatingEvaluation
                    //TODO implementar CrearSeccionActivity, por mientras redirige a CrearPautaActivity
                    //intent = new Intent(CrearEvaluacionActivity.this, CrearSeccionActivity.class);
                    intent = new Intent(CrearEvaluacionActivity.this, CrearPautaActivity.class);
                }

                //Pasar datos a la activity CrearEvaluación
                Bundle bundle = new Bundle();
                bundle.putLong("EvaluacionID", insertedID); //isCreatingPauta = false significa que se creará una evaluación completa.
                intent.putExtras(bundle);

                //Iniciar Activity
                CrearEvaluacionActivity.this.startActivity(intent);
            }

        }
    }

    class onClickAtrasButton implements View.OnClickListener {
        onClickAtrasButton() {

        }

        public void onClick(View v) {
            CrearEvaluacionActivity.this.startActivity(new Intent(CrearEvaluacionActivity.this, MainMenuActivity.class));
        }
    }
}

