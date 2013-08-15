import java.awt.Container;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BoxLayoutTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(Locale.US.getLanguage());
		JFrame frame = new BoxLayoutTest();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public BoxLayoutTest(){
		setTitle("BoxLayoutTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		for(int j = 0; j < NUMBER; j++){
			JButton button = new JButton("Button " + j);
			panel.add(button);
		}
		
		add(panel);
	}

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
	private static final int NUMBER = 10;
}
