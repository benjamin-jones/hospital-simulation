
/* FunctionalGUI - The GUI displays Game state information and sends user input info to the Game
 * 
 * Author: Josh Hay
 * Creation Date: May 21, 2012
 *
 * Revisions
 * May 21, 2012: Initial rev.
 */
import java.awt.*;  
import java.awt.event.*;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;  
   
public class FunctionalGUI extends JFrame
{  
	static ImageIcon icu_background;
	static ImageIcon patient_image;
	static ImageIcon hosp_icon;
	
	int screenX;
	int screenY;
	int backgroundX, backgroundY;
	double xScale, yScale;
	int panelwidth;
	
	Game manager;
	static MainMenu menu;
	
	JTextField cur_patient;
	JTextArea cur_symptoms;
	JList poss_diag;
	JButton bDiagnose;
	JButton bStore;
	static JTextField messages;
	JButton bUpgrades[] = new JButton[6];
	JTextField visits_disp;
	JTextField saved_disp;
	JTextField died_disp;
	JTextField score_disp;
	JTextField money_disp;
	JTextField time_disp;
	static JButton bPause;
	JButton bHelp;
	JButton bQuit;
	
	DrawJPanel background;
	JPanel pTop;
	JPanel icu;
	
	static JButton patient[];
	static JProgressBar patientHealth[];
	
