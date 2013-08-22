import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CollationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				JFrame frame = new CollationFrame();
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame.setVisible(true);
//			}
//		});
		
		//分解形式测试
//		String str = "A\u030angstro\u0308m";
//		System.out.println(str);
//		String str1 = "Ångström";
//		System.out.println(str1);
//		String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
//		System.out.println(normalized);
//		normalized = Normalizer.normalize(str1, Normalizer.Form.NFD);
//		System.out.println(normalized);
		
		//信息格式测试
		String msg = MessageFormat.format("On {2, date, long}, a {0} destroyed {1} houses and caused {3, number, currency} of damage.",
				   "hurricane", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0E8);
		System.out.println(msg);
		
		String pattern = "On {2,date,long}, {0} destroyed " + 
				"{1,choice,0#no houses|1#one house|2#{1} houses} "
				+ "and caused {3,number,currency} of damage.";
		MessageFormat messageFormat = new MessageFormat(pattern);
		
		System.out.println(messageFormat.format(new Object[]{"an earthquake", 1, new GregorianCalendar(1999, 0, 1).getTime(), 10.0E3}));
		System.out.println(messageFormat.format(new Object[]{"an earthquake", 0, new GregorianCalendar(1999, 0, 1).getTime(), 10.0E1}));
		System.out.println(messageFormat.format(new Object[]{"an earthquake", 5, new GregorianCalendar(1999, 0, 1).getTime(), 10.0E5}));
	
		messageFormat.applyPattern("On {0,date,long}, {1} destroyed and caused {2,number,currency} of damage");
		StringBuffer result = new StringBuffer();
		FieldPosition fieldPosition = new FieldPosition(MessageFormat.Field.ARGUMENT);
		fieldPosition.setBeginIndex(15);
		StringBuffer returnedResult = messageFormat.format(new Object[]{new GregorianCalendar(1999, 0, 1).getTime(), "an earthquake", 10.0E1}, result, fieldPosition);
		System.out.println(returnedResult.toString());
	}
}

class CollationFrame extends JFrame{
	public CollationFrame(){
		setTitle("CollationTest");
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		locales = (Locale[])Collator.getAvailableLocales().clone();
		colList = new ArrayList<>();
		
		Arrays.sort(locales, new Comparator<Locale>() {
			private Collator collator = Collator.getInstance(Locale.getDefault());
			
			public int compare(Locale o1, Locale o2){
				return collator.compare(o1.getDisplayName(), o2.getDisplayName());
			}
		});
		
			
		JLabel localLabel = new JLabel("Locale");
		localBox = new JComboBox();
		
		for(Locale ll : locales)
			localBox.addItem(ll.getDisplayName());
		localBox.setSelectedItem(Locale.getDefault().getDisplayName());
		
		JLabel strenglLabel = new JLabel("Strength");
		//strengBox = new JComboBox<String>();
		JLabel decomLabel = new JLabel("Decomposition");
		//decomBox = new JComboBox<String>();
		addButton = new JButton("Add");
		textField = new JTextField(30);
		textArea = new JTextArea(30, 1);
		
		contentPanel.add(localLabel, new GBC(0, 0).setAnchor(GBC.EAST));
		contentPanel.add(localBox, new GBC(1, 0).setAnchor(GBC.WEST));
		contentPanel.add(strenglLabel, new GBC(0, 1).setAnchor(GBC.EAST));
		contentPanel.add(strengBox, new GBC(1, 1).setAnchor(GBC.WEST));
		contentPanel.add(decomLabel, new GBC(0, 2).setAnchor(GBC.EAST));
		contentPanel.add(decomBox, new GBC(1, 2).setAnchor(GBC.WEST));
		contentPanel.add(addButton, new GBC(0, 3).setAnchor(GBC.EAST));
		contentPanel.add(textField, new GBC(1, 3).setAnchor(GBC.WEST));
		contentPanel.add(textArea, new GBC(0, 4, 2, 1).setFill(GBC.BOTH));
		
		colList.add("America");
		colList.add("able");
		colList.add("Zulu");
		colList.add("zebra");
		colList.add("\u00C5ngstr\u00F6m");
		colList.add("A\u030angstro\u0308m");
		colList.add("Angstrom");
		colList.add("Able");
		colList.add("office");
		colList.add("o\uFB03ce");
		colList.add("Java\u2122");
		colList.add("JavaTM");
		
		
		localBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateDisplay();
			}
		});
		
		strengBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateDisplay();
			}
		});
		
		decomBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateDisplay();
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String string = textField.getText().trim();
				colList.add(string);
				updateDisplay();
			}
		});
		
		updateDisplay();
		add(contentPanel);
		pack();
	}
	
	private void updateDisplay(){
		Locale locale = locales[localBox.getSelectedIndex()];
		localBox.setLocale(locale);
		int strength = strengBox.getValue();
		int decomposition = decomBox.getValue();
		collator = Collator.getInstance(locale); 
		collator.setStrength(strength);
		collator.setDecomposition(decomposition);
		Collections.sort(colList, collator);
		for(int i = 0; i < colList.size(); i++){
			if(i == 0){
				textArea.setText(colList.get(i) + "\n");
				continue;
			}
			if(collator.compare(colList.get(i - 1), colList.get(i)) == 0){
				textArea.append("=" + colList.get(i) + "\n");
			}else {
				textArea.append(colList.get(i) + "\n");
			}
		}
	}
	
	private Collator collator;
	private JTextArea textArea;
	private JComboBox localBox;
	private EnumCombo strengBox = new EnumCombo(Collator.class, new String[]{"PRIMARY","SECONDARY","TERTIARY","IDENTICAL"});
	private EnumCombo decomBox = new EnumCombo(Collator.class, new String[]{"NO DECOMPOSITION","CANONICAL DECOMPOSITION","FULL DECOMPOSITION"});
	private JButton addButton;
	private JTextField textField;
	private ArrayList<String> colList;
	private Locale[] locales;
}

class EnumCombo extends JComboBox
{ 
   /**
      Constructs an EnumCombo.
      @param cl a class
      @param labels an array of static field names of cl
   */
   public EnumCombo(Class<?> cl, String[] labels)
   {  
      for (String label : labels)
      {  
         String name = label.toUpperCase().replace(' ', '_');
         int value = 0;
         try
         {  
            java.lang.reflect.Field f = cl.getField(name);
            value = f.getInt(cl);
         }
         catch (Exception e)
         {  
            label = "(" + label + ")";
         }
         table.put(label, value);
         addItem(label);
      }
      setSelectedItem(labels[0]);
   }

   /**
      Returns the value of the field that the user selected.
      @return the static field value
   */
   public int getValue()
   {  
      return table.get(getSelectedItem());
   }

   private Map<String, Integer> table = new TreeMap<String, Integer>();
}



