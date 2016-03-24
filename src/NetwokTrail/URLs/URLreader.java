package NetwokTrail.URLs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Simple URL reader example.
 * The created URL object is used to open an Input stream and print it on the screen.
 */
public class URLreader {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://wp.pl");

            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = input.readLine()) != null)
                System.out.println(inputLine);

            input.close();

        } catch (MalformedURLException e) {
            System.out.println("The URL provided is malformed");
            System.exit(1);

        } catch (IOException e) {
            System.out.println("IO error while operating on a Input Stream");

        }
    }
}




