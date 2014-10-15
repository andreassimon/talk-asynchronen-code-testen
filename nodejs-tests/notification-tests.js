var amqp = require('amqp');
var should = require('should');

var connection = amqp.createConnection({ host: 'localhost' });

var MIN = '30';
var MAX = '40';
var FIB_MIN = '832040';
var FIB_MAX = '102334155';

describe('AMQP Fibonacci service', function() {
  it('calculates fib(' + MIN + ')', function(done) {
    connection.on('ready', function () {
      connection.queue('my-queue', function(q) {
        // Catch all messages
        q.bind('#');

        q.subscribe(function (message) {
          try {
            // Assert
            message.data.toString().should.eql(FIB_MIN);
            done();
          } catch(e) {
            done(e);
          }
        });

        // Act
        connection.publish('calculate-fibonacci', MIN, { replyTo: 'my-queue'});
      });
    });
  });

  it('calculates fib(' + MAX + ')', function(done) {
    // Wait for connection to become established.
    connection.on('ready', function () {

      // Use the default 'amq.topic' exchange
      connection.queue('my-queue', function(q) {
          // Catch all messages
          q.bind('#');

          // Receive messages
          q.subscribe(function (message) {
            try {
              message.data.toString().should.eql(FIB_MAX);
              done();
            } catch(e) {
              done(e);
            }
          });

        connection.publish('calculate-fibonacci', MAX, { replyTo: 'my-queue'});
      });
    });
  });
});
