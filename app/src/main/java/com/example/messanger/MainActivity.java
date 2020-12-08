package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView aestextview = (TextView) findViewById(R.id.text_AES);
        TextView destextview = (TextView) findViewById(R.id.text_DES);
        TextView rsatextview = (TextView) findViewById(R.id.text_RSA);


      aestextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,aesActivity.class);
                startActivity(i);
            }
        });

        destextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,desActivity.class);
                startActivity(i);
            }
        });

        rsatextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,rsaActivity.class);
                startActivity(i);
            }
        });
    }
}