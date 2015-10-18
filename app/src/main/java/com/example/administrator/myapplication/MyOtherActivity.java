package com.example.administrator.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyOtherActivity extends AppCompatActivity {

    private Button btnOK;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_other);

        btnOK = (Button) findViewById(R.id.btnOK);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOtherActivity.this, MainActivity.class);
                setResult(MainActivity.SHOW_RESULTCANCEL, intent);
                finish();
            }
        });
    }

    // return data for MainActivity
    public void sendData(){
        Intent intent = new Intent(MyOtherActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        // put data into bundle packet
        bundle.putString("hello","Xin chao");
        // put bundle packet into intent
        intent.putExtra("packet",bundle);
        // put other data into intent
        intent.putExtra("MyName","Sap");
        // return MainActivity
        setResult(MainActivity.SHOW_RESULTOK, intent);
        finish();
    }
}
