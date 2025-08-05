import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped


public class PersonController implements Serializable
{
	
    private Person neueMeldendePerson = new Person();	

    private Person neueBergendePerson = new Person();

    
//___________________________________Meldende Person__________________________________________________________    
    public Person getNeueMeldendePerson() {
    		return neueMeldendePerson;
	}

	public void setNeueMeldendePerson(Person neueMeldendePerson) {
		this.neueMeldendePerson = neueMeldendePerson;
	}

//___________________________________Bergende Person__________________________________________________________
	public Person getNeueBergendePerson() {
		return neueBergendePerson;
	}


	public void setNeueBergendePerson(Person neueBergendePerson) {
		this.neueBergendePerson = neueBergendePerson;
	}	
	

	
}