package client;

import common.UserView;
import common.Item;

import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserViewImpl extends UnicastRemoteObject implements UserView
{
    UserViewImpl () throws RemoteException {}
    
    /* prints items in array
    */
    public void printItemList (ArrayList<Item> items) throws RemoteException
    {
        System.out.println();
        for (int index = 0; index < items.size(); index++)
        {
            Item currentItem = items.get(index);
            System.out.println(index + ". | " + currentItem.string());
        }
    }
    
    /* print single item
    */
    public void printItem (Item item) throws RemoteException
    {
        System.out.println();
        System.out.println(item.string());
    }
    
    /* prints a message
    */
    public void printMessage (String message) throws RemoteException
    {
        System.out.println(message);
    }
}
