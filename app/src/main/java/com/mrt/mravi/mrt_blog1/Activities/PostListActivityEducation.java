package com.mrt.mravi.mrt_blog1.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.mrt.mravi.mrt_blog1.Data.BlogRecyclerAdapter;
import com.mrt.mravi.mrt_blog1.Model.Blog;
import com.mrt.mravi.mrt_blog1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import uk.co.senab.photoview.PhotoViewAttacher;

//import com.example.mravi.mrt_blog1.Util.Heart;

public class PostListActivityEducation extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<Blog> blogList;

    private EditText mPostDesc;
//like
    private ImageView mheartred;
    private ImageView mheartwhite;
    private GestureDetector mGestureDetector;
    private ImageButton imageView;
//private Heart mHeart;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_listeducation);


        mPostDesc=(EditText)findViewById(R.id.descriptionid);
        //mheartred=(ImageView)findViewById(R.id.like);
        //mheartwhite=(ImageView)findViewById(R.id.likered);


        //material drawer


        //toolbar =(Toolbar)findViewById(R.id.toolbarq);

        toolbar=(Toolbar)findViewById(R.id.toolbarMain);
        //toolbar.setTitle("Material Drawer");

//zoom

/*
        imageView=(ImageButton) findViewById(R.id.imageButton);
        PhotoViewAttacher photoView=new PhotoViewAttacher(imageView);
        photoView.update();
*/
        //end

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.blog)
                .addProfiles(
                        new ProfileDrawerItem().withName("MRT").withEmail("mrt@gmail.com").withIcon(getResources().getDrawable(R.drawable.cvsr1))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();




        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Education").withIcon(getResources().getDrawable(R.drawable.education));
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Articles").withIcon(getResources().getDrawable(R.drawable.technology));
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Announcement").withIcon(getResources().getDrawable(R.drawable.announcement));
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Others").withIcon(getResources().getDrawable(R.drawable.others));
       // SecondaryDrawerItem item5 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(5).withName("About");
        SecondaryDrawerItem item5 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(5).withName("publish").withIcon(getResources().getDrawable(R.drawable.publish));
        SecondaryDrawerItem item8= (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(8).withName("chat").withIcon(getResources().getDrawable(R.drawable.chat));
        SecondaryDrawerItem item6 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(6).withName("Developer").withIcon(getResources().getDrawable(R.drawable.team));
        SecondaryDrawerItem item9 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(9).withName("Signout").withIcon(getResources().getDrawable(R.drawable.signout));







/*

                imageButton=(ImageButton)findViewById(R.id.imageButton);
        PhotoViewAttacher photoView=new PhotoViewAttacher(imageButton);
        photoView.update();

*/


//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1, item2, item3, item4,
                        new DividerDrawerItem(),
                        item5, item8,item6,item9

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        switch(position){
                            case 1:
                                Toast.makeText(PostListActivityEducation.this, "YOU r in Education Page", Toast.LENGTH_SHORT).show();
                                //Intent i=new Intent(PostListActivityEducation.this, AddPostActivityEducation.class);
                                //startActivity(i);

                                break;

                            case 2:
                                Toast.makeText(PostListActivityEducation.this, "Setting", Toast.LENGTH_SHORT).show();
                              Intent is=new Intent(PostListActivityEducation.this,Articles.class);
                                startActivity(is);
                                finish();
                                break;
                            case 3:
                                Toast.makeText(PostListActivityEducation.this, "Setting", Toast.LENGTH_SHORT).show();
                                Intent isa=new Intent(PostListActivityEducation.this, PostListActivityAnnouncement.class);
                                startActivity(isa);
                                finish();
                                break;
                            case 4:
                                Toast.makeText(PostListActivityEducation.this, "Setting", Toast.LENGTH_SHORT).show();
                                Intent b=new Intent(PostListActivityEducation.this,PostListActivity.class);
                                startActivity(b);
                                finish();
                                break;

                            case 5:
                                Toast.makeText(PostListActivityEducation.this, "Sign out", Toast.LENGTH_SHORT).show();

                                // Intent bs=new Intent(PostListActivity.this,PublishActivity.class);
                                //startActivity(bs);
                                //finish();
                                break;

                            case 6:

                                Toast.makeText(PostListActivityEducation.this, "Publish", Toast.LENGTH_SHORT).show();
                                Intent bs=new Intent(PostListActivityEducation.this,PublishActivity.class);
                                startActivity(bs);
                                finish();


                                /* if(mUser!=null&&mAuth!=null){
                                    //there is user and he is authorised
                                    mAuth.signOut();

                                    startActivity(new Intent(PostListActivity.this,MainActivityMD7.class));
                                    finish();//so that  when user cliks back they will not able to see

                                }

*/
                                break;
                            /*
                            case 8:
                                //  Toast.makeText(PostListActivity.this, "Sign out", Toast.LENGTH_SHORT).show();
                                if (mUser != null && mAuth != null) {
                                    //there is user and he is authorised
                                    mAuth.signOut();

                                    startActivity(new Intent(PostListActivityEducation.this, MainActivityMD7.class));
                                    finish();//so that  when user cliks back they will not able to see

                                }

                                // Intent bs=new Intent(PostListActivity.this,PublishActivity.class);
                                //startActivity(bs);
                                //finish();
                                break;

                            case 7:
                                Intent az=new Intent(PostListActivityEducation.this,Team.class);
                                startActivity(az);

                                */



                                //new

                            case 8:

                                Intent az=new Intent(PostListActivityEducation.this,Team.class);
                                startActivity(az);
                                finish();


                              /*  Toast.makeText(PostListActivity.this, "Sign out", Toast.LENGTH_SHORT).show();
                                if (mUser != null && mAuth != null) {
                                    //there is user and he is authorised
                                    mAuth.signOut();

                                    startActivity(new Intent(PostListActivity.this, MainActivityMD7.class));
                                    finish();//so that  when user cliks back they will not able to see

                                }
*/
                                // Intent bs=new Intent(PostListActivity.this,PublishActivity.class);
                                //startActivity(bs);
                                //finish();
                                break;

                            case 7:
                                // Intent az=new Intent(PostListActivity.this,Team.class);
                                //startActivity(az);

                                //Toast.makeText(PostListActivityEducation.this, "Under Process", Toast.LENGTH_SHORT).show();
                                Intent bz=new Intent(PostListActivityEducation.this,MRTCHAT.class);
                                startActivity(bz);
                                finish();


                                break;
                            case 9:
                                //Intent bz=new Intent(PostListActivity.this,MRTCHAT.class);
                                // startActivity(bz);
                                //finish();

                                //  Intent az=new Intent(PostListActivity.this,Team.class);
                                // startActivity(az);
                                //finish();

                                Toast.makeText(PostListActivityEducation.this, "Sign out", Toast.LENGTH_SHORT).show();
                                if (mUser != null && mAuth != null) {
                                    //there is user and he is authorised
                                    mAuth.signOut();

                                    startActivity(new Intent(PostListActivityEducation.this, MainActivityMD7.class));
                                    finish();//so that  when user cliks back they will not able to see

                                }

                                //new

                        }


                        return true;
                    }
                })
                .build();



        //end of material drawer





        //like
    // mheartred.setVisibility(android.view.View.GONE);
       // mHeart= new Heart(mheartwhite,mheartred);

        //mGestureDetector=new GestureDetector(this, new GestureListener());


        //links


      //  mPostDesc.setMovementMethod(LinkMovementMethod.getInstance());
        //mPostDesc.setMovementMethod(ArrowKeyMovementMethod.getInstance());


