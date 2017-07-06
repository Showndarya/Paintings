package com.example.mushu.testintents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Potrait extends AppCompatActivity {

    private Integer images[] = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8,R.drawable.d9, R.drawable.p10, R.drawable.p11, R.drawable.p12};
    private int currImage = 0;
    Button like;

    DatabaseReference databaseLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potrait);
        like = (Button) findViewById(R.id.like);

        initializeImageSwitcher();
        setInitialImage();
        setImageRotateListener();

        setLike();
        databaseLikes = FirebaseDatabase.getInstance().getReference("potraitlikes");

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
                ImageView imageView = new ImageView(Potrait.this);
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
                if (currImage == 12) {
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