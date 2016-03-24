package NetwokTrail.Datagrams;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;

public class DatagramServerThread extends Thread {

    private DatagramSocket datagramSocket;

    private boolean serverState = true;

    private Random random = new Random();
    private static final int PORT_NO = 3334;
    private static final String[] MAGIC_BALL_ANSWERS = {
            "It is certain",
            "It is decidedly so",
            "Without a doubt",
            "Yes, definitely",
            "You may rely on it",
            "As I see it, yes",
            "Most likely",
            "Outlook good",
            "Yes",
            "Signs point to yes",
            "Reply hazy try again",
            "Ask again later",
            "Better not tell you now",
            "Cannot predict now",
            "Concentrate and ask again",
            "Don't count on it",
            "My reply is no",
            "My sources say no",
            "Outlook not so good",
            "Very doubtful" };

    public DatagramServerThread () {
        super("MagicBallThread");
        try {
            datagramSocket = new DatagramSocket(PORT_NO);
        } catch (SocketException e) {
           System.out.println ("SERVER: There was a problem while creating a socket.");
        }

    }

    @Override
    public void run() {
        super.run();
        System.out.println("SERVER: Server is up.");

        while(serverState) {
            try{
                byte[] buf = new byte[256];

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(packet);

                String outString;

                outString = getAnswer();

                buf = outString.getBytes();

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                datagramSocket.send(packet);

                System.out.println("SERVER: Data was sent.");

            } catch (IOException e) {
                System.out.println("There was an IO error while sending the datagram.");
                e.printStackTrace();
            }

        }
        datagramSocket.close();
        System.out.println("SERVER: Server is down.");
    }

    public String getAnswer () {
        int index = random.nextInt(MAGIC_BALL_ANSWERS.length);
        return MAGIC_BALL_ANSWERS[index];
    }
}



