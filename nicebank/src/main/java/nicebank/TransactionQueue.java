package nicebank;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import static org.apache.commons.io.FileUtils.deleteDirectory;

public class TransactionQueue {
    private static String MESSAGES_FOLDER = "./messages";
    private static String MESSAGE_FILE_PATH = "%s/%03d";
    private int nextId = 1;

    public static void clear() {
        try {
            deleteDirectory(new File(MESSAGES_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(MESSAGES_FOLDER).mkdirs();
    }

    public void write(String transaction){
        String messageFilePath
                = String.format(MESSAGE_FILE_PATH, MESSAGES_FOLDER, nextId);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(messageFilePath, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println(transaction);
        writer.close();

        nextId++;
    }

    public String read() {
        // Get files in 'messages'
        File messagesFolder = new File(MESSAGES_FOLDER);
        File[] messages = messagesFolder.listFiles();

        String message = "";

        // If message file found
        if (messages != null && messages.length > 0){
            Arrays.sort(messages, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    return Integer.parseInt(f1.getName())
                            - Integer.parseInt(f2.getName());
                }
            });

            // Open it
            Scanner scanner = null;
            try {
                scanner = new Scanner(messages[0]);

                if (scanner.hasNextLine()) {
                    message = scanner.nextLine();
                    scanner.close();

                    // Delete it
                    messages[0].delete();
                }
                else {
                    scanner.close();
                }

            } catch (FileNotFoundException e) {
                // File has gone away!
            }
        }

        return message;
    }
}
