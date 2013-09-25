import java.rmi.Remote;
import java.rmi.RemoteException;


public interface WarehouseActive extends Remote{
	double getPrice(String description) throws RemoteException;
}
