var img =null;
var img2 =null;
var output =null;

function upLoad1(){
  var imgCvs = document.getElementById("canvas1");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
  var fileInput = document.getElementById("upLoad1");
  img = new SimpleImage(fileInput);
  img.drawTo(imgCvs);
}

function upLoad2(){
  var imgCvs = document.getElementById("canvas2");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
  var fileInput = document.getElementById("upLoad2");
  img2 = new SimpleImage(fileInput);
  img2.drawTo(imgCvs);
}

function createComposite(){
  if(img==null || ! img.complete){
     alert("Foreground image not loaded");
     };
  if(img2==null || ! img2.complete){
     alert("Background image not loaded");
     };
  output = new SimpleImage(img.width,img.height);
  for(var pixel of img.values()){
    var x = pixel.getX();
    var y = pixel.getY();
    var r = pixel.getRed();
    var b = pixel.getBlue();
    var greenTrsh = r+b;
    if (pixel.getGreen() > greenTrsh){
      output.setPixel(x,y,img2.getPixel(x,y));
    }
    else{
      output.setPixel(x,y,img.getPixel(x,y));
    };
  };
  clearCanvases();
  var imgCvs = document.getElementById("canvas1");
  output.drawTo(imgCvs);
}

function clearCanvases(){
  var imgCvs = document.getElementById("canvas1");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
  var imgCvs = document.getElementById("canvas2");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
  img=null;
  im2=null;
  output=null;
}

function saveComposite(){
  img2 = output;
  var imgCvs = document.getElementById("canvas1");
  var context = imgCvs.getContext("2d");
  context.clearRect(0, 0, imgCvs.width, imgCvs.height);
  var imgCvs = document.getElementById("canvas2");
  output.drawTo(imgCvs);
}