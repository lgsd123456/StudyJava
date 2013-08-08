import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;


public class JDBCStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runTest();
//		QueryDB frame = new QueryDB();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
	}
	
	public static void runTest(){
		try {
			Connection conn = getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			int maxState = metaData.getMaxStatements();
			int majorVersion = metaData.getJDBCMajorVersion();
			int minorVersion = metaData.getJDBCMinorVersion();
			int maxConnection = metaData.getMaxConnections();
			boolean batchSupport = metaData.supportsBatchUpdates();
			try{
				Statement stat = conn.createStatement();
				stat.executeUpdate("create table Greetings(Message char(20))");
				stat.executeUpdate("insert into Greetings values('Hello, World!')");
				
				ResultSet result = stat.executeQuery("select * from Greetings");
				if(result.next())
					System.out.println(result.getString(1));
				result.close();
				stat.executeUpdate("DROP TABLE Greetings");
			}
			finally{
				conn.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		catch (IOException e) {
			// TODO: handle exception
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
}
