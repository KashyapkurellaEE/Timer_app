package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekbar;
    TextView textView;
    Boolean counterIsactive=false;
    CountDownTimer countdowntimer;
    Button button= findViewById(R.id.button);
    public void resetTimer(){
        textView.setText("0:30");
        seekbar.setEnabled(true);
        seekbar.setProgress(30);
        countdowntimer.cancel();
        button.setText("GO!");
        counterIsactive=false;
    }
public void controlTimer(View view) {
    if (counterIsactive == false) {
        counterIsactive = true;
        seekbar.setEnabled(false);
        button.setText("Stop!!");

    countdowntimer=   new CountDownTimer(seekbar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }


            @Override
            public void onFinish() {
                resetTimer();;
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
            }
        }.start();
    }else{
       resetTimer();
    }
}
public void updateTimer(int secondsleft){
    int minutes= (int) secondsleft/60;
    int seconds=secondsleft- (minutes*60);
    String secondString= String.valueOf(seconds);
    if (seconds<9) {

        secondString= "0"+secondString;
    }
    textView.setText(Integer.toString(minutes)+":"+secondString);


}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar= findViewById(R.id.seekbar);
         TextView textView= findViewById(R.id.textView);
        seekbar.setMax(600);
        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
