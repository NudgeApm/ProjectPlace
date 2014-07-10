package com.nla.load;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Main {

    public static final String BASE_URL = "http://dev1/projectPlace/";

    public static void main(String[] args) {

        doRequest(BASE_URL);

        System.out.println("Done !");
    }

    private static void doRequest(String url) {
        HttpGet httpget = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(httpget)) {
            System.out.println("GET " + url);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
