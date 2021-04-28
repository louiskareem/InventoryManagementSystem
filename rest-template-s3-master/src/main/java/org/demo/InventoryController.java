package org.demo;
import dao.InventoryDao;
import dao.UserDao;
import mg.utils.TokenGenerator;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("api")
public class InventoryController
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
     * Method that calls the InventoryDao to get all products that's in the inventory from the database
     * @return
     * @throws SQLException
     */
    @GET
    @Path("inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductsInventory(final Request request) throws SQLException
    {
        String requestMethod = request.getMethod();
        InventoryDao inventoryDao = new InventoryDao();

        try
        {
            if(requestMethod.equals("GET"))
            {
                TokenGenerator tokenGenerator = new TokenGenerator();
                return Response.ok(inventoryDao.getAllInventoryProducts())
                        .header("Authorization", tokenGenerator.Generator())
                        .header("Access-Control-Expose-Headers", "Authorization")
                        .allow("OPTIONS").build();
            }
        }
        catch (Exception exception)
        {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return null;
    }
}
