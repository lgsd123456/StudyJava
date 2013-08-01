import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class Dom4jXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File("font.xml"));
			Element rootElm = document.getRootElement();
			Element fontElm = rootElm.addElement("font");
			Element nameElm = fontElm.addElement("fontname");
			nameElm.setText("Scans Serif");
			Element sizeElm = fontElm.addElement("fontsize");
			sizeElm.setText("40");
			List nodes = rootElm.elements("font");
			for(Iterator it = nodes.iterator(); it.hasNext();){
				Element elm = (Element)it.next();
//				List subnodes = elm.elements("fontname");
//				for(Iterator it2 = subnodes.iterator(); it2.hasNext();){
//					Element elm2 = (Element)it2.next();
//					System.out.println(elm2.getText());
//				}
				for(Iterator iterator = elm.elementIterator();iterator.hasNext();){
					Element elm2 = (Element)iterator.next();
					System.out.println(elm2.getText());
				}
			}
			XMLWriter writer = new XMLWriter(new FileWriter("font.xml"));
			writer.write(document);
			writer.close();
		} catch (DocumentException e) {
			// TODO: handle exception
		}
		catch (IOException e){
			
		}
		
	}

}
