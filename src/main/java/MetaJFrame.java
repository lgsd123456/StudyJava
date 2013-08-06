import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class MetaJFrame extends JFrame {
	public static void main(String[] args) {
		JFrame frame = new MetaJFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public MetaJFrame(){
		setTitle("Meta Test");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		tableName = new JComboBox<String>();
		add(tableName, BorderLayout.NORTH);
		try {
			connection = getConnection();
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet mrs = meta.getTables(null, null, null, new String[] { "TABLE" });
			while(mrs.next())
				tableName.addItem(mrs.getString(3));
			mrs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		
		Previous = new JButton("Previous");
		southPanel.add(Previous);
		
		Next = new JButton("Next");
		southPanel.add(Next);
		
		Delete = new JButton("Delete");
		southPanel.add(Delete);
		
		Save = new JButton("Save");
		southPanel.add(Save);
		
		tableName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(scrollPane != null) remove(scrollPane);
				centerJPanel = new JPanel();
				scrollPane = new JScrollPane(centerJPanel);
				add(scrollPane);
				displayTable();
			}
		});	
	}
	
	public void displayTable(){
		String currTable = (String)tableName.getSelectedItem();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from " + currTable);
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			centerJPanel.setLayout(new GridBagLayout());
			int count = 0;
			for(int i = 1; i <= metaData.getColumnCount(); i++){
				JLabel label = new JLabel(metaData.getColumnLabel(i));
				if(metaData.getColumnDisplaySize(i) > 100) count = 100;
				else count = metaData.getColumnDisplaySize(i);
				JTextField textField = new JTextField(count);
				centerJPanel.add(label, new GBC(0 , i - 1, 1, 1).setAnchor(GBC.EAST));
				centerJPanel.add(textField,new GBC(1 , i - 1, 1, 1).setAnchor(GBC.WEST));
			}
			validate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException, IOException{
		Properties props = new Properties();
		FileInputStream in = new FileInputStream("database.properties");
		props.load(in);
		in.close();
		
		String drivers = props.getProperty("jdbc.drivers");
		if(drivers != null) System.setProperty("jdbc.drivers", drivers);
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		
		return DriverManager.getConnection(url, username, password);
	}
	
	private Connection connection;
	private JComboBox<String> tableName;
	private JButton Previous;
	private JButton Next;
	private JButton Delete;
	private JButton Save;
	private JScrollPane scrollPane;
	private JPanel centerJPanel;
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
}
