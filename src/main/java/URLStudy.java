import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.StreamPrintService;
import javax.print.StreamPrintServiceFactory;


public class URLStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//study clipboard
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			DataFlavor[] flavors = clipboard.getAvailableDataFlavors();
			for(DataFlavor flavor : flavors)
				System.out.println(flavor);
			
			//study PrintServiceTest
//			DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
//			String mimeType = "application/postscript";
//			StreamPrintServiceFactory[] factories = StreamPrintServiceFactory.lookupStreamPrintServiceFactories(flavor, mimeType);
//			OutputStream out = new FileOutputStream("ff");
//			StreamPrintService service = factories[0].getPrintService(out);
//			
//			DocPrintJob job = service.createPrintJob();
//			FileInputStream in = new FileInputStream("face.gif");
//			DocFlavor flavor1 = DocFlavor.INPUT_STREAM.GIF;
//			Doc doc = new SimpleDoc(in, flavor1, null);
//			job.print(doc, null);
			
//			DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST;
//			PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
//			if(services.length == 0){
//				System.out.println("No printer for flavor " + flavor);
//				System.exit(0);
//			}
//			
//			FileInputStream in = new FileInputStream("C:\\Data\123.txt");
//			Doc doc = new SimpleDoc(in, flavor, null);
//			PrintService subService = null;
//			for(PrintService service : services){
//				if(service.getName().contains("PDF"))
//					subService = service;
//			}
//			DocPrintJob job = subService.createPrintJob();
//			job.print(doc, null);
			
//			String message = "11111222222333333344444555555";
//			String sub1 = message.substring(1,2);
//			String sub2 = message.substring(2,3);
//			String sub3 = message.substring(3);
			
//			URI uri = new URI("http://java.sun.com/j2se/1.3/docs/guide/collections/designfaq.html#28");
//			String scheme = uri.getScheme();
//			String specialPart = uri.getSchemeSpecificPart();
//			String hostInfo = uri.getHost();
//			int port = uri.getPort();
//			String path = uri.getPath();
//			String query = uri.getQuery();
//			String fraqString = uri.getFragment();
//			
//			
//			URI uri2 = new URI("../../../docs/guide/collections/designfaq.html#28");
//			URI uri3 = uri2.normalize();
//			
//			URI uri4 = uri.resolve(uri2);
//			//URI uri5 = uri2.relativize(uri);
//			
//			System.out.println();
			//URL url = new URL("http://192.168.7.248:8080/nexus-webapp/service/local/lucene/search?_dc=1375429644266&q=dom4j&collapseresults=true");
			
//			URLConnection connection  = url.openConnection();
//			Map<String, List<String>> mapRequests = connection.getRequestProperties();
//			for(Map.Entry<String, List<String>> entry: mapRequests.entrySet()){
//				System.out.print(entry.getKey() + ": ");
//				for(String value : entry.getValue())
//					System.out.print(value + ",");
//				System.out.println();
//			}
//			connection.connect();
//			Map<String, java.util.List<String>> mapFields = connection.getHeaderFields();
//			for(Map.Entry<String, List<String>> entry: mapFields.entrySet()){
//				System.out.print(entry.getKey() + ": ");
//				for(String value : entry.getValue())
//					System.out.print(value + ",");
//				System.out.println();
//			}
//			
//			Scanner in = new Scanner(connection.getInputStream());
//			for(int n = 1; in.hasNextLine() && n <= 100; n++)
//				System.out.println(in.nextLine());
//			if(in.hasNextLine()) System.out.println("...");
			
			//URLConnection connection = url.openConnection();
			//connection.setDoOutput(true);
			//connection.connect();
			//PrintWriter out = new PrintWriter(connection.getOutputStream());
			//out.print(b);
//			Scanner in = new Scanner(connection.getInputStream());
//			for(int n = 1; in.hasNextLine() && n <= 100; n++)
//				System.out.println(in.nextLine());
//			if(in.hasNextLine()) System.out.println("...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
