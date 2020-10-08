package bank;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Money;

public class MoneyRemote extends UnicastRemoteObject implements Money
{
    private double balance = 3.50;

    MoneyRemote () throws RemoteException
    {
        super();
    }

    public void set (double value)
    {
        System.out.println("MoneyRemote server :: setting balance to " + value);
        balance = value;
    }

    public double get ()
    {
        System.out.println("MoneyRemote server :: returning balance ");
        return balance;
    }
}
