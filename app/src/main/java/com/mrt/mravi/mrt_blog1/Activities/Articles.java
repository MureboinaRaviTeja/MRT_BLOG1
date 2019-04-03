package com.mrt.mravi.mrt_blog1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.mrt.mravi.mrt_blog1.R;

/**
 * Created by mravi on 26-04-2018.
 */

public class Articles extends AppCompatActivity {

    private CardView cardview;

    private CardView card2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articles);

 cardview=(CardView)findViewById(R.id.cardView);

        card2=(CardView)findViewById(R.id.card2);

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Articles.this, "hello this is article", Toast.LENGTH_SHORT).show();

                Intent is = new Intent(Articles.this, PostListActivityResearch.class);



                startActivity(is);

            }
        });

card2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(Articles.this, "this is feedback", Toast.LENGTH_SHORT).show();

        Intent is = new Intent(Articles.this,FeedBack.class);



        startActivity(is);

    }
});


    }


}
