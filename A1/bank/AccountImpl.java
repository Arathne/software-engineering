package bank;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Account;

public class AccountImpl extends UnicastRemoteObject implements Account
{   
    private String name = "Edgar Torrez";
    private Money balance = new Money();

    AccountImpl () throws RemoteException
    {
        super();
    }

    AccountImpl (String newName) throws RemoteException
    {
        name = newName;
    }

    public String getName () throws RemoteException
    {
        System.out.println("Server :: sending name to client");
        return name;
    }

    public void setName (String newName) throws RemoteException
    {
        System.out.println("Server :: changing name");
        name = newName;
    }

    public double getBalance () throws RemoteException
    {
        System.out.println("Server :: sending balance to client");
        return balance.get();
    }

    public void setBalance (double newBalance) throws RemoteException
    {
        System.out.println("Server :: setting new balance to " + newBalance);
        balance.set(newBalance);
    }

    public void deposit (double money) throws RemoteException
    {
        System.out.println("Server :: depositing " + money );
        balance.set( balance.get()+money );
    }

    public void withdraw (double money) throws RemoteException
    {
        System.out.println("Server :: withdrawing " + money );
        double moneyLeft = balance.get() - money;
        balance.set( moneyLeft );
    }
}
