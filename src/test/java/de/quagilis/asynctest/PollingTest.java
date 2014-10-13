package de.quagilis.asynctest;


import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PollingTest {

    public static final int MIN = 30;
    public static final int MAX = 40;

    public static final int FIB_MIN = 832040;
    public static final int FIB_MAX = 102334155;

    public static final String UTF_8 = "UTF-8";
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded;charset=" + UTF_8;


    @Test
    public void calculates_fib_30() throws Exception {
        HttpURLConnection connection = POST("http://localhost:3000/", MAX);

        String fibLocation = connection.getHeaderField("Location");

        assertThat(GET(fibLocation), equalTo(Integer.toString(FIB_MAX)));
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
