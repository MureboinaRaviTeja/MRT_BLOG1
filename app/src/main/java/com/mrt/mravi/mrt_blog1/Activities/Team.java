package com.mrt.mravi.mrt_blog1.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.mrt.mravi.mrt_blog1.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by mravi on 16-02-2018.
 */

public class Team extends AppCompatActivity {

    AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team);






        MobileAds.initialize(this,"ca-app-pub-1330885432702758~6287467130");


        mAdview=(AdView)findViewById(R.id.adView);

        AdRequest adRequest=new AdRequest.Builder().build();

        mAdview.loadAd(adRequest);



    }
}