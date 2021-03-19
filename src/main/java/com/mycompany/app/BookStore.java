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
        String shoppingCart = "";
        Scanner scanner = new Scanner(System.in);
        int menuChoice = -1;
        int bookIndex = -1;

        // Loop to continue offering the menu to choose another option until exit with option 0
        while(true) 
        {   
            runMenu();
            menuChoice = getIntegerInput(scanner);
            
            // Add to shopping cart
            if (menuChoice == 1)
            {
                bookIndex = search (scanner, books);

                shoppingCart = addToCart(scanner, books, bookIndex);
            }
            else if (menuChoice == 2)
            {
                // View shopping Cart
                System.out.print("\nYour shopping cart contains:\n");
                System.out.print(shoppingCart + "\n");
            }
            else if (menuChoice == 3)
            {
                // Remove book from cart
                shoppingCart = emptyCart(shoppingCart, scanner);
            }
            else if (menuChoice == 4)
            {
                // Checkout

                // checkout(shoppingCart);
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

    private int search(Scanner scanner, Book[] books)
    {
        int indexMatch = -1;

        // get input
        System.out.print("\nEnter title to search for: ");
        String bookTitle = scanner.nextLine();

        int match = checkBook(books, bookTitle);

        return match;

    }

    private String addToCart (Scanner scanner, Book[] books, int bookIndex)
    {
        String shoppingCart="";
        
        if (bookIndex != -1)
        {
            boolean purchasing = confirmBook(books, bookIndex, scanner);
            if (purchasing == true)
            {
                
                int typeToAdd = typeOfBook(books, bookIndex, scanner);

                if (typeToAdd == 1)
                {
                    boolean ebookPurchased = addEbookToCart(books, bookIndex);
                    if (ebookPurchased == true)
                    {
                        shoppingCart = "1. "+ books[bookIndex].getTitle() + " as a paperback" + "\n";
                    }
                }
                if (typeToAdd == 2)
                {
                    boolean paperbackPurchased = addPaperbackToCart(books, bookIndex);
                    if (paperbackPurchased == true)
                    {
                        shoppingCart = "1. "+ books[bookIndex].getTitle() + " as a paperback" + "\n";
                    }
                }
            }
        }
        else
        {
            System.out.println("\nThere are no titles which start with that!\n");
        }
        return shoppingCart;
    }

    private int checkBook(Book[] books, String bookToCheck) 
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

    private boolean confirmBook(Book[] books, int matchedBook, Scanner scanner)
    {
        boolean buying = false;
        String title = books[matchedBook].getTitle();
        int bookChoice = -1;

        while (bookChoice != 0 && bookChoice !=1) // Wait till a choice is made from list
        {
            cartMatch(books, matchedBook);
            bookChoice = getIntegerInput(scanner);
            if (bookChoice == 1)
            {
                buying = true;
            }
            else if (bookChoice == 0)
            {
                System.out.print("Purchase Cancelled!\n\n");
                buying = false;
            }
            else
            {
                System.out.print("\nInvalid option please choose again\n\n");
            }
        }
        return buying;
    }
    

    private int typeOfBook(Book[] books, int matchedBook, Scanner scanner)
    {
        
        String title = books[matchedBook].getTitle();
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

    
    // Prints out the match to the inputted book
    private void cartMatch(Book[] books, int matchedBook)
    {
        System.out.print("\nThe following title is a match: \n");
        System.out.printf("%n%-10s%-35s%-20s%-15s%-15s%n", "Choice","Title", "Author", "Availabilty", "eBook Availability");
        System.out.printf("%-10d%-35s%-20s%-15d%-15b%n", 1, books[matchedBook].getTitle(), books[matchedBook].getAuthor(), books[matchedBook].getNumOfCopies(), books[matchedBook].getEbookAvailability());
        System.out.printf("%-10s%-35s%n%n", "0.", "Cancel");
        System.out.print("What is your choice: ");
    }


    // Prints out the type of book to buy menu 
    private void buyBookMenu(String title)
    {
        System.out.printf("%s '%s'%n","How would you like to buy", title);
        System.out.print("1. eBook\n");
        System.out.print("2. Paperback\n");
        System.out.print("0. Cancel Purchase\n");
        System.out.print("Your choice: ");
    }

    // Checks if there is a eBook is available
    // Prints if there was one available or not
    // Returns a boolean whether it was added to cart or not
    private boolean addEbookToCart(Book[] books, int matchedBook)
    {
        boolean availability = books[matchedBook].getEbookAvailability();
        String title = books[matchedBook].getTitle();

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
    private boolean addPaperbackToCart(Book[] books, int matchedBook)
    {
        boolean availability;
        String title = books[matchedBook].getTitle(); 
        if (books[matchedBook].getNumOfCopies() != 0)
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
                scanner.nextLine(); // Use up the invalid non integer input
            }
        }
        
        return input;
    }

    private String emptyCart(String shoppingCart, Scanner scanner)
    {
        String cart = shoppingCart;
        int choice = -1;
        
        
        System.out.print("\nYour shopping cart contains:\n");
        System.out.print(shoppingCart);
        System.out.print("0. Cancel\n");
        System.out.print("Please input your choice: ");
        choice = getIntegerInput(scanner);
        while (choice != 1 && choice != 0)
        {
            System.out.print("\nPlease input a valid choice!\n");
            System.out.print("\nYour shopping cart contains:\n");
            System.out.print(shoppingCart);
            System.out.print("0. Cancel\n");
            System.out.print("Please input your choice: ");
            choice = getIntegerInput(scanner);
        }

        if (choice == 1)
        {
            System.out.print("The item was removed from your cart\n");
            cart = "";
        }
        else
        {
            System.out.print("No items were removed\n");
        }
        return cart;
    }

    // private checkout(String shoppingCart)
    //{
    //    if (shoppingCart.contains(paperback))
    //    {

    //    }
    //}
}
