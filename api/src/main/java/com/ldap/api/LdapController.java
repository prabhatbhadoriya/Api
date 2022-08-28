package com.ldap.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapController {
	
	
	DirContext connection;
	ArrayList<String> data = new ArrayList<String>();
	ArrayList<String> idData = new ArrayList<String>();
//	List<UserData> idData;
	
	public void newConnection() {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//		env.put(Context.PROVIDER_URL, "ldap://prabhat-VirtualBox:1389");
		env.put(Context.PROVIDER_URL, "ldap://192.168.1.116:1389");
		env.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
		env.put(Context.SECURITY_CREDENTIALS, "12345678");
		
//		Local Machine Ldap Server 
//		env.put(Context.PROVIDER_URL, "ldap://localhost:1389");
//		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
//		env.put(Context.SECURITY_CREDENTIALS, "secret");
		try {
			connection = new InitialDirContext(env);
			System.out.println("LDAP CONNECTED !");
			
			
			
		}
		catch(NamingException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	public ArrayList getUser() throws NamingException {
		newConnection();
		
		try {
			String[] attr1 = { "uid","cn","sn" };
			SearchControls controls = new SearchControls();
			controls.setReturningAttributes(attr1);
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			
//			NamingEnumeration result = connection.search("ou=users,ou=system","ObjectClass=*",controls);
			NamingEnumeration result = connection.search("ou=People,dc=example,dc=com","ObjectClass=*",controls);
			
			while(result.hasMore()) {
				SearchResult user = (SearchResult)result.next();
//				System.out.println(user);

				Attributes attr = (Attributes) user.getAttributes();
//				System.out.println(attr);
				
				for (NamingEnumeration e = attr.getAll(); e.hasMore(); data.add(e.next().toString()));
			            								
			}
			connection.close();
			System.out.println("Connection Closed!");

			System.out.println("This is String Array: "+ data);
			
			
		}catch(NamingException e){
			System.out.println(e.getMessage());
			System.out.println("Connection Close Successfully !");
		}
		return data;
		
		
	
	}
	
	
//	This Method for GetUserUid
	
	public ArrayList getUsrId(String id) throws NamingException {
		newConnection();
		try {
			
			String[] attr1 = { "uid","cn","sn" };
			SearchControls controls = new SearchControls();
			controls.setReturningAttributes(attr1);
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			
//			NamingEnumeration result = connection.search("ou=users,ou=system","ObjectClass=*",controls);
			NamingEnumeration result = connection.search("ou=People,dc=example,dc=com","uid="+id,controls);
			
			while(result.hasMore()) {
				SearchResult user = (SearchResult)result.next();
//				System.out.println(user);

				Attributes attr = (Attributes) user.getAttributes();
//				System.out.println(attr);
				
				for (NamingEnumeration e = attr.getAll(); e.hasMore(); idData.add(e.next().toString()));
			            								
			}
			connection.close();
			System.out.println("Connection Closed!");

			System.out.println("This is String Array: "+ idData);
			
			
		}catch(NamingException e){
			System.out.println(e.getMessage());
			System.out.println("Connection Close Successfully !");
		}
		return idData;
		
	}
	
	
//	This Method for add user 
	
	public ArrayList addUser(UserData udata) {
		newConnection();
		try {
			Attributes newuser = new BasicAttributes();
			Attribute usr = new BasicAttribute("objectClass");
			usr.add("inetOrgPerson");
			newuser.put(usr);
			
			newuser.put("cn",udata.getCn());
			newuser.put("sn", udata.getSn());
			
			
			
			
//			connection.createSubcontext("uid=17,ou=users,ou=system", newuser);
			connection.createSubcontext("uid="+udata.getUid()+",ou=People,dc=example,dc=com", newuser);
			System.out.println("User Added!");
			connection.close();
			System.out.println("Connection Closed !");
			getUser();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("User not Added !");
		}
		return data;
		
	}
	
//	This Function for Modify
	public void update(String uid,String key,String value) throws NamingException {
		
		
		ModificationItem[] user = new ModificationItem[1];
		Attribute use = new BasicAttribute(key,value);
		System.out.println(use);
			user[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,use);
			
			connection.modifyAttributes("uid="+uid+",ou=People,dc=example,dc=com",user);
//			connection.modifyAttributes("ou=People,dc=example,dc=com",user);
		
		
	}
	
	
//	This Method for update user 
	
	public ArrayList updateUser(String id,UserData udata) throws NamingException {
		newConnection();
		try {
			HashMap<String, String> m = new HashMap<String, String>();
//			while(udata.equals(udata)) {
				if(udata.getCn()!=null) {
					m.put("cn", udata.getCn());
					}
				if(udata.getCn()!=null) {
				m.put("sn", udata.getSn());

			}

		    
		    for(Map.Entry mn : m.entrySet()){
		    	update(id,mn.getKey().toString(),mn.getValue().toString());
   
		       } 
			
			connection.close();
			System.out.println("User Updated!");
			
		}catch(NamingException e) {
			System.out.println(e.getMessage());
			
		}
		return getUser();
		
		
	}
	
	
//	This Method for deleteUser
	
	public ArrayList deleteUser(String uid) throws NamingException {
		newConnection();
		try {
			
//			connection.destroySubcontext("uid=3,ou=users,ou=system");
			connection.destroySubcontext("uid=" + uid +",ou=People,dc=example,dc=com");
			System.out.println("User Deleted !");
			connection.close();
			System.out.println("connection CLosed!");
		}catch(NamingException e) {
			System.out.println(e.getMessage());
//			System.out.println("");
			System.out.println("Connection Closed !");
		}
		return getUser();
		
	}
	
	
	
	
	
	

//	public static void main(String[] args) throws NamingException {
//		LdapController ldap = new LdapController();
//		
//		ldap.getUser();
////		ldap.deleteUser();
////		ldap.addUser();
////		ldap.updateUser();
//		
//		
//		
//
//	}

}
