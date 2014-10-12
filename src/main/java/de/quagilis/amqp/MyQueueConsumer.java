package de.quagilis.amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public abstract class MyQueueConsumer extends DefaultConsumer {

    public MyQueueConsumer(Channel channel) {
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
