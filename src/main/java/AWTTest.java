import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.RescaleOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.ECFieldF2m;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class AWTTest extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new AWTTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	public AWTTest(){
		setTitle("AWTTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JPanel panel = new PaintPanel();
		add(new JScrollPane(panel));
	}

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;
}

class PaintPanel extends JPanel{
	
	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		Graphics2D g2 = (Graphics2D)arg0;
		
		//RoundRectangle2D r = new RoundRectangle2D.Double(10, 15, 100, 50, 20, 20);
		//g2.draw(r);
		
//		Arc2D a = new Arc2D.Double(150, 100, 100, 50, 45.0, 270.0, Arc2D.PIE);
//		g2.draw(a);
//		
//		GeneralPath path = new GeneralPath();
//		path.moveTo(200, 200);
//		path.lineTo(210.0, 200.0 - 10 * Math.sqrt(3.0));
//		path.lineTo(220.0, 200);
//		path.closePath();
//		path.append(r, false);
//		g2.draw(path);
//		
//		Area a1 = new Area(new Ellipse2D.Double(200, 200, 50, 50));
//		Area a2 = new Area(new Rectangle2D.Double(220, 220, 50, 60));
//		a1.intersect(a2);
//		g2.fill(a1);
		
//		g2.setStroke(new BasicStroke(10.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
//		//g2.draw(new Line2D.Double(100, 100, 100, 200));
//		g2.draw(new Rectangle2D.Double(100, 100, 50 , 30));
//		g2.setStroke(new BasicStroke(10.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
//		//g2.draw(new Line2D.Double(120, 100, 120, 200));
//		g2.draw(new Rectangle2D.Double(100, 150, 50 , 30));
//		g2.setStroke(new BasicStroke(10.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//		//g2.draw(new Line2D.Double(140, 100, 140, 200));
//		g2.draw(new Rectangle2D.Double(100, 200, 50 , 30));
//		
//		g2.setPaint(Color.red);
//		g2.setStroke(new BasicStroke(10.0F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0F, new float[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, 0));
//		g2.draw(new Rectangle2D.Double(200, 200, 100 , 50));
		
		//g2.setPaint(new GradientPaint(100, 150, Color.RED, 200, 150, Color.YELLOW, true));
		//g2.scale(2, 2);
		//g2.fill(new Rectangle2D.Double(200, 100, 50, 50));
		//AffineTransform transform = new AffineTransform(0.5, 2, 1, 0.5, 0.5, 1);
		//AffineTransform transform = AffineTransform.getRotateInstance(Math.asin(Math.sqrt(2)/2), 200, 100);
		//AffineTransform transform = AffineTransform.getShearInstance(0.5, 0.5);
		//AffineTransform transform = AffineTransform.getTranslateInstance(20, 20);
		//AffineTransform oldTransform = g2.getTransform();
		//g2.transform(transform);
		
//		g2.rotate(Math.asin(0.5));
		//g2.fill(new Rectangle2D.Double(200, 100, 50, 50));
		//g2.setTransform(oldTransform);
		
