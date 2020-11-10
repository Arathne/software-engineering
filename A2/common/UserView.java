package common;

import java.util.ArrayList;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface UserView extends Remote
{
    public void printItemList (ArrayList<Item> items) throws RemoteException;
    public void printItem (Item item) throws RemoteException;
    public void printMessage (String message) throws RemoteException;
}
