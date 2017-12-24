package com.example.administrator.socketclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SetSrvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_srv);
    }

    public void buttonSetSvr_back(View view) {
        Intent mainIntent = new Intent(SetSrvActivity.this, MainActivity.class);
        Bundle srvBundle = new Bundle();
        srvBundle.putString("ServerIP", "192.168.56.100");
        srvBundle.putInt("ServerPort", 6000);
        mainIntent.putExtras(srvBundle);
        startActivity(mainIntent);
    }
}
