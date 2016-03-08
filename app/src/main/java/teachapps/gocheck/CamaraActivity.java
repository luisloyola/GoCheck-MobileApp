package teachapps.gocheck;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;


public class CamaraActivity extends Activity {

    private static final String TAG = "CamaraActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private long EvaluacionID; //ID de evaluación a revisar

    Button btnTakePhoto;
    ImageView imgTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        //Obtener datos del activity anterior
        Bundle bundleRecibido = getIntent().getExtras();
        EvaluacionID = bundleRecibido.getLong("EvaluacionID");
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            // Pasar a vista resultados obtenidos

            //Pasar datos a la activity RevisionActivity
            Intent intent = new Intent(CamaraActivity.this, RevisionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("EvaluacionID", EvaluacionID);
            intent.putExtras(bundle);

            //Cargar siguiente Activity
            CamaraActivity.this.startActivity(intent);

            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);
        }
    }
}
