package store;

import common.Item;
import common.Account;
import common.AdminView;
import common.AdminController;

import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdminControllerImpl extends UnicastRemoteObject implements AdminController
{
    /* CONSTRUCTORS */
    
    AdminView view_;
    
    AdminControllerImpl (AdminView view) throws RemoteException
    {
        view_ = view;
    }

    /* METHODS */
    
    /* creates a new admin account 
     * NOTE: an admin cannot delete another admin
    */
    public void createAdminAccount (String username, String password) throws RemoteException
    {
        if ( Database.doesUserExist(username) == false ) {
            Account newAccount = new AccountImpl(username, password, true);    
            Database.addAccount(newAccount);
            view_.printMessage("\nsuccessfully created new admin account");
        }
        else
            view_.printMessage("\nerror: account already exists");
    }
    
    /* create a new user account
    */
    public void createUserAccount (String username, String password) throws RemoteException
    {
        if ( Database.doesUserExist(username) == false ) {
            Account newAccount = new AccountImpl(username, password, false);    
            Database.addAccount(newAccount);
            view_.printMessage("\nsuccessfully created new user account");
        }
        else
            view_.printMessage("\nerror: account already exists");
    }
    
    /* remove user account
    */
    public void removeUserAccount (int index) throws RemoteException
    {
        boolean success = Database.removeUserAccount(index);
        
        if (success)
            view_.printMessage("\nsuccessfully remove user account");
        else
            view_.printMessage("\nerror: failed to remove account");
    }
    
    /* prints all accounts in database
    */
    public void printAllAccounts () throws RemoteException
    {
        ArrayList<Account> accounts = Database.getAllAccounts();
        view_.printAllAccounts(accounts);
    }
    
    /* prints all user accounts in database
    */
    public void printUserAccounts () throws RemoteException
    {
        ArrayList<Account> accounts = Database.getAllAccounts();
        view_.printUserAccounts(accounts);
    }
    
    /* prints all items in database
    */
    public void printAllItems () throws RemoteException
    {
        ArrayList<Item> items = Database.getAllItems();
        view_.printAllItems(items);
    }
    
    /* add a new item to database
    */
    public void addItem (String type, String description, double price, int quantity) throws RemoteException
    {   
        Item newItem = new ItemImpl(quantity, price, type, description);
        Database.addItem(newItem);
    }   
    
    /* remove specific item from database
    */
    public void removeItem (int index) throws RemoteException
    {
        boolean success = Database.removeItem(index);   
        
        if (success)
            view_.printMessage("\nsuccessfully removed item");
        else
            view_.printMessage("\nerror: failed to remove item");
    }
    
    /* update description of item
    */
    public void updateDescription (int index, String description) throws RemoteException
    {
        Item item = Database.getItemFromIndex(index);
        
        if (item != null)
        {
            view_.printMessage("\nsuccessfully update description");
            item.setDescription(description);
        }
        else
            view_.printMessage("\nerror: failed to update description");
    }
     
    /* update price of item
    */
    public void updatePrice (int index, double price) throws RemoteException
    {
        Item item = Database.getItemFromIndex(index);
        
        if (item != null)
        {
            view_.printMessage("\nsuccessfully update price");
            item.setPrice(price);
        }
        else
            view_.printMessage("\nerror: failed to update price");

    }
    
    /* update quantity of item
    */
    public void updateQuantity (int index, int quantity) throws RemoteException
    {
        Item item = Database.getItemFromIndex(index);
        
        if (item != null)
        {
            view_.printMessage("\nsuccessfully update quantity");
            item.setQuantity(quantity);
        }
        else
            view_.printMessage("\nerror: failed to update quantity");
    }
}
