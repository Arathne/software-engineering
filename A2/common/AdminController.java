package common;

import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface AdminController extends Remote
{
    public void createAdminAccount (String username, String password) throws RemoteException;
    public void createUserAccount (String username, String password) throws RemoteException;
    public void removeUserAccount (int index) throws RemoteException;
    public void printAllAccounts () throws RemoteException;
    public void printUserAccounts () throws RemoteException;
    
    public void printAllItems () throws RemoteException;
    public void addItem (String type, String description, double price, int quantity) throws RemoteException;
    public void removeItem (int index) throws RemoteException;
    public void updateDescription (int index, String description) throws RemoteException;
    public void updatePrice (int index, double price) throws RemoteException;
    public void updateQuantity (int index, int quantity) throws RemoteException;
}
