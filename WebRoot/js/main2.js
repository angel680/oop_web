/**
 * 
 */
var userName = document.getElementById("btnLogin");
var localeUserID = "";
var localeUserName = "";
var bulletinID = new Array();

function getUserInfo(){
	
}




var bulletinlength = 0;

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
			
			var bulletinTitle = new Array();
			var bulletinMsg = new Array();
			var bulletinTime = new Array();
			var bulletinByWhom = new Array();
			var bulletinFavors = new Array();
			var commentsID = new Array();
			var commentsMsg = new Array();
			var commentsTime = new Array();
			var cuserName = new Array();
			bulletinlength = bulletins.length;
			for(i=0;i<bulletins.length;i++){
				bulletinID[i]= bulletins[i].children[0].childNodes[0].nodeValue;
				bulletinTitle[i] = bulletins[i].children[1].childNodes[0].nodeValue;
				bulletinMsg[i]= bulletins[i].children[2].childNodes[0].nodeValue;
				bulletinTime[i] = bulletins[i].children[3].childNodes[0].nodeValue;
				bulletinByWhom[i]= bulletins[i].children[4].childNodes[0].nodeValue;
				bulletinFavors[i]= bulletins[i].children[5].childNodes[0].nodeValue;
				commentsID[i] = new Array();
				commentsMsg[i] = new Array();
				commentsTime[i] = new Array();
				cuserName[i] = new Array();
				var allcomments = bulletins[i].children[6];
				var comments = allcomments.getElementsByTagName("comment");
				for(j=0;j <comments.length; j++){
					commentsID[i][j] = comments[j].children[0].childNodes[0].nodeValue;
					commentsMsg[i][j] = comments[j].children[1].childNodes[0].nodeValue;
					commentsTime[i][j] = comments[j].children[2].childNodes[0].nodeValue;
					cuserName[i][j] = comments[j].children[3].childNodes[0].nodeValue;
				}
				
			}
			
			for(i=0;i<bulletins.length;i++){
				var sdiv=document.createElement("div");  
				sdiv.className="bulletin";  
				
				txtcontent = ("<div class='bulletin'>"+
						"<img class='headpic' src='headpic.png'>"+
						   "<p class='bulletinTime'>"+ bulletinTime[i] +"</p>"+
							"<div class='bulletinMain'>"+
							 "<div class='bulletinMainSon'>"+
							 "<h2 class='bulletinTitle'>"+bulletinTitle[i] +"</h2>"+
							 "<p class='bulletinContent'>"+bulletinMsg[i] +"</p>"+	
							"</div>"+
							"</div>"+
							"<div class ='bulletinBtn'>"+
							"<input class='bulletinLike' type='button' value = "+bulletinFavors[i] + " id = bulletinLike"+i+" >"+
							"<input class='bulletinCommentBtn' id = bulletinCommentBtn"+ i 
							+ " type='button' value='评论'></div>"
							+"<div class='bulletinComment' id = bulletinComment"+i+ " >");
				
				
				for(j=0;j<commentsID[i].length;j++){
				txtcontent+=(   "<p class='bulletinCommentName'>"+ cuserName[i][j] +":</p>"+
					            "<p class='bulletinCommentCon'>" +commentsMsg[i][j]+ " </p>"+
					            "<p class='bulletinCommentTime'>"+commentsTime[i][j] + "</p>"
					          
					          );
				}    
					
				txtcontent += ("<input type='text' class='bulletinCommentInput'>" +
						"<input type='button' class='bulletinCommentInputBtn' id=bulletinCommentInputBtn" + i + " value='发表评论'>"+
					        "</div></div>");
				if(i==bulletinlength-1){
					txtcontent +="<div id='promptBanner'>没有更多公告了！	</div>";
				}
				
				sdiv.innerHTML = txtcontent;
				container.appendChild(sdiv);
				
				
				////////////////////////////////////////////////////////
				(function(i){
				    $("#bulletinCommentBtn"+i).click(function(){
				      $("#bulletinComment"+i).slideToggle("slow");
				    });
				})(i);
				
							
				
				//这里出了问题
				////////////////////////////////////////////////////////
			}	
			
			var bulletinCommentInputBtns = document.getElementsByClassName("bulletinCommentInputBtn");
			for (var k = 0; k < bulletinCommentInputBtns.length; k++) {
			    bulletinCommentInputBtns[k].onclick = funcComment;
			};
			
			//绑定 赞
            var bulletinLikes = document.getElementsByClassName("bulletinLike");
            for (var i = 0; i < bulletinLikes.length; i++) {
            	bulletinLikes[i].onclick = funcBulletinLike;
            };
			
			
			
			
		}
	}
	
	reqxml.open("GET", "BulletinsHub?reqtype=getAll", true);
	reqxml.send();
	
	
	var reqxml2;
	if(window.XMLHttpRequest){
		reqxml2 = new XMLHttpRequest();
	}else{
		reqxml2 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	reqxml2.onreadystatechange = function(){
		if(reqxml2.readyState==4 && reqxml2.status==200){
			xmldoc = reqxml2.responseXML;
			student = xmldoc.getElementsByTagName("Student");
			if(student[0].childElementCount != 0){
				localeUserID = student[0].children[0].childNodes[0].nodeValue;
				localeUserName = student[0].children[1].childNodes[0].nodeValue;
				document.getElementById("userName").innerHTML = "欢迎您，" + localeUserName;
			}
			
		}

	}
	reqxml2.open("GET","Login?reqtype=getUserInfoBySession", true);
	reqxml2.send();
		
}


