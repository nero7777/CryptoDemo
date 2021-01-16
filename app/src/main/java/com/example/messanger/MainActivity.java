package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void aes(View view)
    {
        Intent intent = new Intent( MainActivity.this, aesActivity.class  );
        startActivity(intent);
    }
    public void des(View view)
    {
        Intent intent = new Intent( MainActivity.this, desActivity.class  );
        startActivity(intent);
    }
    public void rsa(View view)
    {
        Intent intent = new Intent( MainActivity.this, rsaActivity.class  );
        startActivity(intent);
    }



}