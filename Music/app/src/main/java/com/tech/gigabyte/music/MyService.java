package com.tech.gigabyte.music;

/**
 * Created by GIGABYTE on 6/2/2017.
 */

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    String tag = "MyService";

    //control playback of audio/video files and streams
    MediaPlayer mp;

    @Override
    //another component wants to bind with the service by calling bindService().
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    //The system calls this method when the service is first created
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(getApplicationContext(), R.raw.song);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();

        //Using this return value, if the OS kills our Service
        // it will recreate it but the Intent that was sent to the Service isn’t redelivered.
        // In this way the Service is always running
        return START_STICKY;

    }

    @Override
    //Service that it is no longer used and is being removed
    public void onDestroy() {
        mp.release();
        super.onDestroy();
    }

}