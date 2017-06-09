package com.example.sarin.alucross;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//    public static int SPLASH_TIME_OUT= 1000;

    int activePlayer = 0;
    boolean gameActive = true;
    int[] gameRule = {2,2,2,2,2,2,2,2,2};

    int[] [] positions  ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


public void dropIn(View view) {

    ImageView counter = (ImageView) view;
    int tappedCounter = Integer.parseInt(counter.getTag().toString());

    if (gameRule[tappedCounter] == 2 && gameActive) {

        gameRule[tappedCounter] = activePlayer;
        counter.setTranslationY(-1000f);

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.cross);

            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.circle);

            activePlayer = 0;
        }
        counter.animate().translationYBy(1000f).rotation(180).setDuration(5);
        for (int[] position : positions){
            if (gameRule[position[0]] == gameRule[position[1]]
                    && gameRule[position[1]] == gameRule[position[2]]
                    && gameRule[position[0]] != 2 ){

                gameActive = false;
                String winner = "O";
                if(gameRule[position[0]] == 0){
                    winner ="X";
                }

                TextView winnerPopup = (TextView)findViewById(R.id.winnerMessage);
                winnerPopup.setText(winner +" has Won !");
                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);

            }else{
                boolean gameOver = true;
                for(int draw : gameRule){
                    if (draw == 2) gameOver = false;
                    }
                    if (gameOver){
                        TextView winnerPopup = (TextView)findViewById(R.id.winnerMessage);
                        winnerPopup.setText("Its a draw");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }


    }



public void playAgain(View view){
    gameActive = true;
    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
    layout.setVisibility(View.INVISIBLE);
    activePlayer =0 ;

    for (int i = 0; i < gameRule.length; i++){
     gameRule[i] = 2;

    }

    GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
    for (int i =0 ; i < gridLayout.getChildCount(); i ++){
        ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
    }
}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run(){
//                Intent homeIntent = new Intent(MainActivity.this, splashActivity.class);
//                startActivity(homeIntent);
//                finish();
//
//            }
//        },SPLASH_TIME_OUT);
    }
}