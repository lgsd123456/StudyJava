import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PrintJob;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PrintTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new PrintTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	public PrintTest(){
		setTitle("PrintTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		canvas = new PrintComponent();
		add(canvas);
		
		attributes = new HashPrintRequestAttributeSet();
		JPanel buttonPanel = new JPanel();
		JButton printButton = new JButton("Print");
		buttonPanel.add(printButton);
		printButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					PrinterJob job = PrinterJob.getPrinterJob();
					job.setPrintable(canvas);
					if(job.printDialog(attributes)) job.print(attributes);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		
		JButton pageSetupButton = new JButton("Page setup");
		buttonPanel.add(pageSetupButton);
		pageSetupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PrinterJob job = PrinterJob.getPrinterJob();
				job.pageDialog(attributes);
			}
		});
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private PrintComponent canvas;
	private PrintRequestAttributeSet attributes;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;
}

class PrintComponent extends JComponent implements Printable{
	
	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		Graphics2D g2 = (Graphics2D)arg0;
		drawPage(g2);
	}
	
	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2)
			throws PrinterException {
		// TODO Auto-generated method stub
		if(arg2 >= 1) return Printable.NO_SUCH_PAGE;
		Graphics2D g2 = (Graphics2D)arg0;
		FontRenderContext context = g2.getFontRenderContext();
		Font f = new Font("Serif", Font.PLAIN, 10);
		
		g2.translate(arg1.getImageableX(), arg1.getImageableY());
		g2.draw(new Rectangle2D.Double(0, 0, arg1.getImageableWidth(), arg1.getImageableHeight()));
		try {
			FileInputStream in = new FileInputStream("C:\\Data\\20110930TestLog.txt");
			Scanner scanner = new Scanner(in);
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//drawPage(g2);
		return Printable.PAGE_EXISTS;
	}
	
	public void drawPage(Graphics2D g2){
		FontRenderContext context = g2.getFontRenderContext();
		Font f = new Font("Serif", Font.PLAIN, 72);
		GeneralPath clipShape = new GeneralPath();
		
		TextLayout layout = new TextLayout("Hello", f, context);
		AffineTransform transform = AffineTransform.getTranslateInstance(0, 72);
		Shape outline = layout.getOutline(transform);
		clipShape.append(outline, false);
		
		layout = new TextLayout("World", f, context);
		transform = AffineTransform.getTranslateInstance(0, 144);
		outline = layout.getOutline(transform);
		clipShape.append(outline, false);
		
		g2.draw(clipShape);
		g2.clip(clipShape);
		
		final int NILNES = 50;
		Point2D p = new Point2D.Double(0, 0);
		for(int i = 0; i < NILNES; i++){
			double x = (2 * getWidth() * i) / NILNES;
			double y = (2 * getHeight() * (NILNES - 1 - i)) / NILNES;
			Point2D q = new Point2D.Double(x, y);
			g2.draw(new Line2D.Double(p, q));
		}
	}
}
