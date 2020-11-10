package common;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface Account extends Remote
{
	public void setUsername (String username) throws RemoteException;
	public String getUsername () throws RemoteException;
	 
	public void setPassword (String password) throws RemoteException;
	public String getPassword () throws RemoteException;
	
	public void giveAdminPrivileges () throws RemoteException;
	public void removeAdminPrivileges () throws RemoteException;
	public boolean isAdmin () throws RemoteException;

    public String string () throws RemoteException;
}
