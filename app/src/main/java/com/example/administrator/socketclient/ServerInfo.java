package com.example.administrator.socketclient;

/**
 * Created by wangwenjun on 2017/12/24 0024.
 */

public class ServerInfo {
    private String IP= "";
    private int Port = 0;


    public ServerInfo(){
    }

    public ServerInfo(String strIP, int nPort){
        this.setIP(strIP);
        this.setPort(nPort);
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return Port;
    }

    public void setPort(int port) {
        Port = port;
    }
}
