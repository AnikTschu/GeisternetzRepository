import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@Named
@ApplicationScoped

public class PersonDAO implements Serializable{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("geisternetzePersistenceUnit");
	
//	public boolean existiertBereits(String name, String telefonnr) {
//	    EntityManager em = emf.createEntityManager();
//	    try {
//	        Query query = em.createQuery(
//	            "SELECT COUNT(m) FROM Person m WHERE m.name = :name AND m.telefonnr = :telefonnr");
//	        query.setParameter("name", name);
//	        query.setParameter("telefonnr", telefonnr);
//	        Long count = (Long) query.getSingleResult();
//	        return count > 0;
//	    } finally {
//	        em.close();
//	    }
//	}	
	
	public Person findePerson(String name, String telefonnr) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        Query query = em.createQuery(
	            "SELECT m FROM Person m WHERE m.name = :name AND m.telefonnr = :telefonnr");
	        query.setParameter("name", name);
	        query.setParameter("telefonnr", telefonnr);
	        List<Person> result = query.getResultList();
	        if (result.isEmpty()) {
	            return null;
	        } else {
	            return result.get(0);
	        }
	    } finally {
	        em.close();
	    }
	}
	
	
}	
	
	