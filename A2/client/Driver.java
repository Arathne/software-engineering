package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.*;

public class Driver
{
    static Scanner scan = new Scanner(System.in);
	static String uri = "//in-csci-rrpc01:2324/RemoteLogIn";
	static LogIn accountLogIn;
    static UserController userController;
    static AdminController adminController;
    
    /* main loop that decides which method to run
     * NOTE: each method returns an integer that will make another method to run
    */
    public static void main (String[] args)
	{
        int state = 0;

        try {
			accountLogIn = (LogIn) Naming.lookup(uri);
            
            while (state >= 0) {
                if (state == 0)
                    state = StartMenu();
                else if (state == 1)
                    state = LogInMenu();
                else if (state == 2)
                    state = RegisterMenu();
                else if (state == 3)
                    state = ClientMenu();
                else if (state == 4)
                    state = AdminMenu();
                else if (state == 5)
                    state = ClientBrowseMenu();
                else if (state == 6)
                    state = ClientAddToCart();
                else if (state == 7)
                    state = ClientCartMenu();
                else if (state == 8)
                    state = ClientRemoveFromCart();
                else if (state == 9)
                    state = ClientPurchaseCart();
                else if (state == 10)
                    state = AdminAccountMenu();
                else if (state == 11)
                    state = CreateNewAdminAccount();
                else if (state == 12)
                    state = CreateNewUserAccount();
                else if (state == 13)
                    state = RemoveUserAccount();
                else if (state == 14)
                    state = AdminItemMenu();
                else if (state == 15)
                    state = AdminAddItem();
                else if (state == 16)
                    state = AdminRemoveItem();
                else if (state == 17)
                    state = AdminUpdateItem();
                else if (state == 18)
                    state = AdminUpdateItemDescription();
                else if (state == 19)
                    state = AdminUpdateItemPrice();
                else if (state == 20)
                    state = AdminUpdateItemQuantity();
            }


            System.out.println("\nexiting...");
            System.exit(0);
        }
        catch (Exception e) {
			System.out.println("Client err :: " + e.getMessage());
			e.printStackTrace();
        }
	}
    
    /* menu that first shows when the program starts
    */
    static public int StartMenu () throws RemoteException // STATE 0
    {
        if (adminController != null)
            adminController = null;

        if (userController != null)
            userController = null;

        int nextState = 0;
        String input = getStringInput("\n1. log-in\n2. register\n3. exit\n\n?: ");
        
        if (input.equals("1"))
            nextState = 1;
        else if (input.equals("2"))
            nextState = 2;
        else if (input.equals("3"))
            nextState = -1;

        return nextState;
    }
    
    /* create the appropriate controller for an account
    */
    static public int LogInMenu () throws RemoteException // STATE 1
    {
        int nextState = 0;

        String username = getStringInput("\nusername: ");
        String password = getStringInput("password: ");
        
        Account account = accountLogIn.authenticate(username, password);
      
        if (account != null) {
            if (account.isAdmin())
            {
                nextState = 4;
                AdminView view = new AdminViewImpl();
                adminController = accountLogIn.createAdminController(view);
                System.out.println("\nAdmin successfully logged in");
            }
            else
            {
                nextState = 3;
                UserView view = new UserViewImpl();
                userController = accountLogIn.createUserController(account, view);
                System.out.println("\nUser successfully logged in");
            }
        }

        return nextState;
    }
    
    /* users are able to create a new account
    */
    static public int RegisterMenu () throws RemoteException // STATE 2
    {
        int nextState = 0;

        String username = getStringInput("\nusername: ");
        String password = getStringInput("password: ");
        
        boolean successfullRegister = accountLogIn.register(username, password);

        System.out.println();
        if (successfullRegister)
            System.out.println("Successfully registered new user");
        else
            System.out.println("Failed to register new user");

        return nextState;
    }

    /* main menu for client
    */
    static public int ClientMenu () throws RemoteException // STATE 3
    {
        int nextState = 3;
        
        String input = getStringInput("\n1. browse catalog\n2. shopping cart\n3. go back\n\n?: ");   
        
        if (input.equals("1"))
            nextState = 5;
        else if (input.equals("2"))
            nextState = 7;
        else if (input.equals("3"))
            nextState = 0;

        return nextState;
    }
    
