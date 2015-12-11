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
 function myFunction(){
 	$("login").submit();
 }
</script>
<!--webfonts-->
<!--//webfonts-->
</head>
<body>
	<div class="main">
		<div class="header">
			<h1>登录</h1>
		</div>
		<p></p>
		<form id="login">
			<ul class="right-form">
				<h3>登录:</h3>
				<div>
					<li><input type="text" name="cusername" placeholder="Username" required /></li>
					<li><input type="password" name="cpassword" placeholder="Password" required /></li>
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