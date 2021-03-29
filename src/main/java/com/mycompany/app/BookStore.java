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
        String bookToRemoveTitle = "";
        String bookToRemoveAuthor = "";
        
        while(true) 
        {
            menu.printMenu();
            menuChoice = menu.getIntegerInput(scanner);

            if (menuChoice == 1) // Add to cart
            {   
                selection.clearSelection(); // Remove all the books which were searched last time
                
                // get input
                System.out.print("\nEnter title to search for: ");
                String bookToCheck = scanner.nextLine(); // Book title to search for

                selection.getChoices(bookToCheck, bookStoreManager); // Finds all matching books and puts into selection
                match = selection.printSelection(); // Prints books in the selection
                
                if (match == true) // If there is a book matching the input
                {
                    System.out.print("Your Choice: ");
                    bookChosen = menu.getIntegerInput(scanner);
        
                    while (bookChosen < 0 || bookChosen > (selection.getLength())) // Ensure the input is valid
                    {
                        System.out.print("\nThat is an invalid choice\n");
                        System.out.print("Choose Again:\n");
                        bookChosen = menu.getIntegerInput(scanner);
                    }
                    if (bookChosen == 0) // If cancel selected
                    {
                        System.out.print("\n\nSelection cancelled!\n");
                    }
                    else
                    {
                        // -1 as selection is printed out starting from 1 but selection array starts at 0
                        bookChoice = selection.getBook(bookChosen -1); 
                        
                        // Print chosen book
                        System.out.print("Selected:");
                        bookChoice.printBookHeader();
                        bookChoice.printBook(1);

                        // Choosing between eBook and physical copy
                        menu.bookTypeMenu();
                        bookType = menu.getIntegerInput(scanner);
                        while (bookType != 1 && bookType !=2 && bookType !=0)
                        {
                            System.out.print("\nInvalid option please choose again\n\n");
                            menu.bookTypeMenu();
                            bookType = menu.getIntegerInput(scanner);
                        }
                        
                        
                        if (bookType == 1 && bookChoice.getEbookAvailability() ==true) // Add eBook
                        {
                            cart.addToCart(bookChoice, bookType);
                        }
                        else if (bookType == 2 && bookChoice.getNumOfCopies() > 0) // Add paperback if copy available
                        {
                            for (int i = 0; i < bookStoreManager.getLength(); i++) // Loop through books and remove chosen copy
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
            else if (menuChoice == 2) // View shopping Cart
            {   
                cart.displayCart();
            }
            else if (menuChoice == 3) // Remove book from cart 
            {                               
                int choice = -1;
        
                if(cart.getLength() != 0) // Ensure cart has an item in it
                {
                    cart.displayCart();
                    System.out.print("0. Cancel\n");
                    
                    System.out.print("Please input your choice: ");
                    choice = menu.getIntegerInput(scanner);
                    while (choice < 0 || choice > cart.getLength()) // Ensure an appropriate option is chosen
                    {
                        System.out.print("\nPlease input a valid choice!\n");
                        cart.displayCart();
                        System.out.print("0. Cancel\n");
                        System.out.print("Please input your choice: ");
                        choice = menu.getIntegerInput(scanner);
                    }

                    if (choice == 0) // 0. cancel
                    {
                        System.out.print("No items were removed\n\n");
                    }
                    else // increase num of avail books, then remove from cart
                    {
                        bookToRemoveTitle = cart.getBook(choice - 1).getTitle();
                        bookToRemoveAuthor = cart.getBook(choice - 1).getAuthor();
                        for (int i = 0; i < bookStoreManager.getLength(); i++)
                        {
                            if (bookStoreManager.checkExactMatch(bookToRemoveTitle, bookToRemoveAuthor ,i) == true)
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
            else if (menuChoice == 4) // Checkout
            {
                cart.checkout();
            }
            else if (menuChoice == 5) // List Books
            { 
                bookStoreManager.listBooks();
            }
            else if (menuChoice == 0)
            {
                for (int i = 0; i <cart.getLength(); i++) // return the books into the library, empty the cart before exiting
                {
                    if (bookStoreManager.checkExactMatch(cart.getBook(i).getTitle(), cart.getBook(i).getAuthor() ,i) == true)
                    {
                        bookStoreManager.increaseNumBooks(i);
                        cart.removeItem(i);
                    }
                }       
                System.out.print("\nGoodbye!\n");
                scanner.close();
                System.exit(0);
            }
            else
            {
                System.out.print("\nSorry that is not a valid option!\n");
                menuChoice = -1;
            }
        }
    }
}
