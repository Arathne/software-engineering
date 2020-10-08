package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.Money;

public class AccountClient
{
    public static void main (String args[])
    {
        try {
            String name = "//in-csci-rrpc01:2324/RemoteMoney";
            Money account = (Money) Naming.lookup(name);

            System.out.println("Found server object!");
            
            System.out.println("Client :: money is" + account.get());
        }
        catch (Exception e) {
            System.out.println("Client err :: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
