/* Module - Ailment Class
 * 
 * Author: Benjamin Zerhusen
 * Creation Date: 5/13/2012
 * 
 * Revisions
 * <1> - Initial creation
 */
import java.util.Random;

/* Description -
 * The Ailment class formall defines the concept of a disease that a patient has.
 * An Ailment has a name and a list of symptoms.
 * 
 */
public class Ailment {
	private String name;
	private String[] symptoms;

	/* Randomly selects a disease */
	public String generateDisease() {
		Random gen = new Random();
		return Game.diseases[gen.nextInt(Game.nDiseases)].getName();	
	}

	/* Constructors */
	Ailment() {
		name = null;
		symptoms = null;
	}

	Ailment(String str,String[] sympt) {
		name = str;
		symptoms = sympt;
	}

	/* Gets and sets */
	public String getName() {
		return name;
	}

	public void setName(String str) {
		name = str;
		//DEBUGGING
		System.out.println("Ailment set to " + str);
	} 

	public String[] getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String[] sympt) {
		symptoms = sympt;
	}
}
