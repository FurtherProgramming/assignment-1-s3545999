package com.mycompany.app;

import java.util.Scanner;
import java.util.ArrayList;

public class BookStore
{
    // Method run at start of project
    public void main(String[] args)
    {

        // Adding comments testing if git is working
        Menu menu = new Menu();
        BookStoreManagement bookStoreManager = new BookStoreManager();
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        
        int menuChoice = -1;
        int bookChoice = -1;
        
        while(true) 
        {
            System.out.print(menu.getMenu());
            menuChoice = menu.getChoice(scanner);

            if (menuChoice == 1)
            {   
                // get input
                System.out.print("\nEnter title to search for: ");
                String bookToCheck = scanner.nextLine();
                bookChoice = bookStore.searchForBook(bookToCheck);
                if (bookStore.confirmCorrectBook(bookChoice, scanner) == 1)
                {
                    int bookType = bookStore.typeOfBook(bookChoice, scanner);
                    bookStore.addToCart(bookType, bookChoice, scanner, cart);
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
                bookStore.emptyCart(scanner);
            }
            else if (menuChoice == 4)
            {
                // Checkout

                bookStore.checkout();
            }
            else if (menuChoice == 5)
            {
                // List Books

                bookStore.listBooks();
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
    // Returns an int of the index of the first book in the bookstore which matches the input
    public int searchForBook(String bookToCheck)
    {
        bookToCheck = bookToCheck.toLowerCase();
        int matchedBook = -1;

        // Loops through all books and stop when match found
        for (int i = 0; i < 5 && matchedBook == -1; i++)
        {
            String arrayTitle = bookList[i].getTitle().toLowerCase();

            if (arrayTitle.startsWith(bookToCheck))
            {
                matchedBook = i;
            }
        }
        return matchedBook;
    }
    
    // Offers the book for purchase
    // Returns a 1 if wanted to purchase 
    // Returns a 0 if not wanted
    public int confirmCorrectBook(int bookIndex, Scanner scanner)
    {
        int bookChoice = -1;
        while (bookChoice != 0 && bookChoice !=1) // Wait till a choice is made from list
        {
            if (bookIndex != -1)
            {
                System.out.print("\nThe following title is a match: \n");
                bookList[bookIndex].printBookHeader();
                bookList[bookIndex].printBook(1); // 1 indicates first in list of books
                System.out.printf("%-10d%-35s%n", 0, "Cancel");
                System.out.print("What is your choice: ");
                bookChoice = getIntegerInput(scanner);
            }
            else
            {
                System.out.println("\nThere are no titles which start with that!\n");
            }
        }
        return bookChoice;
    }

    // Once the book is known an
    public void addToCart(int bookType, int bookIndex, Scanner scanner, ShoppingCart cart)
    {
        if (bookType == 1)
        {
            boolean ebookPurchased = addEbookToCart(bookIndex);
            if (ebookPurchased == true)
            {
                cart.addToCart(bookList[bookIndex].getTitle(), bookType);
            }
        }
        else if (bookType == 2)
        {
            boolean paperbackPurchased = addPaperbackToCart(bookIndex);
            if (paperbackPurchased == true)
            {
                cart.addToCart(bookList[bookIndex].getTitle(), bookType);
            }
        }
        else if (bookType == 0)
        {
            System.out.print("Purchase Cancelled!");
        }

    }

    //allows the choice of type of book
    // Only returns 1, 2 or 0
    // 1 - eBook
    // 2 - paperBack
    // 0 - cancel
    public int typeOfBook(int matchedBook, Scanner scanner)
    {
        
        String title = bookList[matchedBook].getTitle();
        int typeToAdd;

        buyBookMenu(title);
        typeToAdd = getIntegerInput(scanner);
        while (typeToAdd != 1 && typeToAdd !=2 && typeToAdd !=0)
        {
            System.out.print("\nInvalid option please choose again\n\n");
            buyBookMenu(title);
            typeToAdd = getIntegerInput(scanner);
        }
        return typeToAdd;
    }

    // Prints out the type of book to buy menu
    private void buyBookMenu(String title)
    {
        System.out.printf("%n%s '%s'%n","How would you like to buy", title);
        System.out.print("1. eBook\n");
        System.out.print("2. Paperback\n");
        System.out.print("0. Cancel Purchase\n");
        System.out.print("Your choice: ");
    }

    // Checks if there is a eBook is available
    // Prints if there was one available or not
    // Returns a boolean whether it was added to cart or not
    public boolean addEbookToCart(int matchedBook)
    {
        boolean availability = bookList[matchedBook].getEbookAvailability();
        String title = bookList[matchedBook].getTitle();

        if (availability == true)
        {
            System.out.printf("%n%s%s%n%n", title, " has been added to your cart as an eBook");
        }
        else
        {
            System.out.printf("%n%s%s", title, " is not available as an eBook");
        }
        return availability;
    }


    // Checks if there is a paperback is available
    // Prints if there was one available or not
    // Returns a boolean whether it was added to cart or not
    public boolean addPaperbackToCart(int matchedBook)
    {
        boolean availability;
        String title = bookList[matchedBook].getTitle(); 
        if (bookList[matchedBook].getNumOfCopies() != 0)
        {
            availability = true;
            System.out.printf("%n%s%s%n%n", title, " has been added to your cart as a paperback");
        }
        else
        {
            availability = false;
            System.out.printf("%n%s%s%s%n%n", "There are no paperbacks of '", title, "' left");
            System.out.printf("'%s'%s", title, " was not added to your cart\n\n");
        }
        return availability;
    }

    // Hand in scanner and get integer input
    // Ensures non integer can be input instead of int
    // Avoids scanner eating the next input
    // Returns the integer
    public int getIntegerInput(Scanner scanner)
    {
        int input = -1;
        boolean inputted = false;

        while (inputted == false)
        {
            if (scanner.hasNextInt()) // Ensure next input is an integer
            {
                input = scanner.nextInt();
                scanner.nextLine();
                inputted = true;
            }
            else
            {
                System.out.print("\nThat is not a valid integer!\n\n");
                System.out.print("Please enter a number: ");
            }
        }
        return input;
    }

    public void displayCart()
    {
        if (bookList[NUMBEROFBOOKS] != null)
        {
            System.out.print("\nYour shopping cart contains:\n");
            if (bookList[NUMBEROFBOOKS].getEbookAvailability() == true)
            {
                System.out.print("1. " + bookList[NUMBEROFBOOKS].getTitle() + " as an eBook\n\n");
            }
            else
            {
                System.out.print("1. " + bookList[NUMBEROFBOOKS].getTitle() + " as a paperback\n\n");
            }
        }
        else
        {
            System.out.print("\nThere are no items in your shopping cart\n\n");
        }
    }

    public void emptyCart(Scanner scanner)
    {
        int choice = -1;

        if(bookList[NUMBEROFBOOKS] != null)
        {
            displayCart();
            System.out.print("0. Cancel\n");
            
            System.out.print("Please input your choice: ");
            choice = getIntegerInput(scanner);
            while (choice != 1 && choice != 0)
            {
                System.out.print("\nPlease input a valid choice!\n");
                displayCart();
                System.out.print("0. Cancel\n");
                System.out.print("Please input your choice: ");
                choice = getIntegerInput(scanner);
            }

            if (choice == 1)
            {
                System.out.print("The item was removed from your cart\n\n");
                bookList[NUMBEROFBOOKS] = null;
            }
            else
            {
                System.out.print("No items were removed\n\n");
            }
        }
        else
        {
            System.out.print("\nYour shopping cart is empty!\n\n");
        }
    }

    public void checkout()
    {
        int index;
        if (bookList[NUMBEROFBOOKS] != null)
        {
            if (bookList[NUMBEROFBOOKS].getEbookAvailability() == true)
            {
                System.out.print("You have purchased " + bookList[NUMBEROFBOOKS].getTitle() + " as an Ebook for $" + EBOOKCOST + "\n\n");
            }
            else
            {
                System.out.print("You have purchased " + bookList[NUMBEROFBOOKS].getTitle() + " as a paperback for $" + PAPERBACKCOST + "\n\n");
                index = searchForBook(bookList[NUMBEROFBOOKS].getTitle());
                bookList[index].decreaseNumBooks();
            }
            
            bookList[NUMBEROFBOOKS] = null;
        }
        else
        {
            System.out.print("Your shopping cart is empty\n\n");
        }
    }

    public void listBooks()
    {
        bookList[0].printBookHeader();
        for (int i = 0; i < NUMBEROFBOOKS; i++)
        {
            bookList[i].printBook(i+1);
        }
        System.out.print("\n\n");
    }

}
