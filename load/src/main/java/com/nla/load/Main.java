package com.nla.load;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Main {

    public static final String BASE_URL = "http://dev1/projectPlace/";

    public static void main(String[] args){


        HttpGet httpget = new HttpGet(BASE_URL);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(httpget)){
            System.out.println("GET "+ BASE_URL);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Done !");
    }
}
