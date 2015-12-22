import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.io.*;
import java.util.*;


public class Tutorial extends JFrame
{

	private static final long serialVersionUID = 2L;

	public Tutorial()
	{
		JButton bMainMenu;
		
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
}