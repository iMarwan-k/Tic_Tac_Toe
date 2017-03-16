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

        if(activePlayer == 0) {
            counter.setImageResource(R.drawable.x);
            status[Integer.parseInt(counter.getTag().toString())] = 0;
            activePlayer = 1;
        }
        else {
            counter.setImageResource(R.drawable.o);
            status[Integer.parseInt(counter.getTag().toString())] = 1;
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

                Toast.makeText(this, "Player " + player + " is the winner, Congrats!", Toast.LENGTH_SHORT).show();

                reset.setVisibility(View.VISIBLE);
            }

        }
    }

    public void restartGame(View view){
        for(int i=0; i<9; i++){
            ImageView img = (ImageView) findViewById(R.id.mainScreen).findViewWithTag(String.valueOf(i));
            img.setImageResource(android.R.color.transparent);
            status[i] = 2;
        }
        reset.setVisibility(View.INVISIBLE);
    }
}
