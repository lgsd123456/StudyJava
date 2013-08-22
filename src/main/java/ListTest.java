import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Locale;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AbstractDocument.Content;


public class ListTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new ListTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	public ListTest(){
		setTitle("ListTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		FinalListPanel panel = new FinalListPanel();
		add(panel);
	}
	
   public static final int DEFAULT_WIDTH = 400;
   public static final int DEFAULT_HEIGHT = 200;
}

class FinalListPanel extends JPanel{
	public FinalListPanel(){
		//setLayout(new BorderLayout());
		Locale[] words = {Locale.CHINA, Locale.US, Locale.GERMANY, Locale.UK};
		
		
		JList worList = new JList(words);
		worList.setCellRenderer(new MyCellRender());
		
		JScrollPane scrollPane = new JScrollPane(worList);
		add(scrollPane);
	}
	
	static class MyCellRender extends JComponent implements ListCellRenderer{
		@Override
		public Component getListCellRendererComponent(JList arg0, Object arg1,
				int arg2, boolean arg3, boolean arg4) {
			// TODO Auto-generated method stub
			Locale locale = (Locale)arg1;
			value = locale.getDisplayName(locale);
			font = new Font("SansSerif", Font.BOLD, 20);
			backColor = arg3 ? arg0.getSelectionBackground() : arg0.getBackground();
			foreColor = arg3 ? arg0.getSelectionForeground() : arg0.getForeground();
			return this;
		}
		
		public void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D)g;
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bound = font.getStringBounds(value, context);
			int baseY = (int)(-bound.getY());
			g2.setPaint(backColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			g2.setPaint(foreColor);
			g2.drawString(value, 0, baseY);
		}

		public Dimension getPreferredSize(){
			Graphics g = getGraphics();
			FontMetrics fm = g.getFontMetrics(font);
			return new Dimension(fm.stringWidth(value), fm.getHeight());
		}
		
		private Font font;
		private String value;
		private Color backColor;
		private Color foreColor;
	}
}


class MiddleListPanel extends JPanel{
	public MiddleListPanel(){
		setLayout(new BorderLayout());
		//String[] words = {"quick", "brown", "hungry", "wild", "silent", "huge", "private", "abstract", "static", "final"};
		display = new JLabel();
		
		JList worList = new JList(new DataModel(3));
		worList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		worList.setPrototypeCellValue("www");
		//worList.setVisibleRowCount(4);
		
		worList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				JList selectedList = (JList)e.getSource();
				Object[] objects = selectedList.getSelectedValues();
				StringBuilder builder = new StringBuilder();
				for(Object o : objects)
					builder.append(o.toString() + ",");
				display.setText(builder.toString());
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(worList);
		add(scrollPane);
		add(display, BorderLayout.SOUTH);
	}
	
	static class DataModel extends AbstractListModel{
		public DataModel(int n){
			length = n;
		}
		
		public int getSize(){
			return (int)Math.pow(26, length);
		}
		
		//这是我的实现，哎呀，太复杂，涉及3个循环
//		public Object getElementAt(int i){
//			int count = -1;
//			StringBuilder builder = new StringBuilder();
//			for(char c = 'a'; c <= 'z'; c = (char)(c + 1))
//				for(char d = 'a'; d <= 'z'; d = (char)(d + 1))
//					for(char e = 'a'; e <= 'z'; e = (char)(e + 1)){
//						count++;
//						if(count == i) builder.append(c).append(d).append(e);
//					}
//			return builder.toString();
//		}
		
		//这是corejava中的实现，太棒了
		public Object getElementAt(int n){
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < length; i++){
				char c = (char)(FIRST + n % (LAST - FIRST + 1));
				builder.insert(0, c);
				n = n / ((LAST - FIRST + 1));
			}
			return builder.toString();
		}
		
		private int length;
		public static final char FIRST = 'a';
		public static final char LAST = 'z';
	}
	
	private JLabel display;
}

class SimpleListPanel extends JPanel{
	public SimpleListPanel(){
		setLayout(new BorderLayout());
		String[] words = {"quick", "brown", "hungry", "wild", "silent", "huge", "private", "abstract", "static", "final"};
		display = new JLabel();
		
		JList worList = new JList(words);
		worList.setVisibleRowCount(4);
		
		worList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				JList selectedList = (JList)e.getSource();
				Object[] objects = selectedList.getSelectedValues();
				StringBuilder builder = new StringBuilder();
				for(Object o : objects)
					builder.append(o.toString() + ",");
				display.setText(builder.toString());
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(worList);
		add(scrollPane);
		add(display, BorderLayout.SOUTH);
	}
	
	private JLabel display;
}
