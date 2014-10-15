var amqp = require('amqp');

var connection = amqp.createConnection({ host: 'localhost' });

var FIB_MIN = 832040;

describe('AMQP Fibonacci service', function() {
  it('calculates fib(30)', function(done) {
    // Wait for connection to become established.
    connection.on('ready', function () {
      console.log('AMQP connection is ready');

      // Use the default 'amq.topic' exchange
      connection.queue('my-queue', function(q) {
          // Catch all messages
          q.bind('#');

          // Receive messages
          q.subscribe(function (message) {
            // Print messages to stdout
            // require('inspect')(message.data);
            console.log(message.data.toString());
            console.log(message);
            // console.log(assertEquals);
            // assertEquals(FIB_MIN, message.data.toString());
            done();
          });

        connection.publish('calculate-fibonacci', '30', { replyTo: 'my-queue'}, function() {
          console.log('Message published');
        });

      });
    });
  });
});
