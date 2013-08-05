import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SocketChannelStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocketChannel socketChannel = ServerSocketChannel.open();
			socketChannel.bind(new InetSocketAddress(8189));
			ExecutorService service = Executors.newCachedThreadPool();
			
			while (true) {
				SocketChannel socketChannel2 = socketChannel.accept();
				MultipleTask sTask = new MultipleTask(socketChannel2);
				service.execute(sTask);
			}
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
	}
	
	static class MultipleTask implements Runnable{
		public MultipleTask(SocketChannel s){
			socket = s;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				try {
					OutputStream outStream = Channels.newOutputStream(socket);
					
					Scanner in = new Scanner(socket);
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
		
		private SocketChannel socket;
	}

}
