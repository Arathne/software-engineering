package store;

public class ShoppingCartItem
{
    private int id_ = -1;
    private int quantity_ = -1;

    /* CONSTRUCTORS */

    ShoppingCartItem () {}

    ShoppingCartItem (int id, int quantity) 
    {
        id_ = id;
        quantity_ = quantity;
    }

    /* GETTERS - SETTERS */
    
    public void setId (int id)
    {
        id_ = id;
    }

    public int getId ()
    {
        return id_;
    }
    
    public void setQuantity (int quantity)
    {
        quantity_ = quantity;
    }

    public int getQuantity ()
    {
        return quantity_;
    }
}
