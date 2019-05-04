package com.example.ass2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mbcr=new BroadcastReceiver()
    {
        public void onReceive(Context c, Intent i)
        {
            int level=i.getIntExtra("level", 0);
            ProgressBar pb=(ProgressBar)findViewById(R.id.progressBar1);
            TextView tv=(TextView)findViewById(R.id.textView1);
            pb.setProgress(level);
            tv.setText("Batterylevel:"+Integer.toString(level)+"%");
            if(level==100)
            {
                try
                {
                    AssetFileDescriptor afd=getAssets().openFd("small.mp4");
                    MediaPlayer mp=new MediaPlayer();
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                }
                catch(IOException e){}
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(mbcr,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
