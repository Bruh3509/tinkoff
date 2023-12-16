package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class HackerNews {
    private static final int OFFSET = 8;

    public static long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest
            .newBuilder(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json")).build();

        try (HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body();
            var parsedResponseBody = responseBody.substring(1, responseBody.length() - 1).split(",");

            return Arrays.stream(parsedResponseBody)
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (InterruptedException | IOException e) {
            return new long[] {};
        }
    }

    public static String news(long id) {
        HttpRequest request = HttpRequest
            .newBuilder(URI.create(String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id))).build();

        try (HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body();
            var indexOfTitle = responseBody.indexOf("\"title\":") + OFFSET;
            var titleString = responseBody.substring(indexOfTitle, responseBody.length() - 1);
            var parsedTitleString = titleString.split(",");
            var name = parsedTitleString[0];
            return name.substring(1, name.length() - 1);
        } catch (InterruptedException | IOException e) {
            return "";
        }
    }

    private HackerNews() {
    }
}
