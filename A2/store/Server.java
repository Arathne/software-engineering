package store;

import java.util.ArrayList;
import java.rmi.*;
import java.rmi.registry.*;

import common.*;

public class Server
{
	public static void main (String[] args)
	{
		ArrayList<Account> accounts = new ArrayList<Account>();

		try {
			Account account1 = new AccountImpl("Player1", "123");	
			Account account2 = new AccountImpl("Player2", "123");	
			Account account3 = new AccountImpl("abc", "123", true);	
			Item item1 = new ItemImpl(3, 10.0, "bowl", "a place to put cereal");
			Item item2 = new ItemImpl(8, 3.50, "pencils", "unsharpened pack of pencils");
            
            Database.addAccount(account1);
			Database.addAccount(account2);
			Database.addAccount(account3);
	        Database.addItem(item1);
	        Database.addItem(item2);

			LogIn remote_obj = new LogInImpl();
		
			String uri = "//in-csci-rrpc01:2324/RemoteLogIn";
			Naming.bind( uri, remote_obj );

			System.out.println("Server is ready!");
		}
		catch (Exception e) {
			System.out.println( "Server err: " + e.getMessage() );
			e.printStackTrace();
		}
	}
}
