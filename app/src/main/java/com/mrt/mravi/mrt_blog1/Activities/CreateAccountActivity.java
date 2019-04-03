package com.mrt.mravi.mrt_blog1.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mrt.mravi.mrt_blog1.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CreateAccountActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    //  private Button createAccountBtn;
    private TextView createAccountBtn;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private ImageButton profilePic;
    private final static int GALLERY_CODE = 1;
    private Uri resultUri = null;
    private StorageReference mFirebaseStorage;


    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.cv_add)
    CardView cvAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme1);

        setContentView(R.layout.activity_create_account);

        //material login
/*

*/
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });

//end of material login



    mDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mDatabase.getReference().child("MUsers");
        //creates new users in fb
        mAuth=FirebaseAuth.getInstance();


       // mFirebaseStorage=FirebaseStorage.getInstance().getReference().child("MBlog_Profile_pics");

       // mProgressDialog=new ProgressDialog(this);
    //mProgressDialog = new ProgressBar(this);

        mFirebaseStorage=FirebaseStorage.getInstance().getReference().child("MBlog_Profile_pics");



        mProgressDialog=new ProgressDialog(this);

        firstName=(EditText)findViewById(R.id.firstNameAct);
        lastName=(EditText)findViewById(R.id.lastNameAct);
        email=(EditText)findViewById(R.id.emailAct);
        password=(EditText)findViewById(R.id.passwordAct);
        profilePic=(ImageButton)findViewById(R.id.profilePic);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fetch the image
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });

        createAccountBtn=(TextView)findViewById(R.id.createAccountAct);

        //createAccountBtn=(Button)findViewById(R.id.createAccountAct);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });



    }

    private void createNewAccount() {

        final String name=firstName.getText().toString().trim();
        final String lname=lastName.getText().toString().trim();
        String em=email.getText().toString().trim();
        String pwd=password.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(lname)&&!TextUtils.isEmpty(em)&&!TextUtils.isEmpty(pwd)){

            mProgressDialog.setMessage("Creating Account...");
        mProgressDialog.show();

            mAuth.createUserWithEmailAndPassword(em,pwd)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if(authResult!=null){

                                //StorageReference imagePath=mFirebaseStorage.c

StorageReference imagePath=mFirebaseStorage.child("MBlog_Profile_pics")
        .child(resultUri.getLastPathSegment());

                                imagePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        String userid=mAuth.getCurrentUser().getUid();
                                        DatabaseReference currentUserDb=mDatabaseReference.child(userid);
                                        currentUserDb.child("firstname").setValue(name);
                                        currentUserDb.child("lastname").setValue(lname);
                                        currentUserDb.child("image").setValue(resultUri.toString());
                                        //currentUserDb.child("so")
                                        mProgressDialog.dismiss();

                                        //send users to postList;
                                        Intent intent=new Intent(CreateAccountActivity.this,PostListActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                });




                                /*

*/

                            }
                        }
                    });




        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

    if(requestCode==GALLERY_CODE&&resultCode==RESULT_OK){
Uri mImageUri=data.getData();
        CropImage.activity(mImageUri)
                .setAspectRatio(1,1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(CreateAccountActivity.this);
    }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                 resultUri = result.getUri();
                profilePic.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }


    }



    //material create


    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
               CreateAccountActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    //material create end


}
