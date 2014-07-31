package com.nla.load;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static final String BASE_URL = "http://dev1/projectPlace/";

    private static AtomicBoolean stop = new AtomicBoolean(false);

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                stop.set(true);
                System.out.println("Shutting down");
            }
        });

        while (!stop.get()) {
            doRequest(BASE_URL);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
