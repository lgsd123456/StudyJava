import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
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
		
		tableName = new JComboBox();
		
		add(tableName, BorderLayout.NORTH);
		tableName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(scrollPane != null) remove(scrollPane);
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from " + (String)tableName.getSelectedItem());
					CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
					crs.setTableName((String)tableName.getSelectedItem());
					crs.populate(resultSet);
					centerJPanel = new DataPanel1(crs);
					scrollPane = new JScrollPane(centerJPanel);
					add(scrollPane);
					validate();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		
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
		Previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				centerJPanel.ShowPrevious();
			}
		});
		
		Next = new JButton("Next");
		southPanel.add(Next);
		Next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				centerJPanel.ShowNext();
			}
		});
		
		Delete = new JButton("Delete");
		southPanel.add(Delete);
		Delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				centerJPanel.deleteRow();
			}
		});
		
		Save = new JButton("Save");
		southPanel.add(Save);
		Save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				centerJPanel.editSaveRow();
			}
		});
		
			
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
	private JComboBox tableName;
	private JButton Previous;
	private JButton Next;
	private JButton Delete;
	private JButton Save;
	private JScrollPane scrollPane;
	private DataPanel1 centerJPanel;
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
}

class DataPanel1 extends JPanel{
	public DataPanel1(CachedRowSet sets){
		set = sets;
		sTextFields = new ArrayList<JTextField>();
		try {
			
			ResultSetMetaData metaData = sets.getMetaData();
			setLayout(new GridBagLayout());
			int count = 0;
			sets.next();
			for(int i = 1; i <= metaData.getColumnCount(); i++){
				JLabel label = new JLabel(metaData.getColumnLabel(i));
				if(metaData.getColumnDisplaySize(i) > 100) count = 100;
				else count = metaData.getColumnDisplaySize(i);
				JTextField textField = new JTextField(count);
				add(label, new GBC(0 , i - 1, 1, 1).setAnchor(GBC.EAST));
				add(textField,new GBC(1 , i - 1, 1, 1).setAnchor(GBC.WEST));
				sTextFields.add(textField);
				textField.setText(sets.getString(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void ShowNext(){
		try {
			if(set == null || set.isLast()) return;
			boolean flag = set.next();
			if(flag){
				for(int i=1; i <= sTextFields.size(); i++){
					sTextFields.get(i - 1).setText(set.getString(i));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void ShowPrevious(){
		try {
			if(set == null || set.isFirst()) return;
			boolean flag = set.previous();
			if(flag){
				for(int i=1; i <= sTextFields.size(); i++){
					sTextFields.get(i - 1).setText(set.getString(i));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deleteRow(){
		try {
			connection = MetaJFrame.getConnection();
			try{
				int num = set.getRow();
				if(num != 0){
					set.deleteRow();
					set.acceptChanges(connection);
					if(!set.isLast()) ShowNext();
					else if(!set.isFirst()) ShowPrevious();
				}
			}
			finally{
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void editSaveRow(){
		try {
			connection = MetaJFrame.getConnection();
			try{
				int num = set.getRow();
				if(num != 0){
					for(int i=1; i <= sTextFields.size(); i++){
						String field = set.getString(1);
						JTextField tb = (JTextField)sTextFields.get(i - 1);
						if(!field.equals(tb.getText()))
							set.updateString(i, tb.getText().trim());
					}
					set.updateRow();
					set.acceptChanges(connection);
				}
			}
			finally{
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private Connection connection;
	private CachedRowSet set;
	private ArrayList<JTextField> sTextFields;
}
