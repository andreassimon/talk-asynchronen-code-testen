package de.quagilis.amqp;

import com.rabbitmq.client.*;

import java.io.IOException;

import static de.quagilis.amqp.AMQPConstants.*;

public class FibonacciCalculator extends DefaultConsumer {

    public static final String QUEUE_NAME = "calculate-fibonacci";

    public FibonacciCalculator(Channel channel) {
        super(channel);
    }

    public void consumeQueue(String queueName) throws IOException {
        getChannel().basicConsume(queueName, AMQPConstants.AUTO_ACK, this);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.printf("Received body '%s'\n", new String(body));
        int n = Integer.parseInt(new String(body));
        int result = fib(n);
        String replyTo = properties.getReplyTo();
        if(null != replyTo) {
            publishNumber(result, replyTo);
        }
    }

    public void publishNumber(int number, String queueName) throws IOException {
        String response = Integer.toString(number);
        System.out.printf("Sending fib = %s\t", response);
        byte[] responseBytes = response.getBytes();
        System.out.printf("bytes = ");
        for(byte b : responseBytes) {
            System.out.print(b);
            System.out.print(" ");
        }
        System.out.println();
        getChannel().basicPublish(
            AMQPConstants.DEFAULT_EXCHANGE,
            queueName,
            AMQPConstants.NO_PROPERTIES,
            responseBytes
        );
    }

    protected int fib(int n) {
        switch(n) {
            case 0:
            case 1:
                return n;
            default:
                return fib(n-2) + fib(n-1);
        }
    }


    public static void main(String[] args) throws IOException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        create(channel);
    }

    public static FibonacciCalculator create(Channel channel) throws IOException {
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator(channel);
        channel.queueDeclare(QUEUE_NAME, NOT_DURABLE, NOT_EXCLUSIVE, AUTO_DELETE, NO_ADDITIONAL_ARGUMENTS).getQueue();
        fibonacciCalculator.consumeQueue(QUEUE_NAME);
        System.out.printf("Fibonacci Calculator is running on queue '%s'\n", QUEUE_NAME);
        return fibonacciCalculator;
    }
}
