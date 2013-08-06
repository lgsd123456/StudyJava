import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.*;



public class ScrollUpdateRowset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection connection = getConnection();
			try{
//				DatabaseMetaData metaData = connection.getMetaData();
//				boolean b1 = metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
//				boolean b2 = metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				//Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//				Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//				ResultSet sets = stat.executeQuery("select * from Books");
				//更新行
//				sets.absolute(1);
//				double increase = 20.00;
//				double price = sets.getDouble("price");
//				sets.updateDouble("price", price + increase);
//				sets.updateRow();
//				System.out.println(sets.getString(1) + ", " + sets.getString(2) + ", " + sets.getString(3) + ", " + sets.getString(4));
				//sets.absolute(20);
				//增加行
//				sets.absolute(11);
//				sets.moveToInsertRow();
//				sets.updateString("title", "Core Java1");
//				sets.updateString("isbn", "1-23-09073-35");
//				sets.updateString("publisher_id", "013");
//				sets.updateDouble("price", 90.0);
//				sets.insertRow();
				//sets.moveToCurrentRow();
				//删除行
//				sets.absolute(21);
//				sets.deleteRow();
				//System.out.println(sets.getString(1) + ", " + sets.getString(2) + ", " + sets.getString(3) + ", " + sets.getString(4));
//				CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
//				crs.setUrl("jdbc:derby://localhost:1527/jdbcdb");
//				crs.setUsername("app");
//				crs.setPassword("secret");
//				crs.setCommand("select * from books");
//				crs.execute();
//				while(crs.next())
//					System.out.println(crs.getString(1).trim() + "|" + crs.getString(2) + "|" + crs.getString(3) + "|" + crs.getString(4));
				
				DatabaseMetaData metaData = connection.getMetaData();
				ResultSet mrs = metaData.getTables(null, null, null, new String[]{"TABLE"});
				while(mrs.next())
					System.out.println(mrs.getString(3));
			}
			finally{
				connection.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			for(Throwable throwable : e)
				System.out.println(throwable.getMessage());
		}
		catch (Exception e) {
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

}
