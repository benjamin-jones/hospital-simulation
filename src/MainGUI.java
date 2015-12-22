//TODO: Delete because it was deprecated
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.GroupLayout.Alignment;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
   
public class MainGUI extends JFrame
{  
	Game manager;
	
	ImageIcon icu_background;
	ImageIcon hospital_icon;
	
	double xScale = 1.0;
	double yScale = 1.0;
	int backgroundX = 0;
	int backgroundY = 0;
	int iconX = 0;
	int iconY = 0;
	
	JList symptoms_list;
	JPanel poss_diag;
	JTextField score_disp;
	JTextField money_disp;
	JList upgrade_list;
	JTextField time_disp;
	JButton diagnose;
	JButton store;
	JButton pause;
	JButton main_menu; 
	JButton quit;
	
	JPanel pChart = new JPanel();
	JPanel pICU = new JPanel();
	JPanel pStatus = new JPanel();
	JPanel pStoreBar = new JPanel();
	
	public MainGUI(Game man)  	//difficulty determines number of patients
	{  
		manager = man;
		
		//set gui size to full screen
		Toolkit tool = Toolkit.getDefaultToolkit();
		int xSize = ((int) tool.getScreenSize().getWidth());
//		int ySize = ((int) tool.getScreenSize().getHeight());
		int ySize = 1080;	//for work on my dual monitor setup
		int topbarsize = 50;
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		try
		{
			//get background image
			File background_raw = new File("src/icu_background.jpg");
			FileInputStream fin1 = new FileInputStream(background_raw);
			byte fileContent1[] = new byte[(int) background_raw.length()];
			fin1.read(fileContent1);
			icu_background = new ImageIcon( fileContent1 );
			
			//get hospital icon image
			File icon_raw = new File("src/hospital_icon.jpg");
			FileInputStream fin2 = new FileInputStream(icon_raw);
			byte fileContent2[] = new byte[(int) icon_raw.length()];
			fin2.read(fileContent2);
			hospital_icon = new ImageIcon( fileContent2 );
			
			//get patient sprite
			
		}
		catch (FileNotFoundException e) { System.out.println("File not found" + e); }
		catch (IOException ioe) { System.out.println("Exception while reading file" + ioe); }
		
		int panelwidth = xSize/5;
		
		//scale images to fit full screen gui
		double background_height = (double) ySize - topbarsize;
		yScale = background_height/((double)icu_background.getIconHeight());
		double background_width = 3*panelwidth;
		xScale = background_width/((double)icu_background.getIconWidth());
		backgroundX = (int) (icu_background.getIconWidth()*xScale);
		backgroundY = (int) (icu_background.getIconHeight()*yScale);
		double icon_height = topbarsize;
//		yScale = icon_height/((double)hospital_icon.getIconHeight());
//		xScale = yScale;
//		iconX = (int) (hospital_icon.getIconWidth()*xScale);
//		iconY = (int) (hospital_icon.getIconHeight()*yScale);
				
		
		JPanel background =  new DrawJPanel(icu_background, 0, 0, backgroundX, backgroundY);
		background.setBounds(panelwidth+1, 0, backgroundX, backgroundY);
		getContentPane().add(background);
		//DEBUGGING
		System.out.println("Added background image of size: " + icu_background.getIconWidth()*xScale + ", " + icu_background.getIconHeight()*yScale);
		
		//chart panel
//		pChart.setMinimumSize(new Dimension(panelwidth, ySize));
//		pChart.setPreferredSize(new Dimension(panelwidth, ySize));
//		pChart.setSize(panelwidth, ySize);
		pChart.setBounds(0, 0, panelwidth, ySize);
//		pChart.setLayout(new BoxLayout(pChart, BoxLayout.Y_AXIS));
		pChart.setBackground(Color.RED);
		pChart.add(new JLabel("CHART"));
		pChart.add(symptoms_list = new JList());
		pChart.add(poss_diag = new JPanel());
		diagnose = new JButton("DIAGNOSE");
		diagnose.setBounds(0, 0, panelwidth, 50);
		pChart.add(diagnose);
		//TODO: button handler
//		add(pChart);
		//DEBUGGING
		System.out.println("Added left panel of size: " + pChart.getWidth() + ", " + pChart.getHeight());
		
		//status panel
//		pStatus.setMinimumSize(new Dimension(panelwidth, ySize));
//		pStatus.setPreferredSize(new Dimension(panelwidth, ySize));
//		pStatus.setSize(panelwidth,ySize);
		pStatus.setBounds(panelwidth+backgroundX, 0, panelwidth, ySize);
		pStatus.setLayout(new BoxLayout(pStatus, BoxLayout.Y_AXIS));
		pStatus.setBackground(Color.RED);

		JPanel pDisplay = new JPanel();
		pDisplay.setLayout(new GridLayout(0,2));
		JLabel lscore = new JLabel("SCORE: ");
		lscore.setHorizontalAlignment(JLabel.RIGHT);
		pDisplay.add(lscore);
		JLabel lmoney = new JLabel("MONEY: ");
		lmoney.setHorizontalAlignment(JLabel.RIGHT);
		pDisplay.add(score_disp = new JTextField());
		pDisplay.add(lmoney);
		pDisplay.add(money_disp = new JTextField());
		JLabel ltime = new JLabel("TIME: ");
		ltime.setHorizontalAlignment(JLabel.RIGHT);
		pDisplay.add(ltime);
		pDisplay.add(time_disp = new JTextField());
		pStatus.add(pDisplay);
		
		pStatus.add(new JLabel("UPGRADES"));
		pStatus.add(upgrade_list = new JList());
		
		
		JButton pause = new JButton("PAUSE");
		//TODO: button handler
		pause.setAlignmentX(CENTER_ALIGNMENT);
		pStatus.add(pause);
		JButton main_menu = new JButton("MAIN MENU");
		main_menu.setAlignmentX(CENTER_ALIGNMENT);
		pStatus.add(main_menu);
		//TODO: button handler
		JButton quit = new JButton("QUIT");
		quit.setAlignmentX(CENTER_ALIGNMENT);
		pStatus.add(quit);
//		add(pStatus);
		//DEBUGGING
		System.out.println("Added right panel of size: " + pChart.getWidth() + ", " + pChart.getHeight());

		setVisible(true);
		pack();
	}
	
