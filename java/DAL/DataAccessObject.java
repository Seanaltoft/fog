package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DataAccessObject {

    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/fog";
    private static final String Id = "root";
    private static final String Pass = "sean666";

    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(Driver);
        Connection con = DriverManager.getConnection(URL, Id, Pass);  // The connection will be released upon program 
        System.out.println("You have successfully connected to " + URL + " database");

        return con;
    }

    public static void releaseConnection(Connection con) throws SQLException
    {
        con.close();
    }
}

