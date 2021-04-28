package dao;
import model.Inventory;
import model.Product;
import persistence.MysqlDatabase;
import javax.ws.rs.core.GenericEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDao
{
    // this has to be static because the service is stateless:
    private static final MysqlDatabase mysqlDatabase = new MysqlDatabase();
    List<Inventory> inventoryList;
    Product product = new Product();

    /**
     * Method to get and return all products in the inventory from database
     * @return
     * @throws SQLException
     */
    public GenericEntity<List<Inventory>> getAllInventoryProducts() throws SQLException
    {
        try
        {
            inventoryList = new ArrayList<>();
            Connection connection = mysqlDatabase.connect();
            Statement stmt = connection.createStatement();
            String sql = "SELECT product.id, product.name, product.description, product.price, inventory.unitsInStock FROM inventory INNER JOIN product ON inventory.productID = product.id";
            ResultSet resultSet = stmt.executeQuery(sql);

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                int unitsInStock = resultSet.getInt("unitsInStock");
                String productName = resultSet.getString("name");
                String productDescription = resultSet.getString("description");
                double productPrice = resultSet.getDouble("price");

                product = new Product(id, productName, productDescription, productPrice);

                Inventory inventory = new Inventory(id, unitsInStock, product);
                inventoryList.add(inventory);
            }
            connection.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("success");
        return new GenericEntity<List<Inventory>>(inventoryList) {};
    }
}
