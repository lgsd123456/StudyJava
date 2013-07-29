import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

import javax.swing.text.AsyncBoxView.ChildLocator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class ParseXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("font.xml"));
			Element root = document.getDocumentElement();
			PrintChild(root);
//			NodeList children = root.getChildNodes();
//			for(int i = 0; i < children.getLength(); i++){
//				Node child = children.item(i);
//				if(child instanceof Element){
//					System.out.print("  " + child.getNodeName());
//					PrintAttr(child);
//					PrintText(child);
//				}
//			}
		} catch (ParserConfigurationException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (SAXException e) {
			// TODO: handle exception
		}
		catch (IOException e) {
			// TODO: handle exception
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
