package com.mrt.mravi.mrt_blog1.Model;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import static android.view.View.VISIBLE;

/**
 * Created by mravi on 26-12-2017.
 */

public class Blog {

    public String title;
    public String desc;
    public String image;
    public String timestamp;
    public String userid;



/*
//for like
    public String txt;
    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    //for end like
*/


    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }


/*
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimeStamp() {
        return timestamp;
    }

    public void setTimetamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

*/

    public Blog() {
//needs when adding details into firebase;


    }

    public Blog(String title, String desc, String image, String timestamp, String userid) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.timestamp = timestamp;
        this.userid = userid;
    }


/*
public Blog(String title, String desc, String image, String timestamp, String userid,String txt) {
    this.title = title;
    this.desc = desc;
    this.image = image;
    this.timestamp = timestamp;
    this.userid = userid;
    this.txt=txt;
}
*/
/*

    public static  final String TAG="Heart";
    public ImageView like,likered;

    private static final DecelerateInterpolator DECELERATE_INTERPOLATOR=new DecelerateInterpolator();
    private static  final AccelerateInterpolator ACCELERATE_INTERPOLATOR=new AccelerateInterpolator();


    public Blog(ImageView like, ImageView likered) {
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

            ObjectAnimator scaleDownY= ObjectAnimator.ofFloat(likered,"scaleY",1f,0f);
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

*/

}
