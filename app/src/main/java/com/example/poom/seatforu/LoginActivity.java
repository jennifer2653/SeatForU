package com.example.poom.seatforu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    NewDB openHelper;
    EditText ID,PW;
    SQLiteDatabase db;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        openHelper = new NewDB(this);
        db = openHelper.getWritableDatabase();
        ID = (EditText) findViewById(R.id.idText);
        PW = (EditText) findViewById(R.id.passwordText);
        login = (Button) findViewById(R.id.button_Login);
        register = (Button) findViewById(R.id.button_Join);
        login.setOnClickListener(listener);
        register.setOnClickListener(listener);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener listener =new View.OnClickListener(){

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button_Join:
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_Login:
                    String id = ID.getText().toString();
                    String pw = PW.getText().toString();

                    String sql ="select * from newpmember where ID='"+id+"'and PW='"+pw+"'";

                    Cursor cursor = db.rawQuery(sql,null);

                    if(cursor.getCount()==1){
                        Toast.makeText(LoginActivity.this, id+"님 환영합니다!", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(LoginActivity.this , MainActivity.class);
                        intent2.putExtra("id",id);
                        intent2.putExtra("pw",pw);
                        startActivity(intent2);
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                    break;
            }
        }
    };

}
