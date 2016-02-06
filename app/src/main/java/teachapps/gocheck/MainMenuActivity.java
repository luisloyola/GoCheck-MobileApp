package teachapps.gocheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    private static final String TAG = "MainMenuActivity";
    private Button mCrearEvaluacionButton;
    private Button mCrearPautaButton;
    private Button mEvaluacionesCreadasButton;
    private Button mRevisarEvaluacionButton;
    private Button mMisCursosButton;

    class onClickCrearEvaluacionButton implements View.OnClickListener {
        onClickCrearEvaluacionButton() {

        }

        public void onClick(View v){
            //MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, CrearEvaluacionActivity.class));
        }
    }

    class onClickCrearPautaButton implements View.OnClickListener {
        onClickCrearPautaButton() {

        }

        public void onClick(View v){
            //MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, CrearPautaActivity.class));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initMainMenu();
    }

    private void initMainMenu() {
        this.mCrearEvaluacionButton     = (Button) findViewById(R.id.button_crear_evaluacion);
        this.mCrearPautaButton          = (Button) findViewById(R.id.button_crear_pauta);
        this.mEvaluacionesCreadasButton = (Button) findViewById(R.id.button_evaluaciones_creadas);
        this.mRevisarEvaluacionButton   = (Button) findViewById(R.id.button_revisar_evaluaciones);
        this.mMisCursosButton           = (Button) findViewById(R.id.button_mis_cursos);

        this.mCrearEvaluacionButton.setOnClickListener(new onClickCrearEvaluacionButton());
        this.mCrearPautaButton.setOnClickListener(new onClickCrearPautaButton());
        this.mEvaluacionesCreadasButton.setOnClickListener(new onClickEvaluacionesCreadasButton());
        this.mRevisarEvaluacionButton.setOnClickListener(new onClickRevisarEvaluacionButton());
        this.mMisCursosButton.setOnClickListener(new onClickMisCursosButton());
    }
}
