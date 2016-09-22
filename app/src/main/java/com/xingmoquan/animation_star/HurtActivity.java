package com.xingmoquan.animation_star;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xingmoquan.animation_star.util.StrokeTextView;

public class HurtActivity extends AppCompatActivity {

    private Button btn;
    private ImageView ivHurt1;
    private ImageView ivHurt2;
    private ImageView ivHurt3;
    private StrokeTextView tvHurtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hurt);

        btn = (Button) findViewById(R.id.btn);
        ivHurt1 = (ImageView) findViewById(R.id.iv_hurt1);
        ivHurt2 = (ImageView) findViewById(R.id.iv_hurt2);
        ivHurt3 = (ImageView) findViewById(R.id.iv_hurt3);
        tvHurtUsername= (StrokeTextView) findViewById(R.id.tv_hurt_text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 这个OK
                hurtAnimationStart(ivHurt1,ivHurt2,ivHurt3);
                hurtNameAnimationStart(tvHurtUsername);
            }
        });
    }


    private void hurtNameAnimationStart(final View tvHurtUsername) {
        PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("scaleX", 1f, 1f, 1f);
        ObjectAnimator objAnim1 = ObjectAnimator.ofPropertyValuesHolder(tvHurtUsername, pvhX1);
        objAnim1.setDuration(300);

        PropertyValuesHolder pvhX2 = PropertyValuesHolder.ofFloat("scaleX", 0.89f, 0.89f, 1f);
        PropertyValuesHolder pvhY2 = PropertyValuesHolder.ofFloat("scaleY", 0.89f, 0.89f, 1f);

        PropertyValuesHolder pvhX3 = PropertyValuesHolder.ofFloat("scaleX", 1f, 1f, 0.89f);
        PropertyValuesHolder pvhY3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 1f, 0.89f);

        final ObjectAnimator[] objAnisName = new ObjectAnimator[10];
        for (int i = 0; i < objAnisName.length; i++) {
            if (i % 2 == 0) {
                objAnisName[i] = ObjectAnimator.ofPropertyValuesHolder(tvHurtUsername, pvhX2, pvhY2);
            } else {
                objAnisName[i] = ObjectAnimator.ofPropertyValuesHolder(tvHurtUsername, pvhX3, pvhY3);
            }
            objAnisName[i].setDuration(10);
        }

        objAnim1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                tvHurtUsername.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                AnimatorSet animSet = new AnimatorSet();
                for (int i = 0; i < objAnisName.length - 1; i++) {
                    animSet.play(objAnisName[i + 1]).after(objAnisName[i]);
                }
                animSet.setDuration(10 * 10);
                animSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        tvHurtUsername.setVisibility(View.INVISIBLE);
                    }
                });
                animSet.start();
            }
        });
        objAnim1.start();
    }

    private void hurtAnimationStart(final View ivHurt1, final View ivHurt2, final View ivHurt3) {
        PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("scaleX", 1f, 1f, 1f);
        ObjectAnimator objAnim1=ObjectAnimator.ofPropertyValuesHolder(ivHurt1, pvhX1);
        objAnim1.setDuration(300);

        PropertyValuesHolder pvhX2 = PropertyValuesHolder.ofFloat("scaleX", 0.89f, 0.89f, 1f);
        PropertyValuesHolder pvhY2 = PropertyValuesHolder.ofFloat("scaleY", 0.89f, 0.89f, 1f);

        PropertyValuesHolder pvhX3 = PropertyValuesHolder.ofFloat("scaleX", 1f, 1f, 0.89f);
        PropertyValuesHolder pvhY3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 1f, 0.89f);

        final ObjectAnimator[] objAnis = new ObjectAnimator[10];
        for (int i = 0; i < objAnis.length; i++) {
            if (i % 2 == 0) {
                objAnis[i] = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
                objAnis[i].addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        ivHurt1.setVisibility(View.INVISIBLE);
                        ivHurt2.setVisibility(View.VISIBLE);
                        ivHurt3.setVisibility(View.INVISIBLE);
                    }
                });
            } else {
                objAnis[i] = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
                objAnis[i].addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        ivHurt1.setVisibility(View.INVISIBLE);
                        ivHurt2.setVisibility(View.INVISIBLE);
                        ivHurt3.setVisibility(View.VISIBLE);
                    }
                });
            }
            objAnis[i].setDuration(10);
        }

        objAnim1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt1.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                AnimatorSet animSet = new AnimatorSet();
                for(int i=0;i<objAnis.length-1;i++){
                    animSet.play(objAnis[i+1]).after(objAnis[i]);
                }
                animSet.setDuration(10 * 10);
                animSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ivHurt1.setVisibility(View.INVISIBLE);
                        ivHurt2.setVisibility(View.INVISIBLE);
                        ivHurt3.setVisibility(View.INVISIBLE);
                    }
                });
                animSet.start();
            }
        });
        objAnim1.start();

      /*  AnimatorSet animSet = new AnimatorSet();
        for(int i=0;i<objAnis.length-1;i++){
            animSet.play(objAnis[i+1]).after(objAnis[i]);
        }
        animSet.setDuration(3 * 40);
        animSet.start();*/



