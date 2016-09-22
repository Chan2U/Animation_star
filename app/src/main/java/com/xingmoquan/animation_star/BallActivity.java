package com.xingmoquan.animation_star;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.xingmoquan.animation_star.util.Utils;

public class BallActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivHeart;
    private Button btnVer;
    private Button btnGra;
    private int mScreenHeight;
    private static final String TAG ="BallActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);
        mScreenHeight= Utils.getDefaultHeight(this);

        ivHeart= (ImageView) findViewById(R.id.iv_heart_blue);
        btnVer= (Button) findViewById(R.id.btn_ver);
        btnGra= (Button) findViewById(R.id.btn_gra);

        ivHeart.setOnClickListener(this);
        btnVer.setOnClickListener(this);
        btnGra.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_heart_blue:
                break;
            case R.id.btn_ver:
                verCli();
                break;
            case R.id.btn_gra:
                graCli();
                break;
            default:
                break;

        }
    }

    private void graCli() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
        {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue)
            {
                Log.e(TAG, fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                ivHeart.setX(point.x);
                ivHeart.setY(point.y);

            }
        });

    }

    private void verCli() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight
                - 2*ivHeart.getHeight());
        Log.e(TAG,mScreenHeight+"__"+ivHeart.getHeight()+"_"+Utils.dpToPx(BallActivity.this,ivHeart.getHeight()));

        animator.setTarget(ivHeart);
        animator.setDuration(1000).start();
        //      animator.setInterpolator(value)
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                ivHeart.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }
}