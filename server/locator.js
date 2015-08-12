var request = require('superagent');
var https = require('https');
var hoast = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
var url;
var fs = require('fs');
var args = process.argv;//get geolocation and params

//node locator.js  "-33.8670522,151.1957362" "radius=500&types=food&name=cruise"
//-33.8670522,151.1957362
//radius=500&types=food&name=cruise
function constructUri(){
  hoast += "location=" + args[2] + "&" + args[3];
  hoast += "&key=" + fs.readFileSync('./key');
  console.log("URI looks like", hoast);
  console.log("arguments", args[2]);
  console.log("arguments", args[3]);
  
}

//will redo in future version
constructUri();

https.get(hoast, function(res) {
  console.log("statusCode: ", res.statusCode);
  console.log("headers: ", res.headers);
  console.log("data: ", res.body);

  res.on('data', function(d) {
    console.log("Locations ", d.toString());
  });

}).on('error', function(e) {
  console.error(e);
});
