import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;


public class JSpinnerTest extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new JSpinnerTest();
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });
	}

	public JSpinnerTest(){
		setTitle("JSpinnerTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(3, 3));
		JLabel intLabel = new JLabel("Int: ");
		JSpinner intSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 0.5));
		panel.add(intLabel);
		panel.add(intSpinner);
		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.CHINA);
		JSpinner listSpinner = new JSpinner(new SpinnerListModel(fonts){
			@Override
			public Object getNextValue() {
				// TODO Auto-generated method stub
				return super.getPreviousValue();
			}
			
			@Override
			public Object getPreviousValue() {
				// TODO Auto-generated method stub
				return super.getNextValue();
			}
		});
		JLabel fontLabel = new JLabel("Font: ");
		panel.add(fontLabel);
		panel.add(listSpinner);
		
		JLabel dateLabel = new JLabel("Date: ");
		panel.add(dateLabel);
		JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
		String pattern = ((SimpleDateFormat)DateFormat.getDateInstance()).toPattern();
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, pattern));
		panel.add(dateSpinner);
	}
	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}
