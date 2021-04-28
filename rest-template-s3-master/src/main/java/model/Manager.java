package model;

public class Manager extends User
{
    /**
     * Manager constructor
     * @param firstname
     * @param lastname
     * @param age
     * @param address
     * @param email
     * @param password
     * @param role
     */
    public Manager(String firstname, String lastname, int age, String address, String email, String password, Role role)
    {
        super.firstname = firstname;
        super.lastname = lastname;
        super.age = age;
        super.address = address;
        super.email = email;
        super.password = password;
        super.role = role;
    }
}
