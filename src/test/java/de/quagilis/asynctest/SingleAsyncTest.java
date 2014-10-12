package de.quagilis.asynctest;

import com.rabbitmq.client.*;
import de.quagilis.amqp.AMQPConstants;
import de.quagilis.amqp.MyQueueConsumer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SingleAsyncTest {


    public static final int MIN = 30;
    public static final int MAX = 40;
    private Connection connection;
    private Channel channel;
    private String queueName;

    @Before
    public void setUp() throws IOException {
        ExecutorService es = Executors.newFixedThreadPool(5);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connection = connectionFactory.newConnection(es);
        channel = connection.createChannel();
        queueName = channel.queueDeclare().getQueue();
    }

    @Test public void
    should_answer() throws Exception {
        //Arrange
        final CountDownLatch latch = new CountDownLatch(MAX-MIN);

        try {
            new MyQueueConsumer(connection.createChannel()) {
                AtomicInteger expected = new AtomicInteger(MIN+1);

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    final int payload = Integer.parseInt(new String(body));
                    assertEquals(expected.getAndIncrement(), payload);
                    latch.countDown();
                }

                private int fib(int n) {
                    if(n < 2) return n;
                    return fib(n-2) + fib(n-1);
                }
            }.consumeQueue(queueName);
        } catch(IOException e) {
            fail();
        }

        // Act
        publishNumbers(MIN, MAX);

        // Assert
        assertTrue("Did not complete within timeout", latch.await(30000, MILLISECONDS));
    }

    public void publishNumbers(int min, int max) {
        for(int i= min; i<= max; i++) {
            try {
                channel.basicPublish(AMQPConstants.DEFAULT_EXCHANGE, queueName, AMQPConstants.NO_PROPERTIES, Integer.toString(i).getBytes());
            } catch(IOException e) {
                fail();
            }
        }
    }

    private void println(final String String) {
        System.out.println(String);
    }

}
