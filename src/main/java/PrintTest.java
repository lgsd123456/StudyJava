import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PrintJob;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


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
//		canvas = new PrintComponent();
//		add(canvas);
//		
//		attributes = new HashPrintRequestAttributeSet();
//		JPanel buttonPanel = new JPanel();
//		JButton printButton = new JButton("Print");
//		buttonPanel.add(printButton);
//		printButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				try {
//					PrinterJob job = PrinterJob.getPrinterJob();
//					job.setPrintable(canvas);
//					if(job.printDialog(attributes)) job.print(attributes);
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		JButton pageSetupButton = new JButton("Page setup");
//		buttonPanel.add(pageSetupButton);
//		pageSetupButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				PrinterJob job = PrinterJob.getPrinterJob();
//				job.pageDialog(attributes);
//			}
//		});
//		add(buttonPanel, BorderLayout.SOUTH);
		
		//多页打印实例
//		text = new JTextField();
//		add(text, BorderLayout.NORTH);
//		attributes = new HashPrintRequestAttributeSet();
//		JPanel buttonPanel = new JPanel();
//		
//		JButton printButton = new JButton("Print");
//		buttonPanel.add(printButton);
//		printButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				try {
//					PrinterJob job = PrinterJob.getPrinterJob();
//					job.setPageable(makeBook());
//					if(job.printDialog(attributes)){
//						job.print(attributes);
//					}
//				} catch (PrinterException e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		JButton pageSetupButton = new JButton("Page setup");
//		buttonPanel.add(pageSetupButton);
//		pageSetupButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				PrinterJob job = PrinterJob.getPrinterJob();
//				pageFormat = job.pageDialog(attributes);
//			}
//		});
//		
//		JButton printPreviewButton = new JButton("Print preview");
//		buttonPanel.add(printPreviewButton);
//		printPreviewButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				PrintPreviewDialog dialog = new PrintPreviewDialog(makeBook());
//				dialog.setVisible(true);
//			}
//		});
//		add(buttonPanel, BorderLayout.SOUTH);
//		pack();
		
		//打印可选择的文件
		text = new JTextField(30);
		text.setEditable(false);
		JPanel northPanel = new JPanel();
		northPanel.add(text);
		JButton fileButton = new JButton("Choose file");
		northPanel.add(fileButton);
		fileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
					chooser.setCurrentDirectory(new File("C:\\"));
					chooser.setFileFilter(filter);
					if(chooser.showOpenDialog(PrintTest.this) == JFileChooser.APPROVE_OPTION){
						text.setText(chooser.getSelectedFile().getCanonicalPath());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		add(northPanel, BorderLayout.NORTH);
		
		attributes = new HashPrintRequestAttributeSet();
		
		JPanel southPanel = new JPanel();
		JButton printButton = new JButton("Print");
		southPanel.add(printButton);
		printButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					PrinterJob job = PrinterJob.getPrinterJob();
					job.setPageable(makeBook());
					if(job.printDialog(attributes)) job.print(attributes);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		add(southPanel, BorderLayout.SOUTH);
		JButton printPreviewButton = new JButton("Print preview");
		southPanel.add(printPreviewButton);
		printPreviewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PrintPreviewDialog canvas = new PrintPreviewDialog(makeBook());
				canvas.setVisible(true);
			}
		});
		pack();
	}
	
	//private PrintComponent canvas;
	//private PrintRequestAttributeSet attributes;
	
	public Book makeBook(){
		if(pageFormat == null){
			PrinterJob job = PrinterJob.getPrinterJob();
			pageFormat = job.defaultPage();
		}
		Book book = new Book();
		String filename = text.getText().trim();
		StringBuilder builder = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNext()) {
				builder.append(scanner.nextLine());
				//builder.append("\r\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		String message = builder.toString();
		Banner banner = new Banner(message);
		int pageCount = banner.getPageCount((Graphics2D)getGraphics(), pageFormat);
		//book.append(new CoverPage("log" + "(" + pageCount + " pages)"), pageFormat);
		book.append(banner, pageFormat, pageCount);
		return book;
	}
	
	
	private JTextField text;
	private PageFormat pageFormat;
	private PrintRequestAttributeSet attributes;
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;
}

