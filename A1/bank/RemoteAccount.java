package bank;

import java.rmi.*;
import java.rmi.registry.*;

import common.Account;

public class RemoteAccount
{
    public static void main (String args[])
    {
        try {
            Account remote_obj = new AccountImpl("John Raymond");
        
            String name = "//in-csci-rrpc01:2324/RemoteAccount";
            Naming.bind( name, remote_obj );
        
            System.out.println("Server is ready");
        }
        catch (Exception e) {
            System.out.println( "Server err: " + e.getMessage() );
            e.printStackTrace();
        }
    }
}
