package model;
import model.Product;

import java.security.PrivateKey;
import java.util.List;

public class Inventory
{
    private int Id;
    private int units;
    private Product product;

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
     * Getter units
     * @return units
     */
    public int getUnits()
    {
        return this.units;
    }

    /**
     * Setter units
     * @param unitsInStock
     */
    public void setUnits(int unitsInStock)
    {
        this.units = unitsInStock;
    }

    /**
     * Getter Product ID
     * @return
     */
    public Product getProducts()
    {
       return product;
    }

    public Inventory()
    {

    }

    public Inventory(int ID, int units, Product product)
    {
        this.Id = ID;
        this.units = units;
        this.product = product;
    }
}
