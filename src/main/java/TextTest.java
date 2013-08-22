import java.awt.Color;
import java.awt.EventQueue;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.omg.CORBA.PRIVATE_MEMBER;


public class TextTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new TextTest();
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });
	}

	public TextTest(){
		setTitle("TextTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		DataDisplayPanel panel = new DataDisplayPanel();
		add(panel);
		pack();
	}
	
	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}

class DataDisplayPanel extends JPanel{
	public DataDisplayPanel(){
		JLabel redlLabel = new JLabel("Red: ");
		JLabel greenLabel = new JLabel("Green: ");
		JLabel blueLabel = new JLabel("Blue: ");
		redField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		greenfField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		blueField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	
		DocumentListener listener = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				setBackColor();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				setBackColor();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		redField.setColumns(3);
		redField.setValue(new Integer(255));
		greenfField.setColumns(3);
		greenfField.setValue(new Integer(255));
		blueField.setColumns(3);
		blueField.setValue(new Integer(255));
		setBackColor();
		redField.getDocument().addDocumentListener(listener);
		greenfField.getDocument().addDocumentListener(listener);
		blueField.getDocument().addDocumentListener(listener);
		add(redlLabel);
		add(redField);
		add(greenLabel);
		add(greenfField);
		add(blueLabel);
		add(blueField);
	}
	
	private void setBackColor(){
//		int r = Integer.parseInt(redField.getText().trim());
//		int g = Integer.parseInt(greenfField.getText().trim());
//		int b = Integer.parseInt(blueField.getText().trim());
		
		int r = ((Number)redField.getValue()).intValue();
		int g = ((Number)greenfField.getValue()).intValue();
		int b = ((Number)blueField.getValue()).intValue();
		Color color = new Color(r, g, b);
		setBackground(color);
	}
	
	private JFormattedTextField redField;
	private JFormattedTextField greenfField;
	private JFormattedTextField blueField;
}
