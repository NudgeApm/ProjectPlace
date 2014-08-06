package com.nla.load;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        if (args.length != 1) {
            throw new IllegalArgumentException("one parameter expected : scenario file");
        }

        Path file = Paths.get(args[0]);

        if (!Files.isRegularFile(file) || !Files.isReadable(file)) {
            throw new IllegalArgumentException("file does not exists or is not readable");
        }

        List<Task> tasks = parseTasks(file);

        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("nothing to do !!");
        }


        final Scheduler scheduler;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            scheduleJobs(scheduler, tasks);

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
                log.info("Shutting down");
            }
        });
    }

    public static class TaskJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            doRequest(context.getMergedJobDataMap().getString("url"));
        }
    }

    private static List<Task> parseTasks(Path file) {
        List<Task> tasks = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = Files.newBufferedReader(file, Charset.defaultCharset());
            String line;
            while ((line = reader.readLine()) != null) {
                // ignore comments in file
                if(line.startsWith("#")){
                   continue;
                }
                String[] parts = line.split(";");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("line does not fit expected format : " + line);
                }
                tasks.add(new Task(parts[0], parts[1]));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

    private static void scheduleJobs(Scheduler scheduler, List<Task> tasks) throws SchedulerException {

        int i = 0;
        for (Task t : tasks) {

            JobDetail job = newJob(TaskJob.class)
                    .withIdentity("job" + i, "group")
                    .usingJobData("url", t.url)
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger" + i, "group")
                    .startNow()
                    .withSchedule(cronSchedule(t.schedule))
                    .build();

            scheduler.scheduleJob(job, trigger);
            i++;
        }

    }

    private static void doRequest(String url) {
        HttpGet httpget = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(httpget)) {
            //Calendar dateDepart = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM hh:mm:ss" );
                      
            log.info(sdf.format(new Date()) + "GET " + url);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final class Task {
        public final String url;
        public final String schedule;

        Task(String schedule, String url) {
            this.schedule = schedule;
            this.url = url;
        }
    }
}
