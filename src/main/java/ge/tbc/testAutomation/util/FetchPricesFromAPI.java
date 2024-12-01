package ge.tbc.testAutomation.util;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ge.tbc.testAutomation.data.Constants;
import org.openqa.selenium.WebElement;

public class FetchPricesFromAPI {
    public static Double FetchMaxPrice() {

        try {
            // 1. API URL
            String apiUrl = Constants.PRICE_API_URL;

            // 2. HTTP მოთხოვნა
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // 5. ამოღება "min_price" და "max_price"
            JsonObject pricesObject = jsonResponse.getAsJsonObject("prices");

            String maxPrice = pricesObject.get("max_price").getAsString();

            return Double.parseDouble(maxPrice);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch minimum price", e);
        }
    }

    public static Double FetchMinPrice() {
        try {
            // 1. API URL
            String apiUrl = Constants.PRICE_API_URL;

            // 2. HTTP მოთხოვნა
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // 5. ამოღება "min_price" და "max_price"
            JsonObject pricesObject = jsonResponse.getAsJsonObject("prices");


            String minPrice = pricesObject.get("min_price").getAsString();

            return Double.parseDouble(minPrice);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch minimum price", e);
        }

    }
    public static String FetchDate() {
        try {
            // 1. API URL
            String apiUrl = Constants.DATA_API_URL;

            // 2. HTTP მოთხოვნა
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // 5. ამოღება date
            JsonObject dateObject = jsonResponse.getAsJsonObject("data");


            String date = dateObject.get("date").getAsString();

            return date;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch date", e);
        }
    }

    public static String FetchCinemaName() {
            try {
                // 1. API URL
                String apiUrl = Constants.DATA_API_URL;

                // 2. HTTP მოთხოვნა
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(java.net.URI.create(apiUrl))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // 4. JSON Parsing
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

                // 5. ამოღება date
                JsonObject dateObject = jsonResponse.getAsJsonObject("data");


                String cinemaName = dateObject.get("merchant_name").getAsString();

                return cinemaName;

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to fetch date", e);
            }
    }
    public static String FetchMovieName() {
        try {
            // 1. API URL
            String apiUrl = Constants.DATA_API_URL;

            // 2. HTTP მოთხოვნა
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // 5. ამოღება date
            JsonObject dateObject = jsonResponse.getAsJsonObject("data");


            String movieName = dateObject.get("movie_name").getAsString();

            return movieName;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch date", e);
        }
    }

    public static Double FetchMaxPriceMountain() {
        try {
            // 1. API URL
            String apiUrl = Constants.MAX_PRICE_MOUNTAIN_API_URL;

            // 2. HTTP მოთხოვნა
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // 5. ამოღება "min_price" და "max_price"
            JsonObject pricesObject = jsonResponse.getAsJsonObject("prices");


            String maxPrice = pricesObject.get("max_price").getAsString();

            return Double.parseDouble(maxPrice);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch minimum price", e);
        }

    }

    public static Double[] extractPricesFromElements(List<WebElement> priceElements) {
        List<Double> priceList = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            // Extract only the numeric value (remove everything except digits)
            String number = priceElement.getText().replace("₾", "").replace(",", "").trim(); // Removes everything except digits
            double price = Double.parseDouble(number);
            priceList.add(price);
        }
        return priceList.toArray(new Double[0]);
    }
}
