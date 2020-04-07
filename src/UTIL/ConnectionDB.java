package UTIL;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    private static final String URL="jdbc:mysql://localhost:3306/Entreprise";
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String USERNAME="dorra";
    private static final String PASSWORD="stormborn";
    private static Connection connection=null;

    public static Connection openConnection() {
        if (connection!=null) {
            return connection;
        }else {
            try {
                Class.forName(DRIVER);
                connection= DriverManager.getConnection(URL, USERNAME, PASSWORD );
            }catch(Exception e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
