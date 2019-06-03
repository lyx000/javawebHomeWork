<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
		<span id="num">5</span>秒后回到主页 <a href="" onclick="back()">返回</a>
	  <script type="text/javascript">  
	 
	   //获取显示秒数的元素，通过定时器来更改秒数。
	    var e=document.getElementById("num");
	    var num=5;
	    var id=setInterval("mid()",1000);
	    function mid(){
	        num--;
	        if(num==0){
	            window.location= history.go(-1);
	            clearInterval(id);
	            return;
	        }
	        e.innerHTML=num;
	    }
	   //通过window的location和history对象来控制网页的跳转。
	   function back(){
	       window.history.back();
	   }
	 </script> 
</body>
</html>