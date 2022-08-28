package com.ldap.api;



import java.util.*;

import javax.naming.NamingException;
import javax.ws.rs.DELETE;
//import javax.naming.directory.Attributes;
//import javax.naming.directory.BasicAttributes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UserDetails {
	
	UserResources data = new UserResources();
	
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
    	public String getIt() {
        return "Got it!";
    	}
	
	
	@GET
	@Path("getuser")
	@Produces(MediaType.APPLICATION_JSON)
	public  ArrayList getUserData() throws NamingException {

		return data.getUsers();			
	}
	
	
	@GET
	@Path("getuser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList UserId(@PathParam("id") String uid) throws NamingException {

		return data.getUserId(uid);			
	}
	
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList  addUser(UserData udata) {
		return data.createUser(udata);	
														
	}
	
	
	@DELETE
	@Path("getuser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList  deleteUser(@PathParam("id") String uid) throws NamingException {

		return data.removeUser(uid);			
	}
	
	
	@PUT
	@Path("getuser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList  updateUsr(@PathParam("id") String uid, UserData udata) throws NamingException {
		return data.uUser(uid,udata);	
														
	}
	
}
