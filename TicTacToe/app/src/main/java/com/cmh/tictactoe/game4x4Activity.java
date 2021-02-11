package com.cmh.tictactoe;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class game4x4Activity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[4][4];
    SharedPreferences sharedPref;
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private TextView playerTurn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getPreferences(Context.MODE_PRIVATE);;
        player1Points = sharedPref.getInt("player1Score", 0);
        player2Points = sharedPref.getInt("player2Score", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4x4);
        textViewPlayer1 = findViewById(R.id.player1Score);
        textViewPlayer2 = findViewById(R.id.player2Score);
        playerTurn = findViewById(R.id.player_turn);
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setSoundEffectsEnabled(false);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        Button buttonNewGame = findViewById(R.id.button_new_game);

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(game4x4Activity.this, ChoiceBoardActivity1.class);
                game4x4Activity.this.startActivity(myIntent);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                textViewPlayer1.setText("Player 1: " + 0);
                textViewPlayer2.setText("Player 2: " + 0);
                player1Points = 0;
                player2Points = 0;
                sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("player1Score", 0);
                editor.apply();
                editor = sharedPref.edit();
                editor.putInt("player2Score", 0);
                editor.apply();


            }
        });
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) v).setText("O");

            MediaPlayer player1Song = MediaPlayer.create(game4x4Activity.this, R.raw.player1sound);
            player1Song.start();

            playerTurn.setText("Player 2");
        } else {
            ((Button) v).setText("X");
            MediaPlayer player2Song = MediaPlayer.create(game4x4Activity.this, R.raw.player2sound);
            player2Song.start();
            playerTurn.setText("Player 1");
        }
        roundCount++;
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 16) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }
    private boolean checkForWin() {
        String[][] field = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 4; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && field[i][0].equals(field[i][3])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i].equals(field[3][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0].equals(field[3][3])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][3].equals(field[1][2])
                && field[0][3].equals(field[2][1])
                && field[0][3].equals(field[3][0])
                && !field[0][3].equals("")) {
            return true;
        }
        return false;
    }
    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_LONG).show();
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("player1Score", player1Points);
        editor.apply();
        textViewPlayer1.setText("Player 1: " + player1Points);
        newGame();
    }
    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_LONG).show();
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("player2Score", player2Points);
        editor.apply();
        textViewPlayer2.setText("Player 2: " + player2Points);
        newGame();
    }
    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
        newGame();
    }

    private void newGame() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
        playerTurn.setText("Player 1");
    }
}