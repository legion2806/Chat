import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Archive {

    private static int messagesSize = 30;
    private List<String> archive = new ArrayList<>();

    public void addMessage(String message) {

        if (archive.size() >= messagesSize) {
            archive.remove(0);
            archive.add(message);
        } else {
            archive.add(message);
        }
    }

    public void sendMessage(BufferedWriter bufferedWriter) {
        try {
            if (archive.size() > 0) {

                bufferedWriter.write("\n*** Архив сообщений чата:" + "\n");
                for (String message : archive) {
                    bufferedWriter.write(message + "\n");
                }
                bufferedWriter.write("*** В архиве сообщений больше нет.\n" + "\n");
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}