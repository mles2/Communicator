package com.tcp;
import java.io.IOException;

public class TCP
{
    public static void main(String[] args) throws IOException
    {
        if (args.length == 2 || args.length == 3)
        {
            switch (args[0])
            {
                case "s":
                    TCPServer server = new TCPServer(Integer.parseInt(args[1]));
                    server.run();
                    break;

                case "c":
                    TCPClient client = new TCPClient(Integer.parseInt(args[1]),args[2]);
                    client.run();
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
