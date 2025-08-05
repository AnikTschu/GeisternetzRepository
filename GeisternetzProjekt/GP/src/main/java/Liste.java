import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class Liste implements Serializable
{
    private static Liste instance = new Liste();

    private List<Geisternetz> netzliste = new ArrayList<Geisternetz>();

    public Liste()
    {
    }

    public static Liste getInstance()
    {
        return instance;
    }

    public List<Geisternetz> getNetzliste()
    {
        return netzliste;
    }

	public void setNetzliste(List<Geisternetz> netzliste) {
		this.netzliste = netzliste;
	}
  
}



