package de.quagilis.asynctest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import de.quagilis.amqp.AMQPConstants;

import java.io.IOException;
import java.util.function.IntConsumer;

public class IntegerConsumer extends DefaultConsumer {

    private IntConsumer consumer;

    public IntegerConsumer(Channel channel, IntConsumer consumer) {
        super(channel);
        this.consumer = consumer;
    }

    public void consumeQueue(String queueName) throws IOException {
        getChannel().basicConsume(queueName, AMQPConstants.AUTO_ACK, this);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        consumer.accept(Integer.parseInt(new String(body)));
    }

}
