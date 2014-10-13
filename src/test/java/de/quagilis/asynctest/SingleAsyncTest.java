package de.quagilis.asynctest;

import com.rabbitmq.client.*;
import de.quagilis.amqp.AMQPConstants;
import de.quagilis.amqp.FibonacciCalculator;
import de.quagilis.amqp.MyQueueConsumer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SingleAsyncTest {


    public static final int MIN = 30;
    public static final int MAX = 40;

    public static final int FIB_MIN = 832040;
    public static final int FIB_MAX = 102334155;

    public static final long TIMEOUT = 10000; // Milliseconds

    private Connection connection;
    private Channel channel;
    private String fibonacciQueueName;

    @Before
    public void setUp() throws IOException {
        ExecutorService es = Executors.newFixedThreadPool(5);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connection = connectionFactory.newConnection(es);
        channel = connection.createChannel();
        fibonacciQueueName = channel.queueDeclare().getQueue();
    }

    @Test public void
    should_answer() throws Exception {
        // Arrange
        NotificationTrace<Integer> trace = new NotificationTrace<>(TIMEOUT);
        String replyQueue = channel.queueDeclare().getQueue();
        new FibonacciCalculator(connection.createChannel()).consumeQueue(fibonacciQueueName);

        new MyQueueConsumer(connection.createChannel()) {
            @Override
            public int process(int next) {
                print("   < ");
                println(next);
                trace.append(next);
                return next;
            }
        }.consumeQueue(replyQueue);

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
        try {
            System.out.printf(" Publishing '%d' and reply to '%s'\n", number, replyQueue);
            _channel.basicPublish(AMQPConstants.DEFAULT_EXCHANGE, fibonacciQueueName,
//                    new AMQP.BasicProperties.Builder().build(),
                    new AMQP.BasicProperties.Builder().replyTo(replyQueue).build(),
                    Integer.toString(number).getBytes());
        } catch(IOException e) {
            fail();
        }
    }

    private void print(final String s) {
        System.out.print(s);
    }

    private void println(int n) {
        System.out.println(n);
    }
}