    /* main menu for admin
    */
    static public int AdminMenu () throws RemoteException // STATE 4
    {
        int nextState = 4;

        String input = getStringInput("\n1. accounts\n2. items\n3. go back\n\n?: ");   
        
        if (input.equals("1"))
            nextState = 10;
        else if (input.equals("2"))
            nextState = 14;
        else if (input.equals("3"))
            nextState = 0;

        return nextState;
    }
    
    /* catalog menu for client
    */
    static public int ClientBrowseMenu () throws RemoteException // STATE 5
    {
        int nextState = 5;
        
        userController.printCatalog();
        String input = getStringInput("\n1. add item to shopping cart\n2. go back\n\n?: ");
        
        if (input.equals("1"))
            nextState = 6;       
        else if (input.equals("2"))
            nextState = 3;

        return nextState;
    }
    
    /* add item to cart
    */
    static public int ClientAddToCart () throws RemoteException // STATE 6
    {
        int nextState = 5;

        try {
            int index = Integer.parseInt(getStringInput("\nwhich item would you like to add to shopping cart?: "));
            boolean itemExists = userController.printItem(index);
            
            if (itemExists)
            {
                int quantity = Integer.parseInt(getStringInput("\nquantity of item?: "));
                String input = getStringInput("\nare you sure you wish to add this item to your shopping cart? (y/n)?: ");
        
                if (input.equalsIgnoreCase("y"))
                    userController.addItemToCart(index, quantity);
                else
                    System.out.println("\nitem was not added to shopping cart");
            }
            else
                System.out.println("\nerror: item does not exist");
        }
        catch (Exception e) {
            System.out.println("\nerror: enter a valid number");   
        }

        return nextState;
    }
    
    /* shopping cart menu
    */
    static public int ClientCartMenu () throws RemoteException // STATE 7
    {
        int nextState = 7;
        
        boolean hasItems = userController.printCart();

        if (hasItems)
        {
            String input = getStringInput("\n1. remove item\n2. purchase items\n3. go back\n\n?: ");                 
        
            if (input.equals("1"))
                nextState = 8;
            else if (input.equals("2"))
                nextState = 9;
            else if (input.equals("3"))
                nextState = 3;
        }
        else {
            System.out.println("\nerror: shopping cart is empty");
            nextState = 3;
        }

        return nextState;
    }
    
    /* remove item from cart
    */
    static public int ClientRemoveFromCart () throws RemoteException // STATE 8
    {
        int nextState = 7;
        
        try {
            int index = Integer.parseInt(getStringInput("\nwhich item would you like to remove?: "));
            userController.removeItemFromCart(index);
        }
        catch (Exception e) {
            System.out.println("\nerror: invalid input");
        }

        return nextState;
    }
    
    /* purchase items in cart
    */
    static public int ClientPurchaseCart () throws RemoteException // STATE 9
    {
        int nextState = 7;
        
        double total = userController.getTotalPrice();

        String input = getStringInput("\ndo you wish to purchase items for a total price of $"+total+"? (y/n): ");

        if (input.equalsIgnoreCase("y")) {
            boolean success = userController.buyItemsInCart();

            if (success)
                nextState = 3;
        }

        return nextState;
    }
    
    /* menu for modifying accounts
    */
    static public int AdminAccountMenu () throws RemoteException // STATE 10
    {
        int nextState = 10;
        
        System.out.println();
        adminController.printAllAccounts();
        String input = getStringInput("\n1. create admin account\n2. create user account\n3. remove user account\n4. go back\n\n?: ");
        
        if (input.equals("1"))
            nextState = 11;
        else if (input.equals("2"))
            nextState = 12;
        else if (input.equals("3"))
            nextState = 13;
        else if (input.equals("4"))
            nextState = 4;

        return nextState;
    }

    /* create admin account
    */
    static public int CreateNewAdminAccount() throws RemoteException // STATE 11
    {
        int nextState = 10;
        
        String username = getStringInput("\nusername?: ");
        String password = getStringInput("password?: ");

        adminController.createAdminAccount(username, password);

        return nextState;
    }
    
