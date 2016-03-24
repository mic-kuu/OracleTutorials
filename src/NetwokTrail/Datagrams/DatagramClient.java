package NetwokTrail.Datagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class DatagramClient {
    public static void main (String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket();

            while (true){

                byte[] buffer = new byte[256];
                InetAddress address = InetAddress.getByName("SERVERNAME");

                BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
                String userInput;

                while ((userInput = stdIn.readLine()) != null) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 3334);
                    socket.send(packet);

                    // get response
                    packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);

                    // display response
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Your answer is: " + received);
                }
                socket.close();

            }

        } catch (IOException e) {

        }


    }
}