		//剪贴测试
//		Shape oldClip = g2.getClip();
//		FontRenderContext context = g2.getFontRenderContext();
//		Font font = new Font("Serif", Font.ITALIC, 200);
//		TextLayout layout = new TextLayout("Hello", font, context);
//		
//		AffineTransform transform = AffineTransform.getTranslateInstance(0, 200);
//		Shape outline = layout.getOutline(transform);
//		g2.clip(outline);
//		g2.draw(outline);
//		
//		Point2D p = new Point2D.Double(0, 0);
//		for(int i = 1; i <= 400; i++){
//			double x = 400 - i;
//			double y = i * 0.75;
//			Point2D q = new Point2D.Double(x, y);
//			g2.draw(new Line2D.Double(p, q));
//		}
		//g2.clip(new Rectangle2D.Double(50, 50, 50, 50));
		//g2.draw(new Rectangle2D.Double(100, 100, 50, 50));
//		for (int i = 0; i < 100; i++) {
//			g2.drawLine(0, 0, 60, 60 + i);
//		}
		//g2.setClip(oldClip);
		
		
		//混合测试
//		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
//		Graphics2D gImage = image.createGraphics();
//		Color black1 = new Color(1.0f, 0.0f, 0.0f,0.5f);
//		gImage.setPaint(black1);
//		gImage.fill((new Ellipse2D.Double(100, 100, 90, 50)));
//		//Color red1 = new Color(1.0f, 0.0f, 0.0f, 0.75f);
//		//g2.setPaint(red1);
//		int rule = AlphaComposite.XOR;
//		float alpha = 0.5f;
//		gImage.setComposite(AlphaComposite.getInstance(rule, alpha));
//		gImage.setPaint(Color.BLUE);
//		gImage.fill((new Rectangle2D.Double(130, 130, 80, 70)));
//		g2.drawImage(image, null, 0, 0);
		
		
		//Rendering Hints
//		g2.draw((new Ellipse2D.Double(100, 100, 90, 50)));
//		g2.drawString("Hello", 200, 100);
//		RenderingHints hints = new RenderingHints(null);
//		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		g2.setRenderingHints(hints);
//		g2.draw((new Ellipse2D.Double(100, 200, 90, 50)));
//		g2.drawString("Hello", 200, 200);
		try {
//			BufferedImage image = ImageIO.read(new File("face.gif"));
//			g2.drawImage(image, null, 0, 0);
//			
//			ImageReader reader = null;
//			Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("JPG");
//			int x = 100, y = 100;
//			while(iter.hasNext()){
//				reader = iter.next();
//				ImageReaderSpi spi = reader.getOriginatingProvider();
//				g2.drawString(spi.toString(), x, y);
//				y += 20;
//			}
//			
//			String[] extensions = ImageIO.getWriterFileSuffixes();
//			g2.drawString(Arrays.deepToString(extensions), 100, 200);
			
//			ImageReader reader = null;
//			Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("GIF");
//			if(iter.hasNext()) reader = iter.next();
//			FileInputStream in = new FileInputStream("e-mail.gif");
//			ImageInputStream imageIn = ImageIO.createImageInputStream(in);
//			reader.setInput(imageIn, false);
//			int n = reader.getNumImages(true);
//			int x = 100, y = 100;
//			for(int j = 0; j < n; j++){
//				BufferedImage image = reader.read(j);
//				g2.drawImage(image, null, x, y);
//				x += 50;
//				if((j+1) % 5 == 0) {x = 100; y += 50;}
//			}
			
//			ImageWriter writer = null;
//			Iterator<ImageWriter> writerIter = ImageIO.getImageWritersByFormatName("GIF");
//			if(writerIter.hasNext()) writer = writerIter.next();
//			
//			ImageOutputStream imageOut = ImageIO.createImageOutputStream(new FileOutputStream("ecaf.gif"));
//			writer.setOutput(imageOut);
//			
//			BufferedImage image1 = ImageIO.read(new File("face.gif"));
//			writer.write(new IIOImage(image1, null, null));
//			int i = 0;
//			int x = 100, y = 100;
//			while(true){
//				BufferedImage image = reader.read(i);
//				g2.drawImage(image, null, x, y);
//				x += 50;
//				if((i+1) % 5 == 0) {x = 100; y += 50;}
//				i++;
//			}
			
			BufferedImage image2 = ImageIO.read(new File("cj8v2.png"));
//			BufferedImage image3 = new BufferedImage(image2.getWidth(), image2.getHeight(), image2.getType());
//			AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(30), image2.getWidth() / 2, image2.getHeight() / 2);
//			AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
//			op.filter(image2, image3);
//			
//			g2.drawImage(image2, null, 100, 100);
//			g2.drawImage(image3, null, 100, 100 + image2.getWidth());
		
//			RescaleOp op2 = new RescaleOp(2.0f, 20.0f, null);
//			BufferedImage image4 = op2.filter(image2, null);
//			g2.drawImage(image4, null, 200, 100);
			
			//这个运行不了，报错Number of color/alpha components should be 3 but length of bits array is 1
			short[] negative = new short[256 * 1];
			for(int j = 0; j < 256; j++) negative[j] = (short)(255 - j);
			ShortLookupTable table = new ShortLookupTable(0, negative);
			LookupOp op3 = new LookupOp(table, null);
			BufferedImage image5 = new BufferedImage(image2.getWidth(null), image2.getHeight(null), BufferedImage.TYPE_INT_RGB);
			image5 = op3.filter(image2, null);
			g2.drawImage(image5, 0, 0, null);
			
//			float[] elements = {0.0f, -1.0f, 0.0f, -1.0f, 4.0f, -1.0f, 0.0f, -1.0f, 0.0f};
//			Kernel kernel = new Kernel(3, 3, elements);
//			ConvolveOp op4 = new ConvolveOp(kernel);
//			BufferedImage image6 = op4.filter(image2, null);
//			g2.drawImage(image6, null, 300, 100);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
