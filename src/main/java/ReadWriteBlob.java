import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ReadWriteBlob {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection connection = getConnection();
			try{
				//写入一个blob数据
//				Blob image = connection.createBlob();
//				int offset = 1;
//				OutputStream out = image.setBinaryStream(offset);
//				ImageIO.write(ImageIO.read(new File("mug.png")), "PNG", out);
//				PreparedStatement stat = connection.prepareStatement("insert into lgBlob values(?,?)");
//				stat.setInt(1, 1);
//				stat.setBlob(2, image);
//				stat.executeUpdate();
				
				//读取一个blob数据
				Blob imageBlob = null;
				BufferedImage image = null;
				PreparedStatement stat = connection.prepareStatement("select image from lgBlob where id=?");
				stat.setInt(1, 1);
				ResultSet resultSet = stat.executeQuery();
				if(resultSet.next()){
					imageBlob = resultSet.getBlob(1);
					image = ImageIO.read(imageBlob.getBinaryStream());
				}
				JOptionPane.showMessageDialog(null, "Show blob data", "BlobData", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));
			}
			finally{
				connection.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			for(Throwable throwable : e)
				System.out.println(throwable.getMessage());
		}
		catch (IOException e) {
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
