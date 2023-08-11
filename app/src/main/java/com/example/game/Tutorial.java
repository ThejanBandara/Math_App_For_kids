package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Tutorial extends AppCompatActivity {
    public int tutorialno = 1;
    Button back,prev,next,play;
    ImageView tutimg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        back = (Button) findViewById(R.id.backbtn);
        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        play = (Button) findViewById(R.id.play);

        tutimg = findViewById(R.id.tutimg);
        play.setVisibility(View.GONE);
        prev.setVisibility(View.GONE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tutorial.this, home.class);
                startActivity(intent);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialno = tutorialno - 1;
                setimage();
                audio.playAudio(Tutorial.this, R.raw.clickbtn);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialno = tutorialno + 1;
                setimage();
                audio.playAudio(Tutorial.this, R.raw.clickbtn);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.playAudio(Tutorial.this, R.raw.clickbtn);
                Intent intent = new Intent(Tutorial.this, game.class);
                startActivity(intent);
            }
        });

        }
        public void setimage(){

            if (tutorialno == 1) {
                play.setVisibility(View.GONE);
                prev.setVisibility(View.GONE);
                tutimg.setImageResource(R.drawable.tut1);
            } else if (tutorialno == 2) {
                tutimg.setImageResource(R.drawable.tut2);
                prev.setVisibility(View.VISIBLE);
            } else if (tutorialno == 3) {
                play.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                tutimg.setImageResource(R.drawable.tut3);
            } else if (tutorialno == 4) {
                tutimg.setImageResource(R.drawable.tut4);
                play.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
            }
    }

}