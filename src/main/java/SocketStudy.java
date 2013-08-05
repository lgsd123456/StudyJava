import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.xml.sax.InputSource;


public class SocketStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
//			Socket socket = new Socket("192.168.7.248", 8080);
//			try {
//				OutputStream outputStream = socket.getOutputStream();
//				PrintWriter printStream = new PrintWriter(outputStream, true);
//				printStream.println("GET / HTTP/1.0\r\n");
//				InputStream inputStream = socket.getInputStream();
//				Scanner in = new Scanner(inputStream);
//				while(in.hasNextLine()){
//					String line = in.nextLine();
//					System.out.println(line);
//				}
//			} 
//			finally{
//				socket.close();
//			}
			
//			InetAddress address = InetAddress.getByName("www.invengo.cn");
//			byte[] ipv4Byte = address.getAddress();
//			String ipv4Decimal = address.getHostAddress();
//			String ipv4HostName = address.getHostName();
//			String ipv4CanonicalString = address.getCanonicalHostName();
//			System.out.println(address.toString());
//			System.out.println(InetAddress.getLocalHost());
			
			ServerSocket s = new ServerSocket(8189);
			ExecutorService service = Executors.newCachedThreadPool();
			while(true){
				Socket socket = s.accept();
				MultipleTask sTask = new MultipleTask(socket);
				service.execute(sTask);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	static class MultipleTask implements Runnable{
		public MultipleTask(Socket s){
			socket = s;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				try {
					InputStream inStream = socket.getInputStream();
					OutputStream outStream = socket.getOutputStream();
					
					Scanner in = new Scanner(inStream);
					PrintWriter out = new PrintWriter(outStream, true);
					out.println("Hello,welcome connecting to 8189!");
					out.println("Please Enter BYE to safely exit!");
					
					boolean flag = true;
					while(flag && in.hasNextLine()){
						String line = in.nextLine();
						out.println("Echo: " + line);
						if(line.trim().equals("BYE")) flag = false;
					}
				} finally{
					socket.close();
				}
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}
		
		private Socket socket;
	}
}
