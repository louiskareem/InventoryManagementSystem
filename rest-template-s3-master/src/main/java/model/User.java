package model;

public class User
{
    protected String firstname;
    protected String lastname;
    protected int age;
    protected String address;
    protected String email;
    protected String password;
    public Role role;

    /**
     * Getter password
     * @return
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Setter id
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Getter firstname
     * @return
     */
    public String getFirstname()
    {
        return this.firstname;
    }

    /**
     * Setter firstname
     * @param firstname
     */
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    /**
     * Getter lastname
     * @return
     */
    public String getLastname() { return this.lastname; }

    /**
     * Setter lastname
     * @param lastname
     */
    public void setLastname(String lastname) {this.lastname = lastname; }

    /**
     * Getter age
     * @return
     */
    public int getAge() { return this.age; }

    /**
     * Setter age
     * @param age
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Getter address
     * @return
     */
    public String getAddress() { return this.address; }

    /**
     * Setter address
     * @param address
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Getter email
     * @return
     */
    public String getEmail() { return this.email; }

    /**
     * Setter email
     * @param email
     */
    public void setEmail(String email) { this.email = email; }

    public User()
    {

    }

    /**
     * Constructor
     * @param firstname
     * @param lastname
     * @param age
     * @param address
     * @param email
     * @param password
     */
    public User(String firstname, String lastname, int age, String address, String email, String password, Role role)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
