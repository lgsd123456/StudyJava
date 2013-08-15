import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ComboBoxModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;


public class Retire extends JApplet {
	public void init(){
		setLayout(new GridBagLayout());
		
		bundle = ResourceBundle.getBundle("RetireStrings");
		
		JLabel languageLabel = new JLabel(bundle.getString("language"));
		
		for(Locale ll : locales)
			languageBox.addItem(ll);
		languageBox.setSelectedItem(Locale.CHINA);
		//languageBox.setSize(100, 0);
//		languageBox.setRenderer(languageBox);
		
		
		
//		int localeIndex = 0; // US locale is default selection
//	      for (int i = 0; i < locales.length; i++)
//	         // if current locale one of the choices, select it
//	         if (getLocale().equals(locales[i])) localeIndex = i;
	      
		//languageBox.setSelectedItem(Locale.CHINA);
		
		//setCurrentLocale(Locale.CHINA);
		//languageBox.setRenderer(languageBox);
		
		languageBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//setCurrentLocale((Locale)languageBox.getSelectedItem());
				//languageBox.setLocale((Locale)languageBox.getSelectedItem());
				languageBox.setNew();
				validate();
			}
		});
		
		JLabel startLabel = new JLabel(bundle.getString("savings"));
		startField = new JTextField(20);
		JLabel everyLabel = new JLabel(bundle.getString("contrib"));
		everyField = new JTextField(20);
		JLabel totaLabel = new JLabel(bundle.getString("income"));
		totalField = new JTextField(20);
		JLabel curagelLabel = new JLabel(bundle.getString("currentAge"));
		curageField = new JTextField(20);
		JLabel oldageJLabel = new JLabel(bundle.getString("retireAge"));
		oldageField = new JTextField(20);
		JLabel aginglLabel = new JLabel(bundle.getString("deathAge"));
		agingField = new JTextField(20);
		JLabel penglLabel = new JLabel(bundle.getString("inflationPercent"));
		pengField = new JTextField(20);
		JLabel shoulLabel = new JLabel(bundle.getString("investPercent"));
		shouField = new JTextField(20);
		panel = new JPanel();
		textArea = new JTextArea(30,10);
		JScrollPane scrollPane = new JScrollPane(textArea);
		compute = new JButton(bundle.getString("computeButton"));
		
		add(languageLabel, new GBC(0, 0).setAnchor(GBC.EAST));
		add(languageBox, new GBC(1, 0, 3, 1));
		add(startLabel, new GBC(0, 1).setAnchor(GBC.EAST));
		add(curagelLabel, new GBC(0, 2).setAnchor(GBC.EAST));
		add(penglLabel, new GBC(0, 3).setAnchor(GBC.EAST));
		add(startField, new GBC(1, 1).setAnchor(GBC.WEST).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		add(curageField, new GBC(1, 2).setAnchor(GBC.WEST).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		add(pengField, new GBC(1, 3).setAnchor(GBC.WEST).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		add(everyLabel, new GBC(2, 1).setAnchor(GBC.EAST));
		add(oldageJLabel, new GBC(2, 2).setAnchor(GBC.EAST));
		add(shoulLabel, new GBC(2, 3).setAnchor(GBC.EAST));
		add(everyField, new GBC(3, 1).setAnchor(GBC.WEST).setWeight(100, 0).setFill(GBC.HORIZONTAL));
		add(oldageField, new GBC(3, 2).setAnchor(GBC.WEST).setWeight(100, 0).setFill(GBC.HORIZONTAL));
		add(shouField, new GBC(3, 3).setAnchor(GBC.WEST).setWeight(100, 0).setFill(GBC.HORIZONTAL));
		add(totaLabel, new GBC(4, 1).setAnchor(GBC.EAST));
		add(aginglLabel, new GBC(4, 2).setAnchor(GBC.EAST));
		add(totalField, new GBC(5, 1).setAnchor(GBC.WEST).setWeight(100, 0).setFill(GBC.HORIZONTAL));
		add(agingField, new GBC(5, 2).setAnchor(GBC.WEST).setWeight(100, 0).setFill(GBC.HORIZONTAL));
		add(compute, new GBC(4, 3, 2, 1));
		add(panel, new GBC(0, 4, 4, 1).setFill(GBC.BOTH).setWeight(100, 100));
		add(scrollPane, new GBC(4, 4, 2, 1).setFill(GBC.BOTH).setWeight(0, 100));
	}
	
	public void setCurrentLocale(Locale locale){
		currentLocale = locale;
		languageBox.setSelectedItem(currentLocale);
		languageBox.setLocale(currentLocale);
	}
	
	private Locale currentLocale;
	private ResourceBundle bundle;
	private JTextField startField;
	private JTextField everyField;
	private JTextField totalField;
	private JTextField curageField;
	private JTextField oldageField;
	private JTextField agingField;
	private JTextField pengField;
	private JTextField shouField;
	private JButton compute;
	private JPanel panel;
	private JTextArea textArea;
	private Locale[] locales = {Locale.US, Locale.GERMANY, Locale.CHINA};
	//private ListCellItem languageBox = new ListCellItem(locales);
	private ListCell2 languageBox = new ListCell2(locales);
}

class ListCell2 extends JComboBox{
	
	public ListCell2(Locale[] lls){
		//renderer = super.getRenderer();
		locales = lls;
		setNewModel();
	}
	
	public void setNew(){
		setNewModel();
	}
	
	private void setNewModel(){
		Object selected = getSelectedItem();
		setModel(new ComboBoxModel() {

			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return locales.length;
			}

			@Override
			public Object getElementAt(int index) {
				// TODO Auto-generated method stub
				return locales[index];
			}

			@Override
			public void addListDataListener(ListDataListener l) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeListDataListener(ListDataListener l) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setSelectedItem(Object anItem) {
				// TODO Auto-generated method stub
				if(anItem == null) selected = -1;
				else{
					Locale tmp = (Locale)anItem;
					for(int i = 0; i < locales.length; i++){
						if(tmp.equals(locales[i]))
							selected = i;
					}
				}
			}

			@Override
			public Object getSelectedItem() {
				// TODO Auto-generated method stub
				return selected >= 0 ? locales[selected] : null;
			}
			private int selected = -1;
		});
		setSelectedItem(selected);
	}
	
	public ListCellRenderer getRenderer(){
		if(renderer == null){
			final ListCellRenderer originalRenderer = super.getRenderer();
			if (originalRenderer == null) return null;
			renderer = new ListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList arg0,
						Object arg1, int arg2, boolean arg3, boolean arg4) {
					// TODO Auto-generated method stub
					Locale locale = (Locale)getSelectedItem();
					return originalRenderer.getListCellRendererComponent(arg0, ((Locale)arg1).getDisplayName(locale), arg2, arg3, arg4);
				}
			};
		}
		return renderer;
	}
	
	
