package store;

import common.Account;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class AccountImpl extends UnicastRemoteObject implements Account
{
	String username_;
	String password_;
	boolean admin_ = false;
	
	/* CONSTRUCTORS */

	AccountImpl () throws RemoteException
	{
		username_ = "John";
		password_ = "Snow";
	}
	
	AccountImpl (String username, String password) throws RemoteException
	{
		username_ = username;
		password_ = password;
	}
	
	AccountImpl (String username, String password, boolean admin) throws RemoteException
	{
		username_ = username;
		password_ = password;
		admin_ = admin;
	}
	
    /* METHODS */
    
    /* returns a string with all the data of instance
    */
    public String string () throws RemoteException
    {
        return String.format("username: %s, password: %s, isAdmin: %s", username_, password_, admin_);
    }

	/* GETTERS - SETTERS */

	public void setUsername (String username) throws RemoteException
	{
		username_ = username;
	}
   
	public String getUsername () throws RemoteException
	{
		return username_;
	}

   public void setPassword (String password) throws RemoteException
	{
		password_ = password;
	}
	
   public String getPassword () throws RemoteException
	{
		return password_;
	}
	
	public void giveAdminPrivileges () throws RemoteException
	{
		admin_ = true;
	}

	public void removeAdminPrivileges () throws RemoteException
	{
		admin_ = false;
	}

    public boolean isAdmin () throws RemoteException
    {
		return admin_;
	}
}
