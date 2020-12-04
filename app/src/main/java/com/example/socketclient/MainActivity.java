package com.example.socketclient;

import androidx.appcompat.app.AppCompatActivity;
import java.net.Socket;
import java.net.*;
import java.io.File;
import java.io.IOException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txt_ip, txt_port, txt_msg, txt_history;
    Button butt_connect, butt_dc, butt_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_ip = (EditText)findViewById(R.id.txt_ip);
        txt_port = (EditText)findViewById(R.id.txt_port);
        txt_msg = (EditText)findViewById(R.id.txt_msg);
        txt_history = (EditText)findViewById(R.id.txt_history);

        butt_connect = (Button)findViewById(R.id.butt_connect);
        butt_dc = (Button)findViewById(R.id.butt_dc);
        butt_send = (Button)findViewById(R.id.butt_send);
    }
}