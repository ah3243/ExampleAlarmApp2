package com.example.examplealarmapp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

/**
 * Created by alberthiggins on 30/01/2018.
 */

public class MyAlarm extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();



    }

}