import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import javax.swing.JFrame;
import javax.swing.JPanel;


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
		add(panel);
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
		
		Shape oldClip = g2.getClip();
		FontRenderContext context = g2.getFontRenderContext();
		Font font = new Font("Serif", Font.ITALIC, 200);
		TextLayout layout = new TextLayout("Hello", font, context);
		
		AffineTransform transform = AffineTransform.getTranslateInstance(0, 200);
		Shape outline = layout.getOutline(transform);
		g2.clip(outline);
		g2.draw(outline);
		
		Point2D p = new Point2D.Double(0, 0);
		for(int i = 1; i <= 400; i++){
			double x = 400 - i;
			double y = i * 0.75;
			Point2D q = new Point2D.Double(x, y);
			g2.draw(new Line2D.Double(p, q));
		}
		//g2.clip(new Rectangle2D.Double(50, 50, 50, 50));
		//g2.draw(new Rectangle2D.Double(100, 100, 50, 50));
//		for (int i = 0; i < 100; i++) {
//			g2.drawLine(0, 0, 60, 60 + i);
//		}
		g2.setClip(oldClip);
	}
}
