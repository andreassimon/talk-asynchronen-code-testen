package de.quagilis.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AMQPConstants {
    public static final boolean DURABLE = true;
    public static final boolean NOT_DURABLE = !DURABLE;

    public static final boolean EXCLUSIVE = true;
    public static final boolean NOT_EXCLUSIVE = !EXCLUSIVE;

    public static final boolean AUTO_DELETE = true;
    public static final boolean NO_AUTO_DELETE = !AUTO_DELETE;

    public static final boolean INTERNAL = true;
    public static final boolean PUBLIC = !INTERNAL;

    /**
     * Used for channel.queueDelete attributes ifUnused, and ifEmpty
     */
    public static final boolean ALWAYS = false;

    public static final Map<String, Object> NO_ADDITIONAL_ARGUMENTS = null;
    public static final Map<String, ?> DEFAULT_AMQP_CLIENT_PROPERTIES = new HashMap<String, Object>() {{
        put("host", "localhost");
        put("virtualHost", "/");
        put("port", 5672);
        put("username", "guest");
        put("password", "guest");
        put("requestedHeartbeat", 0);
    }};

    public static final boolean AUTO_ACK = true;
    public static final boolean NO_AUTO_ACK = false;

    public static final boolean SINGLE_MESSAGE = false; // Passed to 'multiple' argument

    // See http://www.rabbitmq.com/tutorials/amqp-concepts.html
    public static final String DEFAULT_EXCHANGE = "";
    public static final String DEFAULT_DIRECT_EXCHANGE  = "amq.direct";
    public static final String DEFAULT_FANOUT_EXCHANGE  = "amq.fanout";
    public static final String DEFAULT_TOPIC_EXCHANGE   = "amq.topic";
    public static final String DEFAULT_HEADERS_EXCHANGE = "amq.match";


    public static final String DIRECT_EXCHANGE = "direct";
    public static final String FANOUT_EXCHANGE = "fanout";
    public static final String TOPIC_EXCHANGE  = "topic";


    public static final com.rabbitmq.client.AMQP.BasicProperties NO_PROPERTIES = null;

}
