package introsde.assignment.soap.endpoint;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;

import javax.xml.ws.Endpoint;

import introsde.assignment.soap.ws.PeopleImplementation;


/**
 * Endpoint publisher
 * @author alan
 *
 */

/*
public class PeoplePublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:6900/ws/hello", new PeopleImplementation());
	}
}
*/

public class PeoplePublisher {
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException{
        String PROTOCOL = "http://";
        String HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        if (HOSTNAME.equals("127.0.0.1")) {
            HOSTNAME = "localhost";
        }
        String PORT = "6902";
        String BASE_URL = "/ws/people";

        if (String.valueOf(System.getenv("PORT")) != "null") {
            PORT=String.valueOf(System.getenv("PORT"));
        }
        
        String endpointUrl = PROTOCOL+HOSTNAME+":"+PORT+BASE_URL;
        System.out.println("Starting People Service...");
        System.out.println("--> Published. Check out "+endpointUrl+"?wsdl");
        Endpoint.publish(endpointUrl, new PeopleImplementation());
    }
}
