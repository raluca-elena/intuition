var http = require ("http");
var url = require("url");
var express = require('express');
var getRawBody = require('raw-body');
var cors = require('cors');
var child_process = require('child_process');
var https = require('https');
var fs = require('fs');

function generate(){
  var x = Math.random()* (255 - 0) + 0;;
  console.log("random value is", x);
  return x;
}

var app = express();
app.use(cors());

//handle get/post/put
app.use(function (req, res, next) {
  getRawBody(req, {
    length: req.headers['content-length'],
    limit: '1mb',
    encoding: 'utf8'
  }, function (err, string) {
    if (err)
      return next(err)

    req.text = string
    next()
  })
})

app.get("/", function(req, res) {
  var x =generate();
  
  //    ["locator","37.4031455,-122.0753819", "rankby=distance&types=park|amusement_park|aquarium|book_store|library|museum|zoo&"]
  var hoast = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
  hoast += "location=" + "-33.8670522,151.1957362" + "&" + "radius=500&types=food&name=cruise";
  hoast += "&key=" + fs.readFileSync('/home/ubuntu/getPlaces/key');
  var r = https.get(hoast, function(ress) {
    ress.on('data', function(d) {
      console.log("break a leg!");
      res.write(d);
    });

    ress.on('end', function(d){ 
	console.log("SENDING DATA");
res.send();});
  });
  r.end();
  r.on('error', function(e) {
    console.error(e);
  });
})

app.put("/", function(req, res, next) {
  res.json("ALOHA ^_^");
});

app.post("/", function(req, res, next) {
  res.json("ALOHA ~/~");
})
app.listen(9000);
