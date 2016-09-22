package com.xingmoquan.animation_star;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DrawableAniActivity extends AppCompatActivity {

    private ImageView ivListD;
    private long temTime=0;
    private long currTime=0;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ((AnimationDrawable)ivListD.getDrawable()).stop();
                    ivListD.getDrawable();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_ani);
        ivListD = (ImageView) findViewById(R.id.iv_listD);

//        drawable();
        scaleDrawable();

    }

    private void scaleDrawable() {



    }

    private void drawable() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTime=System.currentTimeMillis();
                if(currTime-temTime>3000){
                    temTime=currTime;
                    AnimationDrawable anDraw = (AnimationDrawable) ivListD.getDrawable();
                    anDraw.start();
                    mHandler.sendEmptyMessageDelayed(1,3000);
                }
            }
        });
    }
}
