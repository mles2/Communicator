package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer
{
    private int port;
    private Logger logger;
    UDPServer(int p_port)
    {
        port = p_port;
        String logging_filename = java.time.LocalDate.now().toString() + ".log";
        logger = new Logger(logging_filename);
        System.out.println("Creating logging filename: " + logging_filename);
    }


    void run() throws IOException
    {
        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] sendData = new byte[1024];
        String message = " ";
        while(message.charAt(0) != 'q')
        {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("RECEIVED: " + message);
            logger.addToFile(message);
            InetAddress IPAddress = receivePacket.getAddress();
            int l_port = receivePacket.getPort();
            String response = "I received your message!";
            sendData = response.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, l_port);
            serverSocket.send(sendPacket);
        }
        serverSocket.close();
        System.out.println("Server is down!");
    }
}
