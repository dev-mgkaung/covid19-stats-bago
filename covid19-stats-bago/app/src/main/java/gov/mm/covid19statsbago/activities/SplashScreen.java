package gov.mm.covid19statsbago.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import gov.mm.covid19statsbago.R;


/**
 * Created by mk on 4/1/2020.
 */

public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.splashImage)  ImageView splashImage;
    @BindView(R.id.splashText) TextView splashText;

    Animation uptodown,downtoup;
    ArrayList<String> infolist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        Animation_Show();

        RandomTextShow();

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
    private void RandomTextShow()
    {
        Random randomGenerator=new Random();
        infolist.add(getResources().getString(R.string.splash_label_one));
        infolist.add(getResources().getString(R.string.splash_label_two));
        infolist.add(getResources().getString(R.string.splash_label_three));
        infolist.add(getResources().getString(R.string.splash_label_four));
        splashText.setText(infolist.get(randomGenerator.nextInt(3) + 1));
    }

    private void Animation_Show()
    {
        uptodown = AnimationUtils.loadAnimation(this,R.anim.up_to_down);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.down_to_up);

        splashImage.setAnimation(downtoup);
        splashText.setAnimation(uptodown);

        RotateAnimation rotate = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2000);
        rotate.setInterpolator(new LinearInterpolator());
        splashImage.startAnimation(rotate);
    }
}

