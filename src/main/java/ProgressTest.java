import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ProgressMonitor;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.SwingWorker;
import javax.swing.Timer;


public class ProgressTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new ProgressTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	public ProgressTest(){
		setTitle("Progress Test");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		textArea = new JTextArea();
		final int MAX = 1000;
		
		JPanel panel = new JPanel();
		startButton = new JButton("Start");
		//progressBar = new JProgressBar(0, MAX);
		
		
		
		panel.add(startButton);
		//panel.add(progressBar);
		//progressBar.setStringPainted(true);
		
//		checkBox = new JCheckBox("indeterminate");
//		checkBox.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				progressBar.setIndeterminate(checkBox.isSelected());
//				progressBar.setStringPainted(!progressBar.isIndeterminate());
//			}
//		});
		
		//panel.add(checkBox);
		add(new JScrollPane(textArea));
		add(panel, BorderLayout.SOUTH);
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				startButton.setEnabled(false);
				activity = new SimulatedActivity(MAX);
				activity.execute();
				monitor = new ProgressMonitor(ProgressTest.this, "Display num from 0 to 1000", null, 0, MAX);
				cancelMonitor.start();
			}
		});
		
		cancelMonitor = new Timer(500, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(monitor.isCanceled()){
					activity.cancel(true);
					startButton.setEnabled(true);
				}
				else if(activity.isDone()){
					monitor.close();
					startButton.setEnabled(true);
				}
				else {
					monitor.setProgress(activity.getProgress());
				}
			}
		});
		
		JButton readFile = new JButton("ReadFile");
		panel.add(readFile);
		readFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					FileInputStream in = new FileInputStream("C:\\Data\\20110929TestLog.txt");
					ProgressMonitorInputStream progressIn = new ProgressMonitorInputStream(ProgressTest.this, "Read File", in);
					final Scanner scanner = new Scanner(progressIn);
					activityFile = new ReadFileActivity(scanner);
					activityFile.execute();
				} catch (Exception e) {
					// TODO: handle exception
				}	
			}
		});
	}

	
	class ReadFileActivity extends SwingWorker<Void, Void>{
		public ReadFileActivity(Scanner s){
			scanner = s;
		}
		
		@Override
		protected Void doInBackground() throws Exception {
			// TODO Auto-generated method stub
			while(scanner.hasNext()){
				textArea.append(scanner.nextLine() + "\n");
				Thread.sleep(1);
			}
			scanner.close();
			return null;
		}
		
		private Scanner scanner;
	}
	
	class SimulatedActivity extends SwingWorker<Void, Integer>{
		public SimulatedActivity(int t){
			current = 0;
			target = t;
		}
		
		protected Void doInBackground()throws Exception{
			try {
				while(current < target){
					Thread.sleep(100);
					current++;
					textArea.append(current + "\n");
					setProgress(current);
					//publish(current);
				}
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			return null;
		}
		
//		@Override
//		protected void process(List<Integer> arg0) {
//			// TODO Auto-generated method stub
//			for(Integer chunk : arg0){
//				textArea.append(chunk + "\n");
//				monitor.setProgress(chunk);
//				//progressBar.setValue(chunk);
//			}
//		}
		
//		@Override
//		protected void done() {
//			// TODO Auto-generated method stub
//			startButton.setEnabled(true);
//		}
		
		private int current;
		private int target;
	}
	
	private Timer cancelMonitor;
	private SimulatedActivity activity;
	private ReadFileActivity activityFile;
	//private JProgressBar progressBar;
	private ProgressMonitor monitor;
	private JTextArea textArea;
	//private JCheckBox checkBox;
	private JButton startButton;
	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 400;
}