/*
        @Override
        public void onCreateContextMenu(ContextMenu menu, View  view,ContextMenuInfo menuInfo) {
            if(mPostDesc.getSelectionStart() == -1){ // in case of setMovementMethod(LinkMovementMethod.getInstance())
                menu.add(0, 1, 0, "Enable copy");
            }
            else{
                menu.add(0, 2, 0, "Enable links");
            }
        }
        @Override
        public boolean onContextItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    et.setMovementMethod(ArrowKeyMovementMethod.getInstance());
                    et.setSelection(0, 0);
                    //re-register EditText for context menu:
                    unregisterForContextMenu(et);
                    registerForContextMenu(et);
                    break;
                case 2:
                    et.setMovementMethod(LinkMovementMethod.getInstance());
                    break;
            }
            return true;
        }

*/
        //mPostDesc.setLinksClickable(true);
       // mPostDesc.setAutoLinkMask(Linkify.WEB_URLS);
        //mPostDesc.setMovementMethod(MyMovementMethod.getInstance());

/*
        mPostDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                Linkify.addLinks(s, Linkify.WEB_URLS);

            }
        });
*/
        //end of links





        //start floating button

        FabSpeedDial fabSpeedDial=(FabSpeedDial)findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;//if false it will not show the menu(child menu)
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                //Toast.makeText(PostListActivity.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                String post= (String) menuItem.getTitle();

                if(post.equals("ADD POST"))
                {
                    if(mUser!=null&&mAuth!=null){
                        //there is user and he is authorised
                        String email=mUser.getEmail();
                        if(email.equals("cvsr@gmail.com")||email.equals("cse3a@gmail.com")||email.equals("cse3b@gmail.com")||email.equals("cse3c@gmail.com")||email.equals("cse3d@gmail.com")) {
                            startActivity(new Intent(PostListActivityEducation.this, AddPostActivityEducation.class));
                            finish();

                        }
                        else{
                            Toast.makeText(PostListActivityEducation.this, "YOU ARE NOT ADMIN", Toast.LENGTH_SHORT).show();
                        }


                    }}


                if(post.equals("Signout")){


                    if(mUser!=null&&mAuth!=null){
                        //there is user and he is authorised
                        mAuth.signOut();

                        startActivity(new Intent(PostListActivityEducation.this,MainActivityMD7.class));
                        finish();//so that  when user cliks back they will not able to see

                    }



                }


                if(post.equals("Teja")){

                    Intent intent=new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("https://bot.dialogflow.com/df5bd4a3-c680-4aa5-be75-99550cd70d0f"));
                    startActivity(intent);

                }



                return true;

            }

            @Override
            public void onMenuClosed() {

            }
        });


        //end of floating buton



        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();//will have current user information;


        mDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mDatabase.getReference().child("MBlogEducation");
        //if MBlog data base is not there it will create a one;
        mDatabaseReference.keepSynced(true);//so every thing will b sinkked up when the app starts;


        //blogList=new ArrayList<>();



        blogList=new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.main_menu,menu);

        String email=mUser.getEmail();
        if(email.equals("cvsr@gmail.com")) {
            getMenuInflater().inflate(R.menu.main_menu,menu);

        }
        else{
            getMenuInflater().inflate(R.menu.main_menu1,menu);

        }


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //since we have 2buttons use switch to difference them;

        switch (item.getItemId()){

            case R.id.action_add:
                if(mUser!=null&&mAuth!=null){
                    //there is user and he is authorised
                    String email=mUser.getEmail();
                    if(email.equals("cvsr@gmail.com")) {
                        startActivity(new Intent(PostListActivityEducation.this, AddPostActivityEducation.class));
                        finish();
                    }
                    else {
                        Toast.makeText(this, "You are Not Admin", Toast.LENGTH_SHORT).show();
                        // finish();
                    }
                    // startActivity(new Intent(PostListActivity.this, PostListActivity.class));
                    // finish();//so that  when user cliks back they will not able to see

                }

                break;
            case R.id.action_signout:
                if(mUser!=null&&mAuth!=null){
                    //there is user and he is authorised
                    mAuth.signOut();

                    startActivity(new Intent(PostListActivityEducation.this,MainActivityMD7.class));
                    finish();//so that  when user cliks back they will not able to see

                }

                break;


        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();


        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                Blog blog=dataSnapshot.getValue(Blog.class);
                blogList.add(blog);

                Collections.reverse(blogList);

                blogRecyclerAdapter=new BlogRecyclerAdapter(PostListActivityEducation.this,blogList);
                recyclerView.setAdapter(blogRecyclerAdapter);
                blogRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

   /*
    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

             Blog blog=dataSnapshot.getValue(Blog.class);
              blogList.add(blog);


                blogRecyclerAdapter=new BlogRecyclerAdapter(PostListActivity.this,blogList);
                recyclerView.setAdapter(blogRecyclerAdapter);
                blogRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    */
/*
like

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
        mHeart.toggleLike();
        return true;
    }
}


*/
}



