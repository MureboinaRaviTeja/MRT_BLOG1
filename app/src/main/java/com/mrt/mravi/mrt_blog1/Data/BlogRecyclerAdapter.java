package com.mrt.mravi.mrt_blog1.Data;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;

import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrt.mravi.mrt_blog1.Model.Blog;
import com.mrt.mravi.mrt_blog1.R;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mravi on 26-12-2017.
 */

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder> {
    //used to bind the data and views ;
private Context context;
    private List<Blog> blogList;
private  ImageButton imageButton;

    private DatabaseReference mDatabaselilke;
    private DatabaseReference mDatabaseUsers;



    private  boolean mProcesslike=false;
    public BlogRecyclerAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @Override
    public BlogRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(final BlogRecyclerAdapter.ViewHolder holder, int position) {

       // ImageButton imageButton;

PhotoViewAttacher  mAttacher;
//final String post_key=getR

ImageView imageView;
//        imageButton=(ImageButton)view.findViewById(R.id.imageButton);
      //  PhotoViewAttacher photoView=new PhotoViewAttacher(imageButton);
       // photoView.update();

        //blog object  to get current id
        Blog blog=blogList.get(position);;;
        String imageUrl=null;

        //holder is used to accesss the items in ViewHolder Method

        holder.title.setText(blog.getTitle());
    holder.desc.setText(blog.getDesc());
        holder.timestamp.setText(blog.getTimestamp());
        java.text.DateFormat dateFormat=java.text.DateFormat.getDateInstance();//to change into correct formate

        String formatteDate=dateFormat.format(new Date(Long.valueOf(blog.getTimestamp())));

    //April 14 2017 //formate
        holder.timestamp.setText(formatteDate);
    imageUrl=blog.getImage();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "helo", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProcesslike=true;


            }
        });

//imageButton=imageUrl


 //       imageButton=(ImageButton)view.findViewById(R.id.imageButton);
        //PhotoViewAttacher photoView;
        //photoView = new PhotoViewAttacher(imageUrl);
        //photoView.update();




//TODO:Use Picasso library to load images

        //start of palette
/*
        BitmapDrawable bitmapDrawable=(BitmapDrawable)holder.image.getDrawable();
        Bitmap photo=bitmapDrawable.getBitmap();
        Palette.from(photo).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                int bgColor=palette.getVibrantColor(ContextCompat.getColor(context,android.R.color.holo_blue_light));

                //int bgColor1=palette.getVibrantColor(ContextCompat.getColor(context,android.R.color.darker_gray));


                //  holder.title.setBackgroundColor(bgColor);
holder.title.setBackgroundColor(bgColor);
                holder.desc.setBackgroundColor(bgColor);

                //holder.author.setBackgroundColor(bgColor);
                //holder.author.setTextColor(bgColor);
            }
        });

*/

/*
BitmapDrawable bitmapDrawable=(BitmapDrawable)holder.image.getDrawable();
        Bitmap photo=bitmapDrawable.getBitmap();
        //now we have access to the photos
        Palette.from(photo).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

           int bgcolor=palette.getVibrantColor(ContextCompat.getColor(context,android.R.color.holo_blue_dark));//default color

                holder.desc.setBackgroundColor(bgcolor);

            }
        });

*/
//end of palette



        Picasso.with(context)
                .load(imageUrl)
                .centerCrop()
                .fit()
                .into(holder.image);

//Picasso.with(context).load(imageUrl).resize(300,300).fit().centerCrop().into(holder.image);
//PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(holder.image);


        //imageButton=(ImageButton)findViewById(R.id.imageButton);
       // PhotoViewAttacher photoView=new PhotoViewAttacher(holder.image);
        //photoView.update();


    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

       public TextView title;
        public TextView desc;
        public ImageView image;
        public TextView timestamp;



        public TextView txt;

        public ImageView like;
        public ImageView likered;

        String userid;

//for zoom
        public ImageButton imageButton;
        public ImageView imageView;

        public ViewHolder(View view,Context ctx ) {
            //context is used in the parameter so that it can move from one activity to the other;

            super(view);
View mview;
            mview=itemView;

             imageView=(ImageView)view.findViewById(R.id.shareImageView);


            context=ctx;

   title=(TextView)view.findViewById(R.id.postTitleList);
            desc=(TextView)view.findViewById(R.id.postTextList);
            image=(ImageView)view.findViewById(R.id.postImageList);
            timestamp=(TextView)view.findViewById(R.id.timestampList);

            //for zooom
            //imageButton=(ImageButton)view.findViewById(R.id.postImageList);

            //imageButton=(ImageButton)view.findViewById(R.id.imageButton);
           //wrkd
            PhotoViewAttacher photoView=new PhotoViewAttacher(image);
           photoView.update();




//like=(ImageView)view.findViewById(R.id.like);
          //  likered=(ImageView)view.findViewById(R.id.likered);
            //text like

            // txt=(TextView)view.findViewById(R.id.txt);

        userid=null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //we cant go to next activity..
                }
            });
        }
    }



}
