package org.demo;

import dao.UserDao;
import mg.utils.EmailConfig;
import mg.utils.TokenGenerator;
import model.User;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Locale;

@Path("api")
public class UserController
{
    @Context
    private HttpServletRequest httpServletRequest;
    @Context
    private Request request;

    /**
     * Method that calls UserDao to add a user to the database
     * @param user
     * @return
     */
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) throws SQLException
    {
        String requestMethod = request.getMethod();
        UserDao userDao = new UserDao();
        TokenGenerator tokenGenerator = new TokenGenerator();

        if(requestMethod.equals("POST"))
        {
            return Response.ok(userDao.registerUser(user)).build();
        }

        return null;
    }

    /**
     * Method to log user in
     * @param user
     * @return
     * @throws SQLException
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) throws SQLException
    {
        String requestMethod = request.getMethod();
        UserDao userDao = new UserDao();
        TokenGenerator tokenGenerator = new TokenGenerator();

        if(requestMethod.equals("POST"))
        {
            return Response.ok(userDao.loginUser(user))
                    .header("Authorization", tokenGenerator.Generator())
                    .header("Access-Control-Expose-Headers", "Authorization")
                    .allow("OPTIONS").build();
        }

        return null;
    }

    /**
     * Method to identify user with two-step verification code
     * @param secretCode
     * @return
     * @throws SQLException
     */
    @POST
    @Path("authentication")
    @Produces(MediaType.APPLICATION_JSON)
    public Response identifyUser(String secretCode) throws SQLException
    {
        String requestMethod = request.getMethod();
        TokenGenerator tokenGenerator = new TokenGenerator();

        if(requestMethod.equals("POST"))
        {
            try
            {
                return Response.ok(tokenGenerator.Checker(secretCode))
                        .header("Authorization", tokenGenerator.Generator())
                        .header("Access-Control-Expose-Headers", "Authorization")
                        .allow("OPTIONS").build();
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }

        return null;
    }

    @POST
    @Path("requests")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestRegistration(String email)
    {
        String requestMethod = request.getMethod();

        if(requestMethod.equals("POST"))
        {
            try
            {
                EmailConfig emailConfig = new EmailConfig();
                emailConfig.RequestInit(email.replaceAll("^\"|\"$", ""));
                emailConfig.RequestComplete(email.replaceAll("^\"|\"$", ""));

                return Response.ok("success").build();
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }

        return null;
    }
}
