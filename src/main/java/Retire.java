import java.awt.GridBagLayout;
import java.awt.geom.Area;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Retire extends JApplet {
	public void init(){
		setLayout(new GridBagLayout());
		
		bundle = ResourceBundle.getBundle("RetireStrings");
		
		JLabel languageLabel = new JLabel(bundle.getString("language"));
		languageBox = new JComboBox<String>();
		
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
		add(panel, new GBC(0, 4, 4, 1).setFill(GBC.BOTH).setWeight(0, 100));
		add(scrollPane, new GBC(4, 4, 2, 1).setFill(GBC.BOTH).setWeight(0, 100));
	}
	
	private ResourceBundle bundle;
	private JComboBox<String> languageBox;
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
}
