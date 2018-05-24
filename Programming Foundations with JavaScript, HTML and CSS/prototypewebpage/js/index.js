function upLoad(){
  var imgCanvas = document.getElementById("canvas1");
  var fileInput = document.getElementById("upLoad");
  var img = new SimpleImage(fileInput);
  
var context = imgCanvas.getContext("2d");
context.clearRect(0, 0, imgCanvas.width, imgCanvas.height);

img.drawTo(imgCanvas);
  //var txtInput= document.getElementById("txt");  //alert(txtInput.value);
}