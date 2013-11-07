package lg.java.ent.in;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class AccountServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//System.setSecurityManager(new RMISecurityManager());
			AccountImpl acct = new AccountImpl("Jimf");
			Naming.rebind("rmi://localhost:1099/JimF", acct);
			System.out.println("Registered account as JimF.");
			
			Object dummy = new Object();
			synchronized (dummy) {
				try {
					dummy.wait();
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
