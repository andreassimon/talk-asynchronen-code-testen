package de.quagilis.asynctest;


import book.example.async.polling.Poller;
import book.example.async.polling.Probe;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static book.example.async.polling.Poller.assertEventually;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PollingTest {

    public static final int MIN = 30;
    public static final int MAX = 33;

    public static final int FIB_MIN = 832040;
    public static final int FIB_MAX = 3524578;

    public static final String UTF_8 = "UTF-8";
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded;charset=" + UTF_8;
    public static final long LONG_TIMEOUT = 10000L;


    @Test public void
    calculates_fib_30() throws Exception {
        // Act
        HttpURLConnection connection = POST("http://localhost:3000/", MIN);
        String fibLocation = connection.getHeaderField("Location");

        // Assert
        assertEventually(responseTo(fibLocation, equalTo(Integer.toString(FIB_MIN))), LONG_TIMEOUT);
    }

    @Test public void
    calculates_fib_33() throws Exception {
        // Act
        HttpURLConnection connection = POST("http://localhost:3000/", MAX);
        String fibLocation = connection.getHeaderField("Location");

        // Assert
        assertEventually(responseTo(fibLocation, equalTo(Integer.toString(FIB_MAX))), LONG_TIMEOUT);
    }


    public Probe responseTo(final String fibLocation, final Matcher<String> matcher) {
        return new Probe() {

            private String lastResponse = "";

            @Override
            public boolean isSatisfied() {
                return matcher.matches(lastResponse);
            }

            @Override
            public void sample() {
                try {
                    lastResponse = GET(fibLocation);
                } catch (IOException e) {
                    lastResponse = e.toString();
                }
            }

            @Override
            public void describeAcceptanceCriteriaTo(Description d) {
                d.appendText("GET ")
                 .appendValue(fibLocation)
                 .appendText(" responds with ")
                 .appendDescriptionOf(matcher);
            }

            @Override
            public void describeFailureTo(Description d) {
                d.appendText("last response was ")
                 .appendValue(lastResponse);
            }
        };
    }

    private String GET(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setUseCaches(false);

        StringBuilder body = new StringBuilder();

        try (InputStream response = connection.getInputStream()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, UTF_8))) {
                for (String line; (line = reader.readLine()) != null;) {
                    body.append(line);
                }
            }
        }

        return body.toString();
    }

    private HttpURLConnection POST(String url, int n) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", UTF_8);
        connection.setRequestProperty("Content-Type", FORM_URL_ENCODED);

        try (OutputStream output = connection.getOutputStream()) {
            byte[] body = String.format("n=%d\n", n).getBytes(UTF_8);
            output.write(body);
        }

        return connection;
    }

}
