package test;

import model.Role;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest
{
    /**
     * Unit test
     * Testing to create user
     * Assert that user is not null
     */
    @Test
    public void createUser()
    {
        // Arrange
        String firstname = "John";
        String lastname = "Doe";
        int age = 50;
        String address = "john doe street 45B";
        String email = "johndoe@gmail.com";
        String password = "mystery_man";
        int passcode = 123456;
        Role role = Role.MANAGER;

        // Act
        User user = new User(firstname, lastname, age, address, email, password, role);

        // Assert
        Assertions.assertNotNull(user);
    }
}
