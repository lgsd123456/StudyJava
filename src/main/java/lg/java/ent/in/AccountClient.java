package lg.java.ent.in;

import java.rmi.Naming;

public class AccountClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Account jimAcct =(Account)Naming.lookup("rmi://localhost:1099/JimF");
		 // Make deposit
          jimAcct.deposit(12000);

          // Report results and balance.
          System.out.println("Deposited 12,000 into account owned by " +
                             jimAcct.getName( ));
          System.out.println("Balance now totals: " + jimAcct.getBalance( ));

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
