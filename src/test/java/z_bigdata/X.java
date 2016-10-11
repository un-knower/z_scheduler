package z_bigdata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class X {

	public static void main(String[] args) throws Exception {
		String url="jdbc:mysql://10.201.48.3:3306/test?user=bl&password=bigdata";
		Connection con = DriverManager.getConnection(url);
		DatabaseMetaData metaData = con.getMetaData();
		ResultSet schemas = metaData.getSchemas();
		while(schemas.next()){
			String S = schemas.getString("TABLE_SCHEM");
			System.out.println("---"+S);
		}
		con.close();

	}

}
