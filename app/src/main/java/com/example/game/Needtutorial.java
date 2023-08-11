package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Needtutorial extends AppCompatActivity {

    Button backbtn, yesbtn, nobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needtutorial);

       Button backbtn = findViewById(R.id.backbutton);
       Button yesbtn = findViewById(R.id.yesbutton);
       Button nobtn = findViewById(R.id.nobutton);

        backbtn.setOnClickListener(new View.OnClickListener() {
              @Override
            public void onClick(View view) {
                  audio.playAudio(Needtutorial.this,R.raw.clickbtn);

                  Intent intent = new Intent(Needtutorial.this, home.class);
                startActivity(intent);
            }
        });

        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.playAudio(Needtutorial.this,R.raw.clickbtn);
                Intent intent = new Intent(Needtutorial.this, Tutorial.class);
                startActivity(intent);
            }
        });

        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.playAudio(Needtutorial.this,R.raw.clickbtn);
                Intent intent = new Intent(Needtutorial.this, game.class);
                startActivity(intent);
            }
        });
    }
}