var funcComment = function() {
	
	var xmlhttp;
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	}else{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
    ///获取上一个兄弟节点，得到内容
    var texts = $(this).prev().val();
    $(this).prev().attr("value","");
    
    if(localeUserName == ""){
    	window.location.href="login.html";
    	return;
    }
    
    if(texts == ""){
    	return;
    }
   
    var now = (new Date()).toLocaleString();
    var who = localeUserID;
    var getI = this.id;
    var index = getI.charAt(getI.length-1);
        // 得到 时间
        //得到是谁
        //将内容此格式显示出来 在 评论框前面
    $(this).prev().before("<p class='bulletinCommentName'>" + localeUserName +": </p>" +
        "<p class='bulletinCommentCon'>" + texts + "</p>" +
        " <p class='bulletinCommentTime'>刚刚</p>");
    
    xmlhttp.onreadystatechange= function(){
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			if(xmlhttp.responseText == "failed"){
				alert("评论失败");
			}
			
		}

	}
    //向服务器发请求
    xmlhttp.open("GET", "BulletinsHub?reqtype=addComment&bulletID=" + bulletinID[index] + "&userID="+ who+ "&commentMsg=" + texts , true);
    xmlhttp.send();
}

//事件绑定 会有bug

var funcBulletinLike = function() {


    //得到是那条公告
    var who = localeUserID;
    var getI = this.id;
    var index = getI.charAt(getI.length - 1);
    //得到是谁*********************bulletinID[index]*********!!!!!!!

    //判断是否赞过 *****************如何从服务器上获取
   /*
    if (likes[index] == 1) {
        alert("对不起，您已经赞过一次了");
        likes[index] = 1;
        return;

    }*/
    if(who == ""){
    	window.location.href="login.html";
    	return;
    }
    
    var favorbutton = this;
    //声明交换数据的对象
    var whoLike;
    if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
        whoLike = new XMLHttpRequest();
    } else { // code for IE6, IE5
        whoLike = new ActiveXObject("Microsoft.XMLHTTP");
    }
    //异步的回调函数，写在open send 前面
    whoLike.onreadystatechange = function() {
            if (whoLike.readyState == 4 && whoLike.status == 200) {
                //字符串格式
                if (whoLike.responseText == "failed") {
                    alert("something happened")
                }
                if(whoLike.responseText == "added"){
                	var bulletinLikes = document.getElementsByClassName("bulletinLike");
                	var favornum =  bulletinLikes[index].value;
                	favornum = parseInt(favornum) + 1;
                	bulletinLikes[index].value = favornum;
                }
                if (whoLike.responseText == "deleted") {
                	var bulletinLikes = document.getElementsByClassName("bulletinLike");
                	var favornum =  bulletinLikes[index].value;
                	favornum = parseInt(favornum) - 1;
                	bulletinLikes[index].value = favornum;
				}


            }
        }
        // 向服务器发送请求
    whoLike.open("GET", "BulletinsHub?reqtype=addFavor&bulletID="+ bulletinID[index] + "&userID="+ who, true);
    whoLike.send();

}






