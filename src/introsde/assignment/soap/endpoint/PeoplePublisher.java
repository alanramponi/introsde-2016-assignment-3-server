package introsde.assignment.soap.endpoint;

import javax.xml.ws.Endpoint;

import introsde.assignment.soap.ws.PeopleImplementation;


/**
 * Endpoint publisher
 * @author alan
 *
 */

public class PeoplePublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:6900/ws/hello", new PeopleImplementation());
	}
}