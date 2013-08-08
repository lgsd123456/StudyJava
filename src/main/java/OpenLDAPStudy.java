import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.jar.Attributes.Name;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


public class OpenLDAPStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			Hashtable env = new Hashtable();
//			env.put(Context.SECURITY_PRINCIPAL, "cn=Manager,dc=mycompany,dc=com");
//			env.put(Context.SECURITY_CREDENTIALS, "secret");
//			DirContext initial = new InitialDirContext(env);
//			DirContext context = (DirContext)initial.lookup("ldap://192.168.8.223:389");
//			
//			Attributes attrs = context.getAttributes("uid=jqpublic,ou=people,dc=mycompany,dc=com");
//			Attribute commonNameAttribute = attrs.get("telephoneNumber");
//			System.out.println(commonNameAttribute);
//			NamingEnumeration<String> ids = attrs.getIDs();
//			while(ids.hasMore()){
//				System.out.print(ids.next() + ", ");
//			}
//			System.out.println();
//			
//			NamingEnumeration<? extends Attribute> attrEnum = attrs.getAll();
//			while(attrEnum.hasMore()){
//				System.out.println(attrEnum.next());
//			}
//			Properties props = new Properties();
//			props.put(Context.INITIAL_CONTEXT_FACTORY, 
//					"com.sun.jndi.fscontext.FSContextFactory");
//			props.put(Context.PROVIDER_URL, "file:///");
//			Context initialContext = new InitialContext(props);
//			
//			String name = "cj2v1.jpg";
//			Object object = initialContext.lookup(name);
//			
//			System.out.println(name + " is bound to: " + object);
			
			Locale myLocale = Locale.getDefault();
			System.out.println(myLocale);
			System.out.println(myLocale.getDisplayName());
			System.out.println(myLocale.getDisplayVariant());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
