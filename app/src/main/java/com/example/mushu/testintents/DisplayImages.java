package com.example.mushu.testintents;

/**
 * Created by mushu on 29/4/18.
 */

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayImages extends AppCompatActivity {

    private Integer images[] = new Integer[50];
    private int currImage = 0;
    private int totalImages = 0;
    private int actno;
    Button like;
    TextView likeText;
    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;
    DatabaseReference databaseLikes;

    @Override
    protected void onStart() {
        super.onStart();
        final String id = Integer.toString(currImage);
        databaseLikes.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Likes l = dataSnapshot.getValue(Likes.class);
                int cnt = l.getLikes();
                String lc = Integer.toString(cnt);
                likeText.setText(lc);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_images);

        Intent getid = getIntent();
        Bundle bid = getid.getExtras();
        if(!bid.isEmpty()) actno = bid.getInt("id",0);

        relativeLayout = (RelativeLayout)findViewById(R.id.display_images);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        like = (Button) findViewById(R.id.like);
        likeText = (TextView) findViewById(R.id.likeText);

        switch(actno) {
            case 0: images = new Integer[] {R.drawable.c1, R.drawable.c2, R.drawable.c3};
                    databaseLikes = FirebaseDatabase.getInstance().getReference("craftlikes");
                    break;
            case 1: images = new Integer[] {R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.n10, R.drawable.n11, R.drawable.n121, R.drawable.n122, R.drawable.n13, R.drawable.n14, R.drawable.n15, R.drawable.n16};
                    databaseLikes = FirebaseDatabase.getInstance().getReference("naturelikes");
                    break;
            case 2: images = new Integer[] {R.drawable.a1, R.drawable.a2, R.drawable.a3,R.drawable.a4,R.drawable.a5};
                    databaseLikes = FirebaseDatabase.getInstance().getReference("abstlikes");
                    break;
            case 3: images = new Integer[] {R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6, R.drawable.d7, R.drawable.d8,R.drawable.d9, R.drawable.d10, R.drawable.d11, R.drawable.d12, R.drawable.d13, R.drawable.d14};
                    databaseLikes = FirebaseDatabase.getInstance().getReference("divinelikes");;
                    break;
            case 4: images = new Integer[] {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8,R.drawable.d9, R.drawable.p10, R.drawable.p11, R.drawable.p12};
                    databaseLikes = FirebaseDatabase.getInstance().getReference("potraitlikes");
                    break;
            default: images = new Integer[] {R.drawable.c1, R.drawable.c2, R.drawable.c3};
                     databaseLikes = FirebaseDatabase.getInstance().getReference("craftlikes");
                     break;
        }
        totalImages = images.length;
        
        initializeImageSwitcher();
        setInitialImage();
        setImageRotateListener();

        setLike();
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
                        String lc = Integer.toString(cnt);
                        likeText.setText(lc);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //Likes likesin = new Likes(2);
                // databaseLikes.child(id).setValue(likesin);
            }
        });
    }

    private void initializeImageSwitcher() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(DisplayImages.this);
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
                if (currImage == totalImages) {
                    currImage = 0;
                }
                final String id = Integer.toString(currImage);
                databaseLikes.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Likes l = dataSnapshot.getValue(Likes.class);
                        int cnt = l.getLikes();
                        String lc = Integer.toString(cnt);
                        likeText.setText(lc);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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