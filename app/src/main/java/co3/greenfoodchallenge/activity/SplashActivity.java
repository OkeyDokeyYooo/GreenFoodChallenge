package co3.greenfoodchallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import co3.greenfoodchallenge.R;

public class SplashActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;
    private AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv = (TextView) findViewById(R.id.txt);
        iv = (ImageView) findViewById(R.id.loading_icon);
        if(iv == null) throw new AssertionError();
        iv.setBackgroundResource(R.drawable.anim_loading);
        anim = (AnimationDrawable) iv.getBackground();
        anim.start();
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mtransaction);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);
        /**/
        final Intent i = SwapActivity.makeIntent(SplashActivity.this);
        Thread timer = new Thread(){
            public void run(){
                try
                {
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context,SplashActivity.class);
    }
}
