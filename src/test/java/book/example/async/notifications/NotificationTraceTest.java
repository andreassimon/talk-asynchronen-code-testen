package book.example.async.notifications;

import org.junit.Test;

import java.util.concurrent.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;


public class NotificationTraceTest {

    public static final long TIMEOUT_MS = 2000L;

    @Test public void
    tracks_notifications() throws Exception {
        NotificationTrace<String> trace = new NotificationTrace<>(TIMEOUT_MS);

        trace.append("WANTED");

        trace.containsNotification(equalTo("WANTED"));
    }

    @Test public void
    is_thread_safe() throws Exception {
        NotificationTrace<String> trace = new NotificationTrace<>(TIMEOUT_MS);
        int repetitions = 10000;

        int nThreads = 10;
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        for(int t = 0; t <= nThreads; t++) {
            executorService.schedule(() -> {
                while(true) {
                    trace.append("NOT-WANTED");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 10, TimeUnit.MILLISECONDS);
        }

        new Thread().start();

        executorService.schedule(() ->
            trace.append("WANTED"),
            100,
            TimeUnit.MILLISECONDS
        );

        trace.containsNotification(equalTo("WANTED"));
        trace.containsNotification(equalTo("NOT-WANTED"));

        executorService.shutdown();
    }

}
