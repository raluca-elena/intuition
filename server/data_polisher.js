var fs = require('fs');
var json = process.argv[2];

function getRes() {
  //console.log(json);
  var x = fs.readFileSync(json, 'utf8');
  var data = JSON.parse(x);
  console.log("length of data" , data.results.length);
  for (i = 0; i < data.results.length; i++) {
    console.log("data is ", data.results[i].name);
    console.log("place id ", data.results[i].place_id);
    console.log("type", data.results[i].types);
  }
}

getRes();