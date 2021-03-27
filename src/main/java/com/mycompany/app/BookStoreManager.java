public class BookStoreManager
{
    private final int NUMBEROFBOOKS = 5;
    private final int EBOOKCOST = 8;
    private final int PAPERBACKCOST = 50;
    private book[] bookList = null;

    // constructs the bookstore of Predetermined Books 
    public BookStoreManager()
    {
        bookList = new Book[NUMBEROFBOOKS];
        bookList[0] = new Book("Absolute Java", "Savitch", 5, true);
        bookList[1] = new Book("JAVA: How to Program", "Deitel and Deitel", 0, true);
        bookList[2] = new Book("Computing Concepts with JAVA 8 Essentials", "Horstman", 5, false);
        bookList[3] = new Book("Java Software Solutions", "Lewis and Loftus", 5, false);
        bookList[4] = new Book("Java Program Design", "Cohoon and Davidson", 1, true);
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
}