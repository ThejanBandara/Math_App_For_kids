package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class endscreen extends AppCompatActivity {
        int finalM;

        Button replay, homebtn;
        TextView congtext;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endscreen);

        replay = (Button)findViewById(R.id.Replay);
        homebtn = (Button)findViewById(R.id.home);
        congtext = findViewById(R.id.congtext);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("marksL3")) {
            finalM = intent.getIntExtra("marksL3", 0);
        }
        audio.playAudio(this,R.raw.congrats);
        congtext.setText("You Got " + finalM + " Points !");

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.playAudio(endscreen.this,R.raw.clickbtn);
                Intent intent = new Intent(endscreen.this, game.class);
                startActivity(intent);
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.playAudio(endscreen.this,R.raw.clickbtn);
                Intent intent = new Intent(endscreen.this, home.class);
                startActivity(intent);
            }
        });
    }
}