package bank;

public class Money
{
    private double balance = 0.0;
    
    Money () {}

    public double get ()
    {
        return balance;
    }

    public void set (double newBalance)
    {
        balance = newBalance;
    }
}
