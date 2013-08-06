import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class QueryDB extends JFrame {
	
	public QueryDB(){
		setTitle("QueryDB");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(new GridBagLayout());
		//setSize(300, 400);
		
		authorbBox = new JComboBox();
		authorbBox.setEditable(false);
		authorbBox.addItem("Any");
		
		publisherBox = new JComboBox();
		publisherBox.setEditable(false);
		publisherBox.addItem("Any");
		
		contentArea = new JTextArea(4, 50);
		contentArea.setEditable(false);
		
		priceField = new JTextField(8);
		priceField.setText("-5.00");
		
		
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT Name FROM Authors";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				authorbBox.addItem(resultSet.getString(1));
			}
			resultSet.close();
			
			query = "select Name from Publishers";
			resultSet = statement.executeQuery(query);
			while(resultSet.next())
				publisherBox.addItem(resultSet.getString(1));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO: handle exception
			for(Throwable throwable : e)
				contentArea.append(throwable.getMessage());
		}
		catch (IOException e) {
			// TODO: handle exception
			contentArea.setText("" + e);
		}
		
		
		
		add(authorbBox, new GBC(0, 0, 2, 1));
		
		add(publisherBox, new GBC(2, 0, 2, 1));
		
		queryButton = new JButton("Query");
		add(queryButton, new GBC(0, 1, 1, 1).setInsets(3));
		queryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				executeQuery();
			}
		});
		
		changeButton = new JButton("Change prices");
		add(changeButton, new GBC(2, 1, 1, 1).setInsets(3));
		changeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changePrices();
			}
		});
		
		
		add(priceField, new GBC(3, 1, 1, 1).setFill(GBC.HORIZONTAL));
		
		
		JScrollPane scrollPane = new JScrollPane(contentArea);
		add(scrollPane, new GBC(0, 2, 4, 1).setFill(GBC.BOTH).setWeight(100, 100));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event){
				try {
					if(connection != null) connection.close();
				} catch (SQLException e) {
					// TODO: handle exception
					for(Throwable throwable : e)
						throwable.printStackTrace();
				}
			}
		});
		
	}
	
	public void executeQuery(){
	  ResultSet rs = null;
	  try
	  {
	     String author = (String) authorbBox.getSelectedItem();
	     String publisher = (String) publisherBox.getSelectedItem();
	     if (!author.equals("Any") && !publisher.equals("Any"))
		 {
		    if (authorPublisherQueryStmt == null) authorPublisherQueryStmt = connection
		          .prepareStatement(authorPublisherQuery);
		    authorPublisherQueryStmt.setString(1, author);
		    authorPublisherQueryStmt.setString(2, publisher);
		    rs = authorPublisherQueryStmt.executeQuery();
		 }
		 else if (!author.equals("Any") && publisher.equals("Any"))
		 {
		    if (authorQueryStmt == null) authorQueryStmt = connection.prepareStatement(authorQuery);
		    authorQueryStmt.setString(1, author);
		    rs = authorQueryStmt.executeQuery();
		 }
		 else if (author.equals("Any") && !publisher.equals("Any"))
		 {
		    if (publisherQueryStmt == null) publisherQueryStmt = connection
		          .prepareStatement(publisherQuery);
		    publisherQueryStmt.setString(1, publisher);
		    rs = publisherQueryStmt.executeQuery();
		 }
		 else
		 {
		    if (allQueryStmt == null) allQueryStmt = connection.prepareStatement(allQuery);
		    rs = allQueryStmt.executeQuery();
		 }
	
		 contentArea.setText("");
		 while (rs.next())
		 {
			 contentArea.append(rs.getString(1));
			 contentArea.append(", ");
			 contentArea.append(rs.getString(2));
			 contentArea.append("\n");
		 }
		 rs.close();
	  }
	  catch (SQLException e)
	  {
	     for (Throwable t : e)
	    	 contentArea.append(t.getMessage());         
	  }
	}
	
	public void changePrices(){
		String sqlString = "update Books set Price= Price + ? where Books.publisher_id=(select publisher_id from Publishers where name=?)";
		try {
			String publisher = (String)publisherBox.getSelectedItem();
			if(publisher.equals("Any")){
				contentArea.setText("I am sorry, but I cannot do that.");
				return;
			}
			
			if(preChangeStatement == null)
				preChangeStatement = connection.prepareStatement(sqlString);
			if(priceField.getText().trim().equals("")) return;
			preChangeStatement.setDouble(1, Double.parseDouble(priceField.getText().trim()));
			preChangeStatement.setString(2, (String)publisherBox.getSelectedItem());
			int count = preChangeStatement.executeUpdate();
			preChangeStatement.close();
			if(count > 0){
//				String sqlString2 = "select Title, ISBN, Price, Books.Publisher_ID,Name,URL from Books, Publishers "
//						+ "Books.Publisher_ID = Publishers.Publisher_ID AND Name=?";
//				PreparedStatement statement = connection.prepareStatement(sqlString2);
//				statement.setString(1, (String)publisherBox.getSelectedItem());
//				ResultSet resultSet = statement.executeQuery();
//				while(resultSet.next()){
//					
//				}
				contentArea.setText((String)publisherBox.getSelectedItem() + " Price: " + priceField.getText().trim());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			for(Throwable throwable : e)
				contentArea.append(throwable.getMessage());
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
	
	private JComboBox authorbBox;
	private JComboBox publisherBox;
	private JButton queryButton;
	private JButton changeButton;
	private JTextField priceField;
	private JTextArea contentArea;
	private Connection connection;
	private PreparedStatement preChangeStatement = null;
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
	
	private PreparedStatement authorQueryStmt;
    private PreparedStatement authorPublisherQueryStmt;
    private PreparedStatement publisherQueryStmt;
    private PreparedStatement allQueryStmt;

    private static final String authorPublisherQuery = "SELECT Books.Price, Books.Title FROM Books, BooksAuthors, Authors, Publishers"
		 + " WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN"
		 + " AND Books.Publisher_Id = Publishers.Publisher_Id AND Authors.Name = ?"
		 + " AND Publishers.Name = ?";

    private static final String authorQuery = "SELECT Books.Price, Books.Title FROM Books, BooksAuthors, Authors"
		 + " WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN"
		 + " AND Authors.Name = ?";

    private static final String publisherQuery = "SELECT Books.Price, Books.Title FROM Books, Publishers"
    	 + " WHERE Books.Publisher_Id = Publishers.Publisher_Id AND Publishers.Name = ?";

    private static final String allQuery = "SELECT Books.Price, Books.Title FROM Books";

}
