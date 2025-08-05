import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@Named
@ApplicationScoped
public class GeisternetzDAO implements Serializable{
	
	@Inject 
	private Liste liste;
	@Inject
	private PersonDAO meldendePersonDAO;
	@Inject
	private PersonDAO bergendePersonDAO;
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("geisternetzePersistenceUnit");
	
	public void findAll(){
		EntityManager em = emf.createEntityManager(); //für SELECT ist kein Commit nötig
		Query abfrage = em.createQuery("SELECT g FROM Geisternetz g");
		@SuppressWarnings("unchecked")
		List<Geisternetz> alleGeisternetze = abfrage.getResultList();
		liste.setNetzliste(alleGeisternetze);
		em.close();
	}

	public boolean bereitsExistierendesGeisternetz(Double breitengrad, Double laengengrad, int groesse) {//, String status
	    EntityManager em = emf.createEntityManager();
	    try {
	        Query query = em.createQuery(
	            "SELECT COUNT(g) FROM Geisternetz g WHERE g.breitengrad = :breitengrad AND g.laengengrad = :laengengrad AND g.groesse = :groesse ");
	        //AND g.status = :status")
	        query.setParameter("breitengrad", breitengrad);
	        query.setParameter("laengengrad", laengengrad);
	        query.setParameter("groesse", groesse);
	        //query.setParameter("status", status);
	        Long count = (Long) query.getSingleResult();
	        return count > 0;
	    } finally {
	        em.close();
	    }
	}

	public Geisternetz findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Geisternetz.class, id);
    }		
	
	public String speichereGeisternetzMitMeldenderPerson(Geisternetz geisternetz, Person meldendePerson) {
	    
		if (bereitsExistierendesGeisternetz(
                geisternetz.getBreitengrad(),
                geisternetz.getLaengengrad(),
                geisternetz.getGroesse()
                )) { //geisternetz.getStatus()
            return "Dieses Geisternetz existiert bereits.";
        }
		System.out.println("Dieses Geisternetz existiert noch nicht.");
		
		Person vorhandenePerson = null;
		
		if(meldendePerson.getName()!="" || meldendePerson.getTelefonnr()!=""){
			vorhandenePerson = meldendePersonDAO.findePerson(meldendePerson.getName(), meldendePerson.getTelefonnr());
		}
		
		EntityManager em = emf.createEntityManager();
	    EntityTransaction t = em.getTransaction();

	    try {
	        t.begin();
	        String wasWurdeGespeichert;	
			if(meldendePerson.getName()=="" && meldendePerson.getTelefonnr()=="") {
				em.persist(geisternetz);
	            wasWurdeGespeichert = "Das neue Geisternetz wurde gespeichert.";
			}
			else if (vorhandenePerson != null) {
	            geisternetz.setMeldendePerson(vorhandenePerson);
	        	em.persist(geisternetz);
	            wasWurdeGespeichert = "Diese meldende Person existiert bereits. Das neue Geisternetz wurde ihr zugeordnet.";

	        } else {
	        	System.out.println("Diese meldende Person existiert noch nicht.");
	        	System.out.println(geisternetz.getId());
	        	meldendePerson.addGeisternetzMeldendePerson(geisternetz);
	            em.persist(meldendePerson);
	            wasWurdeGespeichert = "Neue meldende Person und neues Geisternetz wurden gespeichert.";
	        }
	        
	        t.commit();
	        
	        return wasWurdeGespeichert;
	        
	    } catch (Exception e) {
	        if (t.isActive()) {
	        	t.rollback();
	        }
	        e.printStackTrace();
	        return "Oops, hier ist ein Fehler passiert! Bitte kontaktieren Sie unseren Support. Danke!";
	    } finally {
	        em.close();
	    }
	}

	public String speichereGeisternetzMitBergenderPerson(Geisternetz geisternetz, Person bergendePerson) {

		Person vorhandenePerson = null;
		
		if(bergendePerson.getName()!="" || bergendePerson.getTelefonnr()!=""){
			vorhandenePerson = bergendePersonDAO.findePerson(bergendePerson.getName(), bergendePerson.getTelefonnr());
		}
		
		EntityManager em = emf.createEntityManager();
	    EntityTransaction t = em.getTransaction();

	    try {
	        t.begin();
	        String wasWurdeGespeichert;	
			if(vorhandenePerson != null) {
	            geisternetz.setBergendePerson(vorhandenePerson);
	        	em.merge(geisternetz);
	            wasWurdeGespeichert = "Diese Person existiert bereits. Das Geisternetz wurde dieser Person zugeordnet. "
	            		+ "Die Änderungen am Geisternetz wurden gespeichert.";

	        } else {
	        	System.out.println("Diese Person existiert noch nicht.");
	        	System.out.println(geisternetz.getId());
	        	bergendePerson.addGeisternetzBergendePerson(geisternetz);
	            em.persist(bergendePerson);
	            em.merge(geisternetz);
	            wasWurdeGespeichert = "Neue Person wurde gespeichert. Das Geisternetz wurde dieser Person zugeordnet. "
	            		            		+ "Die Änderungen am Geisternetz wurden gespeichert.";
	        }
	        
	        t.commit();
	        
	        return wasWurdeGespeichert;
	        
	    } catch (Exception e) {
	        if (t.isActive()) {
	        	t.rollback();
	        }
	        e.printStackTrace();
	        return "Oops, hier ist ein Fehler passiert! Bitte kontaktieren Sie unseren Support. Danke!";
	    } finally {
	        em.close();
	    }
	}
}
