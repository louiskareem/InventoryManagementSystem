package mg.utils;

import org.glassfish.jersey.server.ResourceConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.ws.rs.*;
/**
*   Helper class that traverses the classes in a server resource configuration and prints all the endpoints
*   @author G. Metaxas
*/
public class ResourceConfigInfo
{
    /**
     *
     * @param base
     * @param path
     * @return
     */
    private static String concatPath(String base,Path path)
    {
        if(path==null)
        {
            return  base;
        }

        String pathValue=path.value();

        if(base.endsWith("/") || pathValue.startsWith("/"))
        {
            return  base+pathValue;
        }

        return  base+"/"+pathValue;
    }

    /**
     * enumerate the classes in the specified enumeration and print all the endpoints
     * @param config    enumerated the registered endpoint classes in this configuration
     * @param baseURI   the server's base uri
     */
    public  static  void printConfigEndpoints(ResourceConfig config,String baseURI)
    {
        for(Class<?> aClass:config.getClasses())
        {
            ResourceConfigInfo.printClassEndpoints(aClass,baseURI);
        }
    }

    /**
     * Print the endpoints defined in the specified class
     * @param aClass    the class implementing the endpoints in question
     * @param baseURI   the base uri of the server
     */
    public static void printClassEndpoints(Class<?> aClass, String baseURI)
    {
        Path path = aClass.getAnnotation(Path.class);

        if (path == null)
        {
            return;
        }
        System.out.printf("\nEndpoints in class \"%s\"",aClass.getName());

        String classURI = concatPath(baseURI,path);
        for (Method aMethod : aClass.getMethods())
        {
            for(Annotation annotation:aMethod.getAnnotations())
            {
                HttpMethod httpMethod=annotation.annotationType().getAnnotation(HttpMethod.class);

                if(httpMethod==null)
                {
                    continue;
                }

                System.out.printf("\n\tjava method:%s",aMethod.getName());
                Path methodPath=aMethod.getAnnotation(Path.class);
                String prefixFmt="\n\t%-15s%s";
                System.out.printf(prefixFmt,"uri:",concatPath(classURI,methodPath));
                System.out.printf(prefixFmt,"method:",httpMethod.value());
                Consumes accepts=aMethod.getAnnotation(Consumes.class);

                if((accepts!=null)&&(accepts.value().length>0))
                {
                    System.out.printf(prefixFmt,"accepts:",String.join(",",accepts.value()));
                }

                Produces returns=aMethod.getAnnotation(Produces.class);

                if((returns!=null)&&(returns.value().length>0))
                {
                    System.out.printf(prefixFmt,"returns:",String.join(",",returns.value()));
                }

                Annotation[][] annotations = aMethod.getParameterAnnotations();
                prefixFmt="\n\t\t%-15s%s";

                for(Annotation[] x:annotations)
                {
                    for(Annotation y:x)
                    {
                        if(y instanceof PathParam)
                        {
                            System.out.printf(prefixFmt,"path param:",((PathParam) y).value());
                        }
                        else if(y instanceof QueryParam)
                        {
                            System.out.printf(prefixFmt,"query param:",((QueryParam) y).value());
                        }
                        else if(y instanceof FormParam)
                        {
                            System.out.printf(prefixFmt,"form param:",((FormParam) y).value());
                        }else if(y instanceof HeaderParam)
                        {
                            System.out.printf(prefixFmt,"header param:",((HeaderParam) y).value());
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}
