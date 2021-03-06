import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;


public class SimpleLoginModule implements LoginModule {

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void initialize(Subject arg0, CallbackHandler arg1,
			Map<String, ?> arg2, Map<String, ?> arg3) {
		// TODO Auto-generated method stub
		this.subject = arg0;
		this.callbackHandler = arg1;
		this.options = arg3;
	}

	@Override
	public boolean login() throws LoginException {
		// TODO Auto-generated method stub
		if(callbackHandler == null) throw new LoginException("no handler");
		
		NameCallback nameCall = new NameCallback("username: ");
		PasswordCallback passCall = new PasswordCallback("password: ", false);
		try {
			callbackHandler.handle(new Callback[]{nameCall, passCall});
		} catch (UnsupportedCallbackException e) {
			// TODO: handle exception
			LoginException e2 = new LoginException("Unsupported callback");
			e2.initCause(e);
			throw e2;
		}
		catch (IOException e) {
			// TODO: handle exception
			LoginException e2 = new LoginException("I/O exception in callback");
			e2.initCause(e);
			throw e2;
		}
		
		return checkLogin(nameCall.getName(), passCall.getPassword());
	}
	
	private boolean checkLogin(String username, char[] password) throws LoginException{
		try {
			Scanner in = new Scanner(new FileReader("" + options.get("pwfile")));
			while (in.hasNextLine()) {
				String[] inputs = in.nextLine().split("\\|");
				if(inputs[0].equals(username) && Arrays.equals(inputs[1].toCharArray(),  password)){
					String role = inputs[2];
					Set<Principal> principals = subject.getPrincipals();
					principals.add(new SimplePrincipal("username", username));
					principals.add(new SimplePrincipal("role", role));
					return true;
				}
			}
			in.close();
			return false;
		} catch (IOException e) {
			// TODO: handle exception
			LoginException e2 = new LoginException("Can't open password file");
			e2.initCause(e);
			throw e2;
		}
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map<String, ?> options;
}
