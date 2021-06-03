package ch.ergonomics.tests;

import ch.ergonomics.Fluent;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Examples4 {
    @Test
    void example1() {
        Fluent
            .stack("https://ifconfig.co/country")
            .map(uri -> {
                var req = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(uri))
                    .build();

                HttpResponse<String> res = null;
                try {
                    res = HttpClient.newHttpClient()
                        .send(req, HttpResponse.BodyHandlers.ofString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return res.body();
            })
            .map(String::toUpperCase)
            .tos();
    }
}
