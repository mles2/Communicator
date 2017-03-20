package com.communicator;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {

    public static void serverApplication(int port) throws IOException
    {
        String message = "";
        ServerSocket socket = new ServerSocket(port);
        Socket connectionSocket = socket.accept();
        BufferedReader bufferFromClient =
                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream bufferForResponse =
                new DataOutputStream(connectionSocket.getOutputStream());

        while (!message.equals("q"))
        {
            message = bufferFromClient.readLine();
            System.out.println("Received: " + message);
            bufferForResponse.writeBytes("I received your message \n");
        }
        System.out.println("Server is down!");
    }

    public static void clientApplication(String address, int port) throws IOException
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

    public static void main(String[] args) throws IOException
    {
        if (args.length == 2 || args.length == 3)
        {
            switch (args[0])
            {
                case "s":
                    serverApplication(Integer.parseInt(args[1]));
                    break;

                case "c":
                    clientApplication(args[2], Integer.parseInt(args[1]));
                    break;
            }
        }
        else
        {
            System.out.println("Bad amount of arguments! You need to specify:");
            System.out.println("[s port] or [c port adress ]");
        }
    }
}
