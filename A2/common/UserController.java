package common;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface UserController extends Remote
{
    public void printCatalog () throws RemoteException;
    public boolean printCart () throws RemoteException;
    public boolean printItem (int index) throws RemoteException;
    public void addItemToCart (int index, int quantity) throws RemoteException;
    public void removeItemFromCart (int index) throws RemoteException;
    public boolean buyItemsInCart () throws RemoteException;
    public double getTotalPrice () throws RemoteException;
}
