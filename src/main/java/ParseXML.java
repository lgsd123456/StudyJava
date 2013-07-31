import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

import javax.management.modelmbean.XMLParseException;
import javax.swing.text.AsyncBoxView.ChildLocator;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;


public class ParseXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String str = "ss   \n   ";
		//String conString = str.trim();
		
		
		//DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			//进行DTD验证
//			factory.setValidating(true);
			
			//打开名字空间支持
//			factory.setNamespaceAware(true);
//			
//			final String JAXP_SHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
//			final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
//			factory.setAttribute(JAXP_SHEMA_LANGUAGE, W3C_XML_SCHEMA);
			
			//忽略空白文本节点
//			factory.setIgnoringElementContentWhitespace(true);
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document document = builder.parse(new File("font.xml"));
//			Element root = document.getDocumentElement();
			
			//定位文本数据
//			XPathFactory xpFactory = XPathFactory.newInstance();
//			XPath path = xpFactory.newXPath();
//			LocateValue(path, root, "/Textfont/font[1]/fontname", XPathConstants.STRING);
//			LocateValue(path, root, "/Textfont/font[1]/fontsize", XPathConstants.NUMBER);
//			LocateValue(path, root, "/Textfont/font/fontname", XPathConstants.NODESET);
			//PrintChild(root);
//			NodeList children = root.getChildNodes();
//			for(int i = 0; i < children.getLength(); i++){
//				Node child = children.item(i);
//				if(child instanceof Element){
//					System.out.print("  " + child.getNodeName());
//					PrintAttr(child);
//					PrintText(child);
//				}
//			}
			
			//SAX解析
//			SAXParserFactory factory = SAXParserFactory.newInstance();
//			SAXParser parser = factory.newSAXParser();
//			
//			
//			DefaultHandler handler = new DefaultHandler(){
//				String strName = "";
//				
//				public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) throws SAXException{
//					strName = qname;
//					if(qname.equals("fontname"))
//						System.out.print("<" + qname + ">");
//				}
//				
//				public void characters(char[] ch, int start, int length){
//					if(strName.equals("fontname")){
//						System.out.print(new String(ch, start, length).trim());
//					}
//				}
//				
//				public void endElement(String uri, String localName, String qName){
//					if(qName.equals("fontname"))
//						System.out.println("</" + qName + ">");
//				}
//			};
//			
//			parser.parse(new File("font.xml"), handler);
			
			
			//STAX解析
//			XMLInputFactory factory = XMLInputFactory.newInstance();
//			XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("font.xml"));
//			String strName = "";
//			while(reader.hasNext()){
//				int event = reader.next();
//				if(event == XMLStreamConstants.START_ELEMENT){
//					strName = reader.getName().toString();
//					if(strName.equals("fontname"))
//						System.out.print("<" + strName + ">");
//				}
//				
//				if(event == XMLStreamConstants.CHARACTERS){
//					if(strName.equals("fontname"))
//						System.out.print(reader.getText().trim());
//				}
//				
//				if(event == XMLStreamConstants.END_ELEMENT){
//					strName = reader.getName().toString();
//					if(strName.equals("fontname"))
//						System.out.println("</" + strName + ">");
//				}
//			}
			
			//Generating new Document use DOM
//			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//			Document document = builder.newDocument();
//			Element rootElement = document.createElement("lg");
//			rootElement.setAttribute("age", "31");
//			rootElement.setAttribute("sex", "male");
//			Element chiElement = document.createElement("invengo");
//			chiElement.setAttribute("simple", "ywg");
//			Element grandChiElement = document.createElement("fromyear");
//			Text text = document.createTextNode("2011");
//			document.appendChild(rootElement);
//			rootElement.appendChild(chiElement);
//			chiElement.appendChild(grandChiElement);
//			grandChiElement.appendChild(text);
//			
//			Transformer transformer = TransformerFactory.newInstance().newTransformer();
//			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd");
//			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD SVG 20000802//EN");
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//			transformer.transform(new DOMSource(document), new StreamResult(new File("lg.xml")));
			
			//Generating new Document use STAX api
			XMLOutputFactory factory = XMLOutputFactory.newFactory();
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("sd.xml"));
			writer.writeStartDocument();
			writer.writeStartElement("sd");
			writer.writeAttribute("age", "30");
			writer.writeAttribute("sex", "female");
			writer.writeStartElement("foremost");
			writer.writeStartElement("fromyear");
			writer.writeCharacters("2006");
			writer.writeEndDocument();
		}
		catch (XMLStreamException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void LocateValue(XPath path, Node doc, String expression, QName tName){
		try {
			if(tName == XPathConstants.STRING){
				String str = (String)path.evaluate(expression, doc, tName);
				System.out.println("STRING: " + str);
			}
			else if(tName == XPathConstants.NODE){
				Node str = (Node)path.evaluate(expression, doc, tName);
				System.out.println("NODE: " + str.getNodeName());
			}
			else if(tName == XPathConstants.NODESET){
				NodeList str = (NodeList)path.evaluate(expression, doc, tName);
				System.out.print("NODESET:");
				for(int i = 0; i < str.getLength(); i++)
					System.out.print(str.item(i).getNodeName() + ",");
				System.out.println();
			}
			else if(tName == XPathConstants.NUMBER){
				Number str = (Number)path.evaluate(expression, doc, tName);
				System.out.println("NUMBER: " + str);
			}
			else if(tName == XPathConstants.BOOLEAN){
				Boolean str = (Boolean)path.evaluate(expression, doc, tName);
				System.out.println("BOOLEAN: " + str);
			}
			else{
				System.out.println("Error XPathConstants type!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public static void PrintChild(Node node){
		int count = 0;
		Throwable atThrowable = new Throwable();
		StackTraceElement[] element = atThrowable.getStackTrace();
		for (StackTraceElement stackTraceElement : element) {
			String methodName = stackTraceElement.getMethodName();
			if(methodName.contains("PrintChild")) count++;
		}
		for(int j = 1; j < count; j++) System.out.print("  ");
		System.out.print(node.getNodeName());
		PrintAttr(node);
		PrintText(node);
		NodeList children = node.getChildNodes();
		if(children.getLength() < 1) return;
		for(int i = 0; i <children.getLength(); i++){
			Node node2 = children.item(i);
			if(node2 instanceof Element){
				PrintChild(node2);
			}
		}
	}
	
	
	public static void PrintText(Node node){
		if(!(node instanceof Element)) return;
		Node childNode = node.getFirstChild();
		if(childNode == null || !(childNode instanceof Text) || node.getChildNodes().getLength() > 1){
			System.out.println();
			return;
		}else {
			Text text = (Text)childNode;
			System.out.println(": " + text.getData().trim());
		}
		
	}
	
	public static void PrintAttr(Node node){
		if(!(node instanceof Element)) return;
		Element element = (Element)node;
		NamedNodeMap attributes = element.getAttributes();
		if(attributes == null){
			return;
		}
		else {
			for(int i = 0; i < attributes.getLength(); i++){
				if(i == 0) System.out.print("(");
				if(i > 0) System.out.print(",");
				Node attribute = attributes.item(i);
				String name = attribute.getNodeName();
				String value = attribute.getNodeValue();
				System.out.print(name + "=" + value);
				if((i + 1) == attributes.getLength()) System.out.print(")");
			}
		}	
	}

}
