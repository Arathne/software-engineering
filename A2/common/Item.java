package common;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface Item extends Remote
{
    public void setQuantity (int quantity) throws RemoteException;
    public int getQuantity () throws RemoteException;
    public void setType (String type) throws RemoteException;
    public String getType () throws RemoteException;
    public void setDescription (String description) throws RemoteException;
    public String getDescription () throws RemoteException;
    public void setPrice (double price) throws RemoteException;
    public double getPrice () throws RemoteException;
    public boolean isEqual (Item item) throws RemoteException;
    public String string () throws RemoteException;
    public int getId () throws RemoteException;
}
