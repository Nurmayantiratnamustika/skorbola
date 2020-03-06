package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity {

    public static final String DATA_KEY = "data";
    public static final String ADD_KEY = "add";
    public static final String LANGGAR_KEY = "langgar";
    ArrayList<String> listnamahome = new ArrayList<>();
    ArrayList<String> listnamaaway = new ArrayList<>();
    private int homescore = 0;
    private int awayscore = 0;
    String homeNama,AwayNama;

    ImageView home,away;
    TextView home1,away1,tv1,tv2,scoreHome,scoreAway;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        home = (ImageView) findViewById(R.id.home_logo);
        away = (ImageView) findViewById(R.id.away_logo);
        home1 = (TextView) findViewById(R.id.txt_home);
        away1 = (TextView) findViewById(R.id.txt_away);
        tv1 = (TextView) findViewById(R.id.txt_home_last);
        tv2=(TextView) findViewById(R.id.txt_away_last);


        scoreHome = (TextView) findViewById(R.id.score_home);
        scoreAway = (TextView) findViewById(R.id.score_away);



        home1.setText(getIntent().getExtras().getString("team"));
        away1.setText(getIntent().getExtras().getString("team1"));
       homeNama = getIntent().getExtras().getString("team");
       AwayNama = getIntent().getExtras().getString("team1");

        Bitmap bitmap = this.getIntent().getParcelableExtra("BitmapImage");
        home.setImageBitmap(bitmap);

        Bitmap bitmap1 = this.getIntent().getParcelableExtra("BitmapDua");
        away.setImageBitmap(bitmap1);

    }


    public void addHomeScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 1);
    }

    public void addAwayScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 2);
    }

    public void addHomeScore1(String name, String time) {
        listnamahome.add(name + " , " + time);
        homescore++;
    }


    public void addAwayScore1(String name, String time){
        listnamaaway.add(name+" , "+time);
        awayscore++;
    }

    public String awayScorer(){
        String scorer = "";
        for(String as : listnamaaway){
            scorer += as + "\n";
        }
        return scorer;
    }

    public String homeScorer(){
        String scorer = "";
        for(String hs : listnamahome){
            scorer += hs + "\n";
        }
        return scorer;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                addHomeScore1(data.getStringExtra(ADD_KEY), data.getStringExtra(LANGGAR_KEY));
                scoreHome.setText(String.valueOf(homescore));
                tv1.setText(homeScorer());

            }
        }else if(requestCode == 2){
            if(resultCode == RESULT_OK) {
                addAwayScore1(data.getStringExtra(ADD_KEY), data.getStringExtra(LANGGAR_KEY));
                scoreAway.setText(String.valueOf(awayscore));
                tv2.setText(awayScorer());

            }
        }
    }



    public void submitCheck(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("homeNama",homeNama);
        intent.putExtra("awayNama",AwayNama);
        intent.putExtra("scoreHome",homescore);
        intent.putExtra("scoreAway",awayscore);
        startActivity(intent);
    }



}


