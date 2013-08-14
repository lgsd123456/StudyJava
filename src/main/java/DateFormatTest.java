import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.PUBLIC_MEMBER;


public class DateFormatTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
//		Date nowDate = new Date();
//		System.out.println(dateFormat.format(nowDate));
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new DateFormatFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
//		System.out.println(Style.FULL.ordinal());
//		System.out.println(Style.values());
	}

}

class DateFormatFrame extends JFrame{
	public DateFormatFrame(){
		setTitle("DateFormatTest");
		
		add(new DateFormatPanel());
		
		pack();
	}
}

class DateFormatPanel extends JPanel{
	public DateFormatPanel(){
		setLayout(new GridBagLayout());
		//GregorianCalendar calendar = new GregorianCalendar(2008, 7, 8, 12, 12, 12);
		calDate = new Date();
		calTime = new Date();
		locale = (Locale[])DateFormat.getAvailableLocales().clone();
		
		JLabel labelLocal = new JLabel("Locale");
		localBox = new JComboBox<String>();
		localBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parseDateTime();
			}
		});
		JLabel labelDateStyle = new JLabel("Date style");
		styleDateBox = new JComboBox<String>();
		JLabel labelTimeStyle = new JLabel("Time style");
		styleTimeBox = new JComboBox<String>();
		JLabel labelDate = new JLabel("Date");
		dateTextField = new JTextField(30);
		dateButton = new JButton("Parse date");
		JLabel labelTime = new JLabel("Time");
		timeTextField = new JTextField(30);
		timeButton = new JButton("Parse time");
		lenientBox = new JCheckBox("Parse lenient");
		lenientBox.setSelected(true);
		
		add(labelLocal, new GBC(0, 0).setAnchor(GBC.EAST));
		add(localBox, new GBC(1, 0, 2, 1).setAnchor(GBC.WEST));
		add(labelDateStyle, new GBC(0, 1).setAnchor(GBC.EAST));
		add(styleDateBox, new GBC(1, 1).setAnchor(GBC.WEST));
		add(labelTimeStyle, new GBC(3, 1).setAnchor(GBC.EAST));
		add(styleTimeBox, new GBC(4, 1).setAnchor(GBC.WEST));
		add(labelDate, new GBC(0, 2).setAnchor(GBC.EAST));
		add(dateTextField,new GBC(1, 2, 3, 1).setAnchor(GBC.WEST));
		add(dateButton, new GBC(4, 2).setAnchor(GBC.WEST));
		add(labelTime, new GBC(0, 3).setAnchor(GBC.EAST));
		add(timeTextField, new GBC(1, 3, 3, 1).setAnchor(GBC.WEST));
		add(labelTime, new GBC(0, 3).setAnchor(GBC.EAST));
		add(timeTextField, new GBC(1, 3, 3, 1).setAnchor(GBC.WEST));
		add(timeButton, new GBC(4, 3).setAnchor(GBC.WEST));
		add(lenientBox, new GBC(0, 4));
		
		
		Arrays.sort(locale, new Comparator<Locale>() {
			public int compare(Locale o1, Locale o2){
				return o1.getDisplayName().compareTo(o2.getDisplayName());
			}
		});
		
		for(Style style : Style.values()){
			styleDateBox.addItem(style.toString());
			if(style.toString().equals("LONG")) styleDateBox.setSelectedItem(style.toString());
			styleTimeBox.addItem(style.toString());
			if(style.toString().equals("DEFAULT")) styleTimeBox.setSelectedItem(style.toString());
		}
		
		for(int i = 0; i < locale.length; i++){
			localBox.addItem(locale[i].getDisplayName());
			if(locale[i].getDisplayName().equals(Locale.CHINA.getDisplayName()))
				localBox.setSelectedIndex(i);
		}	
		
		styleDateBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parseDateTime();
			}
		});
		
		styleTimeBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parseDateTime();
			}
		});
		
		dateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Locale currLocale = locale[localBox.getSelectedIndex()];
				int dateStyle = Style.valueOf(Style.class, (String)styleDateBox.getSelectedItem()).getAbbre();
				DateFormat dateFormat = DateFormat.getDateInstance(dateStyle, currLocale);
				if(lenientBox.isSelected())
					dateFormat.setLenient(false);
				else 
					dateFormat.setLenient(true);
				
				try {
					Date tmpDate = dateFormat.parse(dateTextField.getText().trim());
					calDate = (Date)tmpDate.clone();
					dateTextField.setText(dateFormat.format(calDate));
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		timeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Locale currLocale = locale[localBox.getSelectedIndex()];
				int timeStyle = Style.valueOf(Style.class, (String)styleTimeBox.getSelectedItem()).getAbbre();
				DateFormat dateFormat = DateFormat.getTimeInstance(timeStyle, currLocale);
				if(lenientBox.isSelected())
					dateFormat.setLenient(false);
				else 
					dateFormat.setLenient(true);
				
				try {
					Date tmpDate = dateFormat.parse(timeTextField.getText().trim());
					calTime = (Date)tmpDate.clone();
					timeTextField.setText(dateFormat.format(calTime));
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
	}
	
	private void parseDateTime(){
		Locale currLocale = locale[localBox.getSelectedIndex()];
		int dateStyle = Style.valueOf(Style.class, (String)styleDateBox.getSelectedItem()).getAbbre();
		int timeStyle = Style.valueOf(Style.class, (String)styleTimeBox.getSelectedItem()).getAbbre();
		DateFormat dateFormat = DateFormat.getDateInstance(dateStyle, currLocale);
		dateTextField.setText(dateFormat.format(calDate));
		DateFormat timeFormat = DateFormat.getTimeInstance(timeStyle, currLocale);
		timeTextField.setText(timeFormat.format(calTime));
	}
	
	
	private JComboBox<String> localBox;
	private JComboBox<String> styleDateBox;
	private JComboBox<String> styleTimeBox;
	private JTextField dateTextField;
	private JTextField timeTextField;
	private JCheckBox lenientBox;
	private JButton dateButton;
	private JButton timeButton;
	private Locale[] locale;
	private Date calDate;
	private Date calTime;
}

enum Style {
	FULL(0),LONG(1),DEFAULT(2),MEDIUM(2),SHORT(3);
	
	private Style(int abbre) {this.abbre = abbre;}
	public int getAbbre() {return abbre;}
	private int abbre;
};
