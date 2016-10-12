#!/bin/bash
##################################################################################
#	sqoop导入脚本 crontab定时运行
#	gongpeng 	2016-4-6 14:46:43
#	日志目录格式为:
#	/tmp/import_log/20160302/
#	                       task.log
#			       error.log
#			       name_of_table.log
##################################################################################
#日志记录地址
LOG_PATH="/tmp/import_log/"

#读取的文件为
#APP_PATH=$(cd $(dirname $0)  && pwd)
APP_PATH=/home/bl/IMPORT
CONF_PATH=$APP_PATH"/conf/"
LOG_PATH=$APP_PATH"/log/"
TEMPLATE_FILE=$CONF_PATH"app.template"
TRUE_FILE=$CONF_PATH"app.sqoop"
#日志记录
log(){
	time=`date +"%Y-%m-%d %H:%M:%S"`
	echo $time $@	
	echo "$time $@" >> $current_log_path"/app.log"
	
}
#如果不传参那么任务日期为运行日期的前一天 ,如果传入则覆盖为第一个参数
task_date=`date -d "-1 days" +"%Y%m%d" `
one_day_before_task_date=`date -d "-2 days" +"%Y%m%d" `

args_length=$#
if [ $args_length -eq 1 ]
then
	task_date=$1
	one_day_before_task_date=`date -d "-1 days $1 " +"%Y%m%d"`
fi

#创建日志的生成目录
current_log_path=$LOG_PATH$task_date
mkdir -p  $current_log_path
log "task execute date is " $task_date
log "make dir "$current_log_path

#文件中的日期换为运行的日期
rm ${TRUE_FILE}
sed  "s/\${task_date}/`echo $task_date `/g" ${TEMPLATE_FILE} > ${TRUE_FILE}
log "change date to $task_date finished !!"

#记录任务开始的时间
task_start=`date +"%s"`
#循环文件中行,通过长度判断是要导入的表名还是命令名
cat ${TRUE_FILE} | while read line
do
	log  " $line"
	log "execute task start start start start"
	import_start=`date +%s`
	temp_table=`echo $line | awk -F' ' '{print $19}'`
	log "delete hdfs output dir /user/hive/output/$temp_table"
	hdfs dfs -rm -r "/user/hive/output/$temp_table"
	echo  "alter table sourcedata.$temp_table drop partition (dt != '$task_date')"
        hive -e  "alter table sourcedata.$temp_table drop partition (dt != '$task_date')"
	sqoop import $line --null-string '\\N' --null-non-string '\\N' --hive-drop-import-delims --m 1 --fields-terminated-by '\001'  --outdir  /home/bl/IMPORT/bin >& $current_log_path"/$temp_table.log"
	import_result=$?
	if [ $import_result == 0 ]
	then
		log $temp_table "                             export success"
	else
		log $temp_table "                             export fail"
	fi
	import_end=`date +%s`
	log "use $((import_end-import_start)) seconds" 
	log "executed finished finished finished finished"
done
task_end=`date +"%s"`
log "task finished token  $((task_end-task_start)) secondes !"

