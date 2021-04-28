package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDatabase
{
    /**
     * Method to start and close connection with the database
     * @return
     * @throws SQLException
     */
    public Connection connect() throws SQLException
    {
        Connection connection = null;
        String url = @db;
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = ;
        String pass = ;

        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);

            if (connection == null)
            {
                System.out.println("Connection cannot be established");
            }
            else
            {
                System.out.println("Connected to Pronto's database!");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
