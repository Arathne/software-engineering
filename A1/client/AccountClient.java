package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.Account;

public class AccountClient
{
    public static void main (String args[])
    {
        try {
            String name = "//in-csci-rrpc01:2324/RemoteAccount";
            Account account = (Account) Naming.lookup(name);

            System.out.println("Found server object!");
            
            System.out.println("Client :: name is " + account.getName() + "\n");
            
            System.out.println("Client :: current balance is " + account.getBalance() + "\n");

            System.out.println("Client :: setting balance to 50");
            account.setBalance(50);
            System.out.println("Client :: current balance is " + account.getBalance()+ "\n");
            
            System.out.println("Client :: depositing $10");
            account.deposit(10);
            System.out.println("Client :: current balance is " + account.getBalance()+ "\n");
            
            System.out.println("Client :: withdrawing $20");
            account.withdraw(20);
            System.out.println("Client :: current balance is " + account.getBalance()+ "\n");
        }
        catch (Exception e) {
            System.out.println("Client err :: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
