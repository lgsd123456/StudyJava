import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SplashScreen;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.TransferHandler;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.StyleContext.SmallAttributeSet;
import javax.swing.tree.DefaultMutableTreeNode;


public class DragDropTest extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplashScreenTest.init1();
		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
//              JFrame frame = new DragDropTest();
//              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//              frame.setVisible(true);
        	   SplashScreenTest.init2();
           }
        });
	}
	
	public DragDropTest(){
		setTitle("DragDropTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		JList list = SampleComponents.list();
		tabbedPane.addTab("List", list);
		JTable table = SampleComponents.table();
		tabbedPane.addTab("Table", table);
		JTree tree = SampleComponents.tree();
		tabbedPane.addTab("Tree", tree);
		JFileChooser fileChooser = new JFileChooser();
		tabbedPane.addTab("File Chooser", fileChooser);
		JColorChooser colorChooser = new JColorChooser();
		tabbedPane.addTab("Color Chooser", colorChooser);
		
		final JTextArea textArea = new JTextArea(4, 40);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(), "Drag text here"));
		
		JTextField textField = new JTextField("Drag color here");
		textField.setTransferHandler(new TransferHandler("background"));
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				textArea.setText("");
			}
		});
		
		tree.setDragEnabled(true);
		table.setDragEnabled(true);
		list.setDragEnabled(true);
		fileChooser.setDragEnabled(true);
		colorChooser.setDragEnabled(true);
		textField.setDragEnabled(true);
		
		add(tabbedPane, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		pack();
	}

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}

class SplashScreenTest{
	private static void drawOnSplash(int percent){
		Rectangle bounds = splash.getBounds();
		Graphics2D g1 = splash.createGraphics();
		int height = 20;
		int x = 2;
		int y = bounds.height - height - 2;
		int width = bounds.width - 4;
		Color brightPurle = new Color(76, 36, 121);
		g1.setColor(brightPurle);
		g1.fillRect(x, y, width * percent / 100, height);
		splash.update();
	}
	
	public static void init1(){
		splash = SplashScreen.getSplashScreen();
		if(splash == null){
			System.err.println("Did you specify a splash image with -splash or in the manifest?");
			System.exit(1);
		}
		
		try {
			for(int i = 0; i <= 100; i++){
				drawOnSplash(i);
				Thread.sleep(100);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void init2(){
		final Image img = Toolkit.getDefaultToolkit().getImage(splash.getImageURL());
		final JFrame splashFrame = new JFrame();
		splashFrame.setUndecorated(true);
		
		final JPanel splashPanel = new JPanel(){
			public void paintComponent(java.awt.Graphics g) {
				g.drawImage(img, 0, 0, null);
			};
		};
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		splashPanel.setLayout(new BorderLayout());
		splashPanel.add(progressBar, BorderLayout.SOUTH);
		
		splashFrame.add(splashPanel);
		splashFrame.setBounds(splash.getBounds());
		splashFrame.setVisible(true);
		
		new SwingWorker<Void, Integer>() {
			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				try {
					for(int i = 0; i <= 100; i++){
						publish(i);
						Thread.sleep(100);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}
			
			protected void process(java.util.List<Integer> chunks) {
				for(Integer chunk : chunks){
					progressBar.setString("Loading module " + chunk);
					progressBar.setValue(chunk);
					splashPanel.repaint();
				}
			};
			
			protected void done() {
				splashFrame.setVisible(false);
				
				JFrame frame = new DragDropTest();
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
			};
		}.execute();
	}
	
	private static SplashScreen splash;
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}

class SampleComponents
{
   public static JTree tree()
   {
      DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
      DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
      root.add(country);
      DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
      country.add(state);
      DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
      state.add(city);
      city = new DefaultMutableTreeNode("Cupertino");
      state.add(city);
      state = new DefaultMutableTreeNode("Michigan");
      country.add(state);
      city = new DefaultMutableTreeNode("Ann Arbor");
      state.add(city);
      country = new DefaultMutableTreeNode("Germany");
      root.add(country);
      state = new DefaultMutableTreeNode("Schleswig-Holstein");
      country.add(state);
      city = new DefaultMutableTreeNode("Kiel");
      state.add(city);
      return new JTree(root);
   }

   public static JList list()
   {
      String[] words = { "quick", "brown", "hungry", "wild", "silent", "huge", "private",
            "abstract", "static", "final" };

      DefaultListModel model = new DefaultListModel();
      for (String word : words)
         model.addElement(word);
      return new JList(model);
   }

   public static JTable table()
   {
      Object[][] cells = { { "Mercury", 2440.0, 0, false, Color.YELLOW },
            { "Venus", 6052.0, 0, false, Color.YELLOW },
            { "Earth", 6378.0, 1, false, Color.BLUE }, { "Mars", 3397.0, 2, false, Color.RED },
            { "Jupiter", 71492.0, 16, true, Color.ORANGE },
            { "Saturn", 60268.0, 18, true, Color.ORANGE },
            { "Uranus", 25559.0, 17, true, Color.BLUE },
            { "Neptune", 24766.0, 8, true, Color.BLUE },
            { "Pluto", 1137.0, 1, false, Color.BLACK } };

      String[] columnNames = { "Planet", "Radius", "Moons", "Gaseous", "Color" };
      return new JTable(cells, columnNames);
   }
}

