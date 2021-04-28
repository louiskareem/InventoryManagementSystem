package dao;

import model.Category;
import persistence.MysqlDatabase;
import javax.ws.rs.core.GenericEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao
{
    // this has to be static because the service is stateless:
    private static final MysqlDatabase mysqlDatabase = new MysqlDatabase();
    List<Category> categoryList;

    /**
     * Method to get and return all categories in the database
     * @return
     * @throws SQLException
     */
    public GenericEntity<List<Category>> getAllCategoryProducts() throws SQLException
    {
        try
        {
            categoryList = new ArrayList<>();
            Connection connection = mysqlDatabase.connect();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM prontodatabase.category";
            ResultSet resultSet = stmt.executeQuery(sql);

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                Category category = new Category(id, name, description);
                categoryList.add(category);
            }
            connection.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("success");
        return new GenericEntity<List<Category>>(categoryList) {};
    }
}
