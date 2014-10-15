var amqp = require('amqp');

var connection = amqp.createConnection({ host: 'localhost' });

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
      });

    connection.publish('calculate-fibonacci', '30', { replyTo: 'my-queue'}, function() {
      console.log('Message published');
    });

  });
});

// var options = {
//   hostname: 'localhost',
//   port: 3000,
//   path: '/',
//   method: 'POST'
// };

// var req = http.request(options, function(res) {
//   console.log('STATUS: ' + res.statusCode);
//   console.log('HEADERS: ' + JSON.stringify(res.headers));
//   res.setEncoding('utf8');
//   res.on('data', function (chunk) {
//     console.log('BODY: ' + chunk);
//   });
// });

// req.on('error', function(e) {
//   console.log('problem with request: ' + e.message);
// });

// // write data to request body
// req.write('data\n');
// req.write('data\n');
// req.end();

// describe('AMQP Fibonacci service', function() {
//   it('calculates fib(30)', function() {
     
//   });
// });
