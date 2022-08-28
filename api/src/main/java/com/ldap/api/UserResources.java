package com.ldap.api;

import java.util.*;

import java.util.stream.Collectors;

import javax.naming.NamingException;

public class UserResources {
	
	List<UserData> Value;
	LdapController ldap = new LdapController();
	
	
//	public UserResources() {
//		Value = new ArrayList<>();
//		
//		UserData user1 = new UserData("Prabhat","Singh",101);
//		UserData user2 = new UserData("Himanshu","Rajput",102);
//		UserData user3 = new UserData("Rahul","Gupta",103);
//		
//				
//		Value.addAll(Arrays.asList(user1,user2,user3));
//	}
//	
	
	
	
//	List<UserData>
//	Get All USers
	public ArrayList getUsers() throws NamingException{
		
		return ldap.getUser();
		
		
	}
	
	
//	GetUser By ID
	public ArrayList getUserId(String id) throws NamingException{
		
		return ldap.getUsrId(id);
		
//		return Value.stream().filter(x->x.getUid() == id)
//				.collect(Collectors.toList()).get(0);
		
		
	}

	
//	User Add
	public ArrayList createUser(UserData udata) {
		
		
		return ldap.addUser(udata);
	}
	
	
//	For User Delete
	
	public ArrayList removeUser(String id) throws NamingException{
		return ldap.deleteUser(id);
		
//		Value.removeIf(x->x.getUid()==id);
//		return Value;
		
	}
	
	
//	User Data Update 
	public ArrayList uUser(String id,UserData udata) throws NamingException{
		
//		try {
//		
//
//			if(udata.getCn()!=null) {
//				Value.stream().filter(x->x.getUid()==id).collect(Collectors.toList()).get(0).setCn(udata.getCn());
//				}
//			if(udata.getSn()!=null) {
//				Value.stream().filter(x->x.getUid()==id).collect(Collectors.toList()).get(0).setSn(udata.getSn());
	
//		}
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		
//			
//		}
		return ldap.updateUser(id,udata);
		
				
	}

}
