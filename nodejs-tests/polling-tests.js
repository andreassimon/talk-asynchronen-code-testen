var http = require('http');
var should = require('should');

var POST_fib = {
  hostname: 'localhost',
  port: 3000,
  path: '/',
  method: 'POST'
};
var POLL_DELAY = 100;

function pollGET(url, done) {
  http.get(url, function(res) {
    if(200 != res.statusCode) {
      setTimeout(pollGET, POLL_DELAY, url, done);
    }
    res.on('data', function (chunk) {
      chunk.toString().should.eql('6765');
      done();
    });
  }).on('error', done);
}

describe('Fibonacci server', function() {
  it('should calculate fib(20)', function(done) {
    var req = http.request(POST_fib, function(res) {
      res.setEncoding('utf8');
      res.statusCode.should.eql(201);
      res.headers.location.should.be.ok;
      pollGET(res.headers.location, done);
    }).on('error', done);

    req.setHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
    req.write('n=20\n');
    req.end();
  });

  it('should calculate fib(30)', function(done) {
    var req = http.request(POST_fib, function(res) {
      res.setEncoding('utf8');
      var location = res.headers.location;

      res.statusCode.should.eql(201);
      location.should.be.ok;

      function GET(url) {
        http.get(url, function(res) {
          if(200 != res.statusCode) {
            setTimeout(GET, POLL_DELAY, url);
          }
          res.on('data', function (chunk) {
            chunk.toString().should.eql('832040');
            done();
          });
        }).on('error', done);
      }

      GET(location);
    }).on('error', done);

    req.setHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
    // write data to request body
    req.write('n=30\n');
    req.end();
  });
});
