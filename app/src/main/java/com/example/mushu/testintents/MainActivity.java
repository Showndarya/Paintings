package com.example.mushu.testintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btncraft,btnnature,btnabst,btndivine,btnpot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btncraft=(Button)findViewById(R.id.btncraft);
        btnnature=(Button)findViewById(R.id.btnnature);
        btnabst=(Button)findViewById(R.id.btnabst);
        btndivine=(Button)findViewById(R.id.btndivine);
        btnpot=(Button)findViewById(R.id.btnpot);


        btncraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Craft.class);
                startActivity(i);
            }
        });
        btnnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Nature.class);
                startActivity(i);
            }
        });
        btnabst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Abst.class);
                startActivity(i);
            }
        });
        btndivine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Divine.class);
                startActivity(i);
            }
        });
        btnpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Potrait.class);
                startActivity(i);
            }
        });
    }



}
