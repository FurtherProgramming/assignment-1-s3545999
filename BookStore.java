import java.util.Scanner;

public class BookStore 
{
    
    public static void runMenu() 
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
    
    public void run()
    {
        // Makes all predetermined book obnjects in an array
        Book books[] = fillBookstore();

        Scanner scanner = new Scanner(System.in);
        int menuChoice = -1;

        // Loop to continue offering the menu to choose another option until exit with option 0
        while(true) 
        {   
            runMenu();

            if (scanner.hasNextInt()) // Check to ensure that the next input is an integer
            {
                menuChoice = scanner.nextInt();
                scanner.nextLine(); // To take away the next line so it waits for input next time
            

                // Add to shopping cart
                if (menuChoice == 1)
                {
                    // get input
                    System.out.print("\nEnter title to search for: ");
                    String bookTitle = scanner.nextLine();

                    int match = checkBook(books, bookTitle);

                    addToCart(books, match, scanner);
                    

                }
                else if (menuChoice == 2)
                {
                    // View shopping Cart
                }
                else if (menuChoice == 3)
                {
                    // Remove book from cart
                }
                else if (menuChoice == 4)
                {
                    // Checkout
                }
                else if (menuChoice == 5)
                {
                    // List Books
                }
                else if (menuChoice == 0)
                {
                    scanner.close();
                    System.exit(0);
                }
                else
                {
                    System.out.print("\nSorry that is not a valid option!\n\n");
                    menuChoice = -1;
                }
            }
            else
            {
                System.out.print("\nPlease input an number between 0 and 5\n\n");
                menuChoice = -1;
                scanner.nextLine(); // Use up the invalid non integer input
            }
        }
        
    }

    /**
        Creates the predetermined array of books which fill the store
        @Returns the array of book objects
    **/
    public Book[] fillBookstore()
    {
        final int NUMBEROFBOOKS = 5;
        Book[] books = new Book[NUMBEROFBOOKS];
        String[] titles = {"Absolute Java","JAVA: How to Program", "Computing Concepts with JAVA 8 Essentials (Horstman)", "Java Software Solutions", "Java Program Design"};

        String[] authors = {"Savitch", "Deitel and Deitel", "Horstman", "Lewis and Loftus", "Cohoon and Davidson"};

        int[] numberOfItems ={5, 0, 5, 5, 1};
        boolean[] ebookAvailable = {true, true, false, false, true};
        
        for(int i=0; i < NUMBEROFBOOKS; i++)
        {
            books[i] = new Book(titles[i], authors[i],numberOfItems[i], ebookAvailable[i]);
        }

        return books;
    }


    public static int checkBook(Book[] books, String bookToCheck) 
    {   
        bookToCheck = bookToCheck.toLowerCase();
        int matchedBook = -1;

        for (int i = 0; i < 5 && matchedBook == -1; i++)
        {
            String arrayTitle = books[i].getTitle().toLowerCase();

            if (arrayTitle.startsWith(bookToCheck))
            {
                matchedBook = i;
            }
        }
    
        return matchedBook;
    }


    // This method needs to be broken down into other methods
    public void addToCart(Book[] books, int matchedBook, Scanner scanner)
    {
        if(matchedBook != -1)
        {
            String title = books[matchedBook].getTitle();
            String bookToAdd = "";
            int typeToAdd;
            String bookType;

            cartMatch(books, matchedBook); // Run menu
            if (scanner.hasNextInt())
            {
                int bookChoice = scanner.nextInt(); // Choice from menu

            if (bookChoice == 1)
            {
                buyBookMenu(title);
                typeToAdd = scanner.nextInt();
                scanner.nextLine();

                // Only runs if the choice was not an ebook, physical copy or exit
                while (typeToAdd !=0 && typeToAdd !=1 && typeToAdd !=2)
                {
                    System.out.print("Invalid option choose again");
                    buyBookMenu(title);
                    typeToAdd = scanner.nextInt();
                    scanner.nextLine(); // To take away the next line so it waits for input next time
                }
                
                if (typeToAdd == 1)
                {
        
                    if (books[matchedBook].getEbookAvailability() == true)
                    {
                        System.out.printf("%s%s%n%n", title, " has been added to your cart as an eBook");
                    }
                    else
                    {
                        System.out.printf("%s%s", title, " is not available as an eBook");
                    }
                }
                else if (typeToAdd == 2)
                {
                    if (books[matchedBook].getNumOfCopies() != 0)
                    {
                        System.out.printf("%s%s%n%n", title, " has been added to your cart as a paperback");
                    }
                    else
                    {
                        System.out.printf("%s%s%s%n%n", "There are no paperbacks of ", title, " left");
                    }
                }
                else
                {
                    System.out.print("Purchased cancelled!");
                }
            }
            else
            {
                System.out.print("Purchase Cancelled!\n\n");
            }
            }
            

        }
        else
        {
            System.out.println("\nThere are no titles which start with that!\n");
        }
    }

    private void cartMatch(Book[] books, int matchedBook)
    {
        System.out.print("\nThe following title is a match: \n");
        System.out.printf("%n%-10s%-35s%-20s%-15s%-15s%n", "Choice","Title", "Author", "Availabilty", "eBook Availability");
        System.out.printf("%-10d%-35s%-20s%-15d%-15b%n", 1, books[matchedBook].getTitle(), books[matchedBook].getAuthor(), books[matchedBook].getNumOfCopies(), books[matchedBook].getEbookAvailability());
        System.out.printf("%-10s%-35s%n%n", "0.", "Cancel");
        System.out.print("What is your choice: ");
    }

    private void buyBookMenu(String title)
    {
        System.out.printf("%s '%s'%n","How would you like to buy", title);
        System.out.print("1. eBook\n");
        System.out.print("2. Paperback\n");
        System.out.print("0. Cancel Purchase\n");
    }
}
