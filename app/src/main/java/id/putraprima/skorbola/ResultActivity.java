package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static final String DATA_KEY = "data";
    TextView tvWinner;
    Integer homescore,awayscore;
    String Home,away;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvWinner = findViewById(R.id.tv_winner);
        Home = getIntent().getExtras().getString("homeNama");
        away= getIntent().getExtras().getString("awayNama");
         homescore  = getIntent().getExtras().getInt("scoreHome");
         awayscore = getIntent().getExtras().getInt("scoreAway");

            if(resultScore().equals(Home)) {
                tvWinner.setText("The winner is "+ resultScore());
//                tvList.setText(homeScorer());
            }else if(resultScore().equals(away)) {
                tvWinner.setText("The winner is "+ resultScore());
//                tvList.setText(matchActivity.awayScorer());
            }else{
                tvWinner.setText(resultScore());
//                tvList.setText("");
            }
        }

    public String resultScore(){
        if(awayscore == homescore){
            return "Draw";
        }else if(homescore > awayscore){
            return Home;
        }else if(awayscore > homescore){
            return away;
        }else{
            return "Invalid";
        }
    }

}

