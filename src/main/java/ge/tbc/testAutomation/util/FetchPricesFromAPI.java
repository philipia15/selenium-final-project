package ge.tbc.testAutomation.util;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ge.tbc.testAutomation.data.Constants;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class FetchPricesFromAPI {
    //Fetch the most expensive offer among ALL offers using API
    public static @NotNull Double FetchMaxPrice() {
        try {
            // 1. API URL
            String apiUrl = Constants.PRICE_API_URL;
            // 2. send HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            // 5. Get "max_price"
            JsonObject pricesObject = jsonResponse.getAsJsonObject("prices");
            String maxPrice = pricesObject.get("max_price").getAsString();

            return Double.parseDouble(maxPrice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch maximum price", e);
        }
    }
    //Fetch the least expensive offer among ALL offers using API
    public static @NotNull Double FetchMinPrice() {
        try {
            // 1. API URL
            String apiUrl = Constants.PRICE_API_URL;
            // 2. send HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            // 5. Get "min_price"
            JsonObject pricesObject = jsonResponse.getAsJsonObject("prices");
            String minPrice = pricesObject.get("min_price").getAsString();

            return Double.parseDouble(minPrice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch minimum price", e);
        }
    }
    //Fetch the least expensive offer among ALL offers using API
    public static String FetchDate() {
        try {
            // 1. API URL
            String apiUrl = Constants.DATA_API_URL;
            // 2. HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            // 5. Get date
            JsonObject dateObject = jsonResponse.getAsJsonObject("data");

            return dateObject.get("date").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch date", e);
        }
    }
    //Fetch cinema name using API
    public static String FetchCinemaName() {
        try {
            // 1. API URL
            String apiUrl = Constants.DATA_API_URL;
            // 2. HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            // 5. Get cinema name
            JsonObject dateObject = jsonResponse.getAsJsonObject("data");

            return dateObject.get("merchant_name").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch movie name", e);
        }
    }
    //Fetch movie name using API
    public static String FetchMovieName() {
        try {
            // 1. API URL
            String apiUrl = Constants.DATA_API_URL;
            // 2. HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            // 5. Get movie name
            JsonObject dateObject = jsonResponse.getAsJsonObject("data");

            return dateObject.get("movie_name").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch date", e);
        }
    }
    public static @NotNull Double FetchMaxPriceMountain() {
        try {
            // 1. API URL
            String apiUrl = Constants.MAX_PRICE_MOUNTAIN_API_URL;
            // 2. HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 4. JSON Parsing
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // 5. Get "max_price"
            JsonObject pricesObject = jsonResponse.getAsJsonObject("prices");
            String maxPrice = pricesObject.get("max_price").getAsString();

            return Double.parseDouble(maxPrice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch minimum price", e);
        }
    }
    public static Double @NotNull [] extractPricesFromElements(@NotNull List<WebElement> priceElements) {
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
