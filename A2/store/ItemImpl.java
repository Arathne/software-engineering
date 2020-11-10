package store;

import common.Item;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ItemImpl extends UnicastRemoteObject implements Item
{
    private static int objectCounter = 0;

    private int id_;
    private int quantity_ = 0;
    private double price_ = 0.0;
    private String type_ = "";
    private String description_ = "";
    
    /* CONSTRUCTORS */

    ItemImpl () throws RemoteException 
    {
        id_ = objectCounter++;
    }
    
    ItemImpl (int quantity, double price, String type, String description) throws RemoteException 
    {
        id_ = objectCounter++;
        quantity_ = quantity;
        price_ = price;
        type_ = type;
        description_ = description;
    }

    ItemImpl (Item item, int quantity) throws RemoteException
    {
        id_ = item.getId();
        quantity_ = quantity;
        price_ = item.getPrice();
        type_ = item.getType();
        description_ = item.getDescription();
    }
    
    /* METHODS */
    
    /* returns true if both items are the same
    */
    public boolean isEqual (Item item) throws RemoteException
    {
        boolean equal = true;
        
        if (id_ != item.getId())
            equal = false;

        return equal;
    }
    
    /* returns a string with all details of item
    */
    public String string () throws RemoteException
    {
        return String.format("type: %s, description: %s, price: %s, quantity: %s", type_, description_, price_, quantity_ );
    }

    /* SETTERS - GETTERS */

    public void setQuantity (int quantity) throws RemoteException
    {
        quantity_ = quantity;
    }

    public int getQuantity () throws RemoteException
    {
        return quantity_;
    }

    public void setType (String type) throws RemoteException
    {
        type_ = type;
    }

    public String getType () throws RemoteException
    {
        return type_;
    }

    public void setDescription (String description) throws RemoteException
    {
        description_ = description;
    }

    public String getDescription () throws RemoteException
    {
        return description_;
    }

    public void setPrice (double price) throws RemoteException
    {
        price_ = price;
    }

    public double getPrice () throws RemoteException
    {
        return price_;
    }

    public int getId () throws RemoteException
    {
        return id_;
    }
}
