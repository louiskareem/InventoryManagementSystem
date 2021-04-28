package org.demo;

import mg.utils.CORSFilter;
import mg.utils.ResourceConfigInfo;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    static final URI BASE_URI = URI.create("http://localhost:9090/amazon");

    static final String[] RES_PACKAGES =
    new String[]
    {
        Main.class.getPackage().getName(),
        Main.class.getPackage().getName()+".rest"
    };

    static ResourceConfig config=new ResourceConfig()
    {//resource configuration of our server
        {
            //find all endpoints in the same package as the main class
            System.out.println("Registering packages with endpoints:");

            for(String s:RES_PACKAGES)
            {
                System.out.printf("\t-\"%s\"\n",s);
            };

            packages(RES_PACKAGES);//new String[]{"org.demo"});
            System.out.println();
            //log exchanged http messages
            System.out.println("Initializing logger");
            register
            (
                new LoggingFeature
                (
                    Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                    Level.INFO,
                    LoggingFeature.Verbosity.PAYLOAD_ANY,
                    LoggingFeature.DEFAULT_MAX_ENTITY_SIZE
                )
            );
        }
    };

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            config.register(CORSFilter.class);
            //print the configured endpoints
            ResourceConfigInfo.printConfigEndpoints(config,BASE_URI.toString());
            // create and start a grizzly server
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, config);
            System.out.printf("Starting server, listening at \"%s\"",BASE_URI.toURL());
            server.start();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
