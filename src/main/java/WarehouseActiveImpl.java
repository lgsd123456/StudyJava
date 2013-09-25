import java.io.IOException;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.util.Map;


public class WarehouseActiveImpl extends Activatable implements WarehouseActive{
	
	public WarehouseActiveImpl(ActivationID id, MarshalledObject<Map<String, Double>> param)throws RemoteException, ClassNotFoundException, IOException{
		super(id, 0);
		prices = param.get();
		System.out.println("WarehouseActive implementation constructed");
	}
	
	@Override
	public double getPrice(String description) throws RemoteException {
		// TODO Auto-generated method stub
		Double price = prices.get(description);
		return price == null ? 0 : price;
	}
	
	private Map<String, Double> prices;
}
