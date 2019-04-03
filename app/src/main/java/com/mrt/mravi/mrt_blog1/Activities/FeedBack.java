package com.mrt.mravi.mrt_blog1.Activities;

import
        android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mrt.mravi.mrt_blog1.R;

/**
 * Created by mravi on 13-02-2018.
 */

public class FeedBack extends AppCompatActivity{

    Button sendEmailButton;
    EditText emailAddress;
    EditText emailSubject;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        //emailAddress = (EditText) findViewById(R.id.email);
        //emailSubject = (EditText) findViewById(R.id.subject);
        message = (EditText) findViewById(R.id.message);
        sendEmailButton = (Button) findViewById(R.id.send_button);

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
    }
}




