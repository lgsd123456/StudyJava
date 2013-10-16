import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;


public class TestJNDI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "sd";
		if(args.length > 0)
			name = args[0];
		
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
			props.put(Context.PROVIDER_URL, "file:///C:/lg");
			
			if(args.length > 1)
				props.put(Context.INITIAL_CONTEXT_FACTORY, args[1]);
			if(args.length > 2)
				props.put(Context.PROVIDER_URL, args[2]);
			
			Context initialContext = new InitialContext(props);
			Object obj = initialContext.lookup(name);
			if(name.equals(""))
				System.out.println("Looked up the initial context");
			else {
				System.out.println(name + " is bound to: " + obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
