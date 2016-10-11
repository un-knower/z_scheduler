<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">

 
	function addBatch(){
		var urlPath=getParams();
		urlPath="./batch?op=c&"+encodeURI(urlPath)
		$.ajax({
			url : urlPath,
			data: '',
			dataType : "html",
			type : "GET",
			async : true, // 同步
			success : function(data) {
					$("#message").append("<font color='red'>"+data+"</font><br/>")
				}
		})
	}
	
	
	function getParams(){
		
		var item = {
				bname:$("#bname").val(),
				bdesc:$("#bdesc").val(),
				beforeid:$("#beforeid").val(),
				afterid:$("#afterid").val(),
				taskdate:$("#taskdate").val(),
				flag:$("#flag").val(),
				timeinterval:$("#timeinterval").val(),
				startdate:$("#startdate").val(),
				enddate:$("#enddate").val(),
				nextexecutetime:$("#nextexecutetime").val()
		}
		var params =$.param(item)
		return params
	}

</script>
</head>
<body>
<a href="./batch">创建批次</a>
<br/>
<div id="message"></div>
<label>批次名称</label>
<input id="bname" type="text" value="我是名称"/><br/>
<label>描述</label>
<input id="bdesc" type="text" value="我是描述"/><br/>
<label>前置批次id</label>
<input id="beforeid" type="text" value="-1"/>前一个批次的id(-1表示没有)<br/>
<label>后续批次id</label>
<input id="afterid" type="text" value="-1"/>后一个批次的id(-1表示没有)<br/>
<label>任务执行日期</label>
<input id="taskdate" type="text" value="${today}"/>任务日期<br/>
<label>批次状态</label>
<input id="flag" type="text" value="0"/>0(失效)1(完成)2(正在运行)3(运行失败)<br/>
<label>批次运行间隔</label>
<input id="timeinterval" type="text" value="1440" />批次运行时间间隔<br/>
<label>批次开始时间</label>
<input id="startdate" type="text" value="20160611"/>批次开始的时间<br/>
<label>批次结束时间</label>
<input id="enddate" type="text" value="20990211"/>批次终止时间<br/>
<label>下次运行时间</label>
<input id="nextexecutetime" type="text" value="${nextexecute}"/>批次下次运行时间<br/>
<button onclick="addBatch()">增加批次</button>
<div id="content"></div>

</body>
</html>
