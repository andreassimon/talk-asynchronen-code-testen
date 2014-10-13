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
        int result = process(Integer.parseInt(new String(body)));
        String replyTo = properties.getReplyTo();
        if(null != replyTo) {
            publishNumber(result, replyTo);
        }
    }

    protected abstract int process(int payload);

    public void publishNumber(int number, String queueName) {
        try {
            System.out.print(" > ");
            System.out.println(number);
            getChannel().basicPublish(AMQPConstants.DEFAULT_EXCHANGE, queueName, AMQPConstants.NO_PROPERTIES, Integer.toString(number).getBytes());
        } catch(IOException e) {
        }
    }
}