	public FunctionalGUI(String diff, Game man)
	{
		manager = man;
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//set gui size to full screen
		Toolkit tool = Toolkit.getDefaultToolkit();
		//screenX = ((int) tool.getScreenSize().getWidth());
		//screenY = ((int) tool.getScreenSize().getHeight());
		screenX = 1024;
		screenY = 768;
		try
		{
			//get background image
			File background_raw = new File("images/icu_background.jpg");
			FileInputStream fin1 = new FileInputStream(background_raw);
			byte fileContent1[] = new byte[(int) background_raw.length()];
			fin1.read(fileContent1);
			icu_background = new ImageIcon( fileContent1 );
			
			//get patient image
			File patient_raw = new File("images/patient_small.jpg");
			FileInputStream fin2 = new FileInputStream(patient_raw);
			byte fileContent2[] = new byte[(int) patient_raw.length()];
			fin1.read(fileContent2);
			patient_image = new ImageIcon( fileContent2 );
			
			//hopsital icons
			File icon_raw = new File("images/hospital_icon.jpg");
			FileInputStream fin3 = new FileInputStream(icon_raw);
			byte fileContent3[] = new byte[(int) icon_raw.length()];
			fin1.read(fileContent3);
			hosp_icon = new ImageIcon( fileContent3 );
		}
		catch (FileNotFoundException e) { System.out.println("File not found" + e); }
		catch (IOException ioe) { System.out.println("Exception while reading file" + ioe); }
		
		panelwidth = screenX/5;
		
		
		
		pTop = new JPanel();
		pTop.setLayout(new GridLayout(1,5));
		JLabel lchart = new JLabel("CHART", JLabel.CENTER);
		pTop.add(lchart);
		
//		JLabel limage = new JLabel("WILL BE IMAGE", JLabel.CENTER);
		DrawJPanel icon = new DrawJPanel();
		icon.setImage(hosp_icon.getImage(), 0, 0, 0, 0);
		pTop.add(icon);
		bStore = new JButton("STORE");
		bStore.addActionListener(new StoreButtonHandler());
		pTop.add(bStore);
//		JLabel limage2 = new JLabel("WILL BE IMAGE", JLabel.CENTER);
		DrawJPanel icon2 = new DrawJPanel();
		icon2.setImage(hosp_icon.getImage(), 0, 0, 0, 0);
		pTop.add(icon2);
		JLabel lstats = new JLabel("STATS", JLabel.CENTER);
		pTop.add(lstats);
		add(pTop,BorderLayout.NORTH);
		
		JPanel pChart = new JPanel();
		pChart.setMinimumSize(new Dimension(panelwidth, screenY));
		pChart.setPreferredSize(new Dimension(panelwidth, screenY));
		pChart.setMaximumSize(new Dimension(panelwidth, screenY));
		pChart.setLayout(new BoxLayout(pChart, BoxLayout.Y_AXIS));
		add(pChart, BorderLayout.WEST);
		JPanel pSyms = new JPanel();
		pSyms.setLayout(new FlowLayout());
		JLabel lsymptoms= new JLabel("Current Symptoms for" );
		pSyms.add(lsymptoms);
		cur_patient = new JTextField();
		cur_patient.setEditable(false);
		pSyms.add(cur_patient);
		pChart.add(pSyms);
		cur_symptoms = new JTextArea(4,panelwidth);
//		cur_symptoms.setMinimumSize(new Dimension(panelwidth, (screenY/3)-10));
//		cur_symptoms.setPreferredSize(new Dimension(panelwidth, (screenY/3)-10));
//		cur_symptoms.setMaximumSize(new Dimension(panelwidth, (screenY/3)-10));
		Font font = new Font("Verdana", Font.BOLD, 14);
		cur_symptoms.setFont(font);
		cur_symptoms.setLineWrap(true);
		cur_symptoms.setText("\n\n\n\n\n\n\n\n");
		pChart.add(cur_symptoms);		
		JLabel lposs_diag = new JLabel("Possible Diagnoses");
		pChart.add(lposs_diag);
		String [] d = Game.getDiseases();
		if (d == null)
		{
			return;
		}
		poss_diag = new JList(d);
		poss_diag.setFont(font);
		poss_diag.setFixedCellWidth(panelwidth);
		pChart.add(poss_diag);
		JPanel pDiagnose = new JPanel();
		pDiagnose.setLayout(new GridLayout(1,1));
		bDiagnose = new JButton("DIAGNOSE");
		bDiagnose.addActionListener(new DiagnoseButtonHandler());
		pDiagnose.add(bDiagnose);
		pChart.add(pDiagnose);
		add(pChart, BorderLayout.WEST);
		
		background = new DrawJPanel();
		//scale images to fit full screen gui
		double background_height = screenY-pTop.getHeight();
		yScale = background_height/((double)icu_background.getIconHeight());
		double background_width = 3*panelwidth;
		xScale = background_width/((double)icu_background.getIconWidth());
		backgroundX = (int) (icu_background.getIconWidth()*xScale);
		backgroundY = (int) (icu_background.getIconHeight()*yScale);
		background.setImage(icu_background.getImage(), 0, 0, backgroundX, backgroundY);
		createPatients(diff);
		add(background, BorderLayout.CENTER);
		
		JPanel pStats = new JPanel();
		pStats.setLayout(new BoxLayout(pStats, BoxLayout.Y_AXIS));
		pStats.setMinimumSize(new Dimension(panelwidth, screenY));
		pStats.setPreferredSize(new Dimension(panelwidth, screenY));
		pStats.setMaximumSize(new Dimension(panelwidth, screenY));
		JLabel activity = new JLabel("Recent Activity", JLabel.LEFT);
		pStats.add(activity);
		messages = new JTextField();
		messages.setMaximumSize(new Dimension(panelwidth, 100));
		messages.setHorizontalAlignment(JTextField.CENTER);
		messages.setEditable(false);
		pStats.add(messages);		
		JLabel lupgrades= new JLabel("Available Upgrades", JLabel.CENTER);
		pStats.add(lupgrades);
		JPanel pUpgrades = new JPanel();
		pUpgrades.setLayout(new GridLayout(6,1));
		for(int i = 0; i < bUpgrades.length; i++)
		{
			bUpgrades[i] = new JButton(UpgradeManager.upgrade_name[i] + ": " + UpgradeManager.upgrade_count[i]);
			bUpgrades[i].addActionListener(new UpgradesButtonHandler());
			bUpgrades[i].setEnabled(false);
			pUpgrades.add(bUpgrades[i]);
		}
		pStats.add(pUpgrades);
		JPanel pDisp = new JPanel();
		pDisp.setLayout(new GridLayout(6,2));
		JLabel lvisits = new JLabel("PATIENT VISITS: ");
		lvisits.setHorizontalAlignment(JLabel.CENTER);
		pDisp.add(lvisits);
		pDisp.add(visits_disp = new JTextField());
		JLabel lsaved = new JLabel("PATIENTS SAVED: ");
		lsaved.setHorizontalAlignment(JLabel.CENTER);
		pDisp.add(lsaved);
		pDisp.add(saved_disp = new JTextField());
		JLabel ldied = new JLabel("PATIENTS DIED: ");
		ldied.setHorizontalAlignment(JLabel.CENTER);
		pDisp.add(ldied);
		pDisp.add(died_disp = new JTextField());
		JLabel lscore = new JLabel("SCORE: ");
		lscore.setHorizontalAlignment(JLabel.CENTER);
		pDisp.add(lscore);
		JLabel lmoney = new JLabel("MONEY: ");
		lmoney.setHorizontalAlignment(JLabel.CENTER);
		pDisp.add(score_disp = new JTextField());
		pDisp.add(lmoney);
		pDisp.add(money_disp = new JTextField());
		JLabel ltime = new JLabel("TIME: ");
		ltime.setHorizontalAlignment(JLabel.CENTER);
		pDisp.add(ltime);
		pDisp.add(time_disp = new JTextField());
		visits_disp.setHorizontalAlignment(JTextField.CENTER);
		saved_disp.setHorizontalAlignment(JTextField.CENTER);
		died_disp.setHorizontalAlignment(JTextField.CENTER);
		score_disp.setHorizontalAlignment(JTextField.CENTER);
		money_disp.setHorizontalAlignment(JTextField.CENTER);
		time_disp.setHorizontalAlignment(JTextField.CENTER);
		visits_disp.setEditable(false);
		saved_disp.setEditable(false);
		died_disp.setEditable(false);
		score_disp.setEditable(false);
		money_disp.setEditable(false);
		time_disp.setEditable(false);
		
		pStats.add(pDisp);
		JPanel pControlPanel = new JPanel();
		pControlPanel.setLayout(new GridLayout(3,1));
		bPause = new JButton("PAUSE");
		bPause.addActionListener(new PauseButtonHandler());
		pControlPanel.add(bPause);
		bHelp = new JButton("HELP");
		bHelp.addActionListener(new HelpButtonHandler());
		pControlPanel.add(bHelp);
		bQuit = new JButton("QUIT");
		bQuit.addActionListener(new QuitButtonHandler());
		pControlPanel.add(bQuit);
		pStats.add(pControlPanel);
		add(pStats, BorderLayout.EAST);
		
		setVisible(true);
	}
	
