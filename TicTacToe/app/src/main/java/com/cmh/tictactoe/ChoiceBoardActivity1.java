package com.cmh.tictactoe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChoiceBoardActivity1 extends AppCompatActivity {

    private Button btnStartBoard;
    private RadioGroup radioChoiceBoardGroup;
    private RadioButton radioChoiceBoardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_board1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            setSupportActionBar(toolbar);
            // calling the action bar
            ActionBar actionBar = getSupportActionBar();

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Tic Tac Toe");
        }

        radioChoiceBoardGroup = (RadioGroup)findViewById(R.id.rdGroup);

        /**
         * Play Button action
         */
        btnStartBoard = (Button) findViewById(R.id.btnStartBoard);

        // Set a click listener for the text view
        btnStartBoard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intent myIntent = new Intent(MainActivity.this, ChoiceBoardActivity.class);
                // MainActivity.this.startActivity(myIntent);
                int selectedId = radioChoiceBoardGroup.getCheckedRadioButtonId();
                radioChoiceBoardButton = (RadioButton)findViewById(selectedId);
                //
                String str = "EMPTY: ";
                // Check which radio button was clicked
                switch(selectedId) {
                    case R.id.rdb3x3:
                        Intent myIntent = new Intent(ChoiceBoardActivity1.this, game3x3Activity.class);
                        ChoiceBoardActivity1.this.startActivity(myIntent);
                        str = "Grille 3: ";
                        break;
                    case R.id.rdb4x4:
                        str = "Grille 4: ";
                        break;
                    case R.id.rdb5x5:
                        str = "Grille 5: ";
                        break;
                }
//                Toast.makeText(ChoiceBoardActivity1.this, str +radioChoiceBoardButton.getText() + " - " +selectedId, Toast.LENGTH_SHORT).show();
                Snackbar.make(v, str +radioChoiceBoardButton.getText() + " - " +selectedId, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}