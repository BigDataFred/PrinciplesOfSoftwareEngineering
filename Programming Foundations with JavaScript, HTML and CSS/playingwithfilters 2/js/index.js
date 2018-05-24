var img =null;
var output =null;

function upLoad(){
  var imgCvs = document.getElementById("canvas1");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
  var fileInput = document.getElementById("upLoadButton");
  img = new SimpleImage(fileInput);
  img.drawTo(imgCvs);
}

function draw2Canvas(image){
  var imgCvs = document.getElementById("canvas2");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
  image.drawTo(imgCvs);
}

function clearCanvas(){
  var imgCvs = document.getElementById("canvas1");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
  var imgCvs = document.getElementById("canvas2");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
}

function grayScale(){
  output = img;
   for(var pixel of output.values()){
    var r = pixel.getRed();
    var g = pixel.getGreen();
    var b = pixel.getBlue();
    var grayVal = (r+g+b)/3;
    pixel.setRed(grayVal);
    pixel.setGreen(grayVal);
    pixel.setBlue(grayVal);
  };
  var imgCvs = document.getElementById("canvas2");
  output.drawTo(imgCvs);
}

function blurFilt(){

}

function rgbFilt(rgb){
   output = img;
   var rgb = document.getElementById(canvasID);
   for(var pixel of output.values()){
     if rgb == "r"{       
      pixel.setRed(255);
     }
     elseif rgb == "g"{
       pixel.setGreen(255);
     }
     else {
       pixel.seBlue(255);
     }
  };
  var imgCvs = document.getElementById("canvas2");
  output.drawTo(imgCvs);
}