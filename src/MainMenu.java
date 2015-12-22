import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


public class MainMenu extends JFrame
{
	Game manager;
	
	static String scoreFile = "data/HighScores.txt";
	
	static JButton bStart;
	static JButton bEasy;
	static JButton bMedium;
	static JButton bHard;
	static JButton bTutorial;
	static JButton bScores;
	static JButton bQuit;
	
	public MainMenu()
	{
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//get full screen size parameters
		Toolkit tool = Toolkit.getDefaultToolkit();
		int xSize = ((int) tool.getScreenSize().getWidth());
		int ySize = ((int) tool.getScreenSize().getHeight());
		
		int panelwidth = xSize/4;
		int panelheight = ySize/2;
		
		int buttonheight = panelheight/7;
		
		setMinimumSize(new Dimension(panelwidth, panelheight));
		setPreferredSize(new Dimension(panelwidth, panelheight));
		setMaximumSize(new Dimension(panelwidth, panelheight));
		
		setLocation((xSize/2)-(panelwidth/2),(ySize/2)-(panelheight/2));
		
		
		JPanel pMenu = new JPanel();
		pMenu.setMinimumSize(new Dimension(panelwidth, panelheight));
		pMenu.setPreferredSize(new Dimension(panelwidth, panelheight));
		pMenu.setMaximumSize(new Dimension(panelwidth, panelheight));
		pMenu.setLayout(new BoxLayout(pMenu, BoxLayout.Y_AXIS));
		pMenu.setBorder(BorderFactory.createLineBorder(Color.red));
		pMenu.setLayout(new GridLayout(7,1));
		pMenu.setBackground(Color.GRAY);
		add(pMenu,BorderLayout.NORTH);

		JLabel lMainImage = new JLabel("Sims E.R.", JLabel.CENTER);
		lMainImage.setMinimumSize(new Dimension(panelwidth, buttonheight*2));
		lMainImage.setPreferredSize(new Dimension(panelwidth, buttonheight*2));
		lMainImage.setMaximumSize(new Dimension(panelwidth, buttonheight*2));
		lMainImage.setFont(new Font("Serif", Font.BOLD, 48));
		lMainImage.setForeground(Color.RED);
		pMenu.add(lMainImage);
		
		JLabel lMenu = new JLabel("MAIN MENU", JLabel.CENTER);
		lMenu.setMinimumSize(new Dimension(panelwidth, buttonheight*2));
		lMenu.setPreferredSize(new Dimension(panelwidth, buttonheight*2));
		lMenu.setMaximumSize(new Dimension(panelwidth, buttonheight*2));
		lMenu.setFont(new Font("Serif", Font.BOLD, 20));
		lMenu.setForeground(Color.ORANGE);
		pMenu.add(lMenu);
		
		JPanel pStart = new JPanel();
		pStart.setMinimumSize(new Dimension(panelwidth, buttonheight));
		pStart.setPreferredSize(new Dimension(panelwidth, buttonheight));
		pStart.setMaximumSize(new Dimension(panelwidth, buttonheight));
		pStart.setLayout(new GridBagLayout());
		pStart.setBackground(Color.WHITE);
		JLabel lImage1 = new JLabel("+", JLabel.CENTER);
		lImage1.setMinimumSize(new Dimension(buttonheight, buttonheight));
		lImage1.setPreferredSize(new Dimension(buttonheight, buttonheight));
		lImage1.setMaximumSize(new Dimension(buttonheight, buttonheight));
		lImage1.setFont(new Font("Serif", Font.BOLD, 75));
		lImage1.setForeground(Color.RED);
		pStart.add(lImage1);
		bStart = new JButton("START");
		bStart.setMinimumSize(new Dimension(panelwidth-(buttonheight*2), buttonheight));
		bStart.setPreferredSize(new Dimension(panelwidth-(buttonheight*2), buttonheight));
		bStart.setMaximumSize(new Dimension(panelwidth-(buttonheight*2), buttonheight));
		bStart.addActionListener(new StartButtonHandler());
		pStart.add(bStart);
		JLabel lImage2 = new JLabel("+", JLabel.CENTER);
		lImage2.setMinimumSize(new Dimension(buttonheight, buttonheight));
		lImage2.setPreferredSize(new Dimension(buttonheight, buttonheight));
		lImage2.setMaximumSize(new Dimension(buttonheight, buttonheight));
		lImage2.setFont(new Font("Serif", Font.BOLD, 75));
		lImage2.setForeground(Color.RED);
		pStart.add(lImage2);
		pMenu.add(pStart);
		
		JPanel pDifficulty = new JPanel();
		pDifficulty.setMinimumSize(new Dimension(panelwidth, buttonheight));
		pDifficulty.setPreferredSize(new Dimension(panelwidth, buttonheight));
		pDifficulty.setMaximumSize(new Dimension(panelwidth, buttonheight));
		pDifficulty.setLayout(new GridBagLayout());
		bEasy = new JButton("EASY");
		bEasy.setEnabled(false);
		bEasy.setMinimumSize(new Dimension(panelwidth/3, buttonheight));
		bEasy.setPreferredSize(new Dimension(panelwidth/3, buttonheight));
		bEasy.setMaximumSize(new Dimension(panelwidth/3, buttonheight));
		bEasy.addActionListener(new EasyButtonHandler());
		pDifficulty.add(bEasy);
		bMedium = new JButton("MEDIUM");
		bMedium.setEnabled(false);
		bMedium.setMinimumSize(new Dimension(panelwidth/3, buttonheight));
		bMedium.setPreferredSize(new Dimension(panelwidth/3, buttonheight));
		bMedium.setMaximumSize(new Dimension(panelwidth/3, buttonheight));
		bMedium.addActionListener(new MediumButtonHandler());
		pDifficulty.add(bMedium);
		bHard = new JButton("HARD");
		bHard.setEnabled(false);
		bHard.setMinimumSize(new Dimension(panelwidth/3, buttonheight));
		bHard.setPreferredSize(new Dimension(panelwidth/3, buttonheight));
		bHard.setMaximumSize(new Dimension(panelwidth/3, buttonheight));
		bHard.addActionListener(new HardButtonHandler());
		pDifficulty.add(bHard);
		pMenu.add(pDifficulty,BorderLayout.WEST);
		
		bTutorial = new JButton("Tutorial");
		bTutorial.setMinimumSize(new Dimension(panelwidth, buttonheight));
		bTutorial.setPreferredSize(new Dimension(panelwidth, buttonheight));
		bTutorial.setMaximumSize(new Dimension(panelwidth, buttonheight));
		bTutorial.addActionListener(new TutorialButtonHandler());
		pMenu.add(bTutorial);
		
		bScores = new JButton("HIGH SCORES");
		bScores.setMinimumSize(new Dimension(panelwidth, buttonheight));
		bScores.setPreferredSize(new Dimension(panelwidth, buttonheight));
		bScores.setMaximumSize(new Dimension(panelwidth, buttonheight));
		bScores.addActionListener(new ScoresButtonHandler());
		pMenu.add(bScores);
		
		bQuit = new JButton("QUIT GAME");
		bQuit.setMinimumSize(new Dimension(panelwidth, buttonheight));
		bQuit.setPreferredSize(new Dimension(panelwidth, buttonheight));
		bQuit.setMaximumSize(new Dimension(panelwidth, buttonheight));
		bQuit.addActionListener(new QuitButtonHandler());
		pMenu.add(bQuit);
		
		add(pMenu,BorderLayout.NORTH);
		setVisible(true);
	}

