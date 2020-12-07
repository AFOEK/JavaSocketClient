package com.example.socketclient;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;
import java.io.File;
import java.io.IOException;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText txt_ip, txt_port, txt_msg;
    TextView txt_history, lbl_stat;
    Button butt_connect, butt_dc, butt_send;
    RadioGroup rad_grup;
    RadioButton rad_client, rad_server;
    int Port;
    String  IPaddress, s, END_CHAT_SESSION;
    ServerSocket svr;
    Socket socket;
    PrintWriter out;
    BufferedReader in;


    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EDIT TEXT
        txt_ip = (EditText) findViewById(R.id.txt_ip);
        txt_port = (EditText) findViewById(R.id.txt_port);
        txt_msg = (EditText) findViewById(R.id.txt_msg);
        txt_history = (TextView) findViewById(R.id.txt_history);

        //BUTTONS
        butt_connect = (Button) findViewById(R.id.butt_connect);
        butt_dc = (Button) findViewById(R.id.butt_dc);
        butt_send = (Button) findViewById(R.id.butt_send);

        //LABEL
        lbl_stat = (TextView) findViewById(R.id.lbl_stat);

        //RADIO GROUP & BTN
        rad_grup = (RadioGroup)findViewById(R.id.rad_grup);
        rad_server = (RadioButton) findViewById(R.id.rad_server);
        rad_client = (RadioButton)findViewById(R.id.rad_client);


        //CODE BTN

        butt_dc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_msg.setText("");
                txt_ip.setText("");
                txt_port.setText("");
                butt_send.setEnabled(false);
                butt_dc.setEnabled(false);
                rad_client.setEnabled(true);
                rad_server.setEnabled(false);
                in= null;
                out=null;
                svr=null;
                socket=null;
                lbl_stat.setText("Disconnected");
                lbl_stat.setBackgroundColor(0xFFFF0000);
            }
        });

        butt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = txt_msg.getText().toString();
                if(msg !=""){
                    s=msg;
                    out.print(s);
                    txt_msg.setText("");
                    s="";
                }

            }
        });

        butt_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SERVER LINE 192-198
                Port = Integer.parseInt(txt_port.getText().toString());
                IPaddress = txt_ip.getText().toString();
                if (IPaddress != "" && rad_client.isSelected() ) {



                    if (rad_client.isSelected()) {
//                        if (txt_port.getText().toString() == "") {
//                            Port = 8000;
//                        }

                        butt_send.setEnabled(true);
                        butt_dc.setEnabled(true);
                        txt_msg.append(s);

                    }
                }
               //209-210
                txt_msg.setText("");
                txt_msg.setEnabled(false);

                //213-215, 221, 264-265
                butt_send.setEnabled(false);
                butt_dc.setEnabled(false);

                butt_connect.setEnabled(true);

                lbl_stat.setText("Disconnected");
                lbl_stat.setBackgroundColor(0xFFFF0000);

            }
        });
    }
}