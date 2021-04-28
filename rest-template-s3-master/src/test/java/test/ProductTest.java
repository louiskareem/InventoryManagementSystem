package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Product;
import org.demo.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest
{
    /**
     * Unit test
     * Testing to create product
     * Assert that product is not null
     */
    @Test
    public void createProduct()
    {
        // Arrange
        int Id = 1;
        String name = "Playstation 5";
        String description = "Product of Sony";
        double price = 600.00;

        // Act
        Product product = new Product(Id, name, description, price);

        // Assert
        Assertions.assertNotNull(product);
    }
}
