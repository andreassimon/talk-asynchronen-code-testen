package de.quagilis.amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class FibonacciCalculator extends MyQueueConsumer {

    public FibonacciCalculator(Channel channel) {
        super(channel);
    }

    @Override
    public int process(int n) {
        System.out.printf("fib(%d) = %d\n", n, fib(n));
        return fib(n);
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
