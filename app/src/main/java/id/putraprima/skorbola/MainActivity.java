package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int kodeGallery = 1, kodeKamera = 99,kodegalerry1=2;
    Uri imageUri;

    EditText home,away;
    ImageView home1,away2;
    Button next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = (EditText)findViewById(R.id.home_team);
        away = (EditText) findViewById(R.id.away_team);
        home1 = (ImageView) findViewById(R.id.home_logo);
        away2 = (ImageView) findViewById(R.id.away_logo);
        next = (Button) findViewById(R.id.btn_team);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (home.equals(" ") && away.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Nama Team tidak boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (home.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Nama Team Pertama tidak boleh kososng", Toast.LENGTH_LONG).show();
                } else if (away.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Nama Team Kedua boleh kosong", Toast.LENGTH_LONG).show();
                } else {


                    String awal = home.getText().toString();
                    String dua = away.getText().toString();
                    home1.buildDrawingCache();
                    Bitmap bitmap = home1.getDrawingCache();
                    away2.buildDrawingCache();
                    Bitmap bitmap1 = away2.getDrawingCache();






                    Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                    intent.putExtra("BitmapImage", bitmap);
                    intent.putExtra("BitmapDua", bitmap1);
                    intent.putExtra("team",awal);
                    intent.putExtra("team1",dua);
                    startActivity(intent);


                    }
                }
            });

        }
       public void loadImagefromGallery(View view) {
           Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
           startActivityForResult(intentGallery, kodeGallery);
    }

    public void loadImagefromGallery1(View view) {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery, kodegalerry1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == kodeGallery && resultCode == RESULT_OK) {
            imageUri = data.getData();
            home1.setImageURI(imageUri);
        }
            else if(requestCode == kodegalerry1 && resultCode == RESULT_OK){
            imageUri = data.getData();
            away2.setImageURI(imageUri);
            }
            else {
                    Toast.makeText(this, "Anda belum mengambil gambar",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

