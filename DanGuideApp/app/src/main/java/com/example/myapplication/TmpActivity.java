package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TmpActivity extends AppCompatActivity {

    TextView tv;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp);

        mp = MediaPlayer.create(this, R.raw.map);
        mp.setLooping(false);

        tv = (TextView) this.findViewById(R.id.hellobutton);
        tv.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mp.isPlaying()) {
                    mp.start();
                    tv.setText("Stop");
                } else {
                    mp.pause();
                    tv.setText("Start");
                }

            }
        });
    }
}