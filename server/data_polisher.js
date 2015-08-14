var fs = require('fs');
//var json = process.argv[2];

module.exports.getRes = function getRes(json) {
  //console.log(json);
  var x = fs.readFileSync(json, 'utf8');
  var data = JSON.parse(x);
  console.log("nr of hits" , data.results.length);
  for (i = 0; i < data.results.length; i++) {
    console.log("place name ", data.results[i].name);
    console.log("place id   ", data.results[i].place_id);
    console.log("place type ", data.results[i].types);
    if (data.results[i].photos !== undefined)
      console.log("photo ref", data.results[i].photos[0].photo_reference);
    console.log();
  }
}

//getRes();