package com.mycompany.app;
import java.util.Scanner;

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
}