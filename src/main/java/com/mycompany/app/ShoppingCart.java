package com.mycompany.app;

public class ShoppingCart
{
    private Book[] shoppingcart;
    private final int MAXCARTSIZE = 100;
    private final int EBOOKCOST = 8;
    private final int PAPERBACKCOST = 50;
    private int length = 0;
    private int cost = 0;

    // Creates the ShoppingCart Object
    public ShoppingCart()
    {
        shoppingcart = new Book[MAXCARTSIZE];
    }

    // Returns a book at the chosen index
    public Book getBook(int index)
    {   
        return shoppingcart[index];
    }

    // Adding a book object into the array
    public void addToCart(Book bookToAdd, int typeToAdd)
    {
        if (typeToAdd == 1)
        {
            Book bookForCart = new Book(bookToAdd.getTitle(), bookToAdd.getAuthor(), 0, true);
            shoppingcart[length] = bookForCart;
            cost += EBOOKCOST;

            System.out.printf("'%s' %s%n%n", bookToAdd.getTitle(), "has been added to your cart as an eBook!");
            length ++;
        }
        else if (typeToAdd == 2)
        {
            bookToAdd.decreaseNumBooks();
            Book bookForCart = new Book(bookToAdd.getTitle(), bookToAdd.getAuthor(), 1, false);
            shoppingcart[length] = bookForCart;
            cost += PAPERBACKCOST;

            System.out.printf("'%s' %s%n%n", bookToAdd.getTitle(), "has been added to your cart as a paperback!");
            length ++;
        }
        else
        {
            System.out.print("The add to cart was cancelled!");
        }
        
    }

    // returns the length of the array
    public int getLength()
    {
        return length;
    }

    // Displays the array
    public void displayCart()
    {
        if (length != 0)
        {
            System.out.print("\nYour shopping cart contains:\n");
            for (int i =0; i< length; i++)
            {
                if (shoppingcart[i].getEbookAvailability() == true)
                {
                    System.out.printf("%d%s%s%s%n", i+1, ". ", shoppingcart[i].getTitle(), " as an eBook");
                }
                else
                {
                    System.out.printf("%d%s%s%s%n", i+1, ". ", shoppingcart[i].getTitle(), " as a paperback");
                }
            }
        }
        else 
        {
            System.out.print("\nYour shopping cart is empty!\n\n");
        }
    }

    // Checkingout prints ou
    public void checkout()
    {
        if (length != 0)
        {
            for (int i = 0; i< length; i++)
            {
                if (shoppingcart[i].getEbookAvailability() == true)
                {
                    System.out.print("You have purchased " + shoppingcart[i].getTitle() + " as an Ebook for $" + EBOOKCOST + "\n");
                }
                else
                {
                    System.out.print("You have purchased " + shoppingcart[i].getTitle() + " as a paperback for $" + PAPERBACKCOST + "\n");
                }
                shoppingcart[i] = null;
            }

            System.out.print("\nThe total cost is: $" + cost + "\n");

            cost = 0;
            length = 0;
        }
        else
        {
            System.out.print("\nYour shopping cart is empty\n");
        }
    }

    public void removeItem(int index)
    {
        if (shoppingcart[index].getEbookAvailability() == true)
        {
            cost -= EBOOKCOST;
        }
        else
        {   
            shoppingcart[index].getTitle();
            cost -= PAPERBACKCOST;
        }
        shoppingcart[index] = null;
        for (int i = index; i < length -1; i++)
        {
            shoppingcart[index] = shoppingcart[index + 1];
        }
        length --;
    }
}