package com.example.poom.seatforu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    NewDB openHelper;
    SQLiteDatabase db;
    EditText editText_name, editText_birth, editText_id, editText_pwd, editText_phone;
    Button btnSave, joinBtn;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initPreferences(); //SharedPreferences 초기화
        initView(); //View 초기화
        btn_Event(); //버튼 클릭 시의 이벤트
        loadState(); //상태를 불러오는 메소드

        openHelper = new NewDB(this);
        db = openHelper.getWritableDatabase();
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_birth = (EditText) findViewById(R.id.editText_birth);
        editText_id = (EditText) findViewById(R.id.editText_id);
        editText_pwd = (EditText) findViewById(R.id.editText_password);
        editText_phone = (EditText) findViewById(R.id.editText_phonenumber);
        joinBtn = (Button) findViewById(R.id.Button_join2);
        joinBtn.setOnClickListener(listener);

        Toolbar rToolbar = (Toolbar) findViewById(R.id.rToolbar);
        rToolbar.setTitle("");
        setSupportActionBar(rToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김

    }

    private void btn_Event() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveState(); //상태를 저장하는 메소드
                //Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void saveState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",editText_name.getText().toString());
        editor.putString("birth", editText_birth.getText().toString());
        editor.putString("id",editText_id.getText().toString());
        editor.putString("pwd",editText_pwd.getText().toString());
        editor.putString("phone",editText_phone.getText().toString());
        editor.commit();
    }

    private void loadState() {
        editText_name.setText(sharedPreferences.getString("name", ""));
        editText_birth.setText(sharedPreferences.getString("birth",""));
        editText_id.setText(sharedPreferences.getString("id",""));
        editText_pwd.setText(sharedPreferences.getString("pwd",""));
        editText_phone.setText(sharedPreferences.getString("phone",""));
    }

    private void initPreferences() {
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
    }

    private void initView() {
        editText_name= (EditText)findViewById(R.id.editText_name);
        editText_birth= (EditText)findViewById(R.id.editText_birth);
        editText_id = (EditText)findViewById(R.id.editText_id);
        editText_pwd = (EditText)findViewById(R.id.editText_password);
        editText_phone = (EditText)findViewById(R.id.editText_phonenumber);
        btnSave = (Button)findViewById(R.id.btnSave);
    }

    private void resetView() {
        editText_name.setText("");
        editText_birth.setText("");
        editText_id.setText("");
        editText_pwd.setText("");
        editText_phone.setText("");
    }

    View.OnClickListener listener = new View.OnClickListener() {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Button_join2:
                    String name = editText_name.getText().toString();
                    String birth = editText_birth.getText().toString();
                    String id = editText_id.getText().toString();
                    String pwd = editText_pwd.getText().toString();
                    String phone = editText_phone.getText().toString();

                    String sql = "select * from newpmember where ID = '"+id+"'";
                    Cursor cursor = db.rawQuery(sql, null);
                    if(cursor.getCount() == 1) {
                        // 해당 이메일과 아이디가 있으면 1개의 row를 가져오겠죠?
                        Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        //finish();
                    } else {
                        // 없다면 아무 값도 가져오지 않으므로 count 가 0 이겠죠?

                        String sql2 ="insert into newpmember(NAME, BIRTH, ID, PW, PHONE) values('"+name+"','"+birth+"','"+id+"','"+pwd+"','"+phone+"')";
                        db.execSQL(sql2);
                        Toast.makeText(RegisterActivity.this, "회원가입을 축하합니다.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        resetView();
                        saveState();
                    }
                    cursor.close();
                    break;
            }

        }
    };

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
