package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends AppCompatActivity implements View.OnClickListener {
    Button [][] bts = new Button [3][3];
    Button reset ;
    TextView player1 ;
    TextView player2;
    int player1Points;
    int player2points;
    int round_count ;
    Boolean  player1Turn = true ;// in the first player 1 will be "x" player 2 will be "o"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        reset = findViewById(R.id.button_reset);
        player1 = findViewById(R.id.text_view_p1);
        player2 = findViewById(R.id.text_view_p2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3 ; j++) {
                String bt_id = "button_" + i+j;
                 int id = getResources().getIdentifier(bt_id,"id",getPackageName());
                 bts[i][j] =( Button)findViewById(id);
                 bts[i][j].setOnClickListener(this);

            }
        }





        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    resetBoard();
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (!((Button)view).getText().toString().equals("")){
            return;
        }

        if (player1Turn){
            ((Button) view).setText("X");
        }else {
            ((Button) view).setText("O");

        }
        round_count++;
        if (cheekWinner()){
            if (player1Turn){
                 player1Wins();

            }
            else {
                player2Wins();
            }
        } else if (round_count==9){
              draw();
        } else {
            player1Turn = !player1Turn;
        }


    }

    private void player2Wins() {

        player2points++;
        Toast.makeText(getApplicationContext(), "player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(getApplicationContext(), "player 1 wins!", Toast.LENGTH_SHORT).show();
         updatePointsText();
         resetBoard();
     }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bts[i][j].setText("");
            }
        }
        round_count = 0;
        player1Turn = true;

    }

    private void updatePointsText() {
          player1.setText(new StringBuilder().append("Player 1: ").append(player1Points).toString());
          player2.setText(new StringBuilder().append("Player 2: ").append(player2points).toString());
    }

    private void draw() {
        Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    Boolean cheekWinner(){
       String [][]field = new String[3][3];

        // get data of buttons to array string
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = bts[i][j].getText().toString();
            }
        }

            //check rows
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) &&
                    field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {

                return true;
            }

        }

             //check columns
            for (int i = 0; i < 3; i++) {
                if (field[0][i].equals(field[1][i]) &&
                        field[0][i].equals(field[2][i])  &&! field[0][i].equals("") ){

                    return true;
                }

            }


             //check diagnol1 : 00 ,11,22

        if (field[0][0].equals(field[1][1]) &&
                field[0][0].equals(field[2][2])  &&! field[0][0].equals("") ){

            return true;
        }

             // heck diagnol1 : 02 ,11,20
        if (field[0][2].equals(field[1][1]) &&
                field[0][2].equals(field[2][0])  &&! field[0][2].equals("") ){

            return true;
        }



        return false;
        }





}