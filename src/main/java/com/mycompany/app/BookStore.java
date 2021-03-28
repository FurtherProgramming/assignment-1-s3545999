package com.mycompany.app;

import java.util.Scanner;

public class BookStore
{
    // Method run at start of project
    public static void main(String[] args)
    {

        // Adding comments testing if git is working
        Menu menu = new Menu();
        BookStoreManager bookStoreManager = new BookStoreManager();
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        Selections selection = new Selections(bookStoreManager.getLength());

        int menuChoice = -1;
        Book bookChoice = null;
        int bookChosen;
        int bookType = -1;
        boolean match = false;
        String bookToRemove;
        
        while(true) 
        {
            System.out.print(menu.getMenu());
            menuChoice = menu.getIntegerInput(scanner);

            if (menuChoice == 1)
            {   
                selection.clearSelection();
                // get input
                System.out.print("\nEnter title to search for: "); 
                String bookToCheck = scanner.nextLine(); // Book title to search for

                selection.getChoices(bookToCheck, bookStoreManager); // Finds all matching books and puts into selection
                match = selection.printSelection(); // Prints books in the selection
                
                if (match == true) // If there is a book in the selection
                {
                    System.out.print("Your Choice: ");
                    bookChosen = menu.getIntegerInput(scanner);
        
                    while (bookChosen < 0 || bookChosen > (selection.getLength()))
                    {
                        System.out.print("\nThat is an invalid choice\n");
                        System.out.print("Choose Again:\n");
                        bookChosen = menu.getIntegerInput(scanner);
                    }
                    if (bookChosen == 0)
                    {
                        System.out.print("\n\nSelection cancelled!\n");
                    }
                    else
                    {
                        bookChoice = selection.getBook(bookChosen -1); // Choice of which book returns book object

                        System.out.print("Selected:");
                        bookChoice.printBookHeader();
                        bookChoice.printBook(1);

                        buyBookMenu();
                        bookType = menu.getIntegerInput(scanner);
                        while (bookType != 1 && bookType !=2 && bookType !=0)
                        {
                            System.out.print("\nInvalid option please choose again\n\n");
                            buyBookMenu();
                            bookType = menu.getIntegerInput(scanner);
                        }

                        if (bookType == 1 && bookChoice.getEbookAvailability() ==true)
                        {
                            cart.addToCart(bookChoice, bookType);
                        }
                        else if (bookType == 2 && bookChoice.getNumOfCopies() > 0)
                        {
                            for (int i = 0; i < bookStoreManager.getLength(); i++)
                            {
                                if (bookStoreManager.checkExactMatch(bookChoice.getTitle(), bookChoice.getAuthor() ,i) == true)
                                {
                                    bookStoreManager.decreaseNumBooks(i);
                                }
                            }
                            cart.addToCart(bookChoice, bookType);
                        }
                        else
                        {
                            System.out.print("\nBook is not available in this type!\n\n");
                        }

                    }
                }
            }
            else if (menuChoice == 2)
            {
                // View shopping Cart
                cart.displayCart();
            }
            else if (menuChoice == 3)
            {
                // Remove book from cart                
                int choice = -1;
        
                if(cart.getLength() != 0)
                {
                    cart.displayCart();
                    System.out.print("0. Cancel\n");
                    
                    System.out.print("Please input your choice: ");
                    choice = menu.getIntegerInput(scanner);
                    while (choice < 0 || choice > cart.getLength())
                    {
                        System.out.print("\nPlease input a valid choice!\n");
                        cart.displayCart();
                        System.out.print("0. Cancel\n");
                        System.out.print("Please input your choice: ");
                        choice = menu.getIntegerInput(scanner);
                    }

                    if (choice == 0)
                    {
                        System.out.print("No items were removed\n\n");
                    }
                    else
                    {
                        bookToRemove = cart.getBook(choice - 1).getTitle();
                        for (int i = 0; i < bookStoreManager.getLength(); i++)
                        {
                            if (bookStoreManager.checkExactMatch(bookChoice.getTitle(), bookChoice.getAuthor() ,i) == true)
                            {
                                bookStoreManager.increaseNumBooks(i);
                            }
                        }
                        cart.removeItem(choice - 1);

                    }
                }
                else
                {
                    System.out.print("\nYour shopping cart is empty!\n\n");
                }
            }
            else if (menuChoice == 4)
            {
                // Checkout

                cart.checkout();
            }
            else if (menuChoice == 5)
            {
                // List Books

                bookStoreManager.listBooks();
            }
            else if (menuChoice == 0)
            {
                System.out.print("\nGoodbye!\n");
                scanner.close();
                System.exit(0);
            }
            else
            {
                System.out.print("\nSorry that is not a valid option!\n\n");
                menuChoice = -1;
            }
        }
    }


    // Prints out the type of book to buy menu
    private static void buyBookMenu()
    {
        System.out.printf("%n%s%n","How would you like to buy:");
        System.out.print("1. eBook\n");
        System.out.print("2. Paperback\n");
        System.out.print("0. Cancel Purchase\n");
        System.out.print("Your choice: ");
    }   
}
