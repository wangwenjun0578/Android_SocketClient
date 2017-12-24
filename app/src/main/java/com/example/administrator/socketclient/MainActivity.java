package com.example.administrator.socketclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle srvBundle = this.getIntent().getExtras();
        if(srvBundle!=null)
        {
            String ip = srvBundle.getString("ServerIP");
            int port = srvBundle.getInt("ServerPort");
        }
    }

    public void button_set_server_click(View view) {
        //TextView textView_msg = findViewById(R.id.textView_msg);
        //textView_msg.setText("haha");

        Intent setSrvIntent = new Intent(MainActivity.this, SetSrvActivity.class);
        startActivity(setSrvIntent);
    }
}
