package com.example.mushu.testintents;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Nature extends AppCompatActivity {

    private Integer images[] = {R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.n10, R.drawable.n11, R.drawable.n121, R.drawable.n122, R.drawable.n13, R.drawable.n14, R.drawable.n15, R.drawable.n16};
    private int currImage = 0;
    Button like;
    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;
    DatabaseReference databaseLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nature);
        relativeLayout = (RelativeLayout)findViewById(R.id.activity_nature);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        like = (Button) findViewById(R.id.like);

        initializeImageSwitcher();
        setInitialImage();
        setImageRotateListener();

        setLike();
        databaseLikes = FirebaseDatabase.getInstance().getReference("naturelikes");

    }

    private void setLike(){
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = Integer.toString(currImage);
               databaseLikes.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Likes l = dataSnapshot.getValue(Likes.class);
                        int cnt = l.getLikes();
                        cnt = cnt + 1;
                        Likes likecnt = new Likes(cnt);
                        databaseLikes.child(id).setValue(likecnt);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //Likes likesin = new Likes(2);
                //databaseLikes.child(id).setValue(likesin);
            }
        });
    }

    private void initializeImageSwitcher() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(Nature.this);
                return imageView;
            }
        });

        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
    }

    private void setImageRotateListener() {
        final Button rotatebutton = (Button) findViewById(R.id.btnRotateImage);
        rotatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currImage++;
                if (currImage == 17) {
                    currImage = 0;
                }
                setCurrentImage();
            }
        });
    }

    private void setInitialImage() {
        setCurrentImage();
    }

    private void setCurrentImage() {

        final ImageSwitcher imageView = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageView.setImageResource(images[currImage]);

    }

}