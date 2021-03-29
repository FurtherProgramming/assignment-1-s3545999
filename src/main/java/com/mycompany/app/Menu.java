package com.mycompany.app;
import java.util.Scanner;

public class Menu {

    private String menu;

    // create a menu
    public Menu()
    {
        String menuString = "";

        menuString += "\nWelcome to Daintree!\n\n";
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

    // Prints out the menu object
    public void printMenu()
    {
        System.out.print(menu);
    }

    // Used to ensure only integers can be input by catching errors and looping till no error
    // Returns the int inputted
    public int getIntegerInput(Scanner scanner)
    {
        String input = "";
        int intInputted = -1;
        boolean inputted = false;

        while (inputted == false)
        {

            input = scanner.nextLine();
            try 
            {
                intInputted = Integer.parseInt(input.trim());

                inputted = true;
            }
            catch (NumberFormatException n)
            {
                System.out.print("\nThat is not a valid integer!\n\n");
                System.out.print("Please enter a number: ");
            }         
        }
        return intInputted;
    }

    // Prints out the type of book to buy menu
    public void bookTypeMenu()
    {
        System.out.printf("%n%s%n","How would you like to buy:");
        System.out.print("1. eBook\n");
        System.out.print("2. Paperback\n");
        System.out.print("0. Cancel Purchase\n");
        System.out.print("Your choice: ");
    } 
}