    /* create user account
    */
    static public int CreateNewUserAccount() throws RemoteException // STATE 12
    {
        int nextState = 10;

        String username = getStringInput("\nusername?: ");
        String password = getStringInput("password?: ");

        adminController.createUserAccount(username, password);
        
        return nextState;
    }
    
    /* remove user account
    */
    static public int RemoveUserAccount() throws RemoteException // STATE 13
    {
        int nextState = 10;
        
        try {
            int index = Integer.parseInt(getStringInput("\nwhich account would you like to remove?: "));
            adminController.removeUserAccount(index);
        }
        catch (Exception e) {
            System.out.println("error: invalid input");
        }

        return nextState;
    }
    
    /* item menu for modifying items
    */
    static public int AdminItemMenu () throws RemoteException // STATE 14
    {
        int nextState = 14;
        
        System.out.println();
        adminController.printAllItems();
        String input = getStringInput("\n1. add item\n2. remove item\n3. update item\n4. go back\n\n?: ");
        
        if (input.equals("1"))
            nextState = 15;
        else if (input.equals("2"))
            nextState = 16;
        else if (input.equals("3"))
            nextState = 17;
        else if (input.equals("4"))
            nextState = 4;

        return nextState;
    }
    
    /* add item to database
    */
    static public int AdminAddItem () throws RemoteException // STATE 15
    {
        int nextState = 14;
        
        try {
            String type = getStringInput("\ntype?: ");
            String description = getStringInput("description?: ");
            double price = Double.parseDouble(getStringInput("price?: "));
            int quantity = Integer.parseInt(getStringInput("quantity?: "));

            adminController.addItem(type, description, price, quantity);
        } 
        catch (Exception e) {
            System.out.println("\nerror: invalid input");
        }

        return nextState;
    }
    
    /* remove item from database
    */
    static public int AdminRemoveItem () throws RemoteException // STATE 16
    {
        int nextState = 14;
        
        try {
            int index = Integer.parseInt(getStringInput("which item would you like to remove?: "));
            adminController.removeItem(index);
        }
        catch (Exception e) {
            System.out.println("\nerror: invalid input");
        }
        return nextState;
    }
    
    /* menu for updating an item
    */
    static public int AdminUpdateItem () throws RemoteException // STATE 17
    {
        int nextState = 17;
        
        System.out.println();
        adminController.printAllItems();
        String input = getStringInput("\n1. description\n2. price\n3. quantity\n4. go back\n\n?: ");
        
        if (input.equals("1"))
            nextState = 18;
        if (input.equals("2"))
            nextState = 19;
        if (input.equals("3"))
            nextState = 20;
        if (input.equals("4"))
            nextState = 14;

        return nextState;
    }
    
    /* update description of item
    */
    static public int AdminUpdateItemDescription () throws RemoteException // STATE 18
    {
        int nextState = 17;
        
        try {
            int index = Integer.parseInt(getStringInput("\nwhich item would you like to update?: "));
            String description = getStringInput("new description?: ");
            adminController.updateDescription(index, description);
        }
        catch (Exception e) {
            System.out.println("\nerror: invalid input");
        }

        return nextState;
    }
    
    /* update price of item
    */
    static public int AdminUpdateItemPrice () throws RemoteException // STATE 19
    {
        int nextState = 17;
        
        try {
            int index = Integer.parseInt(getStringInput("\nwhich item would you like to update?: "));
            double price = Double.parseDouble(getStringInput("new price?: "));
            adminController.updatePrice(index, price);
        }
        catch (Exception e) {
            System.out.println("\nerror: invalid input");
        }

        return nextState;
    }
    
    /* updates quantity of an item
    */
    static public int AdminUpdateItemQuantity () throws RemoteException // STATE 20
    {
        int nextState = 17;
        
        try {
            int index = Integer.parseInt(getStringInput("\nwhich item would you like to update?: "));
            int quantity = Integer.parseInt(getStringInput("new quantity?: "));
            adminController.updateQuantity(index, quantity);
        }
        catch (Exception e) {
            System.out.println("\nerror: invalid input");
        }

        return nextState;
    }

    static public String getStringInput (String message)
    {
        System.out.print(message);
        return scan.nextLine();
    }
}
