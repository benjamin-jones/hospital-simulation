
/* Game - The game handles all the state information and updates the game state based on received events/user input 
 * 
 * Author: Benjamin Jones
 * Creation Date: May 21, 2012
 *
 * Revisions
 * May 21, 2012: Initial rev.
 */
import java.security.KeyStore.Entry;
import java.util.Random;
import java.awt.Color;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Game
{
        static int time;
        static int countdown = 0;
        static public boolean run = true;
        static public boolean pause = false;
        static String diff;
        static int funds = 1000;
        static int points = 0;
        static int maxPatients = 0;
        static int nPatients = 0;
        static int nDeaths = 0;
        static int nSaved = 0;
        static int patientVisits = 0;
        static Patient [] patients;
        static Random gen = new Random();
        static UpgradeManager upgrademanager = new UpgradeManager();
        public static Ailment[] diseases;
        public static ArrayList<String> diseaseName = new ArrayList<String>();
        public static ArrayList<String> symptomslist = new ArrayList<String>();
        public static int nDiseases = 0;
        public static HashMap<String,String[]> symptoms_map = new HashMap<String,String[]>();
        
      //GUI components
        static FunctionalGUI gui;
        static Simulation sim;
        
        Game(String difficulty)
        {
        	
        		
			time = 0;
	        diff = difficulty;
	        setDifficulty();
	        patients = new Patient[maxPatients];
	        gui = new FunctionalGUI(diff, this);
	        sim = new Simulation();
	        sim.start();
	        
	        
                
        }
        
        
        public void setDifficulty()
        {
                if (diff.equals("easy"))   { maxPatients = 4; }
                else if (diff.equals("medium")) { maxPatients = 6; }
                else if (diff.equals("hard"))   { maxPatients = 8; }
        }
        
 /*       static void setSymptoms()
        {

        	
                Scanner s = null;
                int nSymptoms = 0;
                String key;
                String[] symptoms;
                try {
                        s = new Scanner(new BufferedReader(new FileReader("data/symptoms.txt"))).useDelimiter("\n");
                } catch (IOException e)
                {
                        System.out.println("Can't open symptoms db");
                        return;
                }
              
                try {
                while (s.hasNext())
                {
                        if (s.hasNext()) {
                                key = s.next();
                        }
                        else { return; }
                        if (s.hasNext()) {
                                nSymptoms = s.nextInt();
                        }
                        if (nSymptoms > 0)
                        {
                                symptoms = new String[nSymptoms];
                        }
                        else
                        {
                                return;
                        }
                        for (int i = 0; i < nSymptoms; i++)
                        {
                                if (s.hasNext())
                                {
                                        symptoms[i] = s.next();
                                }
                        }

                        symptoms_map.put(key,symptoms);
                }
                if (s != null) s.close();
                } catch (Exception e) {
                        System.out.println("Symptoms db is corrupted");
                        return;
                }
        }
        
        static void setAilments()
        {
                Scanner s = null;
                nDiseases = 0;
                try {
                        s = new Scanner(new BufferedReader(new FileReader("data/diseases.txt"))).useDelimiter("\n");
                        while (s.hasNext()) {
                                s.next();
                                nDiseases++;
                        }
                        if (s != null) s.close();
                } catch (IOException e) {
                        System.out.println("Could not open disease database");
                        return;
                }        
                if (nDiseases > 0) {
                        diseases = new Ailment[nDiseases];
                }
                else
                {
                        System.out.println("No diseases found");
                        return;
                }
                try {
                        s = new Scanner(new BufferedReader(new FileReader("diseases.txt"))).useDelimiter("\n");
                        for (int i = 0; i < nDiseases; i++) {
                                if (s.hasNext()) {
                                        diseases[i] = new Ailment();
                                        diseases[i].setName(s.next());
                                }
                        }
                        if (s != null) s.close();
                } catch (IOException e) {
                        System.out.println("Could not open disease database");
                        return;
                }
        }
*/
	// Reads the disease DB
        static void setSymptoms()
        {
        	int nSymptoms = 0;
            String key;
            String[] symptoms;
            String tmp;

        	for(int i = 0; i < nDiseases; i++)
        	{
        		key = diseaseName.get(i);
        		
        		tmp = symptomslist.get(i);
        		StringTokenizer st = new StringTokenizer(tmp.substring(tmp.indexOf(":")+1), ";");
        		nSymptoms = st.countTokens();
        		if (nSymptoms > 0)
                {
                        symptoms = new String[nSymptoms];
                }
                else
                {
                        return;
                }
        		
        		System.out.print("Mapping disease " + diseaseName.get(i) + " with ");
        		for (int i2 = 0; i2 < nSymptoms; i2++)
                {
                        if(st.hasMoreTokens())
                        {
                        		symptoms[i2] = st.nextToken();
                        		System.out.print(symptoms[i2] + ",");
                        }
                        
                }
        		System.out.print("\n");
        		symptoms_map.put(key,symptoms);
        	}
/*        	
                Scanner s = null;
                int nSymptoms = 0;
                String key;
                String[] symptoms;
                try {
                        s = new Scanner(new BufferedReader(new FileReader("data/symptoms.txt"))).useDelimiter("\n");
                } catch (IOException e)
                {
                        System.out.println("Can't open symptoms db");
                        return;
                }
              
                try {
                while (s.hasNext())
                {
                        if (s.hasNext()) {
                                key = s.next();
                        }
                        else { return; }
                        if (s.hasNext()) {
                                nSymptoms = s.nextInt();
                        }
                        if (nSymptoms > 0)
                        {
                                symptoms = new String[nSymptoms];
                        }
                        else
                        {
                                return;
                        }
                        for (int i = 0; i < nSymptoms; i++)
                        {
                                if (s.hasNext())
                                {
                                        symptoms[i] = s.next();
                                }
                        }

                        symptoms_map.put(key,symptoms);
                }
                if (s != null) s.close();
                } catch (Exception e) {
                        System.out.println("Symptoms db is corrupted");
                        return;
                }
*/
        }
        
        static void setAilments()
        {
        	ArrayList<String> entry = new ArrayList<String>();
        	
        	//populate ailments and corresponding symptoms
	        try {
		        FileInputStream input = new FileInputStream("Ailments.txt");
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		    	String line;
		    	while ((line = reader.readLine()) != null)  {
		    		entry.add(line);
		    		
		    		//DEBUGGING
		    		//System.out.println(line);
		    	}
		    	nDiseases = entry.size();
		    	reader.close();
		    		    	
		    	if (nDiseases > 0) {
	                diseases = new Ailment[nDiseases];
		        }
		        else
		        {
		                System.out.println("No diseases found");
		                return;
		        }   
		    	
		    	Collections.sort(entry); 
		    	
		    	for(int i = 0; i < entry.size(); i++)
		    	{
		    		diseaseName.add(entry.get(i).substring(0,entry.get(i).indexOf(":")));
                    diseases[i] = new Ailment();
                    diseases[i].setName(diseaseName.get(i));
		    		symptomslist.add(entry.get(i).substring(entry.get(i).indexOf(":")+1, entry.get(i).indexOf(".")));
		    		//DEBUGGING
		    		//System.out.println("Added disease " + diseaseName.get(i) + " with symptoms " + symptomslist.get(i));
		    	}
	        }
	        catch(Exception e) { System.out.println("FAILED TO RETRIEVE NEW AILMENTS DB"); };    
	        
	         
        }
        
        public static String[] getDiseases()
        {
                String[] d;
                setAilments();
                setSymptoms();
                if (nDiseases > 0)
                {
                        d = new String[nDiseases];
                        for (int i = 0; i < nDiseases; i++)
                        {
                                d[i] = diseases[i].getName();
                        }
                        return d;
                }
                return null;
        }
        

        

        public static int getTime()
        {
                return time;
        }
	// Creates new patients and puts them in the next available bed
        public static void newPatientEventHandler(Event e)
        {
                System.out.println(time + ": " + e.getString());
                if (nPatients == maxPatients)
                {
                        System.out.println(time + ": Patient turned away.");
                        gui.messages.setText("Patient was turned away.");
                }
                else
                {
                        for (int i = 0; i < maxPatients; i++)
                        {
                                if (patients[i] == null)
                                {
                                        patients[i] = new Patient(i);
                                        //DEBUG:
                                        System.out.println("Patient has: " + patients[i].getAilment().getName());
                                        String d = patients[i].getAilment().getName();
                                        String[] symps = symptoms_map.get(d);
                                        if (symps.length > 0) { 
                                                String symp = symps[gen.nextInt(symps.length)];
                                                patients[i].getChart().addSymptom(symp);
                                                if (UpgradeManager.use_upgrade[4]) {		//multiple symptom upgrade flag
                                                        String symp2 = symps[gen.nextInt(symps.length)];
                                                        int count = 0;
                                                        while (symp.equals(symp2)) {
                                                                symp2 = symps[gen.nextInt(symps.length)];
                                                                count++;
                                                                if (count == symps.length) { break; }
                                                        }
                                                        patients[i].getChart().addSymptom(symp2);
                                                        UpgradeManager.use_upgrade[4] = false;
                                                } 
                                        }

                                        nPatients++;
                                        patientVisits++;
                                        break;
                                }
                        }
                }
        }
	// Randomly selects a patient and the next available symptom 
        public static void showSymptomEventHandler(Event e)
        {
                if (nPatients > 0)
                {
                        int affectedPatient = gen.nextInt(maxPatients);
			int count = 0;
                        while (patients[affectedPatient] == null)
                        {
                                affectedPatient = gen.nextInt(maxPatients);
				count++;
				if (count == maxPatients) { return; }
                        }
                        System.out.println(time + ": " + e.getString());
                        System.out.println("Patient " + affectedPatient + " affected!");
                        String d = patients[affectedPatient].getAilment().getName();
                        String[] symps = symptoms_map.get(d);
                        if (symps.length > 0) { 
                                String symp = symps[gen.nextInt(symps.length)];
                                ArrayList<String> currSymps = patients[affectedPatient].getChart().getCurSymptoms();

                                if (currSymps.isEmpty()) {
                                        patients[affectedPatient].getChart().addSymptom(symp);
                                } else {
                                        int i = 0;
                                        while (currSymps.contains(symp)) {
                                                int nSymps = symps.length;
                                                if (i == nSymps) {
                                                        return;
                                                }
                                                symp = symps[i];
                                                i++;
                                        }
                                        patients[affectedPatient].getChart().addSymptom(symp);
                                }
                                //DEBUG:
                                        System.out.println("Patient has " + symp);
                        }
                }
        }
	// Checks a user guess against the patient's actual disease
        public static void diagnosePatientEventHandler(Event e)
        {
                if (nPatients > 0)
                {
                        System.out.println(time + ": " + e.getString());
                        if (patients[e.getPatient()] != null)
                        {
                                if (patients[e.getPatient()].returnDiagnosisCorrect(diseases[e.getDisease()]))
                                {
                                        gui.cur_symptoms.setText("\n\n\n\n\n\n\n\n");
                                		System.out.println("Diagnosis correct!");
                                        gui.messages.setText("Diagnosis correct!");
                                        patients[e.getPatient()].nDiags++;
                                        nSaved++;
                                        if (UpgradeManager.use_upgrade[1] && patients[e.getPatient()].getHealthPoints() >= 90) { 	//quick diagnoses upgrade flag
                                                points += 10; 
                                                UpgradeManager.use_upgrade[1] = false;
                                        }
                                        if (UpgradeManager.use_upgrade[2]) { //second change upgrade flag
                                        	points += 10;
                                        	UpgradeManager.use_upgrade[2] = false;
                                        }	
                                        else {
                                                points += Math.ceil(((10/patients[e.getPatient()].nDiags)*patients[e.getPatient()].getHealthPoints())/100);
                                        }
                                        funds += 100;
                                        patients[e.getPatient()] = null; 
					nPatients--;      
                                } else {
                                        System.out.println("Diagnosis incorrect!");
                                        gui.messages.setText("Diagnosis incorrect!");
                                        if (points == 0) { return;}
                                        else { 
                                                if (UpgradeManager.use_upgrade[3])	//malpractice insurance upgrade flag
                                                {
                                                		UpgradeManager.use_upgrade[3] = false;
                                                        patients[e.getPatient()].nDiags++;
                                                        return;
                                                }
                                                points -= 10; 
                                                patients[e.getPatient()].nDiags++;
                                        }
                                }
                        }
                }
        }
	// Updates the game state 
        public static void clockTickEventHandler(Event e)
        {
              
        	
        		//update stats displays
                gui.visits_disp.setText(Integer.toString(patientVisits));
        		gui.saved_disp.setText(Integer.toString(nSaved));
        		gui.died_disp.setText(Integer.toString(nDeaths));
                gui.score_disp.setText(Integer.toString(points));
                gui.money_disp.setText(Integer.toString(funds));
                gui.time_disp.setText(Integer.toString(time));
                
                System.out.println(time + ": " + e.getString());
                time++;
                System.out.println("Funds: " + funds);
                System.out.println("Points: " + points);
                
                //gui.messages.setText("");
                
                for (int i = 0; i < maxPatients; i++)
                {
                        if (patients[i] == null) {
                                gui.updatePatient(i,"Bed Empty");
                                gui.patient[i].setEnabled(false);
                                gui.patientHealth[i].setValue(0);
                                continue;
                        }
                        if(FunctionalGUI.bPause.getText().equals("PAUSE")) { gui.patient[i].setEnabled(true); }
                        //update patient health bar
                        FunctionalGUI.patientHealth[i].setValue(patients[i].getHealthPoints());
                        if(patients[i].getHealthPoints() < 50) { FunctionalGUI.patientHealth[i].setForeground(Color.YELLOW); }
                        if(patients[i].getHealthPoints() < 25) { FunctionalGUI.patientHealth[i].setForeground(Color.RED); }
                        
                        if (patients[i].getHealthPoints() == 0)
                        {
                                System.out.println("Patient " + i + " is dead!");
                                FunctionalGUI.patient[i].setIcon(null);
                                gui.updatePatient(i,"Patient is dead!");
                                gui.messages.setText("Patient " + (i+1) + " died!");
                                patients[i] = null;
                                nDeaths++;
                                nPatients--;
                                funds -= 100;
                                continue;
                        }
                        System.out.println("Patient " + (i+1) + " has " + patients[i].getHealthPoints() + "hp");
                        gui.updatePatient(i,"Patient " + (i+1) + ": " + patients[i].getHealthPoints() + "hp");
                        String[] symps = new String[4];
                        symps = patients[i].getChart().getCurSymptoms().toArray(symps);
                        for (int j = 0; j < symps.length; j++)
                        {
                                if (symps[j] == null) break;
                                System.out.println("- "+symps[j]);
                        }
                        if (UpgradeManager.use_upgrade[5]) { 	//stable upgrade
                                countdown += 30;
                                UpgradeManager.use_upgrade[5] = false;
                        }
                        if (gen.nextInt(2) == 1)
                        {
                                if (countdown == 0) {
                                        patients[i].setHealthPoints(patients[i].getHealthPoints() - 1);
                                }
                        }
                        if (UpgradeManager.use_upgrade[0])
                        {
                                patients[i].setHealthPoints(patients[i].getHealthPoints() + 25);
        
                        }
                }
                if (UpgradeManager.use_upgrade[0]){ UpgradeManager.use_upgrade[0] = false; }
                if (countdown != 0) { countdown--; }
                if (time % 300 == 0)
                {
                        if (nDeaths > 10) {
                                Game.run = false;
                                System.out.println("Too many patient deaths. GAME OVER.");
                                JOptionPane.showMessageDialog(null, "GAME OVER: You let too many people die...");
                                enterScore();                                
                                gui.dispose();
                        }
                        else
                        {
                                nDeaths = 0;
                        }
                }
                if (funds <= 0) {
                        Game.run = false;
                        System.out.println("Out of money. GAME OVER.");
                        JOptionPane.showMessageDialog(null, "GAME OVER: You are bankrupt...");
                        enterScore();
                        gui.dispose();
                }
                if(patientVisits > 20)
                {
                	if(points/patientVisits >= 7 && nSaved > 20)
                	{
                		Game.run = false;
                        System.out.println("YOU WIN.");
                        JOptionPane.showMessageDialog(null, "YOU WIN: YOU ARE AN ADEQUATE DOCTOR.");
                        enterScore();
                        gui.dispose();
                	}
                }
        }
        
        public ArrayList<String> getPatientSymptoms(int patientnum)
        {
        	if(patients[patientnum] != null) { return patients[patientnum].chart.getCurSymptoms(); }
        	else { return null; }
        }
        
        public static void pause()
        {
                pause = true;
                gui.cur_symptoms.setText("\n\n\n\n\n\n\n\n");
                
                gui.bDiagnose.setEnabled(false);
                gui.cur_patient.setEnabled(false);
                gui.cur_symptoms.setEnabled(false);
                gui.poss_diag.setEnabled(false);
                gui.bDiagnose.setEnabled(false);
                
                for(int i = 0; i < gui.patient.length; i++)
                {
                        gui.patient[i].setEnabled(false);
                }
                for(int i = 0; i < gui.bUpgrades.length; i++)
                {
                        gui.bUpgrades[i].setEnabled(false);
                }
        }
        
        public static void resume()
        {
                pause = false;
                
                gui.bDiagnose.setEnabled(true);
                gui.cur_patient.setEnabled(true);
                gui.cur_symptoms.setEnabled(true);
                gui.poss_diag.setEnabled(true);
                gui.bDiagnose.setEnabled(true);
                gui.score_disp.setEnabled(true);
                gui.money_disp.setEnabled(true);
                gui.time_disp.setEnabled(true);
                
                for(int i = 0; i < gui.patient.length; i++)
                {
                        gui.patient[i].setEnabled(true);
                }
                for(int i = 0; i < gui.bUpgrades.length; i++)
                {
                        if(UpgradeManager.upgrade_count[i] != 0)
                        {
                                gui.bUpgrades[i].setEnabled(true);
                        }       
                }
        }
        
        public static void enterScore()
        {
        	String name = JOptionPane.showInputDialog(null,"Enter user name: ");
        	try
        	{
	        	FileWriter fstream = new FileWriter(MainMenu.scoreFile,true);
	        	BufferedWriter out = new BufferedWriter(fstream);
	        	out.write(((double)patientVisits/(double)points) + ":" + name + ";\n");
	        	out.close();
        	}
        	catch (Exception e) {System.err.println("Error: " + e.getMessage());}
        }
      
}
