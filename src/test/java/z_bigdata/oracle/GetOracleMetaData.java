package z_bigdata.oracle;

import java.sql.*;

/**
 * Created by GP39 on 2016/8/15.
 */
public class GetOracleMetaData {
    private static Connection connection=null;

    public static Connection getConnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.201.48.18:1521:report","idmdata","bigdata915");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static String getSchema() throws Exception{
        String schema = getConnection().getMetaData().getUserName();
        System.out.println(schema);
        return schema.toUpperCase();
    }

    public static void getMetaData() throws SQLException {
        DatabaseMetaData metaData = getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, "IDMDATA", "%", new String[]{"TABLE"});
        while (tables.next()){
            String table_name = tables.getString("TABLE_NAME");
            System.out.println(table_name);
            if("M_UEC_POWER_DATA_POWER_B".equals(table_name)) {
                ResultSet columns =
                        getConnection().getMetaData().getColumns(null, "IDMDATA", "M_UEC_POWER_DATA_POWER_B", null);
                while (columns.next()){
                    String column_name = columns.getString("COLUMN_NAME");
                    String type_name = columns.getString("TYPE_NAME");
                    String column_size = columns.getString("COLUMN_SIZE");
                    System.out.println(column_name+"-----------------------------"+type_name+"             "+column_size);
                }
            }
        }
    }




    public static void main(String[] args ) throws Exception {
        getSchema();
        getMetaData();
    }

}
