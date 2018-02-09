import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOperations {

    private static String filePath = "C:\\Users\\Public\\Documents\\statsTest.rtf";

    public static boolean isFileExist(String pathFile) {
        Path path = Paths.get(pathFile);
        return Files.exists(path);
    }

    public void writeToFile(String date, String write) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(date + " : " + write + " \n");
            bufferWriter.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.rtf");
        }
    }

    public static void readFromFile(String one, String two, String request) {
        boolean isShow = false;

        if (isFileExist(filePath)) {
        } else {
            System.out.println("\nFile not found");
        }

        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        String dateNow = formatForDateNow.format(date);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(dateNow)) {
                    String first = line.substring(13, 16);
                    String second = line.substring(20, 23);

                    if (first.equals(one) & second.equals(two)) {
                        System.out.println("\n" + line.substring(13, 32));
                        isShow = true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (!isShow) {
            CurrencyRequest.makeGetReques(request);
        }
    }


}
