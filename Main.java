import java.util.Scanner;
import java.util.ArrayList;


public class Main
{
    public void runMenu() 
    {

        String menu = "";

        menu += "Welcome to Daintree!\n";
        menu += "Choose an option:\n";
        menu += "1. Add a book to shopping cart\n";
        menu += "2. View shopping cart\n";
        menu += "3. Remove a book from shopping cart\n";
        menu += "4. Checkout\n";
        menu += "5. List all books\n";
        menu += "0. Quit\n";
        menu += "Please make a selection: ";

        System.out.print(menu);
    }

    public static int checkBook(String[] bookArray, String bookToCheck) 
    {   
        bookToCheck = bookToCheck.toLowerCase();
        int matchedBook = -1;

        final int BOOKLISTLENGTH = 5;
        for (int i = 0; i < BOOKLISTLENGTH && matchedBook == -1; i++)
        {
            String arrayTitle = bookArray[i].toLowerCase();

            if (arrayTitle.startsWith(bookToCheck))
            {
               
                matchedBook = i;
            }
        }
        
        /* if (matchedBook != "")
        {
            System.out.print("The following title is a match:\n");
            System.out.print("1. " + matchedBook + "\n");
        }
        else 
        {
            System.out.print("There is not a title starting with that\n");
        }
        */
        return matchedBook;
    }

    public static void addToCart(int matchedBook, String[] titles, int[] numberOfItems, boolean[] ebookAvailable)
    {
        if(matchedBook != -1)
        {
            System.out.print("\nThe following title is a match: \n");
            System.out.printf("%n%-50s%-15s%-15s%n", "Title", "Availabilty", "eBook Availability");
            System.out.printf("%-50s%-15d%-15b%n", titles[matchedBook], numberOfItems[matchedBook], ebookAvailable[matchedBook]);
        }
        
    }


    public static void main(String[] args) 
    {
        
        String[] titles = {"Absolute Java (Savitch)","JAVA: How to Program (Deitel and Deitel)", "Computing Concepts with JAVA 8 Essentials (Horstman)", "Java Software Solutions (Lewis and Loftus)", "Java Program Design (Cohoon and Davidson)"};
        int[] numberOfItems ={5, 0, 5, 5, 1};
        boolean[] ebookAvailable = {true, true, false, false, true};

        runMenu();
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        if (choice == 1)
        {
            
            System.out.print("\nEnter title to search for: ");
            
            in.nextLine(); // To take away the next line so it waits for input

            String bookTitle = in.nextLine();

            int matchedBook = checkBook(titles, bookTitle);
            addToCart(matchedBook, titles, numberOfItems, ebookAvailable);
        }

        in.close();
    }
}
