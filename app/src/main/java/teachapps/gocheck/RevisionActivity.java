package teachapps.gocheck;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Luis on 08/03/2016.
 */
public class RevisionActivity extends Activity{
    //text
    TextView texto1;
    private long EvaluacionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision);
        initRevision();

        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        EvaluacionID = bundleRecibido.getLong("EvaluacionID");

        //Debug
        texto1.setText("" + EvaluacionID);
    }

    private void initRevision(){
        texto1 = (TextView) findViewById(R.id.textView_revisionEvaluacionID);

    }
}
