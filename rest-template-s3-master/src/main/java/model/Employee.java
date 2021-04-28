package model;

public class Employee extends User
{
    /**
     * Employee constructor
     * @param firstname
     * @param lastname
     * @param age
     * @param address
     * @param email
     * @param password
     * @param role
     */
    public Employee(String firstname, String lastname, int age, String address, String email, String password, Role role)
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
