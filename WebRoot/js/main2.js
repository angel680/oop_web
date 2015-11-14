/**
 * 
 */

var container = document.getElementById("container");
window.onload = function showBulletins(){
	var reqxml;
	txtcontent = "";
	if(window.XMLHttpRequest){
		reqxml = new XMLHttpRequest();
	}else{
		reqxml = new ActiveXObject("Microsoft.XMLHTTP");
	}
	reqxml.onreadystatechange = function(){
		if(reqxml.readyState==4 && reqxml.status==200){
			xmldoc = reqxml.responseXML;
			bulletins = xmldoc.getElementsByTagName("bullet")
			
			var bulletinID = new Array();
			var bulletinMsg = new Array();
			var bulletinTime = new Array();
			var bulletinByWhom = new Array();
			var bulletinFavors = new Array();
			
			for(i=0;i<bulletins.length;i++){
				bulletinID[i]= bulletins[i].children[0].childNodes[0].nodeValue;
				bulletinMsg[i]= bulletins[i].children[1].childNodes[0].nodeValue;
				bulletinTime[i] = bulletins[i].children[2].childNodes[0].nodeValue;
				bulletinByWhom[i]= bulletins[i].children[3].childNodes[0].nodeValue;
				bulletinFavors[i]= bulletins[i].children[4].childNodes[0].nodeValue;
			}
			
			for(i=0;i<bulletins.length;i++){
			 var sdiv=document.createElement("div");  
              sdiv.className="bulletin";  
              //sdiv.id = "ps"+i;
             // document.getElementById("ps"+i).classNa = "bulletin";
              
			txtcontent =( "<p><b>Message:</b><br/><br/>" + bulletinMsg[i] +"<br/><br/>"
								+ "<b>Time: </b>" + bulletinTime[i] + "     <b>By: </b>" + bulletinByWhom[i]
								+ "<b>   Favors </b>"+ bulletinFavors[i] +	"</p><hr>");
			sdiv.innerHTML = txtcontent;
			container.appendChild(sdiv);  
			}	
			
			
		}
	}
	
	reqxml.open("GET", "BulletinsHub?reqtype=getAll", true);
	reqxml.send();
}
