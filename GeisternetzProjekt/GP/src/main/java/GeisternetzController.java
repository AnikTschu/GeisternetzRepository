import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("geisternetzController")
@ViewScoped
public class GeisternetzController implements Serializable
{
    @Inject
    private GeisternetzDAO geisternetzDAO;
    
    private Geisternetz neuesGeisternetz = new Geisternetz();
    private Integer geisternetzId; 


//__Getter/Setter______________________________________________________________________________________    
    
    public Geisternetz getNeuesGeisternetz() {
		return neuesGeisternetz;
	}

	public void setNeuesGeisternetz(Geisternetz neuesGeisternetz) {
		this.neuesGeisternetz = neuesGeisternetz;
	}
    
    public Integer getGeisternetzId() {
		return geisternetzId;
	}
    
	public void setGeisternetzId(Integer geisternetzId) {
		this.geisternetzId = geisternetzId;
	}

//__Sonstige Methoden______________________________________________________________________________________ 
	
    public String ladeListe(){
    	geisternetzDAO.findAll();
    	return "liste.xhtml";
    }
    
    public void ladeGeisternetz() {
    	System.out.println("GeisternetzController.ladeGeisternetz() ausführen");
        if (geisternetzId != null) {
            neuesGeisternetz = geisternetzDAO.findById(geisternetzId);
            System.out.println("Geisternetz geladen: ID=" + neuesGeisternetz.getId() + " | Größe=" + neuesGeisternetz.getGroesse());
        } else {
            System.out.println("Keine ID übergeben, lade kein Geisternetz.");
        }
    }      
   
    public String bearbeiteDiesesGeisternetz (Integer id) {
    	System.out.println("GeisternetzController.bearbeiteDiesesGeisternetz("+id+") ausführen");
    	return "editmeldung.xhtml?faces-redirect=true&amp;id=" + id;
    }
    
    public String edit(){
    	return "edit.xhtml";
    }
    
}