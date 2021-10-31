package com.example.teste;

import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.net.Socket;
import java.net.*;
import java.io.*;
public class socket {

    private String ip1 = null;
    private int port1;
    private Socket socket;
    private Object IOException;


    public void set_conf(String ip, int port){
        ip1 = ip;
        port1 = port;

    }
    public static String returnLog(String e){
        String e1 = e;
        return e1;
    }

    public void ini(){
        try {
            InetAddress serv = InetAddress.getByName(ip1);

            socket = new Socket(serv,port1);
            Log.d("logg12", String.valueOf( socket.toString() ) );
        } catch (UnknownHostException e1) {
            Log.d("logg12", String.valueOf( e1 ));
            e1.printStackTrace();
        } catch (IOException e1) {
            Log.d( "logg12", String.valueOf( e1 ));
            e1.printStackTrace();
        }

    }

    static class ClientThread implements Runnable {

        @Override
        public void run() {

            Log.d("logg12", String.valueOf( "ewqewqeqweqweqweqw" ) );

        }

    }
}
