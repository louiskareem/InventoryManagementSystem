package dao;

import mg.utils.EmailConfig;
import model.Employee;
import model.Manager;
import model.Role;
import persistence.MysqlDatabase;
import mg.utils.Cryptography;
import model.User;
import java.sql.*;

/**
 * DAO class for users
 */
public class UserDao
{
    // this has to be static because the service is stateless:
    private static final MysqlDatabase mysqlDatabase = new MysqlDatabase();
    public static String tokenEmail;
    public static String tokenPassword;
    public static Role userRole;

    /**
     * Method that register the user to the database
     * @param user
     * @throws SQLException
     * @return
     */
    public String registerUser(User user)
    {
        final String secretKey = "fontys_cia_advanced_secret_key";
        String userPassword = user.getPassword();
        Cryptography cryptography = new Cryptography();
        String encryptedPassword = cryptography.encrypt(userPassword, secretKey);
        String message = "error";

        try
        {
            Connection connection = mysqlDatabase.connect();
            String sql = "INSERT INTO staff (firstname, lastname, age, address, email, password, roleID) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, encryptedPassword);
            stmt.setInt(7, user.role.toInt());
            stmt.executeUpdate();
            message = "success";
            stmt.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return message;
    }

    /**
     * Method that logs user in based on credentials
     * @param user
     * @return
     * @throws SQLException
     */
    public String loginUser(User user)
    {
        final String secretKey = "fontys_cia_advanced_secret_key";
        String userPass = user.getPassword();
        Cryptography cryptography = new Cryptography();
        String encryptedPassword = cryptography.encrypt(userPass, secretKey);
        String message = "error";

        try
        {
            Connection connection = mysqlDatabase.connect();
            String sql = "SELECT * FROM staff WHERE email = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, encryptedPassword);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String decryptedPassword = cryptography.decrypt(resultSet.getString("password"), secretKey);
                int role = resultSet.getInt("roleID");

                if (decryptedPassword.equals(userPass))
                {
                    EmailConfig emailConfig = new EmailConfig();
                    emailConfig.SendEmail(user.getEmail());

                    if (role == 1)
                    {
                        Manager manager = new Manager(firstName, lastName, age, address, email, decryptedPassword, Role.MANAGER);
                        userRole = manager.role;
                        tokenEmail = manager.getEmail();
                        tokenPassword = manager.getPassword();
                    }
                    else if(role == 2)
                    {
                        Employee employee = new Employee(firstName, lastName, age, address, email, decryptedPassword, Role.EMPLOYEE);
                        userRole = employee.role;
                        tokenEmail = employee.getEmail();
                        tokenPassword = employee.getPassword();
                    }
                    message = "success";
                }
            }
            stmt.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return message;
    }

    /**
     * Boolean to return true or false if user is admin or not
     * @return
     */
    public boolean IsAdmin()
    {
        if (userRole.equals(Role.MANAGER))
        {
            return true;
        }

        return false;
    }
}
