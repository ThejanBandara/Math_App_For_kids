package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    Button playbtn, backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        playbtn = findViewById(R.id.playbutton);
        backbtn = findViewById(R.id.exitbutton);
        audio.startBackgroundMusic(this);

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio.playAudio(home.this,R.raw.clickbtn);
                Intent intent = new Intent(home.this, Needtutorial.class);
                startActivity(intent);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                audio.playAudio(home.this,R.raw.clickbtn);
            }
        });

    }
}
