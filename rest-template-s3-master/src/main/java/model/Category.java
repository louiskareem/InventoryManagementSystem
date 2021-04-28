package model;

public class Category
{
    private int ID;
    private String Name;
    private String Description;

    public int getID()
    {
        return this.ID;
    }

    public void setID(int id)
    {
        this.ID = id;
    }

    public String getName()
    {
        return this.Name;
    }

    public void setName(String name)
    {
        this.Name = name;
    }

    public String getDescription()
    {
        return this.Description;
    }

    public void setDescription(String description)
    {
        this.Description = description;
    }

    public Category()
    {

    }

    public Category(int id, String name, String description)
    {
        this.ID = id;
        this.Name = name;
        this.Description = description;
    }
}
