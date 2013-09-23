import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class DigitalSignatures extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new DigitalSignatures();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	public DigitalSignatures(){
		setTitle("Digital Signatures");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JPanel panel = new JPanel();
		ButtonGroup group = new ButtonGroup();
		addRadioButton(panel, "SHA-1", group);
		addRadioButton(panel, "MD5", group);
		add(panel, BorderLayout.NORTH);
		setAlgorithm("SHA-1");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem fileDigestItem = new JMenuItem("File digest");
		fileDigestItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				loadFile();
			}
		});
		menu.add(fileDigestItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		JScrollPane pane = new JScrollPane(message);
		add(pane);
		add(digest, BorderLayout.SOUTH);
		
		
		//
	}
	
	private void loadFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("C:\\"));
		
		int r = chooser.showOpenDialog(this);
		if(r == JFileChooser.APPROVE_OPTION){
			try {
				String name = chooser.getSelectedFile().getAbsolutePath();
				computeDigest(loadBytes(name));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private void computeDigest(byte[] messages){
		currentAlgorithm.reset();
		currentAlgorithm.update(messages);
		byte[] hash = currentAlgorithm.digest();
		String d = "";
		for(int i = 0; i < hash.length; i++){
			int v = hash[i] & 0xFF;
			if(v < 16) d += "0";
			d += Integer.toString(v, 16).toUpperCase() + " ";
		}
		digest.setText(d);
	}
	
	private byte[] loadBytes(String filename) throws IOException{
		FileInputStream inputStream = new FileInputStream(filename);
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			StringBuffer strbuBuffer = new StringBuffer();
			int ch = inputStream.read();
			while (ch != -1) {
				buffer.write(ch);
				strbuBuffer.append(ch);
				ch = inputStream.read();
			}
			message.setText(strbuBuffer.toString());
			return buffer.toByteArray();
		}
		finally{
			inputStream.close();
		}
	}
	
	
	private void addRadioButton(JPanel panel, final String algString, ButtonGroup group){
		JRadioButton radioButton = new JRadioButton(algString);
		radioButton.setSelected(group.getButtonCount() == 0);
		group.add(radioButton);
		panel.add(radioButton);
		radioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setAlgorithm(algString);
				try {
					computeDigest(message.getText().getBytes());
				} catch (Exception e) {
					// TODO: handle exception
				}	
			}
		});
	}

	private void setAlgorithm(final String algString){
		try {
			currentAlgorithm = MessageDigest.getInstance(algString);
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private JTextArea message = new JTextArea();
	private JTextField digest = new JTextField();
	private MessageDigest currentAlgorithm;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}