class Banner implements Printable{
	public Banner(String m){
		message = m;
	}
	
	public int getPageCount(Graphics2D g2, PageFormat pf){
		if(message.equals("")) return 0;
		FontRenderContext context = g2.getFontRenderContext();
		//Font f = new Font("Serif", Font.PLAIN, 72);
		Font f = new Font("Serif", Font.PLAIN, 10);
		Rectangle2D bounds = f.getStringBounds(message, context);
		int pageLines = (int)(pf.getImageableHeight() / bounds.getHeight());
		//double totalWidth = bounds.getWidth() / pf.getImageableWidth();
		//scale = pf.getImageableHeight() / bounds.getHeight();
		//double width = scale * bounds.getWidth();
		int average = (int)(bounds.getWidth() / message.length());
		int lineChars = (int)(pf.getImageableWidth() / average);
		if(lineChars > 75) lineChars = 75;
		int pages = (int)Math.ceil(message.length() / (lineChars * pageLines));
		return pages;
	}
	
	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2)
			throws PrinterException {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D)arg0;
		if(arg2 > getPageCount(g2, arg1)) return Printable.NO_SUCH_PAGE;
		g2.translate(arg1.getImageableX(), arg1.getImageableY());
		
		drawPage(g2, arg1, arg2);
		
		return Printable.PAGE_EXISTS;
	}
	
	public void drawPage(Graphics2D g2, PageFormat pf, int page){
		
		//page--;
		
		//drawCropMarks(g2, pf);
		//g2.clip(new Rectangle2D.Double(0, 0, pf.getImageableWidth(), pf.getImageableHeight()));
		//g2.translate(-page * pf.getImageableWidth(), 0);
		//g2.scale(scale, scale);
		
		FontRenderContext context = g2.getFontRenderContext();
		Font f = new Font("Serif", Font.PLAIN, 10);
		Rectangle2D standardWidth = f.getStringBounds(message, context);
		int pageLines = (int)(pf.getImageableHeight() / standardWidth.getHeight());
		int averageWidth = (int)(standardWidth.getWidth() / message.length());
		
		int lineChars = (int)(pf.getImageableWidth() / averageWidth);
		if(lineChars > 75) lineChars = 75;
		String printString1 = message.substring(page * pageLines * lineChars, (page + 1) * pageLines * lineChars);
		
		try {
			for(int j = 0; j < pageLines; j++){
				StringBuilder builder = new StringBuilder(printString1);
				String printString2 = builder.substring(j * lineChars, (j+1) * lineChars);
				g2.drawString(printString2, 0, (int)((-standardWidth.getY()) + j * standardWidth.getHeight()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		//LineMetrics metrics = f.getLineMetrics(message, context);
		//double acent = metrics.getAscent();
		
		
		//Rectangle2D standardWidth = f.getStringBounds("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", context);
		//int averageWidth = (int)(standardWidth.getWidth() / 52);
		//int pageLines = (int)(pf.getImageableHeight() / standardWidth.getHeight());
		//int lineChars = (int)(pf.getImageableWidth() / averageWidth);
		//int lineChars = 50;
		
		//message = message.substring(page * pageLines * lineChars, pageLines * lineChars);
		//if(message.equals("")) return;
		
		//for(int j = 0; j < pageLines; j++){
		//	TextLayout layout = new TextLayout(message.substring(j * lineChars, lineChars), f, context);
		//	AffineTransform transform = AffineTransform.getTranslateInstance(0, layout.getAscent() + j * standardWidth.getHeight());
		//	Shape outline = layout.getOutline(transform);
		//	g2.draw(outline);
			//g2.drawString(message.substring(j * lineChars, lineChars), 0, (int)(acent + j * metrics.getHeight()));
		//}	
		
	}
	
	public void drawCropMarks(Graphics2D g2, PageFormat pf){
		final double C = 36;
		double w = pf.getImageableWidth();
		double h = pf.getImageableHeight();
		g2.draw(new Line2D.Double(0, 0, 0,C));
		g2.draw(new Line2D.Double(0, 0, C,0));
		g2.draw(new Line2D.Double(w, 0, w,C));
		g2.draw(new Line2D.Double(w, 0, w - C,0));
		g2.draw(new Line2D.Double(0, h, 0,h - C));
		g2.draw(new Line2D.Double(0, h, C,h));
		g2.draw(new Line2D.Double(w, h, w,h - C));
		g2.draw(new Line2D.Double(w, h, w- C,h));
	}
	
	private String message;
	//private double scale;
}

class CoverPage implements Printable{
	public CoverPage(String t){
		title = t;
	}
	
	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2)
			throws PrinterException {
		// TODO Auto-generated method stub
		if(arg2 > 1) return Printable.NO_SUCH_PAGE;
		Graphics2D g2 = (Graphics2D)arg0;
		g2.setPaint(Color.BLACK);
		g2.translate(arg1.getImageableX(), arg1.getImageableY());
		FontRenderContext context = g2.getFontRenderContext();
		Font f = g2.getFont();
		TextLayout layout = new TextLayout(title, f, context);
		float ascent = layout.getAscent();
		g2.drawString(title, 0, ascent);
		return Printable.PAGE_EXISTS;
	}
	
	private String title;
}

class PrintPreviewDialog extends JDialog{
	public PrintPreviewDialog(Printable p, PageFormat pf, int pages){
		Book book = new Book();
		book.append(p, pf, pages);
		layoutUI(book);
	}
	
	public PrintPreviewDialog(Book b){
		layoutUI(b);
	}
	
	public void layoutUI(Book book){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		canvas = new PrintPreviewCanvas(book);
		add(canvas, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		JButton nextButton = new JButton("Next");
		buttonPanel.add(nextButton);
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				canvas.flipPage(1);
			}
		});
		
		JButton previousButton = new JButton("Previous");
		buttonPanel.add(previousButton);
		previousButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				canvas.flipPage(-1);
			}
		});
		
		JButton closeButton = new JButton("Close");
		buttonPanel.add(closeButton);
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		add(buttonPanel,BorderLayout.SOUTH);
	}
	
	private PrintPreviewCanvas canvas;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
}

