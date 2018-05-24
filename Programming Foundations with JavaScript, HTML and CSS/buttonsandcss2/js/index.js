function dochangeON(){
  var dd1 = document.getElementById('div1');
 var choice = confirm('Do you want to turn this ON?');
  if (choice='OK'){
    dd1.className ='yellowback';
    dd1.innerHTML = 'Bonjour';
  }
}

function dochangeOFF(){
var dd2=document.getElementById('div2');
var im1=document.getElementById('img1');
var choice=confirm('Do you want to turn this ON?');
  if (choice='OK'){
    dd2.className ='yellowback';
    dd2.innerHTML = 'Que tengas un dia de maravilla';
    im1.src ='https://m.popkey.co/350290/4QDYD.gif';
  }
}

 document.getElementById("dateANDtime").innerHTML = Date();