package mg.utils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter
{
    /**
     * Method to control and filter CORS in HTTP requests
     * @param requestContext
     * @param responseContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException
    {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Requested-With, Content-Type, Accept, Authorization");
    }
}
