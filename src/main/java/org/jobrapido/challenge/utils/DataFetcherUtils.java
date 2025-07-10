package org.jobrapido.challenge.utils;

import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.enums.StatusEnum;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataFetcherUtils {

    public static String getData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println(new ResultDto(StatusEnum.GENERIC_ERROR));
            System.exit(1);
        }

        if (response.statusCode() != 200) {
            System.out.println(new ResultDto(StatusEnum.GENERIC_ERROR));
            System.exit(1);
        }

        return response.body();
    }

}
