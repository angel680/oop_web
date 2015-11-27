//加载界面时显示公告

var changeContainer = document.getElementById("changeContainer");

window.onload = function showBulletinTitles() {

    //声明交换数据的对象

    var bulletinTitles;
    if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
        bulletinTitles = new XMLHttpRequest();
    } else { // code for IE6, IE5
        bulletinTitles = new ActiveXObject("Microsoft.XMLHTTP");
    }
    //异步的回调函数，写在open send 前面
    bulletinTitles.onreadystatechange = function() {
            if (bulletinTitles.readyState == 4 && bulletinTitles.status == 200) {

                //xml格式
                xmldoc = bulletinTitles.responseXML;
                
                bulletins = xmldoc.getElementsByTagName("bullet")

                var bulletinTitle = new Array();
                var bulletinID = new Array();
                
                bulletinlength = bulletins.length;
                for (i = 0; i < bulletins.length; i++) {
                	bulletinID[i]= bulletins[i].children[0].childNodes[0].nodeValue;                																															
                    bulletinTitle[i] = bulletins[i].children[1].childNodes[0].nodeValue;
                    
                }

                //定义单个重复子div
                
                for(i = 0; i < bulletinlength; i++){
                	var temp = "";
                    var sdiv = document.createElement("div");
                	temp = "<form method = 'post' action='BulletinsHub?reqtype=modify&bulletID="+bulletinID[i]+ "'>" +
                	"<p class='bulletinsName'>" + bulletinTitle[i] + "</p>" +
                    "<input class='btnDelete' type='submit' value='删除' name=operbutton>" +
                    "<input class='btnAmend' type='submit' value='修改' name=operbutton>" +
                    "</form>";
                    sdiv.className = "bulletins";
                    sdiv.innerHTML = temp;
                    changeContainer.appendChild(sdiv);
                }
                

            }

        }
        // 向服务器发送请求
    bulletinTitles.open("GET", "BulletinsHub?reqtype=getAll", true);
    bulletinTitles.send();
}
