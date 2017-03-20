package com.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient
{
    private int port;
    private String address;
    UDPClient(int p_port, String p_address)
    {
        port = p_port;
        address = p_address;
    }

    void run() throws IOException
    {
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(address);
        byte[] sendData;
        byte[] receiveData = new byte[1024];
        String message = "";
        while(!message.equals("q"))
        {
            message = inFromUser.readLine();
            sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
        }
        clientSocket.close();
        System.out.println("Client is down!");
    }
}
