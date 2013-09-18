import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;


public class SimpleCallbackHandler implements CallbackHandler {

	public SimpleCallbackHandler(String username, char[] password){
		this.username = username;
		this.password = password;
	}
	
	@Override
	public void handle(Callback[] arg0) throws IOException,
			UnsupportedCallbackException {
		// TODO Auto-generated method stub
		for(Callback callback : arg0){
			if(callback instanceof NameCallback){
				((NameCallback)callback).setName(username);
			}
			else if(callback instanceof PasswordCallback){
				((PasswordCallback)callback).setPassword(password);
			}
		}
	}

	private String username;
	private char[] password;
}
