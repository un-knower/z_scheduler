<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function to_rerun(){
		window.location="./util?op=toRerun"
	}
</script>
</head>
<body>
	<button onclick="to_rerun()">返回重跑页面</button>
	<form action="./util" enctype="multipart/form-data" method="post">
		<input type="file" name="file"/><br/>
		<input type="submit" value="提交"/>
	</form>
	

	<div id="message">	
		${message }<br/>
		${scpinfo }<br/>
		文件名为:${filename }
	</div>
</body>
</html>
