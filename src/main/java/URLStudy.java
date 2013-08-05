import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class URLStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
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
			URL url = new URL("http://192.168.7.248:8080/nexus-webapp/service/local/lucene/search?_dc=1375429644266&q=dom4j&collapseresults=true");
			
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
			
			URLConnection connection = url.openConnection();
			//connection.setDoOutput(true);
			connection.connect();
			//PrintWriter out = new PrintWriter(connection.getOutputStream());
			//out.print(b);
			Scanner in = new Scanner(connection.getInputStream());
			for(int n = 1; in.hasNextLine() && n <= 100; n++)
				System.out.println(in.nextLine());
			if(in.hasNextLine()) System.out.println("...");
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
