package teachapps.gocheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Luis on 08/03/2016.
 */
public class DummyCamaraActivity extends Activity {
    //Buttons
    Button mboton;

    private long EvaluacionID; //ID de evaluación a revisar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_camara);
        initDummyCamara();

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        EvaluacionID = bundleRecibido.getLong("EvaluacionID");

    }

    private void initDummyCamara(){
        //Buttons
        mboton = (Button) findViewById(R.id.button_dummy);

        //Set Buttons' OnClickListener
        this.mboton.setOnClickListener(new onClickDummyButton());
    }

    private void attachDataToNextActivity(Intent intent){

    }

    class onClickDummyButton implements View.OnClickListener {
        onClickDummyButton() {

        }

        public void onClick(View v) {
            Intent intent = new Intent(DummyCamaraActivity.this, RevisionActivity.class);

            //Pasar datos a la activity RevisionActivity
            Bundle bundle = new Bundle();
            bundle.putLong("EvaluacionID", EvaluacionID);
            intent.putExtras(bundle);

            //Cargar siguiente Activity
            DummyCamaraActivity.this.startActivity(intent);
        }
    }
}
