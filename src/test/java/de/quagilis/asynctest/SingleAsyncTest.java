package de.quagilis.asynctest;

import com.rabbitmq.client.*;
import de.quagilis.amqp.AMQPConstants;
import de.quagilis.amqp.MyConsumer;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SingleAsyncTest {


    private Channel channel;

    @Test public void
    should_answer() throws Exception {
        final CountDownLatch latch = new CountDownLatch(10);

        ExecutorService es = Executors.newFixedThreadPool(5);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection(es);
        channel = connection.createChannel();

        String queueName = channel.queueDeclare().getQueue();

        Arrays.asList("Consumer 1", "Consumer 2", "Consumer 3").forEach( c -> {
            try {
                Channel _channel = connection.createChannel();
                new MyConsumer(_channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        final int i = Integer.parseInt(new String(body));
                        println(" [" + c + " @" + Thread.currentThread().getName() + "] " + fib(i));
                        latch.countDown();
//                        fail("EPIC FAIL");
                    }

                    private int fib(int n) {
                        if(n < 2) return n;
                        return fib(n-2) + fib(n-1);
                    }
                }.consumeQueue(queueName);
            } catch(IOException e) {
                fail();
            }
        });

        for(int i=30; i<=40; i++) {
            try {
                channel.basicPublish(AMQPConstants.DEFAULT_EXCHANGE, queueName, AMQPConstants.NO_PROPERTIES, Integer.toString(i).getBytes());
            } catch(IOException e) {
                fail();
            }
        }

        assertTrue("Did not complete within timeout", latch.await(30000, MILLISECONDS));
    }

    private void println(final String String) {
        System.out.println(String);
    }

}
