package common;

import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface AdminView extends Remote
{
    public void printMessage (String message) throws RemoteException;
    public void printAllAccounts (ArrayList<Account> accounts) throws RemoteException;
    public void printUserAccounts (ArrayList<Account> accounts) throws RemoteException;
    public void printAllItems (ArrayList<Item> items) throws RemoteException;
}
