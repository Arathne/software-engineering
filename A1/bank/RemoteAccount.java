package bank;

import java.rmi.*;
import java.rmi.registry.*;

import common.Money;

public class RemoteAccount
{
    public static void main (String args[])
    {
        try {
            Money remote_obj = new MoneyRemote();
            String name = "//in-csci-rrpc01:2324/RemoteMoney";

            Naming.rebind( name, remote_obj );

            System.out.println("Server is ready");
        }
        catch (Exception e) {
            System.out.println("Server err: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
