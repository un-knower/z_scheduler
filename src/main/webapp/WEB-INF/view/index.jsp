<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function allOraclesid() {
		$.ajax({
					url : "./index?op=oraclelist",
					data : '',
					dataType : "json",
					type : "GET",
					async : true, // 同步
					success : function(response) {
						var list = response.list;
						var head = "<tr><th>序号</th><th>dbdesc</th><th>category</th><th>ip</th><th>port</th><th>dbname</th>"
								+ "<th>username</th><th>password</th><th>sign</th><th>oracle table</th><th>hive table</th><th>map_sign</th><th>desc</th></tr>"
						var body = ""
						for (var i = 0; i < list.length; i++) {
							var tditem = ""
							var item = list[i]
							tditem += "<td>" + item.id + "</td>"
							tditem += "<td>" + item.dbdesc + "</td>"
							tditem += "<td>" + item.category + "</td>"
							tditem += "<td>" + item.ip + "</td>"
							tditem += "<td>" + item.port + "</td>"
							tditem += "<td>" + item.dbname + "</td>"
							tditem += "<td>" + item.username + "</td>"
							tditem += "<td>" + item.password + "</td>"
							tditem += "<td>" + item.sign + "</td>"
							tditem += "<td>" + item.oracle_source_table
									+ "</td>"
							tditem += "<td>" + item.hive_table + "</td>"
							tditem += "<td>" + item.map_sign + "</td>"
							tditem += "<td>" + item.desc + "</td>"
							tditem = "<tr>" + tditem + "</tr>"
							body = body + tditem
						}
						table = "<table border='1' class='data_list' >" + head
								+ body + "</table>"
						$("#content").empty()
						$("#content").append(table)
					}
				})
	}

	function allBatch() {
		$.ajax({
					url : "./index?op=batchlist",
					data : '',
					dataType : "json",
					type : "GET",
					async : true, // 同步
					success : function(response) {
						var list = response.list;
						var head = "<tr><th>序号XXXX</th><th>bname</th><th>bdesc</th><th>beforeid</th><th>afterid</th><th>taskdate</th>"
								+ "<th>flag</th><th>timeinterval</th><th>startdate</th><th>enddate</th></tr>"
						var body = ""
						for (var i = 0; i < list.length; i++) {
							var tditem = ""
							var item = list[i]
							tditem += "<td>" + i + "</td>"
							tditem += "<td>" + item.bname + "</td>"
							tditem += "<td>" + item.bdesc + "</td>"
							tditem += "<td>" + item.beforeid + "</td>"
							tditem += "<td>" + item.afterid + "</td>"
							tditem += "<td>" + item.taskdate + "</td>"
							tditem += "<td>" + item.flag + "</td>"
							tditem += "<td>" + item.timeinterval + "</td>"
							tditem += "<td>" + item.startdate + "</td>"
							tditem += "<td>" + item.enddate + "</td>"
							tditem = "<tr>" + tditem + "</tr>"
							body = body + tditem
						}
						table = "<table border='1'  class='data_list' >" + head
								+ body + "</table>"
						$("#content").empty()
						$("#content").append(table)

					}
				})
	}

	function allTask() {
		//$("#content").innerHtml("");
		$.ajax({
					url : "./index?op=tasklist",
					data : '',
					dataType : "json",
					type : "GET",
					async : true, // 同步
					success : function(response) {
						var list = response.list;
						var head = "<tr><th>id</th><th>seq</th><th>tname</th><th>tdesc</th><th>batchid</th><th>tasktype</th><th>commandpath</th><th>command</th><th>args</th></tr>"
						var body = ""
						for (var i = 0; i < list.length; i++) {
							debugger
							var tditem = ""
							var item = list[i]
							tditem += "<td>" + i + "</td>"
							tditem += "<td>" + item.seq + "</td>"
							tditem += "<td>" + item.tname + "</td>"
							tditem += "<td>" + item.tdesc + "</td>"
							tditem += "<td>" + item.batchid + "</td>"
							tditem += "<td>" + item.taskType + "</td>"
							tditem += "<td>" + item.commandpath + "</td>"
							tditem += "<td>" + item.command + "</td>"
							tditem += "<td>" + item.args + "</td>"
							tditem = "<tr>" + tditem + "</tr>"
							console.log("-----------------")
							console.log(tditem)
							body = body + tditem
						}
						table = "<table border='1' class='data_list' >" + head
								+ body + "</table>"
						$("#content").empty()
						$("#content").append(table)
					}
				})
	}

	function refreshMap() {
		$.ajax({
			url : "./index?op=refreshMap",
			data : '',
			dataType : "html",
			type : "GET",
			async : true, // 同步
			success : function(response) {
				$("#content").empty()
				$("#content").append(response)
				$("#content").append("<br/>配置已经手动刷新")
			}
		})
	}

	function allHistroy(pageNum) {
		$.ajax({
					url : "./index?op=history&pageNum=" + pageNum,
					data : '',
					dataType : "json",
					type : 'GET',
					async : true,
					success : function(response) {
						debugger
						var list = response.list
						var pageLength=response.pageLength
						var length=response.length
						var head = "<tr><th>start_date</th><th>task_date</th><th>cmd</th><th>exit_code</th><th>用时间</th><th>message</th></tr>"
						var body = ""
						for (var i = 0; i < list.length; i++) {
							debugger
							var tditem = ""
							var item = list[i]
							tditem += "<td>" + item.start_date + "</td>"
							tditem += "<td>" + item.task_date + "</td>"
							tditem += "<td>" + item.cmd + "</td>"
							tditem += "<td>" + item.exit_code + "</td>"
							tditem += "<td>" + (item.spend_time/60000).toFixed(1) + "min</td>"
							//tditem += "<td>" + item.spend_time + "</td>"
							tditem += "<td>" + item.message + "</td>"
							tditem = "<tr>" + tditem + "</tr>"
							body = body + tditem
						}
						table = "<table border='1' class='data_list' >" + head
								+ body + "</table>"
						$("#content").empty()
						$("#content").append(table)
						$("#content").append("共有"+pageLength+"页"+length+"条记录")
						var p=""
						for(var i=1;i<=pageLength;i++){
							if(i==pageNum){
								p+="<font size='6'>"+i+"</font>&nbsp;"
							}else{
								p+="<a href='javascript:allHistroy("+i+")'>"+i+"</a>&nbsp;"
							}
							if(i%15==0){
								p+="<br/>"
							}
						}
								
						$("#content").append("共有"+pageLength+"页"+length+"条记录<br/>")
						$("#content").append("跳转到:	"+p)
						
						
						
					}
				})
	}

	$(function() {
		//allOraclesid();
		allBatch();
	});
