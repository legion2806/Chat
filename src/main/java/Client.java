import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Client {

    public static String nameSettings = "settings.txt";
    public static String ip = "127.0.0.1";
    public static int port;


    public static void main(String[] args) {

        port = readPort();
        new ClientPath(ip, port);

    }

    public static int readPort() {
        String port = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(nameSettings))) {
            String a;
            while ((a = reader.readLine()) != null) {
                port = a;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(port);
    }

}