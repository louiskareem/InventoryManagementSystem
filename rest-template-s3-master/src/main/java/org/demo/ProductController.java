package org.demo;
import dao.ProductDao;
import dao.UserDao;
import mg.utils.TokenGenerator;
import model.Product;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("api")
public class ProductController
{
    @Context
    private UriInfo uriInfo;
    @Context
    public HttpServletRequest httpServletRequest;
    @Context
    private Request request;
    @Context
    HttpHeaders httpHeaders;
    private final UserDao userDao = new UserDao();

    /**
     * Method that calls the ProductDao to get all products from the database
     * @return
     * @throws SQLException
     */
    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts(final Request request) throws SQLException
    {
        String requestMethod = request.getMethod();
        ProductDao productDao = new ProductDao();

        try
        {
            if(requestMethod.equals("GET"))
            {
                TokenGenerator tokenGenerator = new TokenGenerator();
                return Response.ok(productDao.getAllProducts())
                        .header("Authorization", tokenGenerator.Generator())
                        .header("Access-Control-Expose-Headers", "Authorization")
                        .allow("OPTIONS").build();
            }
        }
        catch (Exception exception)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return null;
    }

    /**
     * Method that calls the ProductDao and add a new product to the database
     * @param product
     * @return
     * @throws SQLException
     */
    @POST
    @Path("products/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) throws SQLException
    {
        String requestMethod = request.getMethod();
        ProductDao productDao = new ProductDao();
        UserDao userDao = new UserDao();

        if(requestMethod.equals("POST"))
        {
            if (userDao.IsAdmin())
            {
                TokenGenerator tokenGenerator = new TokenGenerator();
                return Response.ok(productDao.createProduct(product))
                        .header("Authorization", tokenGenerator.Generator())
                        .header("Access-Control-Expose-Headers", "Authorization")
                        .allow("OPTIONS").build();
            }
            else if(!userDao.IsAdmin())
            {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return null;
    }

    /**
     * Method that calls the ProductDao and deletes a product from the database based on whether user is admin or not
     * @param id
     * @return
     */
    @DELETE
    @Path("products/delete/{id}")
    public Response deleteProduct(@PathParam("id") int id)
    {
        String requestMethod = request.getMethod();
        if(requestMethod.equals("DELETE"))
        {
            if (userDao.IsAdmin())
            {
                ProductDao productDao = new ProductDao();
                return Response.ok(productDao.deleteProduct(id)).build();
            }
            else if(!userDao.IsAdmin())
            {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }

        return null;
    }

    @PUT
    @Path("products/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Product product)
    {
        product.setID(id);
        String requestMethod = request.getMethod();

        if(requestMethod.equals("PUT"))
        {
            if (userDao.IsAdmin())
            {
                ProductDao productDao = new ProductDao();
                return Response.ok(productDao.updateProduct(product)).build();
            }
            else if(!userDao.IsAdmin())
            {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }

        return null;
    }
}
