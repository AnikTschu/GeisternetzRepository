import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class Anschrift
{
    private String name = "Shea Sepherd - Non-Profit Organisation";
    private String strasse = "Ozean Straße";
    private int hausnummer = 92;
    private String plz = "12345";
    private String ort = "Bad Seenhausen";
    private String land ="Neuseeland";

    //Getter-/Setter______________________________________
    public Anschrift()
    {
    }

    public String getName()
    {
        return name;
    }


    public String getStrasse()
    {
        return strasse;
    }

    public int getHausnummer()
    {
        return hausnummer;
    }

    public String getPlz()
    {
        return plz;
    }

    public String getOrt()
    {
        return ort;
    }

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}
    
    
}
