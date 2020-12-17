package com.example.socketclient;

import java.io.DataInputStream;
import java.net.InetAddress;

public class ClientThread extends MainActivity {
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
