<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
/**
 * 
	$(function(){
			$.ajax({
				url:"./util?op=view",
				type:"GET",
				dataType : "json",
				async : true, // 同步
				data : '',
				success:function(response){
					var lsinfo = response.lsinfo
					$("#message").append(lsinfo)
				}
			})		
	})	
 */
 	function cls(){
 		$("#message").empty();
	}
 
 	function upload(){
 		window.location="./util?op=upload"
 	}
 
 	function createShell(){
 		window.location="./util?op=toCreateShell"
 	}
 	
 	function viewSQLContent(){
 		var script=$("#script_name").val()
 		$.ajax({
			url:"./util?op=viewSQLContent&script="+script,
			type:"GET",
			dataType : "json",
			async : true, // 同步
			data : '',
			success:function(response){
				debugger
				var lsinfo = response.map.message
				$("#message").empty();
				$("#message").append(response.map.sort)
				$("#message").append(lsinfo)
			}
		})
 	}
 	
 	
	function viewSQL(){
			debugger
			$.ajax({
				url:"./util?op=viewSQL",
				type:"GET",
				dataType : "json",
				async : true, // 同步
				data : '',
				success:function(response){
					debugger
					var lsinfo = response.map.message
					$("#message").empty();
					$("#message").append(response.map.sort)
					$("#message").append(lsinfo)
				}
			})		
	}
	
	function view(){
		debugger
		$.ajax({
			url:"./util?op=view",
			type:"GET",
			dataType : "json",
			async : true, // 同步
			data : '',
			success:function(response){
				debugger
				var lsinfo = response.map.message
				$("#message").empty();
				$("#message").append(response.map.sort)
				$("#message").append(lsinfo)
			}
		})		
}
	
	function viewCrontab(){
		$.ajax({
			url:"./util?op=viewCrontab",
			type:"GET",
			dataType : "json",
			async : true, // 同步
			data : '',
			success:function(response){
				debugger
				var lsinfo = response.map.message
				$("#message").empty();
				$("#message").append(response.map.sort)
				$("#message").append(lsinfo)
			}
		})	
	}
	
	
	
	
	function rerun_clock(){
		$("#clock").addClass('divcssblock');
		clock()
		rerun()
	}
	
	
	$(function(){
		$("#is_custom").hide();
		$("input[name='is_custom']").change(function (){
			var is_custom=$('input[name="is_custom"]:checked').val()
			console.log(is_custom)
			if(is_custom==1){
				$("#is_custom").show();
				$("#not_custom").hide();
			}else{
				$("#not_custom").show();
				$("#is_custom").hide();
			}
		})
	})
	
	function rerun(){
		var script=$("#script_name").val()
		var start_date=$("#start_date").val()
		var end_date=$("#end_date").val()
		var custom=$("#custom").val()
		var is_custom=$('input[name="is_custom"]:checked').val()
		
		$("#message").empty();
		$("#message").append("开始重跑,耗时比较长,耐心等待!<br/>")
		$.ajax({
				url:"./util?op=rerun&script="+script+"&start_date="+start_date+"&end_date="+end_date+"&custom="+custom+"&is_custom="+is_custom,
				type:"GET",
				dataType : "html",
				async : true, // 同步
				data : '',
				success:function(response){
					debugger
					var message = response
					$("#message").append(message)
					//$("#clock").addClass('divcssnone');
					$("#clock").hide();
				}
			})
		/**
			*/
	}
	
	
	function clock()
	{
		var oClock = document.getElementById("clock");
		var aSpan = oClock.getElementsByTagName("span");
		setInterval(getTimes, 1000);
		getTimes();
		var time=0;
		function getTimes (){
				time=time+1
				aSpan[0].innerHTML = time 
			}
	}
	
	
</script>

<style>
.divcssnone{display:none}
.divcssblock{display:block}
</style>
</head>
<body>
	<label>script name:</label><input type="text" id="script_name"/>
	<br/>
	<label>时间段选取</label>
	<input type="radio" name="is_custom" value="0" checked="checked" >
	<label>手动传参(周,月)</label>
	<input type="radio" name="is_custom" value="1" >
	<br/>
	<div id="not_custom">
		<label>between:</label><input type="text" id="start_date" value="${d }"/>
		<label>and</label><input type="text" id="end_date" value="${d }" />
	</div>
	<div id="is_custom">
		<textarea id="custom" rows="2" cols="100"></textarea>
	</div>	
	<br/>
	<button onclick="createShell()">创建shell&查看shell</button>
	<button onclick="upload()">去上传sql界面</button>
	<button onclick="rerun_clock()">重跑</button>
	<button onclick="view()">查看shell脚本列表</button>
	<button onclick="viewSQL()">查看SQL列表</button>
	<button onclick="viewSQLContent()">查看SQL内容</button>
	<button onclick="viewCrontab()">查看调度</button>
	<button onclick="cls()">清屏</button>
	<br/>
	<div id="clock" class="divcssnone">
		已经用去<span></span>秒
	</div>
	<div id="message">	
	</div>
</body>
</html>
