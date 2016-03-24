package NetwokTrail.Datagrams;

/**
 * Created by root on 24.03.16.
 */
public class DatagramServer {
    public static void main (String[] args){
        new DatagramServerThread().start();

    }
}

