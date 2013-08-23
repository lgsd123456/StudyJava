import java.awt.Color;
import java.awt.EventQueue;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

import org.omg.CORBA.PRIVATE_MEMBER;


public class TextTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new TextTest();
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });
	}

	public TextTest(){
		setTitle("TextTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		DataDisplayPanel panel = new DataDisplayPanel();
		add(panel);
		pack();
	}
	
	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}

class DataDisplayPanel extends JPanel{
	public DataDisplayPanel(){
		JLabel redlLabel = new JLabel("Red: ");
		JLabel greenLabel = new JLabel("Green: ");
		JLabel blueLabel = new JLabel("Blue: ");
		redField = new JFormattedTextField(new InternationalFormatter(NumberFormat.getIntegerInstance()){
			protected DocumentFilter getDocumentFilter(){
				setOverwriteMode(false);
				return filter;
			}
			private DocumentFilter filter = new DigitFilter();
		});
		greenfField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		greenfField.setInputVerifier(new ImplementVerifier());
		//try {
			//格式太死，只能三位，并且是改写模式
			//blueField = new JFormattedTextField(new MaskFormatter("###"));
			blueField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		//} catch (ParseException e) {
			// TODO: handle exception
		//}
		
	
		DocumentListener listener = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				setBackColor();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				setBackColor();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		redField.setColumns(3);
		redField.setValue(new Integer(255));
		redField.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		
		greenfField.setColumns(3);
		greenfField.setValue(new Integer(255));
		blueField.setColumns(3);
		blueField.setValue(new Integer(255));
		setBackColor();
		redField.getDocument().addDocumentListener(listener);
		greenfField.getDocument().addDocumentListener(listener);
		blueField.getDocument().addDocumentListener(listener);
		add(redlLabel);
		add(redField);
		add(greenLabel);
		add(greenfField);
		add(blueLabel);
		add(blueField);
	}
	
	private void setBackColor(){
//		int r = Integer.parseInt(redField.getText().trim());
//		int g = Integer.parseInt(greenfField.getText().trim());
//		int b = Integer.parseInt(blueField.getText().trim());
		
		int r = ((Number)redField.getValue()).intValue();
		int g = ((Number)greenfField.getValue()).intValue();
		int b = ((Number)blueField.getValue()).intValue();
		if(r > 255 || g > 255 || b > 255) return;
		Color color = new Color(r, g, b);
		setBackground(color);
	}
	
	private JFormattedTextField redField;
	private JFormattedTextField greenfField;
	private JFormattedTextField blueField;
}

class DigitFilter extends DocumentFilter{
	@Override
	public void insertString(FilterBypass arg0, int arg1, String arg2,
			AttributeSet arg3) throws BadLocationException {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder(arg2);
		for(int i = builder.length() - 1; i >= 0; i--){
			int cp = builder.codePointAt(i);
			if(!Character.isDigit(cp) && cp != '-'){
				builder.deleteCharAt(i);
				if(Character.isSupplementaryCodePoint(cp)){
					i--;
					builder.deleteCharAt(i);
				}
			}
		}
		super.insertString(arg0, arg1, builder.toString(), arg3);
	}
	
	@Override
	public void replace(FilterBypass arg0, int arg1, int arg2, String arg3,
			AttributeSet arg4) throws BadLocationException {
		// TODO Auto-generated method stub
		if(arg3 != null){
			StringBuilder builder = new StringBuilder(arg3);
			for(int i = builder.length() - 1; i >= 0; i--){
				int cp = builder.codePointAt(i);
				if(!Character.isDigit(cp) && cp != '-'){
					builder.deleteCharAt(i);
					if(Character.isSupplementaryCodePoint(cp)){
						i--;
						builder.deleteCharAt(i);
					}
				}
			}
			arg3 = builder.toString();
		}
		super.replace(arg0, arg1, arg2, arg3, arg4);
	}
}

class ImplementVerifier extends InputVerifier{
	@Override
	public boolean verify(JComponent arg0) {
		// TODO Auto-generated method stub
		JFormattedTextField field = (JFormattedTextField)arg0;
		return field.isEditValid();
	}
}
