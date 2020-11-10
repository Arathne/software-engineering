package store;

import common.Account;
import common.Item;

import java.util.ArrayList;
import java.rmi.RemoteException;

public class Database
{
    static ArrayList<Account> accounts_ = new ArrayList<Account>();
    static ArrayList<Item> items_ = new ArrayList<Item>();
    
    /* add a new account
    */
    static void addAccount (Account account)
    {
        accounts_.add(account);
    }
    
    /* get an account from index
    */
    static Account getAccount (int index)
    {
        Account user = null;

        if (index >= 0 && index < accounts_.size())
            user = accounts_.get(index);

        return user;
    }
    
    /* removes only user accounts
    */
    static boolean removeUserAccount (int index) throws RemoteException
    {
        boolean success = false;

        if (index >= 0 && index < accounts_.size()) {
            if (accounts_.get(index).isAdmin() == false) {
                accounts_.remove(index);
                success = true;
            }
        }

        return success;
    }
    
    /* add a new item
    */
    static void addItem (Item item)
    {
        items_.add(item);   
    }
    
    /* remove item from index
    */
    static boolean removeItem (int index)
    {
        boolean success = false;

        if (index >= 0 && index < items_.size())
        {
            items_.remove(index);
            success = true;
        }

        return success;
    }
    
    /* returns item
    */
    static Item getItem (int index)
    {
        Item item = null;
        
        if (index >= 0 && index < items_.size())
            item = items_.get(index);
        
        return item;
    }
    
    /* returns all items
    */
    static ArrayList<Item> getAllItems ()
    {
        return items_;
    }
    
    /* returns all accounts
    */
    static ArrayList<Account> getAllAccounts ()
    {
        return accounts_;
    }
    
    /* get an account that matches username and password
    */
    static Account getAccount (String username, String password) throws RemoteException
    {
        Account account = null;
        
        boolean run = true;
        int index = 0;

        while (run && index < accounts_.size())
        {
            Account user = accounts_.get(index);
            
            if (user.getUsername().equalsIgnoreCase(username))
            {
                run = false;
                if (user.getPassword().equals(password))
                    account = user;           
            }       

            index++;     
        }

		return account;
    }
    
    /* returns true if an account with username exists
    */
    static public boolean doesUserExist (String username) throws RemoteException
    {
        boolean exist = false;
        int index = 0;
        
        while (exist == false && index < accounts_.size())
        {
            Account user = accounts_.get(index);
            
            if (username.equalsIgnoreCase(user.getUsername().toUpperCase()))
                exist = true;
            
            index++;
        }
        
        return exist;
    }
    
    /* searches for item with id and returns it
    */
    static public Item getItemFromId (int id) throws RemoteException
    {
        Item item = null;
        boolean found = false;

        for (int index = 0; index < items_.size() && found == false; index++)
        {
            Item currentItem = items_.get(index);
            if (currentItem.getId() == id)
            {
                item = currentItem;
                found = true;
            }
        }

        return item;
    } 
    
    /* returns item from index of array
    */
    static public Item getItemFromIndex (int index) throws RemoteException
    {
        Item item = null;

        if (index >= 0 && index < items_.size())
            item = items_.get(index);
    
        return item;
    }
    
    /* calculates total total of accounts
    */
    static public int totalAccounts () throws RemoteException
    {
        return accounts_.size();   
    }
    
    /* calculates total number of items
    */
    static public int totalItems () throws RemoteException
    {
        return items_.size();
    }
}
