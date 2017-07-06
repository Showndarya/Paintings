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

public class Abst extends AppCompatActivity {

    private Integer images[] = {R.drawable.a1, R.drawable.a2, R.drawable.a3,R.drawable.a4,R.drawable.a5};
    private int currImage = 0;
    Button like;

    DatabaseReference databaseLikes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abst);
        like = (Button) findViewById(R.id.like);

        initializeImageSwitcher();
        setInitialImage();
        setImageRotateListener();
        setLike();
        databaseLikes = FirebaseDatabase.getInstance().getReference("abstlikes");

    }

    private void setLike(){
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             final String id = Integer.toString(currImage);
            /* databaseLikes.child("abstlikes").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot dataSnapshot) {
                      Likes l = dataSnapshot.getValue(Likes.class);
                      int likes = l.getLikes();
                      likes++;
                      Likes likesin = new Likes(likes);
                      databaseLikes.child(id).setValue(likesin);
                  }

                  @Override
                  public void onCancelled(DatabaseError databaseError) {

                  }
              });*/
                databaseLikes.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                      //  for (DataSnapshot ds : dataSnapshot.getChildren()) {
                           // if(dataSnapshot.getKey()==id) {
                                Likes l = dataSnapshot.getValue(Likes.class);
                                int cnt = l.getLikes();
                                cnt = cnt + 1;
                                Likes likecnt = new Likes(cnt);
                                databaseLikes.child(id).setValue(likecnt);
                                //break;
                            //}
                        //}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

             //  Likes likesin = new Likes(2);
             //  databaseLikes.child(id).setValue(likesin);
            }
        });


    }

    private void initializeImageSwitcher() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(Abst.this);
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
                if (currImage == 5) {
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