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
  //console.log("random value between 0 and 255", x);
  
  //child = child_process.spawn("node",
  //    ["locator","37.4031455,-122.0753819", "rankby=distance&types=park|amusement_park|aquarium|book_store|library|museum|zoo&"]
  //);
  var hoast = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
  hoast += "location=" + "-33.8670522,151.1957362" + "&" + "radius=500&types=food&name=cruise";
  hoast += "&key=" + fs.readFileSync('./key');



  https.get(hoast, function(ress) {
    //console.log("statusCode: ", res.statusCode);
    //console.log("headers: ", res.headers);
    //console.log("data: ", res.body);

    ress.on('data', function(d) {
      console.log("break a leg!", d.toString());
      //polisher.getRes('./res.json');
      res.write(d);

    });

  }).on('error', function(e) {
    console.error(e);
  });




  //console.log("pid of child", child.pid);
  //child.stdout.on("data", function (data) {
  //console.log("DATA ", data.toString());
  //res.write(data);
  //});


  //res.json(x);
})

app.put("/", function(req, res, next) {
  console.log('i am in put and i want to write a tag : ', req.text);
  res.json("ALOHA ^_^");
});

app.post("/", function(req, res, next) {
	console.log("got this post req with following req body", req.text);
	res.json("ALOHA ~/~");
})
app.listen(9000);