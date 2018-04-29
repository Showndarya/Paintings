package com.example.mushu.testintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button btncraft,btnnature,btnabst,btndivine,btnpot;
    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout)findViewById(R.id.activity_main);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);


        btncraft=(Button)findViewById(R.id.btncraft);
        btnnature=(Button)findViewById(R.id.btnnature);
        btnabst=(Button)findViewById(R.id.btnabst);
        btndivine=(Button)findViewById(R.id.btndivine);
        btnpot=(Button)findViewById(R.id.btnpot);


        btncraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DisplayImages.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",0);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        btnnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DisplayImages.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",1);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        btnabst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DisplayImages.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",2);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        btndivine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DisplayImages.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",3);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        btnpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DisplayImages.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",4);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }



}
