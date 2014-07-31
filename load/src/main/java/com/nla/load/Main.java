package com.nla.load;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class Main {

    public static final String BASE_URL = "http://dev1/projectPlace/";

    // TODO :
    // - slf4j logging
    // - allow to use more than one set of scenarios
    public static void main(String[] args) {
        final Scheduler scheduler;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    scheduler.shutdown();
                } catch (SchedulerException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Shutting down");
            }
        });
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
