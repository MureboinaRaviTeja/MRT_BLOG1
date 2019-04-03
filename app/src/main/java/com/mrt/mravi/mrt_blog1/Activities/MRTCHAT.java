package com.mrt.mravi.mrt_blog1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrt.mravi.mrt_blog1.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mravi on 24-02-2018.
 */

public class MRTCHAT extends AppCompatActivity {



    private static int SIGN_IN_REQUEST_CODE=1;
    private FirebaseListAdapter<ChatMessage> adapter;

    RelativeLayout activity_main;

    FloatingActionButton fab;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.action_signout){
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_main,"you have been signout",Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

        return true;
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.main_menu,menu);

        //    return super.onCreateOptionsMenu(menu);
        return  true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SIGN_IN_REQUEST_CODE)
        {
            if(resultCode==RESULT_OK){
                Snackbar.make(activity_main,"successfully signed in",Snackbar.LENGTH_SHORT).show();
        //        displayChatMessage();

                Toast.makeText(this, "Under Process", Toast.LENGTH_SHORT).show();
            }
            else {
                Snackbar.make(activity_main,"could not sign in try again",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mrt_main);

        activity_main=(RelativeLayout)findViewById(R.id.activity_main);

        fab=(FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input=(EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");

            }
        });



        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);

        }
        else
        {
            //if(FirebaseAuth.getInstance().getCurrentUser().getEmail()=='')
            Snackbar.make(activity_main,"welcome"+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();


            displayChatMessage();
        }


    }

    private void displayChatMessage() {
        ListView listOfMessages=(ListView) findViewById(R.id.list_of_message);
        adapter =new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference())
        {

            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                //get reference to the view  of the list item ;


                TextView messageText,messageUser,messageTime;
                messageText=(TextView)v.findViewById(R.id.message_text);
                messageUser=(TextView)v.findViewById(R.id.message_user);
                messageTime=(TextView)v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy  (HH:mm:ss)",model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }
}
