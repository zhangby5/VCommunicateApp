<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link href="/VCommunicateApp/css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=request.getContextPath()%>/js/jquery-1.4a2.min.js"
	type="text/javascript"></script>
<script type="application/x-javascript">
addEventListener("load", 
function() { 
setTimeout(hideURLbar, 0); 
}, false); 
function hideURLbar(){
 window.scrollTo(0,1); 
 } 
 
 function myFunction(){
 	$("register").submit();
 }
</script>
<!--webfonts-->
<!--//webfonts-->
</head>
<body>
	<div class="main">
		<div class="header">
			<h1>登录或创建一个免费帐户！</h1>
		</div>
		<p></p>
		<form id="register" action="/VCommunicateApp/register" method="post">
			<ul class="left-form">
				<h2>新账户:</h2>
				<li><input type="text" name="cusername" placeholder="Username" required /> <a
					href="#" class="icon ticker"> </a>
					<div class="clear"></div></li>
				<li><input type="text" name="cemail" placeholder="Email" required /> <a
					href="#" class="icon ticker"> </a>
					<div class="clear"></div></li>
				<li><input type="password" name="cpassword" placeholder="password" required />
					<a href="#" class="icon into"> </a>
					<div class="clear"></div></li>
				<label class="checkbox"><input type="checkbox"
					name="checkbox" checked=""><i> </i>Please inform me of
					upcoming w3layouts, Promotions and news</label>
				<input type="submit" onClick="myFunction()" value="创建账户">
				<div class="clear"></div>
			</ul>
		</form>
		<form>
			<ul class="right-form">
				<h3>登录:</h3>
				<div>
					<li><input type="text" placeholder="Username" required /></li>
					<li><input type="password" placeholder="Password" required /></li>
					<h4>I forgot my Password!</h4>
					<input type="submit" onClick="myFunction()" value="登录">
				</div>
				<div class="clear"></div>
			</ul>
			<div class="clear"></div>
		</form>

	</div>
	<!-----start-copyright---->
	<div class="copy-right">
		<p>VC</p>
	</div>
	<!-----//end-copyright---->


</body>
</html>