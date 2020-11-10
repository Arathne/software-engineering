package store;

import common.Item;

import java.util.ArrayList;
import java.rmi.RemoteException;

public class ShoppingCart
{
    private ArrayList<ShoppingCartItem> items_ = new ArrayList<ShoppingCartItem>();

    /* CONSTRUCTORS */

    ShoppingCart () {}

    /* METHODS */
    
    /* add a new item to cart
    */
    public void addItem (Item item, int quantity) throws RemoteException
    {
        ShoppingCartItem newItem = new ShoppingCartItem(item.getId(), quantity);
        items_.add(newItem);
    }
    
    /* removes a specific item from cart
    */
    public boolean removeItem (int index)
    {
        boolean success = false;

        if (index >= 0 && index < items_.size())
        {
            success = true;
            items_.remove(index);   
        }

        return success;
    }
    
    /* calculates total cost of cart
    */
    public double calculateTotalCost () throws RemoteException
    {
        double total = 0.0;
        
        for (int index = 0; index < items_.size(); index++)
        {
            ShoppingCartItem cartItem = items_.get(index);
            Item databaseItem = Database.getItemFromId(cartItem.getId());
            
            total += databaseItem.getPrice() * cartItem.getQuantity();
        }

        return total;
    }
    
    /* returns a list of items
    */
    public ArrayList<Item> getItems () throws RemoteException
    {
        ArrayList<Item> itemList = new ArrayList<Item>();
        
        for (int index = 0; index < items_.size(); index++)
        {
            ShoppingCartItem cartItem = items_.get(index);

            int id = cartItem.getId();
            Item currentItem = Database.getItemFromId(id);
            Item newItem = new ItemImpl(currentItem, cartItem.getQuantity());
            
            itemList.add( newItem );
        }

        return itemList;
    }
    
    /* returns total amount of items in cart
    */
    public int totalItems ()
    {
        return items_.size();
    }
    
    /* purchase items (assumes user has enough money)
    */
    public boolean buyItems () throws RemoteException
    {
        boolean success = false;
        
        if (checkItemValidity()) {
            for (int index = 0; index < items_.size(); index++) {
                ShoppingCartItem cartItem = items_.get(index);
                Item currentItem = Database.getItemFromId(cartItem.getId());
            
                int newQuantity = currentItem.getQuantity() - cartItem.getQuantity();

                currentItem.setQuantity(newQuantity);
            }
            
            items_.clear();
            success = true;
        }

        return success;
    }
    
    /* detects any anomolies or outdated data
    */
    private boolean checkItemValidity () throws RemoteException
    {
        boolean valid = false;

        if (itemsExist()) {
            if (checkItemQuantities()) {
                valid = true;
            }
        }

        return valid;
    }
    
    /* items must exist in database
    */
    private boolean itemsExist () throws RemoteException
    {
        boolean itemsExistInDatabase = true;

        for (int index = 0; index < items_.size() && itemsExistInDatabase; index++) {
            ShoppingCartItem cartItem = items_.get(index);
            Item currentItem = Database.getItemFromId(cartItem.getId());

            if (currentItem == null)
                itemsExistInDatabase = false;
        }
        
        System.out.println("item exist: " + itemsExistInDatabase);

        return itemsExistInDatabase;
    }
    
    /* makes sure quantities of items are still available
    */
    private boolean checkItemQuantities () throws RemoteException
    {
        boolean quantitiesValid = true;
        
        for (int index = 0; index < items_.size() && quantitiesValid; index++)
        {
            ShoppingCartItem cartItem = items_.get(index);  
            Item currentItem = Database.getItemFromId(cartItem.getId());
            
            int cartQuantity = cartItem.getQuantity();
            int databaseQuantity = currentItem.getQuantity();

            if ( !(cartQuantity > 0 && cartQuantity <= databaseQuantity) )
                quantitiesValid = false;   
        }

        System.out.println("quantities valid: " + quantitiesValid);

        return quantitiesValid;
    }
}
