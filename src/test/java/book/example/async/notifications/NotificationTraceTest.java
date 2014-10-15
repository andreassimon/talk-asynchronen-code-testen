package book.example.async.notifications;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class NotificationTraceTest {

  public static final long TIMEOUT_MS = 2000L;

  private NotificationTrace<String> trace;
  private ScheduledExecutorService scheduler;
  private ExecutorService executorService;
  private CountDownLatch latch;

  public static final int STRESSING_THREADS = 3;
  public static final int ITERATIONS = 200;
  public static final int SLEEPTIME = 50;


  @Before
  public void setUp() {
    trace = new NotificationTrace<>(TIMEOUT_MS);

    scheduler = Executors.newSingleThreadScheduledExecutor();
    executorService = Executors.newCachedThreadPool();
  }

  public CountDownLatch startStressing(int stressingThreads, Runnable action) {
    latch = new CountDownLatch(stressingThreads);

    for (int t = 0; t < stressingThreads; t++) {
      executorService.execute(action);
    }

    return latch;
  }

  @After
  public void tearDown() throws InterruptedException {
    executorService.shutdownNow();
    scheduler.shutdownNow();
  }

  @Test
  public void
  tracks_notifications() throws Exception {
    startStressing(STRESSING_THREADS, () -> {
      try {
        for (int i = 0; i < ITERATIONS; i++) {
          trace.append("NOT-WANTED");
          try {
            Thread.sleep(SLEEPTIME);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } finally {
        latch.countDown();
      }
    });
    trace.append("WANTED");

    trace.containsNotification(equalTo("WANTED"));
  }

  @Test
  public void failsIfNoMatchingMessageReceived() throws InterruptedException {
    startStressing(STRESSING_THREADS, () -> {
      try {
        for (int i = 0; i < ITERATIONS; i++) {
          trace.append("NOT-WANTED");
          try {
            Thread.sleep(SLEEPTIME);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } finally {
        latch.countDown();
      }
    });
    try {
      trace.containsNotification(equalTo("WANTED"));
    } catch (AssertionError e) {
      assertThat("error message includes trace of messages received before failure",
        e.getMessage(), containsString("NOT-WANTED"));
      latch.await();
      return;
    }

    fail("should have thrown AssertionError");
  }

  @Test public void
  is_thread_safe() throws Exception {
    latch = startStressing(STRESSING_THREADS, () -> {
      try {
        for (int i = 0; i < ITERATIONS; i++) {
          trace.append("NOT-WANTED");
          try {
            Thread.sleep(SLEEPTIME);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } finally {
        latch.countDown();
      }
    });
    scheduler.schedule(
      () -> trace.append("WANTED"),
      100, TimeUnit.MILLISECONDS
    );

    trace.containsNotification(equalTo("WANTED"));

    latch.await();
    assertThat(
      trace.getAppendCount(),
      is(equalTo((long) STRESSING_THREADS * ITERATIONS + 1))
    );
  }

}
