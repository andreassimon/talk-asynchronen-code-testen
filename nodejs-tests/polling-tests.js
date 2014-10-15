var http = require('http');
var should = require('should');

var options = {
  hostname: 'localhost',
  port: 3000,
  path: '/',
  method: 'POST'
};

describe('Fibonacci server', function() {
  it('should calculate fib(20)', function(done) {
    var req = http.request(options, function(res) {
      res.setEncoding('utf8');
      var location = res.headers.location;

      res.statusCode.should.eql(201);
      location.should.be.ok;

      function GET(loc) {
        http.get(location, function(res) {
          if(200 != res.statusCode) {
            setTimeout(GET, loc, 100);
          }
          res.on('data', function (chunk) {
            chunk.toString().should.eql('6765');
            done();
          });
        }).on('error', function(e) {
          console.log("Got error: " + e.message);
        });
      }

      GET(location);
    }).on('error', function(e) {
      console.log('problem with request: ' + e.message);
    });

    req.setHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
    // write data to request body
    req.write('n=20\n');
    req.end();
  });

  it('should calculate fib(30)', function(done) {
    var req = http.request(options, function(res) {
      res.setEncoding('utf8');
      var location = res.headers.location;

      res.statusCode.should.eql(201);
      location.should.be.ok;

      function GET(loc) {
        http.get(location, function(res) {
          if(200 != res.statusCode) {
            setTimeout(GET, loc, 100);
          }
          res.on('data', function (chunk) {
            chunk.toString().should.eql('832040');
            done();
          });
        }).on('error', function(e) {
          console.log("Got error: " + e.message);
        });
      }

      GET(location);
    }).on('error', function(e) {
      console.log('problem with request: ' + e.message);
    });

    req.setHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
    // write data to request body
    req.write('n=30\n');
    req.end();
  });
});
