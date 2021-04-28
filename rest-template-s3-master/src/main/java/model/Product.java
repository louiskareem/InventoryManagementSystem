package model;

public class Product
{
    private int Id;
    private String name;
    private String description;
    private double price;

    /**
     * Getter id
     * @return
     */
    public int getID()
    {
        return this.Id;
    }

    /**
     * Setter id
     * @param id
     */
    public void setID(int id)
    {
        this.Id = id;
    }

    /**
     * Getter name
     * @return
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Setter name
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter description
     * @return
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Setter description
     * @return
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Getter price
     * @return
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * Setter price
     * @param price
     * @return
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    public Product()
    {

    }

    /**
     * Constructor
     * @param productID
     * @param name
     * @param description
     * @param price
     */
    public Product(int productID, String name, String description, double price)
    {
        this.Id = productID;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
