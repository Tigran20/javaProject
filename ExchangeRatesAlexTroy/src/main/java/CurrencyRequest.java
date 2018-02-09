import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyRequest extends Thread {

    private static final String CURRENCY_REQUEST = "http://api.fixer.io/latest?base="
            + Currency.getTo()
            + "&symbols="
            + Currency.getFrom();

    public void run() {
        FileOperations.readFromFile(Currency.getTo(), Currency.getFrom(), CURRENCY_REQUEST);
    }

    public static void makeGetReques(String request) {
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        FileOperations write = null;
        ApiResponse apiResponse = null;

        try {
            URL url = new URL(CURRENCY_REQUEST);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf8"));

                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(RateObject.class, new RatesDeserializer())
                        .create();

                apiResponse = gson.fromJson(reader, ApiResponse.class);

                reader = new BufferedReader(new InputStreamReader(System.in));

                String text = (Currency.getTo() + " => " + Currency.getFrom() + " : "
                        + apiResponse.getRates().getRates());
                String date = apiResponse.getDate();

                System.out.println("\n" + text);

                write = new FileOperations();
                write.writeToFile(date, text);
            }
        } catch (Exception e) {
            System.out.println("\nThe request failed. Try again.");
        } finally {
            if (connection != null && reader != null) {
                connection.disconnect();
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
