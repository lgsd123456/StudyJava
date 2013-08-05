import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.omg.CORBA.PUBLIC_MEMBER;


public class InterruptibleSocketFrame extends JFrame {
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new InterruptibleSocketFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	
	public InterruptibleSocketFrame(){
		setSize(WIDTH, HEIGHT);
		setTitle("InterruptibleSocketTest");
		
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		
		messages = new JTextArea();
		add(new JScrollPane(messages));
		
		interruptibleButton = new JButton("Interruptible");
		blockingButton = new JButton("Blocking");
		
		northPanel.add(interruptibleButton);
		northPanel.add(blockingButton);
		
		interruptibleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				interruptibleButton.setEnabled(false);
				blockingButton.setEnabled(false);
				cancelButton.setEnabled(true);
				connectThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							connectInterruptibly();
						} catch (IOException e) {
							// TODO: handle exception
							messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e);
						}
					}
				});
				connectThread.start();
			}
		});
		
		blockingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				interruptibleButton.setEnabled(false);
				blockingButton.setEnabled(false);
				cancelButton.setEnabled(true);
				connectThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							connectBlocking();
						} catch (IOException e2) {
							// TODO: handle exception
							messages.append("\nInterruptibleSocketTest.connectBlocking: " + e2);
						}
					}
				});
				connectThread.start();
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(false);
		northPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				connectThread.interrupt();
				cancelButton.setEnabled(false);
			}
		});
		server = new TestServer();
		new Thread(server).start();
	}
	
	public void connectInterruptibly() throws IOException{
		messages.append("Interruptibly:\n");
		SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189));
		try{
			in = new Scanner(channel);
			while(!Thread.currentThread().isInterrupted()){
				messages.append("Reading ");
				if(in.hasNextLine()){
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		}
		finally{
			channel.close();
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					messages.append("Channel closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}
			});
		}
	}
	
	
	public void connectBlocking() throws IOException{
		messages.append("Blocking:\n");
		Socket sock = new Socket("localhost", 8189);
		try {
			in = new Scanner(sock.getInputStream());
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if(in.hasNextLine()){
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		}
		finally{
			sock.close();
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					messages.append("Socket closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}
			});
		}
	}
	
	class TestServer implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				ServerSocket s = new ServerSocket(8189);
				
				while(true){
					Socket incoming = s.accept();
					Runnable r = new TestServerHandler(incoming);
					Thread t = new Thread(r);
					t.start();
				}
			} catch (IOException e) {
				// TODO: handle exception
				messages.append("\nTestServer.run: " + e);
			}
		}
	}
	
	
	class TestServerHandler implements Runnable{
		public TestServerHandler(Socket i){
			incoming = i;
		}
		
		public void run(){
			try {
				OutputStream outputStream = incoming.getOutputStream();
				PrintWriter out = new PrintWriter(outputStream, true);
				while(counter < 100){
					counter++;
					if(counter <= 10) out.println(counter);
					Thread.sleep(100);
				}
				incoming.close();
				messages.append("Clsing server\n");
			} catch (Exception e) {
				// TODO: handle exception
				messages.append("\nTestServerHandler.run: " + e);
			}
		}
		
		private Socket incoming;
		private int counter;
	}
	
	private Scanner in;
	private JButton interruptibleButton;
	private JButton blockingButton;
	private JButton cancelButton;
	private JTextArea messages;
	private TestServer server;
	private Thread connectThread;
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
}
