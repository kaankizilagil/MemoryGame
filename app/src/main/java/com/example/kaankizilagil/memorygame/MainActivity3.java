package com.example.kaankizilagil.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView tv = (TextView)findViewById(R.id.textView5);
        Intent i = getIntent();
        String isim = i.getStringExtra("NAME");
        int skore = i.getIntExtra("POINT",0);
        tv.setText("Congratulations " + isim +" , won with "+ skore + " errors.");
    }
}