//	@Override
//	public Component getListCellRendererComponent(JList arg0, Object arg1,
//			int arg2, boolean arg3, boolean arg4) {
//		// TODO Auto-generated method stub
//		Locale locale = (Locale)getSelectedItem();
//		return renderer.getListCellRendererComponent(arg0, ((Locale)arg1).getDisplayName(locale), arg2, arg3, arg4);
//	}
	
	public void setRenderer(ListCellRenderer newValue){
		renderer = null;
		super.setRenderer(newValue);
	}
	
	private ListCellRenderer renderer;
	private Locale[] locales;
}


class ListCellItem extends JComboBox{
	public ListCellItem(Locale[] ll){
		locales = (Locale[])ll.clone();
		sort();
		setSelectedItem(getLocale());
	}
	
	public void setLocale(Locale newValue)
   {
      super.setLocale(newValue);
      sort();
   }
	
	private void sort()
	   {
	      Object selected = getSelectedItem();
	      final Locale loc = getLocale();
	      final Collator collator = Collator.getInstance(loc);
	      final Comparator<Locale> comp = new Comparator<Locale>()
	         {
	            public int compare(Locale a, Locale b)
	            {
	               return collator.compare(a.getDisplayName(loc), b.getDisplayName(loc));
	            }
	         };
	      Arrays.sort(locales, comp);
	      setModel(new ComboBoxModel()
	         {
	            public Object getElementAt(int i)
	            {
	               return locales[i];
	            }

	            public int getSize()
	            {
	               return locales.length;
	            }

	            public void addListDataListener(ListDataListener l)
	            {
	            }

	            public void removeListDataListener(ListDataListener l)
	            {
	            }

	            public Object getSelectedItem()
	            {
	               return selected >= 0 ? locales[selected] : null;
	            }

	            public void setSelectedItem(Object anItem)
	            {
	               if (anItem == null) selected = -1;
	               else selected = Arrays.binarySearch(locales, (Locale) anItem, comp);
	            }

	            private int selected;
	         });
	      setSelectedItem(selected);
	   }
	
	public ListCellRenderer getRenderer()
	   {
	      if (renderer == null)
	      {
	         final ListCellRenderer originalRenderer = super.getRenderer();
	         if (originalRenderer == null) return null;
	         renderer = new ListCellRenderer()
	            {
	               public Component getListCellRendererComponent(JList list, Object value, int index,
	                     boolean isSelected, boolean cellHasFocus)
	               {
	                  String renderedValue = ((Locale) value).getDisplayName(getLocale());
	                  return originalRenderer.getListCellRendererComponent(list, renderedValue, index,
	                        isSelected, cellHasFocus);
	               }
	            };
	      }
	      return renderer;
	   }
	
	
	public void setRenderer(ListCellRenderer newValue)
	   {
	      renderer = null;
	      super.setRenderer(newValue);
	   }
	
	private Locale[] locales;
	private ListCellRenderer renderer;
}


