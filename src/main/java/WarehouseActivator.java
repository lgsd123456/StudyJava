import java.io.File;
import java.io.IOException;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class WarehouseActivator {

	public static void main(String[] args) throws RemoteException, NamingException, ActivationException,IOException{
		// TODO Auto-generated method stub
		System.out.println("Constructing activation descriptors...");

		Properties props = new Properties();
		props.put("java.security.policy", new File("server.policy").getCanonicalPath());
		ActivationGroupDesc groupDesc = new ActivationGroupDesc(props, null);
		ActivationGroupID id = ActivationGroup.getSystem().registerGroup(groupDesc);
		
		Map<String, Double> prices = new HashMap<String, Double>();
		prices.put("Blachwell Toaster", 24.95);
		prices.put("ZapXpress Microware Oven", 49.95);
		
		MarshalledObject<Map<String, Double>> param = new MarshalledObject<Map<String,Double>>(prices);
		String codebase = "http://192.168.6.19:8080/";
		
		ActivationDesc desc = new ActivationDesc(id, "WarehouseActiveImpl", codebase, param);
		WarehouseActive centralWarehouseActive = (WarehouseActive)Activatable.register(desc);
		
		System.out.println("Binding activable implementation to registry...");
		Context namingContext = new InitialContext();
		namingContext.bind("rmi://192.168.6.19:1099/central_warehouseactive", centralWarehouseActive);
		System.out.println("Exiting...");
	}

}