</script>
</head>
<body>
	<a href="javascript:allBatch()">batch 的配置信息</a>
	<br/>
	<a href="javascript:allTask()">task 的配置信息</a>
	<br/>
	<a href="./history">历史信息</a>
	<br/>
	<a href="./util?op=toRerun">ETL脚本上传及重跑页面</a>
	<br/>
	<a href="./batch">批次管理页面</a>
	<br/>
	<a href="./ob?op=etl">ETL</a>
	<br/>
	<a href="javascript:refreshMap()">刷新内存map</a>
	<br/>
	<a href="./monitor">监控</a>
	<br/>
	<a href="./ob?op=front">前端对应后台</a>
	<br/>
	<a href="./ob?op=source">添加的源表</a>
    <br/>
    <a href='http://10.201.48.26/etl/${yesterday}/error.log'>etl error log</a>
    <br/>
    <a href='http://10.201.48.26/pdatalog/${yesterday}/error.log'>pdata error log </a>
    <br/>
    <a href='./monitor?op=refreshFront'>修改前端页面后点我</a>
    <br/>
    <a href='./monitor?op=oracleCount2Mysql'>获取oracle中的数据量 </a>
	<br/>
	<a href='./email?op=getTaskInfo'>发送批次运行状态 </a>
    ${abc}
	<br/>
	<div id="content"></div>

</body>
</html>
