package de.quagilis.asynctest;

import book.example.async.notifications.NotificationTrace;
import com.rabbitmq.client.*;
import de.quagilis.amqp.AMQPConstants;
import de.quagilis.amqp.FibonacciCalculator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class NotificationTest {


    public static final int MIN = 30;
    public static final int MAX = 40;

    public static final int FIB_MIN = 832040;
    public static final int FIB_MAX = 102334155;

    public static final long TIMEOUT = 2000; // Milliseconds

    private Connection connection;
    private Channel channel;

    @Before
    public void setUp() throws IOException {
        ExecutorService es = Executors.newFixedThreadPool(5);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connection = connectionFactory.newConnection(es);
        channel = connection.createChannel();
    }

    @Test public void
    should_reply_with_Fibonacci_numbers() throws Exception {
        // Arrange
        NotificationTrace<Integer> trace = new NotificationTrace<>(TIMEOUT);
        String replyQueue = channel.queueDeclare().getQueue();
        FibonacciCalculator.create(connection.createChannel());

        new IntegerConsumer(connection.createChannel(), trace::append).consumeQueue(replyQueue);

        // Act
        publishNumbers(MIN, MAX, replyQueue);

        // Assert
        trace.containsNotification(equalTo(FIB_MIN));
        trace.containsNotification(equalTo(FIB_MAX));
    }


    public void publishNumbers(int min, int max, String queue) throws IOException {
        for(int i= min; i<= max; i++) {
            publishNumber(i, channel, queue);
        }
    }

    public void publishNumber(int number, Channel _channel, String replyQueue) throws IOException {
        _channel.basicPublish(
            AMQPConstants.DEFAULT_EXCHANGE,
            FibonacciCalculator.QUEUE_NAME,
            new AMQP.BasicProperties.Builder().replyTo(replyQueue).build(),
            Integer.toString(number).getBytes()
        );
    }

}
