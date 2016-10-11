<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">

 	function cls(){
 		$("#message").empty();
	}

 	function to_rerun(){
 		window.location="./util?op=toRerun"
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

	//查看shell
	function viewShell() {
		var script = $("#script_name").val();
		var url="./util?op=viewShell&script="+script;
		$.ajax({
			url:url,
			type:"GET",
			dataType:"json",
			async:true,
			data:"",
			success:function (response) {
				debugger
				$("#message").empty()
				var message = response.message
				$("#message").append(message)
			}

		})
	}
	
	
	function create_shell(){
		var script=$("#script_name").val()
		var is_inc=$('input[name="is_inc"]:checked').val()
		var is_partation=$('input[name="is_partation"]:checked').val()
		var delete_column=$("#delete_column").val()
		
		var url="./util?op=createShell&script="+script+"&is_inc="+is_inc+"&is_partation="+is_partation+"&delete_column="+delete_column
		$("#message").empty();
		$("#message").append("开始生产shell脚本,耐心等待!<br/>")
		$.ajax({
				url:"./util?op=createShell&script="+script+"&is_inc="+is_inc+"&is_partation="+is_partation+"&delete_column="+delete_column,
				type:"GET",
				dataType : "json",
				async : true, // 同步
				data : '',
				success:function(response){
					debugger
					var message = response.message
					$("#message").append(message)
				}
			})
	}
	
	$(function(){
		$("input[name='is_inc']").change(function (){
			var is_inc=$('input[name="is_inc"]:checked').val()
			if(is_inc==0){
				$("#column").hide();
			}else{
				$("#column").show();
			}
		})
	})
	
	
	
	
</script>
</head>
<body>
	
	<button onclick="to_rerun()">返回重跑页面</button>
	<button onclick="view()">查看shell列表</button>
	<button onclick="viewShell()">查看shell内容</button>
	<button onclick="cls()">清屏</button>
	<br/>
	<font color="red">只针对常规的任务,如果特殊任务,请发邮件</font><br/>
	<label>表名:</label>
		<input type="text" id="script_name"/>
	<br/>
	<label>oracle增量:</label>
		<input type="radio" name="is_inc" value="0"/> 非增量
		<input type="radio" name="is_inc" value="1" checked="checked"/> 增量
	<br/>
	<label>hive&nbsp;分区:</label>
		<input type="radio" name="is_partation" value="0"/> 非分区
		<input type="radio" name="is_partation" value="1" checked="checked"/> 分区
	<br/>
	<div id="column">
	<label>oracle 增量删除字段:</label>
		<input type="text" id="delete_column" value="cdate"/>		
	</div>
	<br/>
	<button onclick="create_shell()">创建常规shell</button>
	<div id="message">	
	</div>
</body>
</html>
