package dao;

import model.Product;
import persistence.MysqlDatabase;
import javax.ws.rs.core.GenericEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao
{
    // this has to be static because the service is stateless:
    private static final MysqlDatabase mysqlDatabase = new MysqlDatabase();
    private List<Product> products;

    /**
     * Method to get and return all products from database
     * @return
     * @throws SQLException
     */
    public GenericEntity<List<Product>> getAllProducts() throws SQLException
    {
        try
        {
            products = new ArrayList<>();
            Connection connection = mysqlDatabase.connect();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM prontodatabase.product";
            ResultSet resultSet = stmt.executeQuery(sql);

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                String productDescription = resultSet.getString("description");
                double productPrice = resultSet.getDouble("price");

                Product product = new Product(id, productName, productDescription, productPrice);
                products.add(product);
            }
            connection.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("success");
        return new GenericEntity<List<Product>>(products) {};
    }

    /**
     * Method that creates a product in the database
     * @param product
     * @return
     */
    public String createProduct(Product product)
    {
        String message = "error";

        try
        {
            Connection connection = mysqlDatabase.connect();
            String sql = "INSERT INTO product (name, description, price) VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.executeUpdate();

			ResultSet rs=stmt.getGeneratedKeys();
            int id = 0;
			if(rs.next())
			{
				id = rs.getInt(1);
			}

            String sql2 = "INSERT INTO inventory (unitsInStock, productID) VALUES(?, ?)";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setInt(1, 500);
            stmt2.setInt(2, id);
            stmt2.executeUpdate();

            message = "success";
            connection.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return message;
    }

    /**
     * Method that deletes a product from the database based on product ID
     * @param id
     * @return
     */
    public String deleteProduct(int id)
    {
        String message = "error";

        try
        {
            Connection connection = mysqlDatabase.connect();
            // Delete product from inventory first otherwise mysql will throw an error
            String sql = "DELETE FROM inventory WHERE productID = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // after first query then delete product from product table
            String sql2 = "DELETE FROM product WHERE id = ?";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setInt(1, id);
            stmt2.executeUpdate();

            message = "success";
            connection.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return message;
    }

    /**
     * Method to update product in database
     * @param product
     * @return
     */
    public String updateProduct(Product product)
    {
        String message = "error";

        try
        {
            Connection connection = mysqlDatabase.connect();
            String sql = "UPDATE product SET name=?, description=?, price=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getID());
            stmt.executeUpdate();
            message = "success";
            connection.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return message;
    }
}
