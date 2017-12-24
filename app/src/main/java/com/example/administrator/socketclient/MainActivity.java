package com.example.administrator.socketclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private ServerInfo srvinfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            TextView tv_set = findViewById(R.id.textview_set);
            if (tv_set != null) {
                tv_set.setText("");
            }
            if (srvinfo == null) {
                srvinfo = new ServerInfo();
            }

            Bundle srvBundle = this.getIntent().getExtras();
            if (srvBundle == null) {
                //set service info from SharedPreferences
                getSrvInfo(srvinfo);
            } else {
                //set service info from UI
                srvinfo.setIP(srvBundle.getString(ConstInfo.XML_IP));
                srvinfo.setPort(srvBundle.getInt(ConstInfo.XML_PORT));

                setSrvInfo(srvinfo);
            }

            TextView tv_ip = findViewById(R.id.textview_ip);
            TextView tv_port = findViewById(R.id.textview_port);
            if (srvinfo.getIP().equals("") || srvinfo.getPort() == 0) {
                if (tv_set != null) {
                    tv_set.setText("Please Reset!");
                }

                if (tv_ip != null) {
                    tv_ip.setText("");
                }
                if (tv_port != null) {
                    tv_port.setText("");
                }
            } else {
                if (tv_ip != null) {
                    tv_ip.setText("IP:" + srvinfo.getIP());
                }
                if (tv_port != null) {
                    tv_port.setText("Port:" + srvinfo.getPort());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void button_set_server_click(View view) {
        //TextView textView_msg = findViewById(R.id.textView_msg);
        //textView_msg.setText("haha");

        Intent setSrvIntent = new Intent(MainActivity.this, SetSrvActivity.class);
        startActivity(setSrvIntent);
    }

    public void getSrvInfo(ServerInfo srvinfo){
        InputStream in = null;
        try {
            //读取属性文件a.properties
            Properties prop = new Properties();
            in = new FileInputStream(android.os.Environment.getExternalStorageDirectory() + File.separator
                    + ConstInfo.XML_NAME);

            //in = new BufferedInputStream(new FileInputStream(ConstInfo.XML_NAME));
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            if(prop.containsKey(ConstInfo.XML_IP)) {
                srvinfo.setIP(prop.getProperty(ConstInfo.XML_IP, ""));
            }
            if(prop.containsKey(ConstInfo.XML_PORT)){
                String strPort =prop.getProperty(ConstInfo.XML_PORT);
                if(!strPort.equals(""))
                {
                    srvinfo.setPort(Integer.parseInt(strPort));
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setSrvInfo(ServerInfo srvinfo){
        FileOutputStream oFile = null;
        try {
            File file = new File(android.os.Environment.getExternalStorageDirectory() + File.separator
                    + ConstInfo.XML_NAME);
            if(!file.exists())
            {
                file.createNewFile();
            }

            ///保存属性到b.properties文件
            Properties prop = new Properties();
            oFile = new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + File.separator
                    + ConstInfo.XML_NAME);

            //oFile = new FileOutputStream(file, true);//true表示追加打开
            prop.setProperty(ConstInfo.XML_IP, srvinfo.getIP());
            prop.setProperty(ConstInfo.XML_PORT, srvinfo.getPort()+"");
            prop.store(oFile, "The New properties file");
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                if(oFile != null) {
                    oFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void button_StartStop_click(View view) {
    }
}
