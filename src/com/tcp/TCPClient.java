package com.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class TCPClient
{
    private int port;
    private String address;

    TCPClient(int p_port, String p_address)
    {
        port = p_port;
        address = p_address;
    }

    void run() throws IOException
    {
        String message = "";
        String responseFromServer;
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        Socket clientSocket = new Socket(address, port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while(!message.equals("q"))
        {
            message = inFromUser.readLine();
            outToServer.writeBytes(message + '\n');
            responseFromServer = inFromServer.readLine();
            System.out.println("Response from server: " + responseFromServer);
        }
        clientSocket.close();
        System.out.println("Client is down!");
    }
}
