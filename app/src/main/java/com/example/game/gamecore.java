package com.example.game;

import java.util.Random;

public class gamecore {

    public static int[] numberGen() {
        Random random = new Random();
        int no1 = 0;
        int no2 = 0;
        int oper = 0;
        int ans = 0;
        int range = 5;
        int choice1t = 0;
        int choice2t = 0;
        int choice1 = 0;
        int choice2 = 0;
        int order = 0;
        boolean no1stat = true;
        boolean no2stat = true;


        while(no1stat == true && no2stat == true) {



            no1 = random.nextInt(12)+1;
            no2 = random.nextInt(12)+1;

            oper = random.nextInt(4)+1;


            if(oper == 1) {

                ans = no1 + no2;

            }
            else if(oper == 2){

                ans = no1 - no2;

            }
            else if(oper == 3) {

                ans = no1 * no2;
            }
            else {

                ans = no1 / no2;

            }

            if(ans <= 0) {
                no1stat = true;
                no2stat = true;
                System.out.println("answer is zero or below zero! restarting ....");
            }
            else {
                no1stat = false;
                no2stat = false;
            }

        }

        // multiple choice maker

        choice1t = random.nextInt(range)+1;
        choice2t = random.nextInt(range)+1;

        choice1 = ans + choice1t;

        if((ans - choice2t)<= 0) {
            choice2 = ans + choice2t;
        }
        else {
            choice2 = ans - choice2t;
        }

        int[] answerlist = {0, 0, 0};

        for(int i=0;i<3;i++){
            order = random.nextInt(3)+1;
            if(answerlist[0] == order) {
                i = i - 1;
            }
            else if(answerlist[1] == order) {
                i = i - 1;
            }
            else if(answerlist[2] == order) {
                i = i - 1;
            }
            else {
                answerlist[i] = order;
            }
        }

        int answers[] ={no1, no2, oper, ans, range, choice1, choice2, answerlist[0], answerlist[1], answerlist[2]};
        return answers;

    }// logical code for generating all the numbers needed for the questions

}
