import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Currency {

    private static List<String> currencies = new ArrayList<>();
    private static String firstCurrency;
    private static String secondCurrency;

    public static void main(String[] args) {
        currencies = addTheListOfCurrencies();
        queryInput();
        courseRequest();
    }

    private static void courseRequest() {
        CurrencyRequest myThread = new CurrencyRequest();
        myThread.start();
        while (myThread.isAlive()) {
            try {
                System.out.print(".");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void queryInput() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter from currency:");
            do {
                firstCurrency = bufferedReader.readLine().toUpperCase().trim();
                if (!currencies.contains(firstCurrency)) {
                    System.out.println("Incorrect data entered, enter the first currency again");
                }
            } while (!currencies.contains(firstCurrency));

            System.out.println("Enter to currency:");
            do {
                secondCurrency = bufferedReader.readLine().toUpperCase().trim();
                if (!currencies.contains(secondCurrency) | firstCurrency.equals(secondCurrency)) {
                    System.out.println("Wrong data entered, enter again the second currency");
                    secondCurrency = null;
                }
            } while (!currencies.contains(secondCurrency));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getTo() {
        return firstCurrency;
    }

    public static String getFrom() {
        return secondCurrency;
    }

    private static List<String> addTheListOfCurrencies() {
        List<String> list = new ArrayList<>();
        list.add("AUD");
        list.add("BGN");
        list.add("BRL");
        list.add("CAD");
        list.add("CHF");
        list.add("CNY");
        list.add("CZK");
        list.add("DKK");
        list.add("EUR");
        list.add("GBP");
        list.add("HKD");
        list.add("HRK");
        list.add("HUF");
        list.add("IDR");
        list.add("ILS");
        list.add("INR");
        list.add("ISK");
        list.add("JPY");
        list.add("KRW");
        list.add("MXN");
        list.add("MYR");
        list.add("NOK");
        list.add("NZD");
        list.add("PHP");
        list.add("PLN");
        list.add("RON");
        list.add("RUB");
        list.add("SEK");
        list.add("SGD");
        list.add("THB");
        list.add("TRY");
        list.add("USD");
        list.add("ZAR");
        return list;
    }
}
