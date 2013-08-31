import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ClipBoardTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new ClipBoardTest();
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });
	}

	public ClipBoardTest(){
		setTitle("ClipBoardTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		textArea = new JTextArea();
		add(textArea);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		copy = new JButton("Copy");
		panel.add(copy);
		copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				String text = textArea.getSelectedText();
				if(text == null) text = textArea.getText();
				StringSelection selection = new StringSelection(text);
				clipboard.setContents(selection, null);
			}
		});
		paste = new JButton("Paste");
		panel.add(paste);
		paste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				DataFlavor flavor = DataFlavor.stringFlavor;
				if(clipboard.isDataFlavorAvailable(flavor)){
					try {
						String text = (String)clipboard.getData(flavor);
						textArea.replaceSelection(text);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		});
		
	}
	
	private JTextArea textArea;
	private JButton copy;
	private JButton paste;
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}


class ImageTransferable implements Transferable{
	public ImageTransferable(Image image){
		theImage = image;
	}
	
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return new DataFlavor[]{DataFlavor.imageFlavor};
	}
	
	@Override
	public boolean isDataFlavorSupported(DataFlavor arg0) {
		// TODO Auto-generated method stub
		return arg0.equals(DataFlavor.imageFlavor);
	}
	
	@Override
	public Object getTransferData(DataFlavor arg0)
			throws UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		if(arg0.equals(DataFlavor.imageFlavor)){
			return theImage;
		}
		else{
			new UnsupportedFlavorException(arg0);
		}
		return null;
	}
	
	private Image theImage;
}
