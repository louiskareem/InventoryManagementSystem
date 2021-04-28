import dao.UserDao;
import model.Role;
import model.User;
import org.demo.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.*;

import java.sql.SQLException;

public class UserIT
{
    private final String firstname = "Jane-Mary";
    private final String lastname = "Doe";
    private final int age = 50;
    private final String address = "jane-mary doe street 45B";
    private final String email = "janemarydoe@gmail.com";
    private final String password = "mystery_woman";
    private final String message = "success";
    private final Role role = Role.MANAGER;
    private User user;

    /**
     * Integration test
     * Testing to create/register user to the database
     * Return success string
     * @throws SQLException
     */
    @Test
    public void registerUserIntegrationTest() throws SQLException
    {
        // Arrange
        user = new User(firstname, lastname, age, address, email, password, role);
        UserDao userDao = new UserDao();

        // Act
        userDao.registerUser(user);

        // Assert
        Assertions.assertEquals(message, "success");
    }

    /**
     * Integration test
     * Testing to log user in using credentials saved in the database
     * Return success string
     * @throws SQLException
     */
    @Test
    public void loginUserIntegrationTest() throws SQLException
    {
        // Arrange
        user = new User(firstname, lastname, age, address, email, password, role);
        UserDao userDao = new UserDao();

        // Act
        userDao.loginUser(user);
        // Assert
        Assertions.assertEquals(message, "success");
    }
}
