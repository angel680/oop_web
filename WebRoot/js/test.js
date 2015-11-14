var myClass ={
	
	myFunction: function(id){
		document.getElementById(id).innerHTML = "ad";
	},
	
	getBulletins: function(id){
		document.getElementById(id).innerHTML = "sdsd";
		alert("gua");
		var reqxml;
		txtcontent = "";
		if(window.XMLHttpRequest()){
			reqxml = new XMLHttpRequest();
		}else{
			reqxml = new ActiveXObject("Microsoft.XMLHTTP");
		}
		reqxml.onreadystatechange = Function(){
			if(xmlhttp.readyState==4 && xmlhttp.status==200){
				xmldoc = reqxml.responseXML;
				bulletins = xmldoc.getElementsByTagName("bullet")
				for(i=0;i<x.length;i++){
					txtcontent =txtcontent + "<p>"+x[i].children[1].childNodes[0].nodeValue+"</p>"
								+ "<br />"; 
				}
				document.getElementById(id).innerHTML = "sdsd";
			}
		}
		
		reqxml.open("GET", "BulletinsHub", true);
		reqxml.send();
	},
	
	
	
	
	getUserName: function(){
		var re = new XMLHttpRequest();
		re.onreadystatechange=function()
		{
			if (re.readyState==4 && re.status==200)
			{
				alert(re.responseText);
			}
		}
		re.open("GET", "login?userID=U201417138&userPasswd=passwd", true);
		re.send();		
	}
}

function getStudentName(eleID){
	var req = new XMLHttpRequest();
	re.onreadystatechange=function()
	{
		if (re.readyState==4 && re.status==200)
		{
			document.getElementById(eleID).innerHTML = req.responseText;
		}
		re.open("GET", "login?userID=U201417138&userPasswd=passwd", true);
		re.send();
	}
}