//        objAnis[0] = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);

    /*    ObjectAnimator anim0 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim0.setDuration(10);
        anim0.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim1 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim1.setDuration(10);
        anim1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        ObjectAnimator anim2 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim2.setDuration(10);
        anim2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim3 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim3.setDuration(10);
        anim3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        ObjectAnimator anim4 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim4.setDuration(10);
        anim4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim5 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim5.setDuration(10);
        anim5.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        ObjectAnimator anim6 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim6.setDuration(10);
        anim6.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim7 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim7.setDuration(10);
        anim7.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        ObjectAnimator anim8 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim8.setDuration(10);
        anim8.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim9 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim9.setDuration(10);
        anim9.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        //==================================
        ObjectAnimator anim10 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim10.setDuration(10);
        anim10.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim11 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim11.setDuration(10);
        anim11.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        ObjectAnimator anim12 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim12.setDuration(10);
        anim12.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim13 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim13.setDuration(10);
        anim13.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        ObjectAnimator anim14 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim14.setDuration(10);
        anim14.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim15 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim15.setDuration(10);
        anim15.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        ObjectAnimator anim16 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim16.setDuration(10);
        anim16.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim17 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim17.setDuration(10);
        anim17.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });

        ObjectAnimator anim18 = ObjectAnimator.ofPropertyValuesHolder(ivHurt2, pvhX2, pvhY2);
        anim18.setDuration(10);
        anim18.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt2.setVisibility(View.VISIBLE);
                ivHurt3.setVisibility(View.INVISIBLE);
            }
        });
        ObjectAnimator anim19 = ObjectAnimator.ofPropertyValuesHolder(ivHurt3, pvhX3, pvhY3);
        anim19.setDuration(10);
        anim19.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivHurt3.setVisibility(View.VISIBLE);
                ivHurt2.setVisibility(View.INVISIBLE);
            }
        });*/



       /* animSet.play(anim1).after(anim0);
        animSet.play(anim2).after(anim1);
        animSet.play(anim3).after(anim2);
        animSet.play(anim4).after(anim3);
        animSet.play(anim5).after(anim4);
        animSet.play(anim6).after(anim5);
        animSet.play(anim7).after(anim6);
        animSet.play(anim8).after(anim7);
        animSet.play(anim9).after(anim8);

        animSet.play(anim10).after(anim9);

        animSet.play(anim11).after(anim10);
        animSet.play(anim12).after(anim11);
        animSet.play(anim13).after(anim12);
        animSet.play(anim14).after(anim13);
        animSet.play(anim15).after(anim14);
        animSet.play(anim16).after(anim15);
        animSet.play(anim17).after(anim16);
        animSet.play(anim18).after(anim17);
        animSet.play(anim19).after(anim18);

        animSet.setDuration(10 * 20);
        animSet.start();*/

    }
}
