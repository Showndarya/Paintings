package com.example.mushu.testintents;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class Craft extends AppCompatActivity {

        private Integer images[] = {R.drawable.c1, R.drawable.c2, R.drawable.c3};
    private int currImage = 0;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_craft);

            initializeImageSwitcher();
            setInitialImage();
            setImageRotateListener();

        }

    private void initializeImageSwitcher() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(Craft.this);
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
                    if (currImage == 3) {
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