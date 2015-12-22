import java.awt.Color;
import java.util.Random;

/* Module - Patient Class
 * 
 * Author: Benjamin Zerhusen
 * Creation Date: 5/15/2012
 * 
 * Revisions
 * <1> - Initial creation
 */

public class Patient {
	public Ailment disease;
	public Chart chart;
	public int nDiags = 0;
	private int healthPoints;
	Patient(int i) {
		Random gen = new Random();
		
		disease = new Ailment();
		disease.setName(disease.generateDisease());
		chart = new Chart();
		healthPoints = 100;
		
		double r = gen.nextDouble();
		if( 0 < r && r < 0.05  )
		{
			healthPoints = 50;
			UpgradeManager.use_upgrade[4]= true;
			FunctionalGUI.messages.setText("Critical Patient has arrived!");
		}
		FunctionalGUI.patientHealth[i].setForeground(Color.GREEN);
		FunctionalGUI.patientHealth[i].setValue(healthPoints);
		
//		FunctionalGUI.patient[i].setIcon(FunctionalGUI.patient_image);
	}

	Patient(Ailment d,Chart c,int hp) {
		disease = d;
		chart = c;
		healthPoints = hp;
	}

	public Ailment getAilment() {
		return disease;
	}

	public void setAilment(Ailment d) {
		disease = d;
	} 
	
	public Chart getChart() {
		return chart;
	}
	public void setChart(Chart c) {
		chart = c;
	} 
	
	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int hp) {
		if (hp > 1)
		{
			healthPoints = hp;
		}
		else
		{
			healthPoints = 0;
		}
	} 
	
	public boolean returnDiagnosisCorrect(Ailment guess/*String guess*/) {
		if (guess.getName().equals(disease.getName())) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
