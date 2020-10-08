package common;

import java.rmi.*;

public interface Account extends Remote
{
    public String getName () throws RemoteException;
    public void setName (String newName) throws RemoteException;
    public double getBalance () throws RemoteException;
    public void setBalance (double newBalance) throws RemoteException;
    public void deposit (double money) throws RemoteException;
    public void withdraw (double money) throws RemoteException;
}
