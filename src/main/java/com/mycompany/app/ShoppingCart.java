package com.mycompany.app;

// second git test
public class ShoppingCart
{
    private Book[] shoppingcart;
    private final int MAXCARTSIZE = 100;
    private final int NUMBEROFBOOKS = 5;
    private final int EBOOKCOST = 8;
    private final int PAPERBACKCOST = 50;
    private int length = 0;

    public ShoppingCart()
    {
        shoppingcart = new Book[MAXCARTSIZE];
    }


    public void addToCart(Book bookToAdd, int typeToAdd)
    {
        if (typeToAdd == 1)
        {
            Book bookForCart = new Book(bookToAdd.getTitle(), bookToAdd.getAuthor(), 0, true);
            shoppingcart[length] = bookForCart;
        }
        else
        {
            Book bookForCart = new Book(bookToAdd.getTitle(), bookToAdd.getAuthor(), 1, false);
            shoppingcart[length] = bookForCart;
        }
        length ++;
    }

    public int getLength()
    {
        return length;
    }

    public void displayCart()
    {
        
        if (length != 0)
        {
            System.out.print("\nYour shopping cart contains:\n");
            for (int i =0; i< length; i++)
            {
                if (shoppingcart[i].getEbookAvailability() == true)
                {
                    System.out.printf("%d%s%s%n", i+1, ". ", shoppingcart[i].getTitle(), "as an eBook");
                }
                else
                {
                    System.out.printf("%d%s%s%n", i+1, ". ", shoppingcart[i].getTitle(), "as a paperback");
                }
            }
            System.out.print("\n");
        }
        else 
        {
            System.out.print("\nYour shopping cart is empty!\n\n");
        }
    }

    public void checkout()
    {
        int index;
        if (length != 0)
        {
            for (int i = 0; i< length; i++)
            {
                if (shoppingcart[i].getEbookAvailability() == true)
                {
                    System.out.print("You have purchased " + shoppingcart[i].getTitle() + " as an Ebook for $" + EBOOKCOST + "\n\n");
                }
                else
                {
                    System.out.print("You have purchased " + shoppingcart[i].getTitle() + " as a paperback for $" + PAPERBACKCOST + "\n\n");
                }
                shoppingcart[i] = null;
            }
            length = 0;
        }
        else
        {
            System.out.print("Your shopping cart is empty\n\n");
        }
    }

    public void removeItem(int index)
    {
        shoppingcart[index] = null;
        for (int i = index; index < length; index++)
        {
            shoppingcart[index] = shoppingcart[index + 1];
        }
        length --;
    }
}