	public class DrawJPanel extends JPanel
	{
		Image image;
		int posx;
		int posy;
		int width;
		int height;
		
		public void setImage(Image i, int x, int y, int w, int h)
		{
			this.image = i;
			posx = x;
			posy = y;
			width = w;
			height = h;
		}

		public void paintComponent( Graphics g )
		{
			super.paintComponent( g );
			g.drawImage(image, posx, posy, width, height, this);
			//DEBUGGING
			System.out.println("Created image: " + image + " with width " + width + " and height " + height);
		}
	}
	
	public class StoreGUI extends JFrame
	{
		FunctionalGUI gui;
		JRootPane root;
		JPanel pFunds;
		JPanel pUpgrades;
		JPanel pButtons;
		JLabel label;
		JTextField qnty[];
		JTextField message;
		
		public StoreGUI(JRootPane r)
		{
			root = r;
			
			setSize(600,300);
			setUndecorated(true);
			this.setLocation((screenX-getWidth())/2, (screenY-getHeight())/2);
			getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
			
			pFunds = new JPanel();
			pFunds.add(new JLabel("Current Funds: $" + Game.funds), JLabel.CENTER);
			add(pFunds);
			
			pUpgrades = new JPanel();
			pUpgrades.setLayout(new GridLayout(0,3));
			
			qnty = new JTextField[6];
			for(int i = 0; i < Game.upgrademanager.upgrades.size(); i++ )
			{
				label = new JLabel(UpgradeManager.upgrade_name[i], JLabel.CENTER);
				pUpgrades.add(label);
				label = new JLabel("Cost: $" + UpgradeManager.upgrade_cost[i], JLabel.CENTER);
				pUpgrades.add(label);
				qnty[i] = new JTextField(4);
				qnty[i].setText("0");
				qnty[i].setEnabled(false);
				pUpgrades.add(qnty[i]);
			}
			
			add(pUpgrades);
			
			pButtons = new JPanel();
			pButtons.setLayout(new FlowLayout());
			JButton bPurchase = new JButton("Purchase");
			bPurchase.addActionListener(new PurchaseButtonHandler());
			pButtons.add(bPurchase);
			JButton bLeave = new JButton("Cancel");
			bLeave.addActionListener(new CancelButtonHandler());
			pButtons.add(bLeave);
			
			add(pButtons);
			
			checkFunds();
			
			pack();
			setVisible(true);
		}
		
