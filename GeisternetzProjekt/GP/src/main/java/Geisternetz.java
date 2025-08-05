import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Geisternetz implements Serializable
{
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Double breitengrad; //Einheit: hddd.ddddd°, z.B.: 46.235197° = N46.235197°, Range: [-90°,90°]
	private Double laengengrad; //Einheit: hddd.ddddd°, z.B.: 008.015445° = E008.015445°, Range: [-180°,180°]
	
	private int groesse; // Einheit: km
    private String status="gemeldet"; //["Gemeldet", "Bergung bevorstehend", "Geborgen", "Verschollen"]
    
    @ManyToOne
    @JoinColumn(name = "MeldendePersonID")
    private Person meldendePerson;
    
    @ManyToOne
    @JoinColumn(name = "BergendePersonID")
    private Person bergendePerson;
    
    //Datum je Status
    @Temporal(TemporalType.DATE)
    private Date erfassungsdatum = new Date(); //Pflichtfeld: Status: "Gemeldet"
    @Temporal(TemporalType.DATE)
    private Date bergungsdatum = null; //optional: Status "Bergung bevorstehend"
    @Temporal(TemporalType.DATE)
    private Date geborgendatum = null; //optional: Status "Geborgen"
    @Temporal(TemporalType.DATE)
    private Date verschollendatum = null; //optional: Status "Verschollen"
 
    public Geisternetz(){
    }

//    public Geisternetz(Double breitengrad, Double laengengrad, int groesse, String status,
//    					Date erfassungsdatum) {
//
//    	this.breitengrad = breitengrad;
//    	this.laengengrad = laengengrad;
//    	this.groesse = groesse;
//    	this.status = status;
//    	
//    	this.erfassungsdatum = erfassungsdatum;
//    }
//    
//    
//    public Geisternetz(Double breitengrad, Double laengengrad, int groesse, String status, 
//    		Date erfassungsdatum, Date bergungsdatum, 
//    		Date geborgendatum, Date verschollendatum) {
//    	
//		super();
//		this.breitengrad = breitengrad;
//		this.laengengrad = laengengrad;
//		this.groesse = groesse;
//		this.status = status;
//		
//		this.erfassungsdatum = erfassungsdatum;
//		this.bergungsdatum = bergungsdatum;
//		this.geborgendatum = geborgendatum;
//		this.verschollendatum = verschollendatum;
//	}

//__Getter/Setter______________________________________________________________________________________
    
	public Integer getId() {
		return id;
	}

	public Double getBreitengrad() {
		return breitengrad;
	}
	public void setBreitengrad(Double breitengrad) {
		this.breitengrad = breitengrad;
	}

	public Double getLaengengrad() {
		return laengengrad;
	}
	public void setLaengengrad(Double laengengrad) {
		this.laengengrad = laengengrad;
	}

	public int getGroesse() {
		return groesse;
	}
	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getErfassungsdatum() {
		return erfassungsdatum;
	}
	public void setErfassungsdatum(Date erfassungsdatum) {
		this.erfassungsdatum = erfassungsdatum;
	}

	public Date getBergungsdatum() {
		return bergungsdatum;
	}
	public void setBergungsdatum(Date bergungsdatum) {
		this.bergungsdatum = bergungsdatum;
	}

	public Date getGeborgendatum() {
		return geborgendatum;
	}
	public void setGeborgendatum(Date geborgendatum) {
		this.geborgendatum = geborgendatum;
	}

	public Date getVerschollendatum() {
		return verschollendatum;
	}
	public void setVerschollendatum(Date verschollendatum) {
		this.verschollendatum = verschollendatum;
	}

	
//__Personen (Getter-/Setter-Methoden)__________________________________________________________________________________________
	
	public Person getMeldendePerson() {
		return meldendePerson;
	}

	public void setMeldendePerson(Person meldendePerson) {
		this.meldendePerson = meldendePerson;
	}

	public Person getBergendePerson() {
		return bergendePerson;
	}

	public void setBergendePerson(Person bergendePerson) {
		this.bergendePerson = bergendePerson;
	}

}
