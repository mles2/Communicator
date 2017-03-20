package com.udp;
import java.io.IOException;

public class UDP
{
    public static void main(String[] args) throws IOException
    {
        if (args.length == 2 || args.length == 3)
        {
            switch (args[0])
            {
                case "s":
                    UDPServer server = new UDPServer(Integer.parseInt(args[1]));
                    server.run();
                    break;

                case "c":
                    UDPClient client = new UDPClient(Integer.parseInt(args[1]),args[2]);
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
