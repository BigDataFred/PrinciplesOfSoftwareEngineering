function makeWhite(){
  var x1 = document.getElementById('d1');
  var ctx = x1.getContext("2d");
  ctx.clearRect(0,0,x1.width,x1.height);
  x1.style.backgroundColor = 'cyan';
  var sldr1=document.getElementById('sldr1');
  sldr1.value = 0;
  var sldr2=document.getElementById('sldr2');
  sldr2.value = 0;
}
function doColor(){
  var x1 = document.getElementById('d1');
  var ctx = x1.getContext("2d");
  ctx.clearRect(0,0,x1.width,x1.height);
  var clri = document.getElementById('clr');
  x1.style.backgroundColor = clri.value;
   var sldr1 =document.getElementById('sldr1');
  sldr1.value = 0;
  var sldr2 =document.getElementById('sldr2');
  sldr2.value = 0;
}

function doSquare1(){
  var x1 = document.getElementById('d1');
  var ctx = x1.getContext("2d");
  ctx.clearRect(0,0,x1.width,x1.height);
  var sldr = document.getElementById('sldr1');
  var sze = sldr.value;
  ctx.fillStyle = 'green';
  var pct = (sze/sldr.max);    ctx.fillRect(0,0,pct*x1.width,pct*x1.height);
}

function doSquare2(){
  var x1 = document.getElementById('d1');
  var ctx = x1.getContext("2d");
  ctx.clearRect(0,0,x1.width,x1.height);
  var sldr = document.getElementById('sldr2');
  var sze = sldr.value;
  ctx.fillStyle = 'green';
  var pct = (sze/sldr.max);    
  var cX = x1.width/2;
  var cY = x1.height/2;
  var w = pct*x1.width;
  var h = pct*x1.height;
  ctx.fillRect(cX-w/2,cY-h/2,w,h);
}