class PrintPreviewCanvas extends JComponent{
	
	public PrintPreviewCanvas(Book b){
		book = b;
		currentPage = 0;
	}
	
	@Override
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		Graphics2D g2 = (Graphics2D)arg0;
		PageFormat pageFormat = book.getPageFormat(currentPage);
		
		double xoff;
		double yoff;
		double scale;
		double px = pageFormat.getWidth();
		double py = pageFormat.getHeight();
		double sx = getWidth() - 1;
		double sy = getHeight() - 1;
		
		if(px / py < sx / sy){
			scale = sy / py;
			xoff = 0.5 * (sx - scale * px);
			yoff = 0;
		}
		else{
			scale = sx / px;
			xoff = 0;
			yoff = 0.5 * (sy - scale * py);
		}
		g2.translate((float)xoff, (float)yoff);
		g2.scale((float)scale, (float)scale);
		
		Rectangle2D page = new Rectangle2D.Double(0, 0, px, py);
		g2.setPaint(Color.white);
		g2.fill(page);
		g2.setPaint(Color.black);
		g2.draw(page);
		
		Printable printable = book.getPrintable(currentPage);
		try {
			printable.print(g2, pageFormat, currentPage);
		} catch (PrinterException e) {
			// TODO: handle exception
			g2.draw(new Line2D.Double(0, 0, px, py));
			g2.draw(new Line2D.Double(px, 0, py, 0));
		}
	}
	
	public void flipPage(int by){
		int newPage = currentPage + by;
		if(0 <= newPage && newPage < book.getNumberOfPages()){
			currentPage = newPage;
			repaint();
		}
	}
	
	private Book book;
	private int currentPage;
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
