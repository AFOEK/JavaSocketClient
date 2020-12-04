package com.example.socketclient;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Closeable;
import java.net.Socket;
import java.net.*;
import java.io.File;
import java.io.IOException;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText txt_ip, txt_port, txt_msg, txt_history;
    Button butt_connect, butt_dc, butt_send;
    String IPaddress = "", Port = "", Message = "", History = "";
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_ip = (EditText) findViewById(R.id.txt_ip);
        String IPaddress = txt_ip.getText().toString();
        txt_port = (EditText) findViewById(R.id.txt_port);
        String Port = txt_port.getText().toString();
        txt_msg = (EditText) findViewById(R.id.txt_msg);
        String Message = txt_msg.getText().toString();
        txt_history = (EditText) findViewById(R.id.txt_history);
        String History = txt_history.getText().toString();

        //oncreate for action
        butt_connect = (Button) findViewById(R.id.butt_connect);
        butt_dc = (Button) findViewById(R.id.butt_dc);
        butt_send = (Button) findViewById(R.id.butt_send);
    }
}