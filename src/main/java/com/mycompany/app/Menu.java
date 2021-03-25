package com.mycompany.app;

public class Menu {

    private String menu;

    public Menu()
    {
        String menuString = "";

        menuString += "Welcome to Daintree!\n";
        menuString += "Choose an option:\n";
        menuString += "1. Add a book to shopping cart\n";
        menuString += "2. View shopping cart\n";
        menuString += "3. Remove a book from shopping cart\n";
        menuString += "4. Checkout\n";
        menuString += "5. List all books\n";
        menuString += "0. Quit\n";
        menuString += "Please make a selection: ";

        menu = menuString;
    }

    public String getMenu()
    {
        return menu;
    }

    public int getChoice(Scanner scanner)
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

    public choice (int menuChoice)
    {
        if (menuChoice == 1)
            {
                addToCart(scanner, books);

            }
            else if (menuChoice == 2)
            {
                // View shopping Cart
                displayCart(books);
            }
            else if (menuChoice == 3)
            {
                // Remove book from cart
                emptyCart(books, scanner);
            }
            else if (menuChoice == 4)
            {
                // Checkout

                checkout(books);
            }
            else if (menuChoice == 5)
            {
                // List Books

                listBooks(books);
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