		public void checkFunds()
		{
			for(int i = 0; i < UpgradeManager.upgrade_cost.length; i++)
			{
				if(Game.funds > UpgradeManager.upgrade_cost[i])
				{
					qnty[i].setEnabled(true);
				}
			}
		}
		
		
		private class PurchaseButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				int cost = 0;
				//check total cost of selected items
				for(int i = 0; i < qnty.length; i++)
				{
					cost = cost + UpgradeManager.upgrade_cost[i]*Integer.parseInt(qnty[i].getText());
				}
				if(cost >= Game.funds)
				{
					JOptionPane.showMessageDialog(null, "INSUFFICIENT FUNDS!");
					dispose();
					new StoreGUI(root);
				}
				else 
				{
					Game.funds = Game.funds - cost;
					for(int i = 0; i < qnty.length; i++)
					{
						UpgradeManager.upgrade_count[i] = UpgradeManager.upgrade_count[i] + Integer.parseInt(qnty[i].getText());
						bUpgrades[i].setText(UpgradeManager.upgrade_name[i] + ": " + UpgradeManager.upgrade_count[i]);
					}		
					dispose();
					root.getGlassPane().setVisible(false);
					if(bPause.getText().equals("PAUSE"))
					{
						Game.resume();
					}
				}
			}
		}
		
		private class CancelButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				
				dispose();
				root.getGlassPane().setVisible(false);
				if(bPause.getText().equals("PAUSE"))
				{
					Game.resume();
				}
			}
		}
	}
	
	public void createPatients(String difficulty)
	{
	
		JPanel pRoom[] = new JPanel[Game.maxPatients];
		patient = new JButton[Game.maxPatients];
		patientHealth = new JProgressBar[Game.maxPatients];
		
		background.setLayout(null);
		
		for(int i = 0; i < Game.maxPatients; i++)
		{
			pRoom[i] = new JPanel();
			pRoom[i].setOpaque(false);
			pRoom[i].setLayout(new BoxLayout(pRoom[i],BoxLayout.Y_AXIS));
			
			DrawJPanel pIconPatient = new DrawJPanel();
			
			patient[i] = new JButton();
/*			patient[i].setMinimumSize(new Dimension(patient_image.getIconWidth(),patient_image.getIconHeight()));
			patient[i].setPreferredSize(new Dimension(patient_image.getIconWidth(),patient_image.getIconHeight()));
			patient[i].setMaximumSize(new Dimension(patient_image.getIconWidth(),patient_image.getIconHeight()));
*/	//		patient[i].setIcon(patient_image);
			patient[i].addActionListener(new PatientButtonHandler());
			pRoom[i].add(patient[i]);
						
			patientHealth[i] = new JProgressBar();
			pRoom[i].add(patientHealth[i]);
			
			//DEBUGGING
			System.out.println("Room " + i + " size: " + pRoom[i].getWidth()+ ", " + pRoom[i].getHeight());
			
			if(i%2==0)	//if even index
			{
				pRoom[i].setBounds(25, screenY/4*(i/2)+50, panelwidth, (screenY-50)/4);
				System.out.println("Adding patient at " + pRoom[i].getX() + ", " + pRoom[i].getY());
			}
			else
			{
				pRoom[i].setBounds(screenX-3*panelwidth-25, screenY/4*((i-1)/2)+50, panelwidth, (screenY-50)/4);
				System.out.println("Adding patient at " + pRoom[i].getX() + ", " + pRoom[i].getY());
			}
					
			//DEBUGGING
			System.out.println("Room " + i + " size: " + pRoom[i].getWidth()+ ", " + pRoom[i].getHeight());
			
			background.add(pRoom[i]);
		}
	}
	

	private class PatientButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			for(int i = 0; i < patient.length; i++)
			{
				if(event.getSource() == patient[i])
				{
					ArrayList<String> symptoms = new ArrayList<String>(); 
					
					cur_symptoms.setText("");
					//cur_patient.setText(patient[i].getText().substring(0,patient[i].getText().indexOf(":")));
					cur_patient.setText("Patient " + (i+1)); 
					
					//get current symptoms from chart
					symptoms = manager.getPatientSymptoms(i);
					
					for(int i2 = 0; i2 < symptoms.size(); i2++)
					{
						cur_symptoms.append("- " + symptoms.get(i2) + "\n" );
					}	
				}
			}		
		}
	}
	public void updatePatient(int i, String status)
	{
		patient[i].setText(status);
	}
	
	private class StoreButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			Game.pause();
			
			getRootPane().setGlassPane(new JComponent() {
			    public void paintComponent(Graphics g) {
			        g.setColor(new Color(0, 0, 0, 200));
			        g.fillRect(0, 0, getWidth(), getHeight());
			        super.paintComponent(g);
			    }
			});
			getRootPane().getGlassPane().setVisible(true);
			
			new StoreGUI(getRootPane());
		}
	}
	
	private class DiagnoseButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			int selectedInd = poss_diag.getSelectedIndex();
			
			String patient = cur_patient.getText().substring(cur_patient.getText().indexOf(" ")+1,cur_patient.getText().indexOf(" ")+2);
			String diag_command = "Diagnose patient " + patient + " with " + Game.diseases[selectedInd].getName() + "?";
			
			int r = JOptionPane.showConfirmDialog(null, diag_command, "Diagnose", JOptionPane.YES_NO_OPTION);
			if(r == 0)	//0 indicates yes was selected
			{
				EventGenerator.generateDiagnoseEvent(Integer.parseInt(patient)-1, selectedInd);
			}
	
			poss_diag.clearSelection();
		}
	}
	
	private class UpgradesButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			for(int i = 0; i < bUpgrades.length ; i++)
			{
				if(event.getSource() == bUpgrades[i])
				{
					for(int i2 = 0; i2 <UpgradeManager.upgrade_name.length; i2++)
					{
						if(bUpgrades[i].getText().substring(0,bUpgrades[i].getText().indexOf(":")).equals(UpgradeManager.upgrade_name[i2]))
						{
							UpgradeManager.use_upgrade[i2] = true;
							UpgradeManager.upgrade_count[i2]--;
							bUpgrades[i].setText(UpgradeManager.upgrade_name[i2] + ": " + UpgradeManager.upgrade_count[i2]);
							if(UpgradeManager.upgrade_count[i2] == 0)
							{
								bUpgrades[i].setEnabled(false);
							}
						}
					}
				}
			}
		}
	}
	
	private class PauseButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			if(bPause.getText().equals("PAUSE"))
			{
				Game.pause();
				bPause.setEnabled(true);
				bPause.setText("RESUME");
			}
			else
			{
				Game.resume();
				bPause.setText("PAUSE");
			}
		}
	}
	
	private class HelpButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			Game.pause();
			new MainMenu.Tutorial();
		}
	}
	
	private class QuitButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			Game.run = false;
			//TODO: open mainmenu and setVisible(false);
			dispose();
		}
	}
}
