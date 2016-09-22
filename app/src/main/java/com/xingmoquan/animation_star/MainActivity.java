package com.xingmoquan.animation_star;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.xingmoquan.animation_star.util.StrokeTextView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivHeart;
    private StrokeTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHeart = (ImageView) findViewById(R.id.iv_heart);
        tv= (StrokeTextView) findViewById(R.id.tv_mua_text);
        findViewById(R.id.tv_an).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                anStart();
                //TODO 这个OK
                anStart(ivHeart);
                anStart(tv);
            }
        });

        findViewById(R.id.tv_an_be).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                anStart();
                anStartBe(ivHeart);
            }
        });


    }

    private void anStartBe(final View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0f,
                0f, 0.8f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 0f,
                0f, 0.8f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 0f,
                0f, 0.8f);
        view.setVisibility(View.VISIBLE);
        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000);
//        oa.setInterpolator(new BezierInterpolator(0.13f, 0.71f, 0.82f, 0.27f));
//        oa.setInterpolator(new OvershootInterpolator());
        oa.setInterpolator(new CustomInterpolator());
        oa.addListener(new AnimatorListenerAdapter() {
         /*   @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }*/

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("alpha", 0.8f,
                        0.8f, 1.0f);
                ObjectAnimator.ofPropertyValuesHolder(view,pvhX1).setDuration(2000).start();
//                view.setVisibility(View.INVISIBLE);
            }
        });
        oa.start();


    }

   /* private void anStart() {
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(ivHeart, "zhy", 0.0F,  1.0F)//
                .setDuration(500);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float cVal = (Float) animation.getAnimatedValue();
                ivHeart.setAlpha(cVal);
                ivHeart.setScaleX(cVal);
                ivHeart.setScaleY(cVal);
            }
        });
    }*/

    private void anStart(final View view) {
        PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat("alpha", 0f,
                0f, 1.0f);
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 0f,
                0f, 0.7f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 0f,
                0f, 0.7f);
//        view.setVisibility(View.VISIBLE);
        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(view,pvhA, pvhX, pvhY).setDuration(1200);
//        oa.setInterpolator(new BezierInterpolator(0.13f, 0.71f, 0.82f, 0.27f));
        oa.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("alpha", 1.0f,
                        1.0f, 0.0f);
                PropertyValuesHolder pvhY1 = PropertyValuesHolder.ofFloat("scaleX", 0.7f,
                        0.7f, 1.0f);
                PropertyValuesHolder pvhZ1 = PropertyValuesHolder.ofFloat("scaleY", 0.7f,
                        0.7f, 1.0f);
                ObjectAnimator oa= ObjectAnimator.ofPropertyValuesHolder(view,pvhX1,pvhY1,pvhZ1).setDuration(1200);
                oa.setStartDelay(2000);
                oa.start();

//                view.setVisibility(View.INVISIBLE);
            }
        });
        oa.start();

    }


    public class CustomInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            // 编写相关的逻辑计算
            //input *= 0.8f;
            return input * input;
        }
    }
}
