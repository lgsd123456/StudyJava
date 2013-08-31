import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SplitPaneTest extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplashScreenTest.init1();
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				JFrame frame = new SplitPaneTest();
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame.setVisible(true);
				SplashScreenTest.init2();
			}
		});
	}

	public SplitPaneTest(){
		setTitle("SplitPaneTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//SplitPane
//		textArea = new JTextArea(20,1);
//		label = new JLabel();
//		list = new JList(planets);
//		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		list.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				Planet planet = (Planet)list.getSelectedValue();
//				if(planet == null) return;
//				label.setIcon(planet.getImage());
//				textArea.setText(planet.getDescription());
//			}
//		});
//		JSplitPane innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, label);
//		JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, textArea);
//		innerPane.setContinuousLayout(true);
//		innerPane.setOneTouchExpandable(true);
//		add(outerPane);

//		JTabbedPane tabbedPane = new JTabbedPane();
//		for(Planet planet : planets){
//			tabbedPane.addTab(planet.toString(), new JLabel(planet.getImage()));
//		}
//		add(tabbedPane);
		
		desktop = new JDesktopPane();
		add(desktop);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem openItem = new JMenuItem("New");
		fileMenu.add(openItem);
		
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int x = 0;
				int y = 0;
				int frameDistance = 0;
				for(Planet planet : planets){
					JInternalFrame iFrame = new JInternalFrame(planet.toString(), true, true, true, true);
					iFrame.add(new JLabel(planet.getImage()));
					iFrame.reshape(x, y, 100, 50);
					iFrame.setVisible(true);
					desktop.add(iFrame);
					frameDistance = iFrame.getHeight() - iFrame.getContentPane().getHeight();
					x += frameDistance;
					y += frameDistance;
					if (x + 100 > desktop.getWidth())
						   x = 0;
					if (y + 50 > desktop.getHeight())
						   y = 0;
				}
			}
		});
		
		
	}
	
//	private JTextArea textArea;
//	private JList list;
//	private JLabel label;
	private JDesktopPane desktop;
	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 400;
	private Planet[] planets = { new Planet("Mercury", 2440, 0), new Planet("Venus", 6052, 0),
			new Planet("Earth", 6378, 1), new Planet("Mars", 3397, 2),
			new Planet("Jupiter", 71492, 16), new Planet("Saturn", 60268, 18),
			new Planet("Uranus", 25559, 17), new Planet("Neptune", 24766, 8),
			new Planet("Pluto", 1137, 1), };

}


class Planet{
	
	public Planet(String n, double r, int m){
		name = n;
		radius = r;
		moons = m;
		image = new ImageIcon(name + ".gif");
	}
	
	public String toString(){
		return name;
	}
	
	public String getDescription(){
		return "Radius: " + radius + "\nMoons: " + moons + "\n";
	}
	
	public ImageIcon getImage(){
		return image;
	}
	
	private String name;
	private double radius;
	private int moons;
	private ImageIcon image;
}
