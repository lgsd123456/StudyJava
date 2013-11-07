package lg.java.ent.in;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccountImpl extends UnicastRemoteObject implements Account {

	private float mBalance = 0;
	private String mName = "";
	
	public AccountImpl(String name)throws RemoteException{
		mName = name;
	}
	
	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return mName;
	}

	@Override
	public float getBalance() throws RemoteException {
		// TODO Auto-generated method stub
		return mBalance;
	}

	@Override
	public void withdraw(float amt) throws RemoteException,
			InsufficientFundsException {
		// TODO Auto-generated method stub
		if(mBalance >= amt){
			mBalance -= amt;
			System.out.println("--> Withdrew " + amt + " from account " + getName());
			System.out.println("    New balance: " + getBalance());
		}else {
			throw new InsufficientFundsException("Withdrawal request of " + amt + " exceeds balance of " + getBalance());
		}
	}

	@Override
	public void deposit(float amt) throws RemoteException {
		// TODO Auto-generated method stub
		mBalance += amt;
		System.out.println("--> Deposited " + amt + " into account " + getName());
		System.out.println("    New balance: " + getBalance());
	}

	@Override
	public void transfer(float amt, Account src) throws RemoteException,
			InsufficientFundsException {
		// TODO Auto-generated method stub
		if(src.getBalance() < amt)
			throw new InsufficientFundsException("Source account balance " + "is insufficient.");
		else{
			src.withdraw(amt);
			this.deposit(amt);
			System.out.println("--> Transferred " + amt + " from account " + src.getName());
			System.out.println("    New balance: " + getBalance());
		}
			
	}

}
