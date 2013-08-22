import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class NumberFormatTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new NumberFormatTest();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public NumberFormatTest(){
		setTitle("NumberFormatTest");
		
		JPanel panel = new displayPanel();
		add(panel);
		pack();
	}	
}


class displayPanel extends JPanel{
	public displayPanel(){
		setLayout(new GridBagLayout());
		data = 12345.67;
		
		ButtonGroup group = new ButtonGroup();
		numberButton = new JRadioButton("Number");
		numberButton.setSelected(true);
		numberButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Locale selected = map.get(localeBox.getSelectedItem());
				NumberFormat numberFormat = NumberFormat.getNumberInstance(selected);
				try {
					textField.setText(numberFormat.format(data));
				} catch (Exception e2) {
					// TODO: handle exception
				}	
				//textField.setText(numberFormat.format(textField.getText().trim()));
			}
		});
		group.add(numberButton);
		//numberButton.setSelected(true);
		currencyButton = new JRadioButton("Currency");
		currencyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Locale selected = map.get(localeBox.getSelectedItem());
				NumberFormat numberFormat = NumberFormat.getCurrencyInstance(selected);
				textField.setText(numberFormat.format(data));
			}
		});
		group.add(currencyButton);
		percentButton = new JRadioButton("Percent");
		percentButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Locale selected = map.get(localeBox.getSelectedItem());
				NumberFormat numberFormat = NumberFormat.getPercentInstance(selected);
				textField.setText(numberFormat.format(data));
			}
		});
		group.add(percentButton);
		
		parseButton = new JButton("Parse");
		parseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String dataParsed = textField.getText().trim();
				data = Double.parseDouble(dataParsed);
				parseData();
			}
		});
		textField = new JTextField();
		//textField.setText("12345.67");
		
		JLabel label = new JLabel("Locale:");
		localeBox = new JComboBox();
		localeBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parseData();
			}
		});
		Locale[] allLocales = NumberFormat.getAvailableLocales();
		map = new HashMap();
		for(Locale l1 : allLocales){
			String disString = l1.getDisplayName();
			map.put(disString, l1);
			localeBox.addItem(disString);
			if(l1.getCountry().equals("CN"))
				localeBox.setSelectedItem(disString);
		}
		
		add(label, new GBC(0, 0, 1, 1).setAnchor(GBC.EAST));
		add(localeBox, new GBC(1, 0, 2, 1).setAnchor(GBC.WEST).setInsets(3));
		add(numberButton, new GBC(1, 1, 1, 1).setAnchor(GBC.EAST).setInsets(3));
		add(currencyButton, new GBC(2, 1, 1, 1));
		add(percentButton, new GBC(3, 1, 1, 1));
		add(parseButton, new GBC(0, 2, 1, 1).setAnchor(GBC.EAST));
		add(textField, new GBC(1, 2, 3, 1).setAnchor(GBC.EAST).setFill(GBC.HORIZONTAL).setInsets(3));
	}
	
	private void parseData(){
		
		Locale locale = map.get((String)localeBox.getSelectedItem());
		NumberFormat numberFormat = null;
		if(numberButton.isSelected())
			numberFormat = NumberFormat.getNumberInstance(locale);
		else if(currencyButton.isEnabled())
			numberFormat = NumberFormat.getCurrencyInstance(locale);
		else if(currencyButton.isEnabled())
			numberFormat = NumberFormat.getPercentInstance(locale);
		else 
			return;
			
		textField.setText(numberFormat.format(data));
	}
	
	private JComboBox localeBox;
	private JRadioButton numberButton;
	private JRadioButton currencyButton;
	private JRadioButton percentButton;
	private JButton parseButton;
	private JTextField textField;
	private HashMap<String, Locale> map;
	private double data;
}
