# !/bin/sh
##
# DO NOT DELETE THIS FILE, SINCE IT WAS USED BY SOME OOZIE ACTION!
##
export ORACLE_HOME=/u01/app/oracle/product/11.2.0/dbhome_1
export PATH=$PATH:$ORACLE_HOME/bin
cdt=`echo \'$2\' `
echo $cdt
sqlplus idmdata/bigdata915@report <<EOF
delete from idmdata.$1 where cdate=$cdt;
commit;
EOF


# !/bin/sh
##
# DO NOT DELETE THIS FILE, SINCE IT WAS USED BY SOME OOZIE ACTION!
##
export ORACLE_HOME=/u01/app/oracle/product/11.2.0/dbhome_1
export PATH=$PATH:$ORACLE_HOME/bin
cdt=`echo \'$3\' `
#echo "------------------"
#echo $1 $2 $3 $cdt
#echo "------------------"
where_content="$2=$cdt"
echo $where_content

sqlplus idmdata/bigdata915@report <<EOF
delete from idmdata.$1 where $where_content;
commit;
EOF
