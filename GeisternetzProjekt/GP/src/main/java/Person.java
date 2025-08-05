import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class Person implements Serializable
{
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name; //Format: NachnameVorname, in Zukunft auch als Username verwendbar
	private String telefonnr;
	
    @OneToMany (mappedBy = "meldendePerson", cascade = CascadeType.PERSIST)
    private List<Geisternetz> geisternetzeMeldendePerson = new ArrayList<>();
	
    @OneToMany (mappedBy = "bergendePerson", cascade = CascadeType.MERGE)
    private List<Geisternetz> geisternetzeBergendePerson = new ArrayList<>();
    
    public Person(){
    }
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelefonnr() {
		return telefonnr;
	}
	
	public void setTelefonnr(String telefonnr) {
		this.telefonnr = telefonnr;
	}

    public void addGeisternetzMeldendePerson(Geisternetz geisternetz) {
    	 geisternetzeMeldendePerson.add(geisternetz);
        geisternetz.setMeldendePerson(this);
    }

     public void addGeisternetzBergendePerson(Geisternetz geisternetz) {
    	 geisternetzeBergendePerson.add(geisternetz);
        geisternetz.setBergendePerson(this);
    }

	
}