package de.quagilis.amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class FibonacciCalculator extends DefaultConsumer {


    public FibonacciCalculator(Channel channel) {
        super(channel);
    }

    public void consumeQueue(String queueName) throws IOException {
        getChannel().basicConsume(queueName, AMQPConstants.AUTO_ACK, this);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        int n = Integer.parseInt(new String(body));
        int result = fib(n);
        String replyTo = properties.getReplyTo();
        if(null != replyTo) {
            publishNumber(result, replyTo);
        }
    }

    public void publishNumber(int number, String queueName) throws IOException {
        getChannel().basicPublish(
            AMQPConstants.DEFAULT_EXCHANGE,
            queueName,
            AMQPConstants.NO_PROPERTIES,
            Integer.toString(number).getBytes()
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
}
