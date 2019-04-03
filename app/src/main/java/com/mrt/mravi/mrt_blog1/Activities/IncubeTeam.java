package com.mrt.mravi.mrt_blog1.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mrt.mravi.mrt_blog1.R;

/**
 * Created by mravi on 11-05-2018.
 */

public class IncubeTeam extends AppCompatActivity {

    AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incubeteam);

        mAdview=(AdView)findViewById(R.id.adView);

        AdRequest adRequest=new AdRequest.Builder().build();


        mAdview.loadAd(adRequest);

    }
}
