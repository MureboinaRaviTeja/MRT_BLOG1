package com.example.mravi.mrt_blog1.Util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

/**
 * Created by mravi on 04-02-2018.
 */


/*
public class Heart {
    public static  final String TAG="Heart";
    public ImageView like,likered;

    private static final DecelerateInterpolator DECELERATE_INTERPOLATOR=new DecelerateInterpolator();
    private static  final AccelerateInterpolator ACCELERATE_INTERPOLATOR=new AccelerateInterpolator();


    public Heart(ImageView like, ImageView likered) {
        this.like = like;
        this.likered = likered;
    }

    public void toggleLike(){
        Log.d(TAG,"togglelike:toggling hear");
        AnimatorSet animationSet=new AnimatorSet();
if(likered.getVisibility()==VISIBLE){
    Log.d(TAG,"togglelike:toggling red heart off.");
    likered.setScaleX(0.1f);
    likered.setScaleY(0.1f);

    ObjectAnimator scaleDownY=ObjectAnimator.ofFloat(likered,"scaleY",1f,0f);
    scaleDownY.setDuration(300);
    scaleDownY.setInterpolator(ACCELERATE_INTERPOLATOR);



    ObjectAnimator scaleDownX=ObjectAnimator.ofFloat(likered,"scaleY",1f,0f);
    scaleDownY.setDuration(300);
    scaleDownY.setInterpolator(ACCELERATE_INTERPOLATOR);

likered.setVisibility(View.GONE);
    like.setVisibility(View.VISIBLE);

    animationSet.playTogether(scaleDownY,scaleDownX);

}
       else if(likered.getVisibility()==View.GONE){
            Log.d(TAG,"togglelike:toggling red heart on.");
            likered.setScaleX(0.1f);
            likered.setScaleY(0.1f);

            ObjectAnimator scaleDownY=ObjectAnimator.ofFloat(likered,"scaleY",0.1f,0f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(DECELERATE_INTERPOLATOR);



            ObjectAnimator scaleDownX=ObjectAnimator.ofFloat(likered,"scaleX",0.1f,0f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(DECELERATE_INTERPOLATOR);

            likered.setVisibility(View.VISIBLE);
            like.setVisibility(View.GONE);

            animationSet.playTogether(scaleDownY,scaleDownX);
    //Toast.makeText(Context, "hello", Toast.LENGTH_SHORT).show();

        }


animationSet.start();


    }

}
*/