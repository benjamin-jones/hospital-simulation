import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


public class HighScores extends JFrame
{

	private static final long serialVersionUID = 3L;

	public HighScores()
	{
		JButton bMainMenu;
		
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
		JTextArea eLabel1 = new JTextArea("Rank");
		eLabel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		pLabel.add(eLabel1);
		JTextArea eLabel2 = new JTextArea("User Name");
		eLabel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		pLabel.add(eLabel2);
		JTextArea eLabel3 = new JTextArea("Score");
		eLabel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		pLabel.add(eLabel3);
		pMenu.add(pLabel);
		
        try {
        	FileInputStream input = new FileInputStream("data/HighScores.txt");
        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        	StringWriter writer = new StringWriter();
        	PrintWriter out = new PrintWriter(writer);
        	String[] line = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
        	int i = 0;
        	while ((line[i] = reader.readLine()) != null)  {
        	  out.println(line[i]);
        	  i = i+1;
        	}
        	out.flush();
        	//eText.setText(writer.toString());

		
			JPanel pScore1 = new JPanel();
			pScore1.setLayout(new GridBagLayout());
			JTextArea eRank1 = new JTextArea("1.");
			eRank1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore1.add(eRank1);
			JTextArea eUser1 = new JTextArea(line[0]);
			eUser1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore1.add(eUser1);
			JTextArea eScore1 = new JTextArea(line[1]);
			eScore1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore1.add(eScore1);
			pMenu.add(pScore1);
			
			JPanel pScore2 = new JPanel();
			pScore2.setLayout(new GridBagLayout());
			JTextArea eRank2 = new JTextArea("2.");
			eRank2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore2.add(eRank2);
			JTextArea eUser2 = new JTextArea(line[2]);
			eUser2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore2.add(eUser2);
			JTextArea eScore2 = new JTextArea(line[3]);
			eScore2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore2.add(eScore2);
			pMenu.add(pScore2);
			
			JPanel pScore3 = new JPanel();
			pScore3.setLayout(new GridBagLayout());
			JTextArea eRank3 = new JTextArea("3.");
			eRank3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore3.add(eRank3);
			JTextArea eUser3 = new JTextArea(line[4]);
			eUser3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore3.add(eUser3);
			JTextArea eScore3 = new JTextArea(line[5]);
			eScore3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore3.add(eScore3);
			pMenu.add(pScore3);
			
			JPanel pScore4 = new JPanel();
			pScore4.setLayout(new GridBagLayout());
			JTextArea eRank4 = new JTextArea("4.");
			eRank4.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore4.add(eRank4);
			JTextArea eUser4 = new JTextArea(line[6]);
			eUser4.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore4.add(eUser4);
			JTextArea eScore4 = new JTextArea(line[7]);
			eScore4.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore4.add(eScore4);
			pMenu.add(pScore4);
			
			JPanel pScore5 = new JPanel();
			pScore5.setLayout(new GridBagLayout());
			JTextArea eRank5 = new JTextArea("5.");
			eRank5.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore5.add(eRank5);
			JTextArea eUser5 = new JTextArea(line[8]);
			eUser5.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore5.add(eUser5);
			JTextArea eScore5 = new JTextArea(line[9]);
			eScore5.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore5.add(eScore5);
			pMenu.add(pScore5);
			
			JPanel pScore6 = new JPanel();
			pScore6.setLayout(new GridBagLayout());
			JTextArea eRank6 = new JTextArea("6.");
			eRank6.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore6.add(eRank6);
			JTextArea eUser6 = new JTextArea(line[10]);
			eUser6.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore6.add(eUser6);
			JTextArea eScore6 = new JTextArea(line[11]);
			eScore6.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore6.add(eScore6);
			pMenu.add(pScore6);
			
			JPanel pScore7 = new JPanel();
			pScore7.setLayout(new GridBagLayout());
			JTextArea eRank7 = new JTextArea("7.");
			eRank7.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore7.add(eRank7);
			JTextArea eUser7 = new JTextArea(line[12]);
			eUser7.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore7.add(eUser7);
			JTextArea eScore7 = new JTextArea(line[13]);
			eScore7.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore7.add(eScore7);
			pMenu.add(pScore7);
			
			JPanel pScore8 = new JPanel();
			pScore8.setLayout(new GridBagLayout());
			JTextArea eRank8 = new JTextArea("8.");
			eRank8.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore8.add(eRank8);
			JTextArea eUser8 = new JTextArea(line[14]);
			eUser8.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore8.add(eUser8);
			JTextArea eScore8 = new JTextArea(line[15]);
			eScore8.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore8.add(eScore8);
			pMenu.add(pScore8);
			
			JPanel pScore9 = new JPanel();
			pScore9.setLayout(new GridBagLayout());
			JTextArea eRank9 = new JTextArea("9.");
			eRank9.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore9.add(eRank9);
			JTextArea eUser9 = new JTextArea(line[16]);
			eUser9.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore9.add(eUser9);
			JTextArea eScore9 = new JTextArea(line[17]);
			eScore9.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore9.add(eScore9);
			pMenu.add(pScore9);
			
			JPanel pScore10 = new JPanel();
			pScore10.setLayout(new GridBagLayout());
			JTextArea eRank10 = new JTextArea("10.");
			eRank10.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore10.add(eRank10);
			JTextArea eUser10 = new JTextArea(line[18]);
			eUser10.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore10.add(eUser10);
			JTextArea eScore10 = new JTextArea(line[19]);
			eScore10.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore10.add(eScore10);
			pMenu.add(pScore10);
			
			JPanel pScore11 = new JPanel();
			pScore11.setLayout(new GridBagLayout());
			JTextArea eRank11 = new JTextArea("11.");
			eRank11.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore11.add(eRank11);
			JTextArea eUser11 = new JTextArea(line[20]);
			eUser11.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore11.add(eUser11);
			JTextArea eScore11 = new JTextArea(line[21]);
			eScore11.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore11.add(eScore11);
			pMenu.add(pScore11);
			
			JPanel pScore12 = new JPanel();
			pScore12.setLayout(new GridBagLayout());
			JTextArea eRank12 = new JTextArea("12.");
			eRank12.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore12.add(eRank12);
			JTextArea eUser12 = new JTextArea(line[22]);
			eUser12.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore12.add(eUser12);
			JTextArea eScore12 = new JTextArea(line[23]);
			eScore12.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore12.add(eScore12);
			pMenu.add(pScore12);
			
			JPanel pScore13 = new JPanel();
			pScore13.setLayout(new GridBagLayout());
			JTextArea eRank13 = new JTextArea("13.");
			eRank13.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore13.add(eRank13);
			JTextArea eUser13 = new JTextArea(line[24]);
			eUser13.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore13.add(eUser13);
			JTextArea eScore13 = new JTextArea(line[25]);
			eScore13.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore13.add(eScore13);
			pMenu.add(pScore13);
			
			JPanel pScore14 = new JPanel();
			pScore14.setLayout(new GridBagLayout());
			JTextArea eRank14 = new JTextArea("14.");
			eRank14.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore14.add(eRank14);
			JTextArea eUser14 = new JTextArea(line[26]);
			eUser14.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore14.add(eUser14);
			JTextArea eScore14 = new JTextArea(line[27]);
			eScore14.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore14.add(eScore14);
			pMenu.add(pScore14);
			
			JPanel pScore15 = new JPanel();
			pScore15.setLayout(new GridBagLayout());
			JTextArea eRank15 = new JTextArea("15.");
			eRank15.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore15.add(eRank15);
			JTextArea eUser15 = new JTextArea(line[28]);
			eUser15.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore15.add(eUser15);
			JTextArea eScore15 = new JTextArea(line[29]);
			eScore15.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			pScore15.add(eScore15);
			pMenu.add(pScore15);
			
			add(pMenu,BorderLayout.NORTH);
			setVisible(true);
		
        } catch (IOException e) {
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