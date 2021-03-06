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
import android.widget.Toast;

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
    String  IPaddress, s, END_CHAT_SESSION, msg="", line = null;;

    ServerSocket svr;
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEXT
        txt_ip = (EditText) findViewById(R.id.txt_ip);
        txt_port = (EditText) findViewById(R.id.txt_port);
        txt_msg = (EditText) findViewById(R.id.txt_msg);
        txt_history = (TextView) findViewById(R.id.txt_history);
        lbl_stat = (TextView) findViewById(R.id.lbl_stat);

        //BUTTONS
        butt_connect = (Button) findViewById(R.id.butt_connect);
        butt_dc = (Button) findViewById(R.id.butt_dc);
        butt_send = (Button) findViewById(R.id.butt_send);

        //RADIO GROUP & BTN
        rad_grup = (RadioGroup)findViewById(R.id.rad_grup);
        rad_server = (RadioButton) findViewById(R.id.rad_server);
        rad_server.setChecked(true);
        rad_client = (RadioButton)findViewById(R.id.rad_client);
        rad_client.setChecked(false);

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
                    out.print(msg);
                    out.flush();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        butt_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SERVER LINE 192-198
                Port = Integer.parseInt(txt_port.getText().toString());
                IPaddress = txt_ip.getText().toString();

                Thread sentThread = new Thread((Runnable) new sentMessage());
                sentThread.start();

                if (IPaddress != "" && rad_client.isSelected() ) {
                    if (rad_client.isSelected()) {
                        if (txt_port.getText().toString() == "") {
                            Port = 8000;
                        }
                        if(txt_ip.getText().toString() == "")
                        {

                        }

                        try
                        {
                            //open socket in server
                            Socket socket = new Socket(IPaddress, Port);

                            //I/O stream for comm
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            //out.writeBytes(s);

                            //receive from client??
                            String response = in.readLine();

                            while(!(s = in.readLine()).isEmpty()){
                               // timer.start();
                            }
                            //close client socket
                            socket.close();
                        }
                        catch(Exception e) {}

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
/* //CODE CLASSES
        class ClientThread implements Runnable
        {
            @Override
            public void run() {

                try
                {
                    while(true)
                    {
                        InetAddress serverAddr = InetAddress.getByName(IPaddress);
                        //Socket socket = new Socket (serverAddr, 10000); //error nih, suruh nambah catch lg

                        DataInputStream in = new DataInputStream(socket.getInputStream());

                        //deklarasi var line di atas bareng String IPaddress dll
                        while ((line = in.readLine()) != null)
                        {
                            msg = msg + "Server : " + line + "\n";

                            handler.post(new Runnable(){
                            @Override
                            public void run()
                            {
                                txt_history.setText(msg); //chat. to txt_history
                            }
                        });
                        }
                        in.close();
                        socket.close();
                        Thread.sleep(100);
                    }
                }
                catch (Exception e)
                {}
            }
        }

        class sentMessage implements Runnable
        {
            @Override
            public void run() {
                try
                {
                    InetAddress serverAddr = InetAddress.getByName(IPaddress);
                    Socket socket = new Socket(serverAddr, 10000); //

                    DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                    s = txt_msg.getText().toString(); // code asli pg.14
                    s = s + "\n";
                    msg = msg + "Client : " + s; //I declared var msg on String, along with IPaddress and s..
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txt_history.setText(msg); //chat. to txt_history.
                        }
                    });
                    os.writeBytes(s); //str to s
                    os.flush();
                    os.close();
                    socket.close();
                }
                catch(IOException e)
                {
                }
            }
            }
*/
            }
        }
