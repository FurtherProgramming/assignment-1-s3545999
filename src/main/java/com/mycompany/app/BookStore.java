import java.util.Scanner;

public class BookStore 
{
    final int NUMBEROFBOOKS = 5;

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
        String[] shoppingCart = new String[3];
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

                shoppingCart = checkout(shoppingCart);
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

    /**
        Creates the predetermined array of books which fill the store
        @Returns the array of book objects
    **/

    public Book[] fillBookstore()
    {
        
        Book[] books = new Book[NUMBEROFBOOKS + 1]; // plus one as last spot is for the cart

        String[] titles = {"Absolute Java","JAVA: How to Program", "Computing Concepts with JAVA 8 Essentials", "Java Software Solutions", "Java Program Design"};

        String[] authors = {"Savitch", "Deitel and Deitel", "Horstman", "Lewis and Loftus", "Cohoon and Davidson",""};

        int[] numberOfItems ={5, 0, 5, 5, 1};
        boolean[] ebookAvailable = {true, true, false, false, true};
        
        for(int i=0; i < NUMBEROFBOOKS; i++)
        {
            books[i] = new Book(titles[i], authors[i],numberOfItems[i], ebookAvailable[i]);
        }

        return books;
    }

    private void addToCart (Scanner scanner, Book[] books)
    {

        int bookIndex;
        bookIndex = search(scanner, books);
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
                        books[NUMBEROFBOOKS] =  new Book(books[bookIndex].getTitle(), books[bookIndex].getAuthor(), 0, true);
                    }
                }
                if (typeToAdd == 2)
                {
                    boolean paperbackPurchased = addPaperbackToCart(books, bookIndex);
                    if (paperbackPurchased == true)
                    {
                        books[NUMBEROFBOOKS] =  new Book(books[bookIndex].getTitle(), books[bookIndex].getAuthor(), 1, false);
                    }
                }
            }

            
        }
        else
        {
            System.out.println("\nThere are no titles which start with that!\n");
        }
    }
    
    private int search(Scanner scanner, Book[] books)
    {
        int indexMatch = -1;

        // get input
        System.out.print("\nEnter title to search for: ");
        String bookTitle = scanner.nextLine();

        int match = checkBook(books, bookTitle);
        bookTitle = bookTitle.toLowerCase();
        return match;

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
            }
        }
        
        return input;
    }

    private void displayCart(Book[] books)
    {
        //if(checkEmptyCart(shoppingCart) == false)
        if (books[NUMBEROFBOOKS] != null)
        {
            System.out.print("\nYour shopping cart contains:\n");
            if (books[NUMBEROFBOOKS].getEbookAvailability() == true)
            {
                System.out.print("1. " + books[NUMBEROFBOOKS].getTitle() + " as an eBook\n");
            }
            else
            {
                System.out.print("1. " + books[NUMBEROFBOOKS].getTitle() + " as a paperback\n");
            }
        }
        else
        {
            System.out.print("\nThere are no items in your shopping cart\n\n");
        }
    }

    private void emptyCart(Book[] books, Scanner scanner)
    {
        int choice = -1;
        if(books[NUMBEROFBOOKS] != null)
        {
            displayCart(books);
            System.out.print("0. Cancel\n");
            
            System.out.print("Please input your choice: ");
            choice = getIntegerInput(scanner);
            while (choice != 1 && choice != 0)
            {
                System.out.print("\nPlease input a valid choice!\n");
                displayCart(books);
                System.out.print("0. Cancel\n");
                System.out.print("Please input your choice: ");
                choice = getIntegerInput(scanner);
            }

            if (choice == 1)
            {
                System.out.print("The item was removed from your cart\n\n");
                books[NUMBEROFBOOKS] = null;
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

    private String[] checkout(String[] shoppingCart)
    {
        if (checkEmptyCart(shoppingCart) == false)
        {
            System.out.print("You have purchased " + shoppingCart[0] + " as a " + shoppingCart[1] + " for $" + shoppingCart[2] + "\n\n");
            for(int i=0; i <shoppingCart.length; i++)
            {
                shoppingCart[i] = null;
            }
        }
        else
        {
            System.out.print("Your shopping cart is empty\n\n");
        }
        
        return shoppingCart;
    }

    private void listBooks(Book[] books)
    {
        System.out.printf("%n%-10s%-50s%-20s%-15s%-15s%n", "Choice","Title", "Author", "Availabilty", "eBook Availability");

        for (int i = 0; i < NUMBEROFBOOKS; i++)
        {
            System.out.printf("%-10d%-50s%-20s%-15d%-15b%n", i, books[i].getTitle(), books[i].getAuthor(), books[i].getNumOfCopies(), books[i].getEbookAvailability());
        }
        System.out.print("\n\n");
    }

    private boolean checkEmptyCart(String[] shoppingCart)
    {
        boolean cart;
        if(shoppingCart[0] != null && shoppingCart[1] != null && shoppingCart[2] != null)
        {
            cart = false;
        }
        else
        {
            cart = true;
        }

        return cart;
    }
}
