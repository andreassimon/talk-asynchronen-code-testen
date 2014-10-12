package de.quagilis.asynctest;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

/**
 * From Growing Object-Oriented Software by Nat Pryce and Steve Freeman
 */
public class NotificationTrace<T> {
    private final Object traceLock = new Object();
    private final List<T> trace = new ArrayList<>();
    private long timeoutMs;

    public void append(T message) {
        synchronized (traceLock) {
            trace.add(message);
            traceLock.notifyAll();
        }
    }

    public void containsNotification(Matcher<? super T> criteria) throws InterruptedException {
        Timeout timeout = new Timeout(timeoutMs);

        synchronized (traceLock) {
            NotificationStream<T> stream = new NotificationStream<>(trace, criteria);

            while (!stream.hasMatched()) {
                if(timeout.hasTimedOut()) {
                    throw new AssertionError(failureDescriptionFrom(criteria));
                }
                timeout.waitOn(traceLock);
            }
        }
    }

    private String failureDescriptionFrom(Matcher<? super T> criteria) {
        return "Did not match";
    }

    private static class NotificationStream<N> {
        private final List<N> notifications;
        private final Matcher<? super N> criteria;
        private int next = 0;

        private NotificationStream(List<N> notifications, Matcher<? super N> criteria) {
            this.notifications = notifications;
            this.criteria = criteria;
        }

        public boolean hasMatched() {
            while (next < notifications.size()) {
                if (criteria.matches(notifications.get(next))) {
                    return true;
                }
                next++;
            }
            return false;
        }
    }
}
