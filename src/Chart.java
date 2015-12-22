/* Module - Chart Class
 * 
 * Author: Benjamin Zerhusen
 * Creation Date: 5/13/2012
 * 
 * Revisions
 * <1> - Initial creation
 */
import java.util.ArrayList;

/* Description - A Chart maintains a list of symptoms that a patient currently has */
public class Chart {
	public ArrayList<String> curSymptoms;
	Chart() {
		curSymptoms = new ArrayList<String>();
	}

	public ArrayList<String> getCurSymptoms() {
		return curSymptoms;
	}

	public void addSymptom(String curSympt) {
		curSymptoms.add(curSympt);
	}
	// Possibly deprecated?
	public void displayPatientChart() {
		// Insert Code here to update display with info from chart
	}
}
