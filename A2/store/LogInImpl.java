package store;

import common.LogIn;
import common.Account;
import common.UserController;
import common.UserView;
import common.AdminController;
import common.AdminView;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class LogInImpl extends UnicastRemoteObject implements LogIn
{
    LogInImpl () throws RemoteException {}

    /* returns Account if username and password are valid
    */
	public Account authenticate (String username, String password) throws RemoteException
	{
	    return Database.getAccount(username, password);
    }
    
    /* creates a new account if user doesn't exist
    */
	public boolean register (String username, String password) throws RemoteException
	{
        boolean success = false;
		
        if ( Database.doesUserExist(username) == false )
        {
            Account user = new AccountImpl(username, password);
            Database.addAccount(user);
            success = true;
        }
        
        return success;
	}

    /* creates user controller for client
    */
    public UserController createUserController (Account account, UserView view) throws RemoteException
    {
        ShoppingCart cart = new ShoppingCart();
        UserController controller = new UserControllerImpl(account, cart, view);
        return controller;
    }
    
    /* create admin controller for client
    */
    public AdminController createAdminController (AdminView view) throws RemoteException
    {
        AdminController controller = new AdminControllerImpl(view);
        return controller;
    }
}
