package com.example.win81.numerosprimos;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        Intent i = getIntent();
        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setTextColor(Color.BLUE);
        tv.setText(i.getStringExtra(MainActivity.NOTIFICACION));
      //  setContentView(tv);
    }



    }

