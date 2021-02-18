import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    public static String nameSettings = "settings.txt";
    public static String nameLog = "file.log";

    public static int port = 56000;

    public static String time = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(new Date());

    public static CopyOnWriteArrayList<ServerThread> listOfClients = new CopyOnWriteArrayList<>();

    public static Archive archive = new Archive();

    public static ConcurrentSkipListSet<String> listOfNames = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws IOException {

        listOfNames.clear();

        createFiles();

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Добро пожаловать в Chat!");
        try (FileWriter writerLog = new FileWriter(nameLog, true)) {
            writerLog.write(time + " Чат начал работу.\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    listOfClients.add(new ServerThread(socket));
                } catch (IOException e) {
                    socket.close();
                    e.printStackTrace();
                }
            }
        } finally {
            serverSocket.close();
        }

    }

    public static void createFiles() {
        String settings = "Файл settings.txt успешно создан";
        String log = "Файл file.log успешно создан";

        File settingsFile = new File(nameSettings);
        try {
            if (settingsFile.createNewFile())
                System.out.println(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writerPortNumber = new FileWriter(nameSettings, false)) {
            writerPortNumber.write(String.valueOf(port));
        } catch (Exception e) {
            e.printStackTrace();
        }

        File logFile = new File(nameLog);
        try {
            if (logFile.createNewFile())
                System.out.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writerLogs = new FileWriter(nameLog, true)) {
            writerLogs.write(time + " " + settings + "\n");
            writerLogs.write(time + " " + log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}