	private class StartButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			bStart.setEnabled(false);
			bEasy.setEnabled(true);
			bMedium.setEnabled(true);
			bHard.setEnabled(true);
		}
	}	
	
	private class EasyButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			//Add action here
			new Game("easy");
			dispose();
		}
	}	
	
	private class MediumButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			//Add action here
			new Game("medium");
			dispose();
		}
	}	
	
	private class HardButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			//Add action here
			new Game("hard");
			dispose();
		}
	}	
	
	private class TutorialButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			new Tutorial();
			dispose();
		}
	}	
	
	private class ScoresButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			new HighScores();
			dispose();
		}
	}
	
	private class QuitButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			dispose();
		}
	}
	
	public class HighScores extends JFrame
	{
		public HighScores()
		{
			JButton bMainMenu;
			
			ArrayList<String> entry = new ArrayList<String>();
			ArrayList<String> name = new ArrayList<String>();
			ArrayList<String> score = new ArrayList<String>();
			
			setUndecorated(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			
			//get full screen size parameters
			Toolkit tool = Toolkit.getDefaultToolkit();
			int xSize = ((int) tool.getScreenSize().getWidth());
			int ySize = ((int) tool.getScreenSize().getHeight());
			
			int panelwidth = xSize/4;
			int panelheight = ySize/2;
			
			setMinimumSize(new Dimension(panelwidth, panelheight));
			setPreferredSize(new Dimension(panelwidth, panelheight));
			setMaximumSize(new Dimension(panelwidth, panelheight));
			
			setLocation((xSize/2)-(panelwidth/2),(ySize/2)-(panelheight/2));

			JPanel pMenu = new JPanel();
			pMenu.setMinimumSize(new Dimension(panelwidth, panelheight));
			pMenu.setPreferredSize(new Dimension(panelwidth, panelheight));
			pMenu.setMaximumSize(new Dimension(panelwidth, panelheight));
			pMenu.setLayout(new BoxLayout(pMenu, BoxLayout.Y_AXIS));
			pMenu.setBorder(BorderFactory.createLineBorder(Color.red));
			pMenu.setLayout(new GridLayout(17,1));
			add(pMenu,BorderLayout.NORTH);
			
			bMainMenu = new JButton("MAIN MENU");
			bMainMenu.addActionListener(new MainMenuButtonHandler());
			pMenu.add(bMainMenu);
			
			JPanel pLabel = new JPanel();
			pLabel.setLayout(new GridBagLayout());
			JTextArea eLabel1 = new JTextArea(" Rank ");
			eLabel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pLabel.add(eLabel1);
			JTextArea eLabel2 = new JTextArea(" User Name ");
			eLabel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pLabel.add(eLabel2);
			JTextArea eLabel3 = new JTextArea(" Score ");
			eLabel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pLabel.add(eLabel3);
			pMenu.add(pLabel);
			
	        try {
	        	FileInputStream input = new FileInputStream(scoreFile);
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        	StringWriter writer = new StringWriter();
	        	PrintWriter out = new PrintWriter(writer);
	        	String line;
	        	int count = 0;
	        	while ((line = reader.readLine()) != null)  {
	        		entry.add(line);
	        	}
	        	
	        	//need to sort arrays
	        	Comparator comp = Collections.reverseOrder();
	        	Collections.sort(entry, comp); 
	        		        	
	        	for(int i = 0; i<entry.size(); i++)
	        	{
	        		JPanel pScore = new JPanel();
					pScore.setLayout(new GridBagLayout());
					JTextArea eRank = new JTextArea(" " + Integer.toString(i+1) + " ");
					eRank.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					pScore.add(eRank);
					JTextArea eUser = new JTextArea(" " + entry.get(i).substring(entry.get(i).indexOf(":")+1, entry.get(i).indexOf(";")) + " ");
					eUser.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					pScore.add(eUser);
					JTextArea eScore = new JTextArea(" " + entry.get(i).substring(0, entry.get(i).indexOf(":")) + " ");
					eScore.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					pScore.add(eScore);
					pMenu.add(pScore);
	        	}

				add(pMenu,BorderLayout.NORTH);
				setVisible(true);
			
	        } catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
			
		}

		private class MainMenuButtonHandler implements ActionListener
		{
			public void actionPerformed( ActionEvent event )
			{
				dispose();
				new MainMenu();
			}
		}
	}

	public static class Tutorial extends JFrame
	{
		public Tutorial()
		{
			JButton bMainMenu;
			JButton bGame;
			
			setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
			setUndecorated(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			
			//get full screen size parameters
			Toolkit tool = Toolkit.getDefaultToolkit();
			int xSize = ((int) tool.getScreenSize().getWidth());
			int ySize = ((int) tool.getScreenSize().getHeight());

			JPanel pMenu = new JPanel();
			pMenu.setMinimumSize(new Dimension(xSize, ySize));
			pMenu.setPreferredSize(new Dimension(xSize, ySize));
			pMenu.setMaximumSize(new Dimension(xSize, ySize));
			pMenu.setBorder(BorderFactory.createLineBorder(Color.red));
			pMenu.setLayout(new BoxLayout(pMenu, BoxLayout.Y_AXIS));
			
			JPanel pSpacer = new JPanel();
			pSpacer.setLayout(new GridBagLayout());
			pSpacer.setMinimumSize(new Dimension(xSize, ySize/15));
			pSpacer.setPreferredSize(new Dimension(xSize, ySize/15));
			pSpacer.setMaximumSize(new Dimension(xSize, ySize/15));
			JLabel lSpacer1 = new JLabel("", JLabel.CENTER);
			pSpacer.add(lSpacer1);
			bMainMenu = new JButton("MAIN MENU");
			bMainMenu.setMinimumSize(new Dimension(xSize/10, ySize/15));
			bMainMenu.setPreferredSize(new Dimension(xSize/10, ySize/15));
			bMainMenu.setMaximumSize(new Dimension(xSize/10, ySize/15));
			bMainMenu.addActionListener(new MainMenuButtonHandler());
			pSpacer.add(bMainMenu);
			bGame = new JButton("GAME");
			bGame.setMinimumSize(new Dimension(xSize/10, ySize/15));
			bGame.setPreferredSize(new Dimension(xSize/10, ySize/15));
			bGame.setMaximumSize(new Dimension(xSize/10, ySize/15));
			bGame.addActionListener(new GameButtonHandler());
			pSpacer.add(bGame);
			if(Game.pause == true)			//in game
			{
					bGame.setEnabled(true);
					bMainMenu.setEnabled(false);
			}
			else { bGame.setEnabled(false); }
			JLabel lSpacer2 = new JLabel("", JLabel.CENTER);
			pSpacer.add(lSpacer2);
			pMenu.add(pSpacer);		
			
			JTextArea eText = new JTextArea();
			JScrollPane scrollPane = new JScrollPane(eText); 
	        try {
	        	FileInputStream input = new FileInputStream("data/Tutorial.txt");
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        	StringWriter writer = new StringWriter();
	        	PrintWriter out = new PrintWriter(writer);
	        	String line;
	        	while ((line = reader.readLine()) != null)  {
	        	  out.println(line);
	        	}
	        	out.flush();
	        	eText.setText(writer.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        getContentPane().add(scrollPane, BorderLayout.CENTER); 
			eText.setLineWrap(true);
			eText.setWrapStyleWord(true);
			eText.setEditable(false);
			eText.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			
			pMenu.add(eText);
			
			add(pMenu,BorderLayout.NORTH);
			setVisible(true);
			
		}

		private class MainMenuButtonHandler implements ActionListener
		{
			public void actionPerformed( ActionEvent event )
			{
				dispose();
				new MainMenu();
			}
		}
		
		private class GameButtonHandler implements ActionListener
		{
			public void actionPerformed( ActionEvent event )
			{
				dispose();
				Game.resume();
			}
		}
	}
	
	public static void main(String[] args)
	{
		new MainMenu();
	}
}