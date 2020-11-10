package common;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface LogIn extends Remote
{
	public Account authenticate (String username, String password) throws RemoteException;
	public boolean register (String username, String password) throws RemoteException;
    public UserController createUserController (Account account, UserView view) throws RemoteException;
    public AdminController createAdminController (AdminView view) throws RemoteException;
}
