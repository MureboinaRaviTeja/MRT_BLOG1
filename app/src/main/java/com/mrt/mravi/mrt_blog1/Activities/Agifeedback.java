package com.mrt.mravi.mrt_blog1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrt.mravi.mrt_blog1.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by mravi on 26-04-2018.
 */

public class Agifeedback extends AppCompatActivity {

//for mail

    Button sendEmailButton;
    EditText emailAddress;
    EditText emailSubject;
    EditText message;
    //end mail



    AdView mAdview;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agifeedback);


        //ads


        MobileAds.initialize(this,"ca-app-pub-1330885432702758~6287467130");


        mAdview=(AdView)findViewById(R.id.adView);

        AdRequest adRequest=new AdRequest.Builder().build();

        mAdview.loadAd(adRequest);


//ads



        //sending mail

        message = (EditText) findViewById(R.id.messageagi);
        sendEmailButton = (Button) findViewById(R.id.send_buttonagi);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String toemailAddress = emailAddress.getText().toString();
                String toemailAddress="15h61a0530@cvsr.ac.in";
                String msubject ="incube";
                String mmessage = message.getText().toString();

                Intent emailApp = new Intent(Intent.ACTION_SEND);
                emailApp.putExtra(Intent.EXTRA_EMAIL, new String[]{toemailAddress});
                emailApp.putExtra(Intent.EXTRA_SUBJECT, msubject);
                emailApp.putExtra(Intent.EXTRA_TEXT, mmessage);
                emailApp.setType("message/rfc822");
                startActivity(Intent.createChooser(emailApp, "Send Email Via"));

            }
        });




        //sending mail



    }








}