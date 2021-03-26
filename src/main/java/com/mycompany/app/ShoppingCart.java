package com.mycompany.app;

// second git test
public class ShoppingCart
{
    private String[] shoppingcart;
    private final int MAXCARTSIZE = 100;
    private int length = 0;

    public ShoppingCart()
    {
        shoppingcart = new String[MAXCARTSIZE];
    }


    public void addToCart(String title, int typeToAdd)
    {
        String type;
        String toAdd;
        if (typeToAdd == 1)
        {
            type = "eBook";
        }
        else
        {
            type = "paperback";
        }
        
        toAdd = title + " as a " + type;
        shoppingcart[length] = toAdd;
        length ++;
    }

    public void displayCart()
    {
        
        if (length != 0)
        {
            System.out.print("\nYour shopping cart contains:\n");
            for (int i =0; i< length; i++)
            {
                System.out.printf("%d%s %s%n", i+1, ".", shoppingcart[i]);
            }
            System.out.print("\n");
        }
        else 
        {
            System.out.print("\nYour shopping cart is empty!\n\n");
        }
    }
}