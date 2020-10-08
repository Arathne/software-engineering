package common;

import java.rmi.*;

public interface Money extends Remote
{
    public void set(double value) throws RemoteException;
    public double get() throws RemoteException;
}
