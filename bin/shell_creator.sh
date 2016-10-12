#!/bin/bash
########################################################################################################
#
#	2016-7-5 15:52:55
#	gongpeng
#       update: 2016-7-27 16:32:26
#	通过提供参数,获取常规的etl脚本
#	有四个参数: 表名 oracle是否是增量 hive导入的时候时候是分区   oracle的删除字段(如果oracle是增量)
#
########################################################################################################
APP_PATH=/home/bl/app/new_template
LOG_PATH=$APP_PATH/log/shell_creator.log
TARGET_PATH=/home/bl/app/new_template/etl_shell
TEMPLATE=$APP_PATH/shell.template
time=`date +"%Y-%m-%d %H:%M:%S"`
echo $time $@ >> $LOG_PATH
if [ ! $# -eq 4 ] 
then
	echo "should have four args"
	echo "eg.   m_member_points_d_offline 1 1 mmm"
	exit
fi
table_name=$1
upper_table_name=$(echo $table_name | tr '[a-z]' '[A-Z]')
is_inc=$2
is_partation=$3
delete_column=$4

target_file=$TARGET_PATH"/"$table_name".sh"
#如果存在,那么删除原有文件
if [ -f $target_file ]
then
	rm $target_file
fi

cp $TEMPLATE $TARGET_PATH/$1".sh"

#替换表名称
sed -i "s/replaceable_table/`echo ${table_name}`/g" ${target_file}
sed -i "s/REPLACEABLE_TABLE/`echo ${upper_table_name}`/g" ${target_file} 

#替换 delete_content  和 partition_content 

to_delete_content=""
to_partition_content=""


#如果是增量,那么要求增量删除

if [ $is_inc -eq 0 ] 
then
	to_delete_content="clearoracletb.sh $table_name"
else
	to_delete_content="customize_clearoracletb.sh $table_name $delete_column \$\{task_date\}"
fi

sed -i "s/delete_content/`echo ${to_delete_content}`/g" ${target_file}


#分区删除处理

if [ $is_partation -eq 1 ] 
then
	to_partition_content="dt\=\$\{task_date\}\/"
fi

sed -i "s/partition_content/`echo ${to_partition_content}`/g" ${target_file}

chmod +x $target_file

scp ${target_file} bl@10.201.48.26:/home/bl/ETL/
echo "--------------------------------------脚本如下---------------------------------------------"
echo "<font color='green'>"
cat ${target_file}
echo "</font>"
