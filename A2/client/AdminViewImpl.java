package client;

import common.Account;
import common.AdminView;
import common.Item;

import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdminViewImpl extends UnicastRemoteObject implements AdminView
{
    AdminViewImpl () throws RemoteException {}
    
    /* prints a message
    */
    public void printMessage (String message) throws RemoteException
    {
        System.out.println(message);
    }
    
    /* prints both admin and user accounts
    */
    public void printAllAccounts (ArrayList<Account> accounts) throws RemoteException
    {
        for (int index = 0; index < accounts.size(); index++)
        {
            Account currentAccount = accounts.get(index);
            System.out.println(index + " | " + currentAccount.string());
        }
    }
    
    /* prints user accounts
    */
    public void printUserAccounts (ArrayList<Account> accounts) throws RemoteException
    {
        for (int index = 0; index < accounts.size(); index++)
        {
            Account currentAccount = accounts.get(index);
           
            if (currentAccount.isAdmin() == false)
                System.out.println(index + " | " + currentAccount.string());
        }
    }
    
    /* prints all items
    */
    public void printAllItems (ArrayList<Item> items) throws RemoteException
    {
        for (int index = 0; index < items.size(); index++)
        {
            Item currentItem = items.get(index);
            System.out.println(index + " | " + currentItem.string());
        }
    }
}
