package httpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientGateway {
    public static void main(String[] args) {
        printUserData();
        printUserDataSynchronous();
    }

    public static void printUserData() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://randomuser.me/api"))
                .build();

        System.out.println("***********Asynchronous*********");

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

    }

    public static void printUserDataSynchronous() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create("https://randomuser.me/api"))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("***********Synchronous*********");
            System.out.println("Request: " + response.request());
            System.out.println("URI: " + response.uri());
            System.out.println("Version: " + response.version());
            System.out.println("Headers: " + response.headers());
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response: " + response.body());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
