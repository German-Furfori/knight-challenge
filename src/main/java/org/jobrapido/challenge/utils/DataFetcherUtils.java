package org.jobrapido.challenge.utils;

import org.jobrapido.challenge.exception.GenericErrorException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataFetcherUtils {

    private static final String GENERIC_ERROR = "GENERIC_ERROR";

    public static String getData(String envVar) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(System.getenv(envVar)))
                    .GET()
                    .build();

            HttpResponse<String> response;

            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new GenericErrorException(GENERIC_ERROR);
            }

            if (response.statusCode() != 200) {
                throw new GenericErrorException(GENERIC_ERROR);
            }

            return response.body();
        } catch (Exception e) {
            throw new GenericErrorException(GENERIC_ERROR);
        }
    }

}
