package com.example.administrator.socketclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SetSrvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_srv);
    }

    public void buttonSetSvr_back(View view) {
        try {
            Intent mainIntent = new Intent(SetSrvActivity.this, MainActivity.class);
            switch (view.getId()) {
                case R.id.btn_save:
                    EditText et_ip = findViewById(R.id.srvip);
                    EditText et_port = findViewById(R.id.srvport);
                    String ip = "";
                    int port = 0;
                    if (et_ip != null) {
                        ip = et_ip.getText().toString().trim();
                    }
                    if (et_port != null) {
                        String strPort = et_port.getText().toString().trim();
                        port = Integer.parseInt(strPort);
                    }

                    Bundle srvBundle = new Bundle();
                    srvBundle.putString(ConstInfo.XML_IP, ip);
                    srvBundle.putInt(ConstInfo.XML_PORT, port);
                    mainIntent.putExtras(srvBundle);
                    break;
                case R.id.btn_cancle:
                    break;
            }

            startActivity(mainIntent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
