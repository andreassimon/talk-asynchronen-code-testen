package de.quagilis.asynctest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import de.quagilis.amqp.AMQPConstants;

import java.io.IOException;

public abstract class IntegerConsumer extends DefaultConsumer {

    public IntegerConsumer(Channel channel) {
        super(channel);
    }

    public void consumeQueue(String queueName) throws IOException {
        getChannel().basicConsume(queueName, AMQPConstants.AUTO_ACK, this);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        process(Integer.parseInt(new String(body)));
    }

    protected abstract void process(int payload);

}
