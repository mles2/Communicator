package com.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer
{
    private int port;

    TCPServer(int p_port)
    {
        port = p_port;
    }

    void run() throws IOException
    {
        String message = "";
        ServerSocket socket = new ServerSocket(port);
        Socket connectionSocket = socket.accept();
        BufferedReader bufferFromClient =
                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream streamForResponse =
                new DataOutputStream(connectionSocket.getOutputStream());

        while (!message.equals("q"))
        {
            message = bufferFromClient.readLine();
            System.out.println("Received: " + message);
            streamForResponse.writeBytes("I received your message \n");
        }
        System.out.println("Server is down!");
    }
}
