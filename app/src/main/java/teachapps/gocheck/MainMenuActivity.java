package teachapps.gocheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends Activity {

    private static final String TAG = "MainMenuActivity";
    private Button mCrearEvaluacionButton;
    private Button mCrearPautaButton;
    private Button mEvaluacionesCreadasButton;
    private Button mRevisarEvaluacionButton;
    private Button mMisCursosButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initMainMenu();
    }

    private void initMainMenu() {
        //Buttons
        this.mCrearEvaluacionButton     = (Button) findViewById(R.id.button_crear_evaluacion);
        this.mCrearPautaButton          = (Button) findViewById(R.id.button_crear_pauta);
        this.mEvaluacionesCreadasButton = (Button) findViewById(R.id.button_evaluaciones_creadas);
        this.mRevisarEvaluacionButton   = (Button) findViewById(R.id.button_revisar_evaluaciones);
        this.mMisCursosButton           = (Button) findViewById(R.id.button_mis_cursos);

        //Set Buttons' OnClickListener
        this.mCrearEvaluacionButton.setOnClickListener(new onClickCrearEvaluacionButton());
        this.mCrearPautaButton.setOnClickListener(new onClickCrearPautaButton());
        this.mEvaluacionesCreadasButton.setOnClickListener(new onClickEvaluacionesCreadasButton());
        this.mRevisarEvaluacionButton.setOnClickListener(new onClickRevisarEvaluacionButton());
        this.mMisCursosButton.setOnClickListener(new onClickMisCursosButton());
    }

    class onClickCrearEvaluacionButton implements View.OnClickListener {
        onClickCrearEvaluacionButton() {

        }

        public void onClick(View v){
            Intent intent = new Intent(MainMenuActivity.this, CrearEvaluacionActivity.class);

            //Pasar datos a la activity CrearEvaluación
            Bundle bundle = new Bundle();
            bundle.putBoolean("isCreatingPauta", false); //isCreatingPauta = false significa que se creará una evaluación completa.

            intent.putExtras(bundle);
            //Iniciar Activity
            MainMenuActivity.this.startActivity(intent);
        }
    }

    class onClickCrearPautaButton implements View.OnClickListener {
        onClickCrearPautaButton() {

        }

        public void onClick(View v){
            Intent intent = new Intent(MainMenuActivity.this, CrearEvaluacionActivity.class);

            //Pasar datos a la activity CrearEvaluación
            Bundle bundle = new Bundle();
            bundle.putBoolean("isCreatingPauta", true); //isCreatingPauta = true significa que solo se creará la pauta de una evaluación.

            intent.putExtras(bundle);
            //Iniciar Activity
            MainMenuActivity.this.startActivity(intent);
        }
    }

    class onClickEvaluacionesCreadasButton implements View.OnClickListener {
        onClickEvaluacionesCreadasButton() {

        }

        public void onClick(View v){
            //MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, EvaluacionesCreadasActivity.class));
        }
    }

    class onClickRevisarEvaluacionButton implements View.OnClickListener {
        onClickRevisarEvaluacionButton() {

        }

        public void onClick(View v){
            //MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, RevisarEvaluacionActivity.class));
        }
    }

    class onClickMisCursosButton implements View.OnClickListener {
        onClickMisCursosButton() {

        }

        public void onClick(View v){
            //MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, MisCursosActivity.class));
        }
    }
}
