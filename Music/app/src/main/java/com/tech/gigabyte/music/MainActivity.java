package com.tech.gigabyte.music;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button play, stop1;
        play = (Button) findViewById(R.id.button1);
        play.setOnClickListener(new OnClickListener() {

            @Override
            //Starting MyService --> Music will be start
            public void onClick(View view) {
                MusicNotification();
                Intent myIntent = new Intent(MainActivity.this, MyService.class);
                startService(myIntent);
            }
        });

        stop1 = (Button) findViewById(R.id.button2);
        stop1.setOnClickListener(new OnClickListener() {

            @Override
            //Stopping MyService --> Music will be stop
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MyService.class);
                stopService(myIntent);

            }
        });

    }

    //Creating Notification for MyService when it will start
    private void MusicNotification() {

        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        //Setting Icon for Notification
                        .setSmallIcon(R.drawable.ic_music)
                        //Once user click on notification it will take to application and AutoCancel
                        .setAutoCancel(true)
                        //Title
                        .setContentTitle("Ed Sheeran - I See Fire ! ")
                        //Text (SubCategory )
                        .setContentText("Click to stop ");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent musicIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(musicIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }


}