	public class DrawJPanel extends JPanel
	{
		ImageIcon image;
		int posx;
		int posy;
		int width;
		int height;
		
		public DrawJPanel(ImageIcon i, int x, int y, int w, int h)
		{
			image = i;
			posx = x;
			posy = y;
			width = w;
			height = h;
		}
		
		public void paintComponent( Graphics g )
		{
			super.paintComponent( g );
			
			//draw image with scaled width and height
			g.drawImage(image.getImage(), posx,	posy, width, height, null);
			
			//DEBUGGING
			System.out.println("Created image: " + image + " with width " + width + " and height " + height);
		}
	}
	
	public void createPatients(String difficulty)
	{
		
	}
	
		
		
/*		//chart panel
		chartp.setBackground(Color.RED);
		chartp.setPreferredSize(new Dimension(x/6,y));
		chartp.add(new JLabel("CHART"));
		String list = "This will be a list of symptoms\npopulated when a patient is selected.";
		symptoms = new JTextArea(list);
		symptoms.setLineWrap(true);
		symptoms.setPreferredSize(new Dimension(x/6,y/3));
		JScrollPane scroll = new JScrollPane(symptoms,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chartp.add(symptoms);
		chartp.add(scroll);
		String diagnoseslist = "This will be a checkbox list of diagnoses";
		diagnoses = new JTextArea(diagnoseslist, 20, 0);
		diagnoses.setLineWrap(true);
		diagnoses.setPreferredSize(new Dimension(x/6,y/2));
//		JScrollPane scroll = new JScrollPane(symptoms,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chartp.add(diagnoses);
		chartp.add(scroll);
		diagnoseb = new JButton("DIAGNOSE");
		chartp.add(diagnoseb);
		add(chartp,BorderLayout.WEST);
		
		//main interface panel; has store button and icu
		JPanel storebar = new JPanel();
		storebar.setLayout(new GridLayout(1,3));
		storebar.add(new JLabel("IMAGE"));
		storeb = new JButton("STORE");
		storebar.add(storeb);
		storebar.add(new JLabel("IMAGE"));
		mainp.add(storebar);
		add(mainp,BorderLayout.CENTER);

		JPanel right = new JPanel();
		right.setBackground(Color.RED);
		right.setPreferredSize(new Dimension(xSize/6,ySize));
		JButton stats = new JButton("STATS");
		stats.setSize(xSize/6,ySize);
		right.add(stats);
		add(right,BorderLayout.EAST);
		
		pack();
	} 
	
	public void setImage()
	{
		//DEBUGGING
//		System.out.println("\nDEBUGGING - ImageWidth: " + icu.getWidth(null)+ "; ImageHeight: " + icu.getHeight(null));
		
		icu_image = createImage(700,700/*icu.getHeight(null),icu.getWidth(null));
		icu_graphics = icu_image.getGraphics();
	}
	
	public void paint(Graphics g)
	{
		if(icu_graphics == null) { return; }
	        icu_graphics.drawImage(icu, 0, 0, this);
       		g.drawImage(icu_image, 0, 0, this);
	}
*/
}
