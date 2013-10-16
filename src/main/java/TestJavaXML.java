import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class TestJavaXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setValidating(true);
		
		XMLReader xmlReader = null;
		try {
			SAXParser saxParser = spf.newSAXParser();
			xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(new OrderHandler());
			xmlReader.setErrorHandler(new OrderErrorHandler());
			xmlReader.parse("orders.xml");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static class OrderHandler extends DefaultHandler{
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			System.out.println("Incoming Orders:");
		}
		
		
		@Override
		public void startElement(String arg0, String arg1, String arg2,
				Attributes arg3) throws SAXException {
			// TODO Auto-generated method stub
			if(arg2.equals("order")){
				System.out.print("\nNew Order Number " + arg3.getValue("idnumber") + 
						" for Customer Number " + arg3.getValue("custno"));
			}
			else if(arg2.equals("item")){
				System.out.print("\nLine Item: " + arg3.getValue("idnumber") + 
						" (Qty " + arg3.getValue("quantity") + ")");
			}
			else if(arg2.equals("shippingaddr")){
				System.out.print("\nShip by " + arg3.getValue("method") + " to:" );
			}
			else if(arg2.equals("handling")){
				System.out.print("\n\tHandling Instructions: ");
			}
		}
		
		@Override
		public void characters(char[] arg0, int arg1, int arg2)
				throws SAXException {
			// TODO Auto-generated method stub
			System.out.print(new String(arg0, arg1, arg2));
		}
	}
	
	private static class OrderErrorHandler implements ErrorHandler{
		@Override
		public void error(SAXParseException arg0) throws SAXException {
			// TODO Auto-generated method stub
			throw new SAXException(arg0);
		}
		
		@Override
		public void warning(SAXParseException arg0) throws SAXException {
			// TODO Auto-generated method stub
			System.out.println("\nParse Warning: " + arg0.getMessage());
		}
		
		@Override
		public void fatalError(SAXParseException arg0) throws SAXException {
			// TODO Auto-generated method stub
			throw new SAXException(arg0);
		}
	}
}
