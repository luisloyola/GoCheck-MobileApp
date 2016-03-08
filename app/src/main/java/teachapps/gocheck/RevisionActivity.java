package teachapps.gocheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Luis on 08/03/2016.
 */
public class RevisionActivity extends Activity{
    //text
    TextView textoResultados;

    //Buttons
    Button mButtonFinalizar;

    private long EvaluacionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision);
        initRevision();

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        EvaluacionID = bundleRecibido.getLong("EvaluacionID");


        //RandomResultados
        int total = 20;
        int omitidas = ((int) (Math.random() * 20));
        int buenas = ((int) (Math.random() * (total-omitidas)));
        int malas = (total - omitidas) - buenas;
        float nota = ((float) buenas / total ) *6 +1;

        String mensaje =    "Buenas: " + buenas + "\n" +
                            "Malas: " + malas + "\n" +
                            "Omitidas: " + omitidas + "\n" +
                            "Puntaje: " + buenas + "/" + total + "\n\n" +
                            "Nota: " + nota;

        textoResultados.setText( mensaje);

    }


    private void initRevision(){
        //Texts
        textoResultados = (TextView) findViewById(R.id.textView_RevisionMensaje);

        //Buttons
        mButtonFinalizar = (Button) findViewById(R.id.buttonRevisionFinalizar);

        //Set Buttons' OnClickListener
        this.mButtonFinalizar.setOnClickListener(new onClickRevisionFinalizarButton());
    }

    class onClickRevisionFinalizarButton implements View.OnClickListener{
        onClickRevisionFinalizarButton(){

        }

        @Override
        public void onClick(View v) {
        //Iniciar siguiente Activity
        Intent intent = new Intent(RevisionActivity.this, MainMenuActivity.class);
        RevisionActivity.this.startActivity(intent);
        }
    }
}
