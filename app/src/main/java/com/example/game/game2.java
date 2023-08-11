package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class game2 extends AppCompatActivity {

    TextView no1, no2, oper, ans,inditext, marks,qno,timert, leveltext, description;
    Button show,ans1,ans2,ans3,back,play, nextbtn;
    Group ui, window;
    private int currentQuestion = 1;//variables for questioning loop
    private int correctAnswers = 0;
    private int totalQuestions = 10;
    public int levelcount = 2;
    public boolean gameover = false;
    public boolean[] answerStatus = {false, false, false,false};//boolean array for storing correct value
    public boolean confirmPlay = false;
    public long[] timeleft = {0,20000,10000};// countdown timer
    public boolean timeout = false;
    public CountDownTimer countDownTimer;
    public int marksall = 0;//total marks variable
    private Handler handler = new Handler();//pause timer
    public int answerN;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game2);

        no1 = findViewById(R.id.no1);// number 1
        no2 = findViewById(R.id.no2);// number 2
        oper = findViewById(R.id.oper);// operator
        ans = findViewById(R.id.ans);// answer
        ans1 = findViewById(R.id.ans1);// choice button 1
        ans2 = findViewById(R.id.ans2);// choice button 2
        ans3 = findViewById(R.id.ans3);// choice button 3
        inditext = findViewById(R.id.inditext);// indicator text
        marks = findViewById(R.id.marks); // marks display
        qno = findViewById(R.id.question);// Question number display
        timert = findViewById(R.id.timer);// timer
        Button back = findViewById(R.id.backbtn);// back button
        ui = findViewById(R.id.ui);
        window = findViewById(R.id.window);
        leveltext = findViewById(R.id.leveltext);
        description = findViewById(R.id.description);
        play = (Button)findViewById(R.id.playbtnw);
        nextbtn =(Button) findViewById(R.id.nextbtnw);

        levelcount = 2;
        setleveldescription(levelcount);
        nextbtn.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("marksL1")) {
            marksall = intent.getIntExtra("marksL1", 0);
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPlay = true;
                showquestions();
                audio.playAudio(game2.this,R.raw.clickbtn);
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(game2.this, game3.class);
                intent.putExtra("marksL2",marksall);
                startActivity(intent);
                audio.playAudio(game2.this,R.raw.clickbtn);

            }
        });
        back.setOnClickListener(new View.OnClickListener() { // onclick listener for back button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(game2.this, home.class);
                startActivity(intent);
                audio.playAudio(game2.this,R.raw.clickbtn);
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
            }
        });

    }

    private void showquestions() {
        marks.setText("marks " + String.valueOf(marksall));

        if (currentQuestion <= totalQuestions) {

            ans1.setEnabled(true);
            ans2.setEnabled(true);
            ans3.setEnabled(true);

            timer(20000);

            window.setVisibility(View.GONE);
            ui.setVisibility(View.VISIBLE);

            qno.setText("Question no: " + currentQuestion);


            int answerR[] = gamecore.numberGen();

            ans.setText("?");
            inditext.setText("");
            answerN = answerR[3];
            no1.setText(String.valueOf(answerR[0]));
            no2.setText(String.valueOf(answerR[1]));

            String operator;
            switch (answerR[2]) {
                case 1:
                    operator = "+";
                    break;
                case 2:
                    operator = "-";
                    break;
                case 3:
                    operator = "x";
                    break;
                case 4:
                    operator = "\u00F7";
                    break;
                default:
                    operator = "";
            }
            oper.setText(operator);

            placeno(answerR[7], answerR[8], answerR[9], answerR[3], answerR[5], answerR[6]);

            if (answerR[7] == 1) {
                answerStatus[0] = true;
            } else if (answerR[7] == 2) {
                answerStatus[1] = true;
            } else if (answerR[7] == 3) {
                answerStatus[2] = true;
            }


            ans1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(answerStatus[0],answerR[3]);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showquestions();
                        }
                    }, 2000);
                }
            });

            ans2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(answerStatus[1],answerR[3]);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showquestions();
                        }
                    }, 2000);
                }
            });

            ans3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(answerStatus[2],answerR[3]);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showquestions();
                        }
                    }, 2000);
                }
            });


        }
        else {
            ui.setVisibility(View.GONE);
            window.setVisibility(View.VISIBLE);
            description.setText("you got " + marksall +" points!");
            play.setVisibility(View.GONE);
            nextbtn.setVisibility(View.VISIBLE);
            leveltext.setText("Good Job!..");

        }

    }

    // method for running the questions

    public void placeno(int order, int order2, int order3, int number1, int number2,int number3){
        //answer 1
        if (order == 1){
            ans1.setText(String.valueOf(number1));
        }
        else if (order2 == 1) {
            ans1.setText(String.valueOf(number2));
        }
        else if(order3 == 1){
            ans1.setText(String.valueOf(number3));
        }
        //answer 2
        if (order == 2){
            ans2.setText(String.valueOf(number1));
        }
        else if (order2 == 2) {
            ans2.setText(String.valueOf(number2));
        }
        else if(order3 == 2){
            ans2.setText(String.valueOf(number3));
        }
        // answer 3
        if (order == 3){
            ans3.setText(String.valueOf(number1));
        }
        else if (order2 == 3) {
            ans3.setText(String.valueOf(number2));
        }
        else if(order3 == 3){
            ans3.setText(String.valueOf(number3));
        }
    }// code that jumbles the answer according to the data given

    private void checkAnswer(boolean isCorrect, int answer) {

        ans1.setEnabled(false);
        ans2.setEnabled(false);
        ans3.setEnabled(false);

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        if (isCorrect) {
            inditext.setText("Answer is correct");
            inditext.setTextColor(Color.GREEN);
            ans.setText(String.valueOf(answer));
            audio.playAudio(game2.this, R.raw.correct);
            marksall = marksall + 10;
            correctAnswers++;


        } else {
            inditext.setText("Answer is incorrect");
            inditext.setTextColor(Color.RED);
            audio.playAudio(game2.this, R.raw.wrong);
            ans.setText(String.valueOf(answer));
        }
        marks.setText("marks " + marksall);
        currentQuestion++;
        answerStatus[0] = false;
        answerStatus[1] = false;
        answerStatus[2] = false;
        answerN = 0;

    }// code that checks the correct answer and outputs

    public void setleveldescription(int level){
        ui.setVisibility(View.GONE);
        window.setVisibility(View.VISIBLE);

        leveltext.setText("Level 2");
        description.setText("20 Second Time Limit");

    }

    public boolean timer(long time){
        countDownTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timert.setText(String.valueOf(millisUntilFinished/1000)+"s");

                if (millisUntilFinished<5000){
                    timert.setTextColor(Color.RED);
                }
                else {
                    timert.setTextColor(Color.WHITE);
                }
                timeout = false;
            }

            @Override
            public void onFinish() {
                timert.setText("Time's Up!");
                checkAnswer(answerStatus[3],answerN);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showquestions();
                    }
                }, 2000);
            }
        }.start();

        return timeout;
    }

}



