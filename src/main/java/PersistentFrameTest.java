import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.EventHandler;
import java.beans.Expression;
import java.beans.Introspector;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class PersistentFrameTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		chooser = new JFileChooser();
//		chooser.setCurrentDirectory(new File("."));
//		PersistentFrameTest test = new PersistentFrameTest();
//		test.init();
		
		PersistenceDelegate delegate = new DefaultPersistenceDelegate(){
			@Override
			protected Expression instantiate(Object arg0, Encoder arg1) {
				// TODO Auto-generated method stub
				Employee e = (Employee)arg0;
				GregorianCalendar c = new GregorianCalendar();
				c.setTime(e.getHireDay());
				return new Expression(arg0, Employee.class, "new", new Object[]{
					e.getName(),
					e.getSalary(),
					c.get(Calendar.YEAR),
					c.get(Calendar.MONTH),
					c.get(Calendar.DATE)
				});
			}
		};
		//BeanInfo info = Introspector.getBeanInfo(Employee.class);
		//info.getBeanDescriptor().setValue("persistenceDelegate", delegate);
		
		XMLEncoder encoder = new XMLEncoder(System.out);
		encoder.setPersistenceDelegate(Employee.class, delegate);
		//encoder.writeObject(new Employee("Harry Hacker", 50000.0, 1989, 10, 1));
		
		Object myData = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		encoder.writeObject(myData);
		encoder.close();
	}

	public void init(){
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("PersistentFrameTest");
		frame.setSize(400, 200);
		
		JButton loadButton = new JButton("Load");
		frame.add(loadButton);
		loadButton.addActionListener(EventHandler.create(ActionListener.class, this, "load"));
		
		JButton saveButton = new JButton("Save");
		frame.add(saveButton);
		saveButton.addActionListener(EventHandler.create(ActionListener.class, this, "save"));
		
		frame.setVisible(true);
	}
	
	public void load(){
		int r = chooser.showOpenDialog(null);
		
		if(r == JFileChooser.APPROVE_OPTION){
			try {
				File file = chooser.getSelectedFile();
				XMLDecoder decoder = new XMLDecoder(new FileInputStream(file));
				decoder.readObject();
				decoder.close();
			} catch (IOException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	
	public void save(){
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			try {
				File file = chooser.getSelectedFile();
				XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
				encoder.writeObject(frame);
				encoder.close();
			} catch (IOException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	
	private static JFileChooser chooser;
	private JFrame frame;
}


