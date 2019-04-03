//package com.example.mravi.mrt_blog1.Activities;
package com.mrt.mravi.mrt_blog1.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mrt.mravi.mrt_blog1.R;
import com.mrt.mravi.mrt_blog1.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.yavski.fabspeeddial.FabSpeedDial;

public class MainActivityMD7 extends AppCompatActivity {


    //material login


    @BindView(R.id.loginEmailid)
    EditText loginEmailid;
    @BindView(R.id.loginPasswordid)
    EditText loginPasswordid;
    @BindView(R.id.loginButtonid)
    Button loginButtonid;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;



    //end of material login

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button loginButton;
    //private Button createActButton;
    private EditText emailField;
    private EditText passwordField;
private TextView createActButton;

    private FirebaseUser mUser;


//material

    private FloatingActionButton fab1;
    //material

    //google signin


    private SignInButton mGoogleBtn;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    //private GoogleSignInApi mGoogleSignInClient;
  //  private FirebaseAuth mAuth;

    private static final String TAG = "Main_Activity";
  //  private FirebaseAuth.AuthStateListener mAuthListener;

    //end of google signin


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_md7);

//floating action bar code







//mateial login

        ButterKnife.bind(this);

        //material login




        mAuth=FirebaseAuth.getInstance();

        loginButton=(Button)findViewById(R.id.loginButtonid);
        //createActButton=(Button)findViewById(R.id.createact);
        emailField=(EditText)findViewById(R.id.loginEmailid);
        passwordField=(EditText)findViewById(R.id.loginPasswordid);

    /*
        createActButton=(TextView)findViewById(R.id.createact);

createActButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivityMD7.this,CreateAccountActivity.class));
    finish();
    }
});
*/



        //google login


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                Toast.makeText(MainActivityMD7.this, "heloo u have logined", Toast.LENGTH_SHORT).show();


//normal login


                mUser=firebaseAuth.getCurrentUser();

                if(mUser!=null){
                    Toast.makeText(MainActivityMD7.this, "Sign in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivityMD7.this,PostListActivity.class));
                    finish();

                }
                else {
                    Toast.makeText(MainActivityMD7.this, "Not signed in ", Toast.LENGTH_SHORT).show();
                }





                //noraml login





            }






        };

        mGoogleBtn = (SignInButton) findViewById(R.id.googleBtn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MainActivityMD7.this, "You got error", Toast.LENGTH_SHORT).show();

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });








        //end of google login



        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //sees wheather the user is connected or not
    mUser=firebaseAuth.getCurrentUser();

                if(mUser!=null){
                    Toast.makeText(MainActivityMD7.this, "Sign in", Toast.LENGTH_SHORT).show();
              startActivity(new Intent(MainActivityMD7.this,PostListActivity.class));
                     finish();

                }
                else {
                    Toast.makeText(MainActivityMD7.this, "Not signed in ", Toast.LENGTH_SHORT).show();
                }



            }
        };


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(!TextUtils.isEmpty(emailField.getText().toString())&&!TextUtils.isEmpty(passwordField.getText().toString())){
               String email=emailField.getText().toString();
                String pwd=passwordField.getText().toString();



                login(email,pwd);

            }
            else {

            }


            }
        });

    }

    private void login(String email, String pwd) {

        mAuth.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivityMD7.this, "Signed in ", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainActivityMD7.this,PostListActivity.class));

                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivityMD7.this, "Failed to Signin", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    //for signout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


    if(item.getItemId()==R.id.action_signout){

        mAuth.signOut();
    }


        return super.onOptionsItemSelected(item);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //-->end of signout


    @Override
    protected void onStart() {
        super.onStart();

    mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

if(mAuthListener!=null)
{
    //removes the authorazation;
    //remove the infinte loop;

    mAuth.removeAuthStateListener(mAuthListener);
}


    }




    //material login







    @OnClick({R.id.loginButtonid, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                //startActivity(new Intent(this,CreateAccountActivity.class));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ||Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                startActivity(new Intent(this,CreateAccountActivity.class),options.toBundle());
                    Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();


                } else {
                    //startActivity(new Intent(this,CreateAccountActivity.class));
                    Toast.makeText(this, "else", Toast.LENGTH_SHORT).show();
                }
                break;*/
            case R.id.loginButtonid:
                Explode explode = new Explode();explode.setDuration(500);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Intent i2 = new Intent(this,PostListActivity.class);
                startActivity(i2, oc2.toBundle());
                break;
        }

    }

    //material login



    //google login




    private void signIn() {

        // Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //startActivityForResult(signInIntent, RC_SIGN_IN);

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            //Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //GoogleSignInResult result=Auth.GOOGLE_SIGN_IN_API.getSignInResultFromIntent(data);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }

            /*
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

                // Google Sign In failed, update UI appropriately
               // Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        */

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {


        //firebaseAuthWithGoogle go
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .

                        addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                Log.d(TAG, "signinwithcredential:oncomplete:" + task.isSuccessful());

                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signinwithcredential", task.getException());
                                    Toast.makeText(MainActivityMD7.this, "auth failed", Toast.LENGTH_SHORT).show();
                                }
           /*
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithCredential:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithCredential:failure", task.getException());
                Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                updateUI(null);
            }
*/
                                // ...

                            }
                        });
    }


    //end of google login



}
