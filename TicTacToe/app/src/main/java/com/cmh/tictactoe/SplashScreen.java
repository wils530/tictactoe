package com.cmh.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmh.tictactoe.R;

public class SplashScreen extends AppCompatActivity {
    private static int COUNTER= 4000;//compteur splash screen en ms
    Animation top_anim,bottom_anim;// fichiers d'animaion
    ImageView image; //logo
    TextView logo; // texte klk
    MediaPlayer welcome_music;// music du splash screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // masquer la status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //music
        welcome_music= MediaPlayer.create(this,R.raw.welcome);
        //animations
        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image=findViewById(R.id.imageView);
        logo=findViewById(R.id.textView);

        welcome_music.start();
        image.setAnimation(top_anim);
        logo.setAnimation(bottom_anim);


        // apres 4000ms on stoope la music et on passe au meu principale
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                welcome_music.release();
                finish();//on termine lactivite splash screen (si il u a precedent lapp se ferme)
            }
        },COUNTER);
    }
}