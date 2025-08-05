import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class AlleDatenController implements Serializable {
  
	@Inject
    private PersonController meldendePersonController;
	
	@Inject
    private PersonController bergendePersonController;

    @Inject
    private GeisternetzController geisternetzController;
    
    @Inject
    private GeisternetzDAO geisternetzDAO;


    public String speichereAlleDatenMitMeldenderPerson() {
        System.out.println("Speichere alle Daten mit meldender Person...");
        Geisternetz geisternetz = geisternetzController.getNeuesGeisternetz();
        Person meldendePerson = meldendePersonController.getNeueMeldendePerson();

        String wasIstPassiert = geisternetzDAO.speichereGeisternetzMitMeldenderPerson(geisternetz, meldendePerson);

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg:", wasIstPassiert));

        return null;  
    }
 
    public String speichereAlleDatenMitBergenderPerson() {
        
    	System.out.println("Speichere alle Daten mit bergender Person...");
        Geisternetz geisternetz = geisternetzController.getNeuesGeisternetz();
        Person bergendePerson = bergendePersonController.getNeueBergendePerson();

        String wasIstPassiert = geisternetzDAO.speichereGeisternetzMitBergenderPerson(geisternetz, bergendePerson);

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg:", wasIstPassiert));

        return null; 	
    	
    }
    
}
