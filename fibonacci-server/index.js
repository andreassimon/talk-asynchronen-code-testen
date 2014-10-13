var express = require('express');

var app = express();

app.use(require('body-parser')());

var fibs = { };

function fib(n, fn) {
  if(n < 2) {
    setImmediate(fn, n);
  } else {
    fib(n-2, function(n2) {
      fib(n-1, function(n1) {
        fn(n2+n1);
      });
    });
  }
}

app.post('/', function(req, res) {
  var n = req.param('n');
  fibs[n] = undefined;
  setImmediate(function () {
    console.log('Calculating fib(' + n + ')');
    fib(n, function(fibN) {
      fibs[n] = fibN;
      console.log('Calculated fib(' + n + ')=' + fibs[n]); 
    });
  });

  res
    .status(201)
    .header('Location', 'http://localhost:3000/' + n)
    .end()
});

app.get('/:n', function(req, res) {
  if(fibs[req.param('n')]) {
    return res
      .status(200)
      .end('' + fibs[req.param('n')]);
  }
  console.log('fib(' + req.param('n') + ') NOT FOUND');
  res
    .status(404)
    .end();
});

var server = app.listen(3000, function() {
  console.log('Listening on port %d', server.address().port);
});
