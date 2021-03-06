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
        //input reading
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        //datagram socket
        DatagramSocket clientSocket = new DatagramSocket();

        //get address to send response later
        InetAddress IPAddress = InetAddress.getByName(address);

        //raw data for sending and receiving
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
            String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("FROM SERVER:" + modifiedSentence);
        }
        clientSocket.close();
        System.out.println("Client is down!");
    }
}
