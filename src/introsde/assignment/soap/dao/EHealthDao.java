package introsde.assignment.soap.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * A class (singleton JAVA instance) that will connect our model to the database, specifically used
 * to create an Entity Manager whenever we need to execute an operation in the SQLite database.
 * 
 * @author alan
 *
 */
public enum EHealthDao {
	instance;
	
	private EntityManagerFactory emf;
	
	private EHealthDao() {
		if (emf!=null) {	// check if the Entity Manager Factory is null
			emf.close();
		}
		
		// Configure the Entity Manager Factory from a particular persistence unit
		emf = Persistence.createEntityManagerFactory("introsde-2016-assignment-3-server");
	}

	public EntityManager createEntityManager() {
		try {
			// Return the Entity Manager that will provide the ops from/to the DB
			return emf.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnections(EntityManager em) {
		em.close();
	}
	
	public EntityTransaction getTransaction(EntityManager em) {
		return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}  
}