package com.example.socketclient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class sentMessage extends MainActivity {
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
