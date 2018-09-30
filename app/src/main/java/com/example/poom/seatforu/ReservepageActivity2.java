package com.example.poom.seatforu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ReservepageActivity2 extends AppCompatActivity {

    Button btnNext;
    Spinner spinner1;
    Spinner spinner2;

    public static String inform3, inform4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservepage2);

        Toolbar rToolbar = (Toolbar) findViewById(R.id.rToolbar);
        rToolbar.setTitle("");
        setSupportActionBar(rToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spinner1 = (Spinner) findViewById(R.id.spinner_W);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this, R.array.spinner_W, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner) findViewById(R.id.spinner_W2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this, R.array.spinner_W2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        btnNext = (Button) findViewById(R.id.btn_next2);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inform3 = (String) spinner1.getSelectedItem();
                inform4 = (String) spinner2.getSelectedItem();

                Intent intent = new Intent(getApplicationContext(), QRcodeActivity.class);
                startActivity(intent);

            }
        });

        /*
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        */

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

    /*
    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("탑승구간: " + spinner1.getSelectedItem() + "\n좌석: " + spinner2.getSelectedItem() + "\n\n선택하신 정보가 맞습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                        Intent intent1=new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent1);
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                        Intent intent2=new Intent(getApplicationContext(), MypageActivity.class);
                        startActivity(intent2);
                    }
                });
        builder.show();
    }
    */


}
