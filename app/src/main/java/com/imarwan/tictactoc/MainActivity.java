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

        outerfor: for(int i=0; i<status.length;i+=3){
            int count =0;

            for(int y=i+1; y<=i+2; y++)
                if(status[i] == status[y] && status[i] != 2 && status[y] != 2)
                    count++;

            if (count == 2) {
                String player;
                if(status[i] == 0)
                    player = "X";
                else
                    player = "O";

                Toast.makeText(this, "Player " + player + " " + " is the winner", Toast.LENGTH_LONG).show();
                reset.setVisibility(View.VISIBLE);
                break outerfor;
            }
            else
                count = 0;

            for (int z=i+3; z<status.length; z+=3)
                if(status[i] == status[z] && status[i] != 2 && status[z] != 2)
                    count++;

            if (count == 2) {
                String player;
                if(status[i] == 0)
                    player = "X";
                else
                    player = "O";

                Toast.makeText(this, "Player " + player + " " + " is the winner", Toast.LENGTH_LONG).show();
                reset.setVisibility(View.VISIBLE);
                break outerfor;
            }
        }
    }

    public void restartGame(View view){
        for(int i=0; i<9; i++){
            ImageView img = (ImageView) findViewById(R.id.mainScreen).findViewWithTag(String.valueOf(i));
            img.setImageResource(android.R.color.transparent);
        }
        reset.setVisibility(View.INVISIBLE);
    }
}
