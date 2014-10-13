var express = require('express');

var app = express();

app.use(require('body-parser')());

var fibs = { };

function fibRec(n, fn) {
  if(n < 2) {
    setImmediate(fn, n);
  } else {
    fibRec(n-2, function(n2) {
      fibRec(n-1, function(n1) {
        fn(n2+n1);
      });
    });
  }
}

function fibLin(n, fn) {
  function fl(n2, n1, n0, _fn) {
    if(n0 == 0) {
      return _fn(n2);
    }
    setImmediate(fl, n1, n2+n1, n0-1, _fn);
  }

  setImmediate(fl, 0, 1, n, fn);
}

var fib = fibRec;

app.post('/', function(req, res) {
  var n = parseFloat(req.param('n'));
  fibs[n] = undefined;
  console.log('Calculating fib(%d)', n);
  fib(n, function(fibN) {
    fibs[n] = fibN;
    console.log('Calculated fib(%d)=%d', n, fibs[n]);
  });

  res
    .status(201)
    .header('Location', 'http://localhost:3000/' + n)
    .end()
});

app.get('/:n', function(req, res) {
  var fibN = fibs[req.param('n')];
  if(fibN) {
    return res
      .status(200)
      .end('' + fibs[req.param('n')]);
  }
  console.log('fib(%d) = %s', req.param('n'), fibN);
  res
    .status(404)
    .end();
});

var server = app.listen(3000, function() {
  console.log('Listening on port %d', server.address().port);
});
