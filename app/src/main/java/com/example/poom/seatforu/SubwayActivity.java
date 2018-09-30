package com.example.poom.seatforu;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SubwayActivity extends AppCompatActivity {

    Button btn_all, btn_only2;
    ImageView imgSubway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subway);

        Toolbar rToolbar = (Toolbar) findViewById(R.id.rToolbar);
        rToolbar.setTitle("");
        setSupportActionBar(rToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_all = (Button) findViewById(R.id.btn_all);
        btn_only2 = (Button) findViewById(R.id.btn_only2);

        imgSubway = (ImageView) findViewById(R.id.imageView_subway);

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSubway.setImageResource(R.drawable.subway);
            }
        });

        btn_only2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSubway.setImageResource(R.drawable.map);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
