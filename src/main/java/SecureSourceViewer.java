import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class SecureSourceViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Authenticator.setDefault(new DialogAuthenticator());
		
		for(int i = 0; i < args.length; i++){
			try {
				URL u = new URL(args[i]);
				InputStream in = u.openStream();
				in = new BufferedInputStream(in);
				Reader r = new InputStreamReader(in);
				int c;
				while ((c = r.read()) != -1) {
					System.out.print((char)c);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println();
		}
		System.exit(0);
	}

}


/**
 * Networking Programming 3rd
 * 第7章 口令认证  one example
 * */
class DialogAuthenticator extends Authenticator{
	
	public DialogAuthenticator(){
		this("", new JFrame());
	}
	
	public DialogAuthenticator(String username){
		this(username, new JFrame());
	}
	
	public DialogAuthenticator(JFrame parent){
		this("", parent);
	}
	
	public DialogAuthenticator(String username, JFrame parent){
		this.passworDialog = new JDialog(parent, true);
		Container pane = passworDialog.getContentPane();
		
		pane.setLayout(new GridLayout(4, 1));
		pane.add(mainLabel);
		JPanel p2 = new JPanel();
		p2.add(userLabel);
		p2.add(usernameField);
		pane.add(p2);
		JPanel p3 = new JPanel();
		p3.add(passwordLabel);
		p3.add(passwordField);
		pane.add(p3);
		JPanel p4 = new JPanel();
		p4.add(okButton);
		p4.add(cancelButton);
		pane.add(p4);
		
		passworDialog.pack();
		
		ActionListener a1 = new OKResponse();
		okButton.addActionListener(a1);
		usernameField.addActionListener(a1);
		passwordField.addActionListener(a1);
		cancelButton.addActionListener(new CancelResponse());
	}
	
	private void show(){
		String prompt = this.getRequestingPrompt();
		if(prompt == null){
			String site = this.getRequestingSite().getHostName();
			String protocol = this.getRequestingProtocol();
			int port = this.getRequestingPort();
			if(site != null & protocol != null){
				prompt = protocol + "://" + site;
				if(port > 0) prompt += ":" + port;
			}
			else {
				prompt = "";
			}
		}
		mainLabel.setText("Please enter username and password for " + prompt + ": ");
		passworDialog.pack();
		passworDialog.setVisible(true);
	}
	
	PasswordAuthentication response = null;
	
	class OKResponse implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			passworDialog.setVisible(false);
			
			char[] password = passwordField.getPassword();
			String username = usernameField.getText();
			passwordField.setText("");
			response = new PasswordAuthentication(username, password);
		}
	}
	
	class CancelResponse implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			passworDialog.setVisible(false);
			passwordField.setText("");
			response = null;
		}
	}
	
	public PasswordAuthentication getPasswordAuthentication(){
		this.show();
		return this.response;
	}
	
	private JDialog passworDialog;
	private JLabel mainLabel = new JLabel("Please enter username and password: ");
	private JLabel userLabel = new JLabel("Username: ");
	private JLabel passwordLabel = new JLabel("Password: ");
	private JTextField usernameField = new JTextField(20);
	private JPasswordField passwordField = new JPasswordField(20);
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
}
