package com.mrt.mravi.mrt_blog1.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mrt.mravi.mrt_blog1.Model.Blog;
import com.mrt.mravi.mrt_blog1.R;
//import com.example.mravi.mrt_blog1.Util.Heart;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity  {
private final String TAG="addpostactivity";
//  private ImageButton mPostImage;
  private  ImageView mPostImage;
   // private VideoView mPostImage;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button mSubmitButton;
    private DatabaseReference mPostDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private  FirebaseDatabase mDatabase;


    //adding progresses bar;

    //adding text for like exg
   // private TextView txt;
private  ImageView imageButton;
//private  ImageButton imageButton;
    private ProgressDialog mProgress;

private Uri mImageUri;
    //Uniform resourse identifier; now it is the path to thee image;

    private  static final int GALLERY_CODE=1;
private StorageReference  mStorage;

//like
   private Blog mHeart;
    private ImageView mheartred;
    private ImageView mheartwhite;
    private GestureDetector mGestureDetector;

    AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
//like
        //mheartred.setVisibility(View.GONE);
        //mHeart= new Blog(mheartwhite,mheartred);

       // mGestureDetector=new GestureDetector(this, new  GestureListener());
//


 //ads


        MobileAds.initialize(this,"ca-app-pub-1330885432702758~6287467130");


        mAdview=(AdView)findViewById(R.id.adView);

        AdRequest adRequest=new AdRequest.Builder().build();

        mAdview.loadAd(adRequest);



        //ads

        mProgress=new ProgressDialog(this);


    mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        mPostDatabase=FirebaseDatabase.getInstance().getReference().child("MBlog");


        mStorage= FirebaseStorage.getInstance().getReference();//get a reference that is a link;


//mPostImage=(VideoView)findViewById(R.id.imageButton);
     //mPostImage=(ImageButton)findViewById(R.id.imageButton);

       mPostImage=(ImageView)findViewById(R.id.imageButton);
        mPostTitle=(EditText)findViewById(R.id.postTitleid);
        mPostDesc=(EditText)findViewById(R.id.descriptionid);
        mSubmitButton=(Button)findViewById(R.id.submitPost);



      //  <!--for links clickable-->

mPostDesc.setLinksClickable(true);
mPostDesc.setAutoLinkMask(Linkify.WEB_URLS);
        mPostDesc.setMovementMethod(MyMovementMethod.getInstance());





        mPostDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                Linkify.addLinks(s,Linkify.WEB_URLS);

            }
        });

      //end of links clickable

        //for like
        //txt=(TextView)findViewById(R.id.txt);

        mPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a gallery intent
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                //now specify what type of content u want to specify

              galleryIntent.setType("image/*");//
                //"/*" means all types of images are accepted here

               // galleryIntent.setType("video/*");//

                startActivityForResult(galleryIntent,GALLERY_CODE);


            }
        });


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //posting our database;

             startPosting();


            }
        });


        //testToggle();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 if(requestCode==GALLERY_CODE&&resultCode==RESULT_OK){

     mImageUri=data.getData();
   mPostImage.setImageURI(mImageUri);

     //mPostImage.setVideoPath(String.valueOf(mImageUri));

 }



    }

    private void startPosting() {

        mProgress.setMessage("Posting to MRTBLOGEducation");
        mProgress.show();

        final String titleVal=mPostTitle.getText().toString().trim();
        final String desVal=mPostDesc.getText().toString().trim();
        if(!TextUtils.isEmpty(titleVal)&&!TextUtils.isEmpty(desVal)&&mImageUri!=null){
            //start the uploading...

            /*
            Blog blog=new Blog("Title","description","imageurl","timestamp","userid");//creating blog object

            //callback is is the methodd that calls once the action is  completed;
            mPostDatabase.setValue(blog).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),"Item added",Toast.LENGTH_LONG).show();
                    mProgress.dismiss();//after processs is over the progress bar will dismiss i.e disapper
}});
*/


//creating storeage reference path;

//mImageUri.getLastPathSegment()==/image/myphoto.jpeg

        StorageReference filepath=mStorage.child("MBlog_imagesEducation").child(mImageUri.getLastPathSegment());

            //to store file in db
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
       Uri downloadurl=taskSnapshot.getDownloadUrl();

                    //creating anotther database referece;
                    //create a new item with unique reference and the newpost will add the methods
                    DatabaseReference newPost=mPostDatabase.push();

                    //creating a hashmap
                    Map<String,String> dataToSave=new HashMap<>();
                    dataToSave.put("title",titleVal);
                    dataToSave.put("desc",desVal);
                    dataToSave.put("image",downloadurl.toString());
                    dataToSave.put("timestamp",String.valueOf(java.lang.System.currentTimeMillis()));
                    dataToSave.put("userid",mUser.getUid());







                    newPost.setValue(dataToSave);

                    //newPost.child("titile").setValue(titileval);//other way


                    mProgress.dismiss();

startActivity(new Intent(AddPostActivity.this,PostListActivity.class));


                    finish();






                }
            });



        }




    }

/*
    private void testToggle(){

        mheartred.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mGestureDetector.onTouchEvent(motionEvent);
            }
        });

        mheartwhite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mGestureDetector.onTouchEvent(motionEvent);
            }
        });

    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;

        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
Log.d(TAG,"on doubletap :doubletap");
            mHeart.toggleLike();
            Toast.makeText(AddPostActivityEducation.this, "double click", Toast.LENGTH_SHORT).show();
            return  true;
        }
    }
    */
}
