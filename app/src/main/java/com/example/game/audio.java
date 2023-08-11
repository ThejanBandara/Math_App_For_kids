package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class audio {

    public static void startBackgroundMusic(Context context) {
        Intent intent = new Intent(context, bgAudio.class);
        context.startService(intent);
    }

    public static void stopBackgroundMusic(Context context) {
        Intent intent = new Intent(context, bgAudio.class);
        context.stopService(intent);
    }

    public static void playAudio(Context context, int audioResourceId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, audioResourceId);
        mediaPlayer.start();
    }
}
