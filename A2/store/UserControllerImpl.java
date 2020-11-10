package store;

import common.Account;
import common.UserView;
import common.UserController;
import common.Item;

import java.util.ArrayList;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class UserControllerImpl extends UnicastRemoteObject implements UserController
{
    Account account_;
    ShoppingCart cart_;
    UserView view_;
    
    /* CONSTRUCTORS */
    
    UserControllerImpl () throws RemoteException {}
    
    UserControllerImpl (Account account, ShoppingCart cart, UserView view) throws RemoteException 
    {
        account_ = account;
        cart_ = cart;
        view_ = view;
    }

    /* METHODS */
    
    /* prints all items in database
    */
    public void printCatalog () throws RemoteException
    {
        view_.printItemList( Database.getAllItems() );
    }
    
    /* print all items in shopping cart
    */
    public boolean printCart () throws RemoteException
    {
        boolean success = false;

        if (cart_.totalItems() > 0) {
            view_.printItemList( cart_.getItems() );
            success = true;
        }

        return success;
    }
    
    /* prints a single item
    */
    public boolean printItem (int index) throws RemoteException
    {
        Item item = Database.getItem(index);
        boolean success = false;

        if (item != null) {
            success = true;
            view_.printItem(item);
        }

        return success;
    }
    
    /* add an item to shopping cart
    */
    public void addItemToCart (int index, int quantity) throws RemoteException
    {
        Item item = Database.getItem(index);
        
        if (item != null)
        {
            if (quantity <= item.getQuantity() && quantity > 0) {
                cart_.addItem(item, quantity);
                view_.printMessage("\nsuccessfully added item to cart");
            }
            else {
                view_.printMessage("\nerror: invalid quantity");   
            }
        }
    }
    
    /* remove a specific item from cart
    */
    public void removeItemFromCart (int index) throws RemoteException
    {
        boolean success = cart_.removeItem(index);

        if (success)
            view_.printMessage("\nsuccessfully removed item");
        else
            view_.printMessage("\nerror: item does not exist");
    }
    
    /* buy all items in shopping cart
    */
    public boolean buyItemsInCart () throws RemoteException
    {
        boolean success = cart_.buyItems();
        
        if (success)
            view_.printMessage("\ntransaction successful");
        else
            view_.printMessage("\nerror: invalid item in cart");

        return success;
    }
    
    /* returns total price of items in shopping cart
    */
    public double getTotalPrice () throws RemoteException
    {
        return cart_.calculateTotalCost();
    }
}
