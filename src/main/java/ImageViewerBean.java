import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ImageViewerBean extends JLabel {

	public ImageViewerBean(){
		setBorder(BorderFactory.createEtchedBorder());
	}
	
	public void setFileName(String fileName){
		try{
			file = new File(fileName);
			setIcon(new ImageIcon(ImageIO.read(file)));
		}
		catch(IOException e){
			file = null;
			setIcon(null);
		}
	}
	
	public String getFileName(){
		if(file == null) return "";
		else return file.getPath();
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(XPREFSIZE, YPREFSIZE);
	}
	
	private File file = null;
	private static final int XPREFSIZE = 200;
	private static final int YPREFSIZE = 200;
}
