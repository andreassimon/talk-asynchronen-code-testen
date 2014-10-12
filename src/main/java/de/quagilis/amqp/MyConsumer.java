package de.quagilis.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {

    public MyConsumer(Channel channel) {
        super(channel);
    }

    public void consumeQueue(String queueName) throws IOException {
        getChannel().basicConsume(queueName, AMQPConstants.AUTO_ACK, this);
    }
}
