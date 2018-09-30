package com.example.poom.seatforu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by poom on 2018-09-29.
 */

public class MyreserveActivity extends AppCompatActivity {

    TextView inform;
    public ImageView myQrimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myreserve);

        Toolbar rToolbar = (Toolbar) findViewById(R.id.rToolbar);
        rToolbar.setTitle("");
        setSupportActionBar(rToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inform = (TextView) findViewById(R.id.textView_inform);

        ReservepageActivity rp1 = new ReservepageActivity();
        ReservepageActivity2 rp2 = new ReservepageActivity2();

        inform.setText("출발지: " + rp1.inform1 + "\n도착지: " + rp1.inform2
                + "\n탑승구간: " + rp2.inform3 + "\n좌석: " + rp2.inform4);

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
