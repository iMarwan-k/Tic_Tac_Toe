package com.imarwan.tictactoc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0  activePlayer = x and 1 for o
    int activePlayer = 0;

    //2 means no player
    int [] status = {2,2,2,2,2,2,2,2,2};

    //all possible winning positions
    int [][] winning = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //to stop the game, use bool and set it to true at the beginning and play again and when someone win set to false
    boolean playing = true;

    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = (Button) findViewById(R.id.restart);
        reset.setVisibility(View.INVISIBLE);
    }


    public void drowIn (View view){

        //we don't need to search for the image by using findbyid
        ImageView counter = (ImageView) view;

        int tap = Integer.parseInt(counter.getTag().toString());

        if (status[tap] == 2 && playing){
            status[tap] = activePlayer;

            if(activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            }
            else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            for(int[] position : winning){
                if (status[position[0]] == status[position[1]]
                        && status[position[0]] == status[position[2]]
                        && status[position[0]] != 2){

                    String player;

                    if(status[position[0]] == 0)
                        player = "X";
                    else
                        player = "O";

                    Toast.makeText(this, "Player " + player + " is the winner, Congrats!", Toast.LENGTH_LONG).show();

                    playing = false;

                    reset.setVisibility(View.VISIBLE);
                }
                else {
                    boolean gameOver = true;
                    for (int i=0; i<status.length;i++){
                        if(status[i] == 2)
                            gameOver = false;
                    }

                    if (gameOver) {
                        Toast.makeText(this, "Ops!, Let play again", Toast.LENGTH_SHORT).show();
                        reset.setVisibility(View.VISIBLE);
                    }
                }

            }
        }

    }

    public void restartGame(View view){
        for(int i=0; i<9; i++){
            ImageView img = (ImageView) findViewById(R.id.gridLayout).findViewWithTag(String.valueOf(i));
            img.setImageResource(android.R.color.transparent);
            status[i] = 2;
        }
        reset.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        playing = true;
    }
}
