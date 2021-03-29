package com.mycompany.app;

public class BookStoreManager
{
    private final int NUMBEROFBOOKS = 5; // Used to set up predefined array of books
    
    private Book[] bookList = null;

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

    public int getLength()
    {
        return NUMBEROFBOOKS;
    }
    
    // Using a book in the array to update available copies
    public void increaseNumBooks(int index)
    {
        bookList[index].increaseNumBooks();
    }

    // Decrease copies in array
    public void decreaseNumBooks(int index)
    {
        bookList[index].decreaseNumBooks();
    }

    // returns the book in the array
    public Book getBook(int index)
    {   
        return bookList[index];
    }
    
    // Print out all books in the array
    public void listBooks()
    {
        System.out.print("\nThe available books are:\n");
        bookList[0].printBookHeader();
        for (int i = 0; i < NUMBEROFBOOKS; i++)
        {
            bookList[i].printBook(i + 1);
        }
        System.out.print("\n");
    }
    
    // Check for exact match of both author and title
    public boolean checkExactMatch(String bookTitle, String bookAuthor, int index)
    {
        boolean match = false;
        if (bookList[index].getTitle() == bookTitle && bookList[index].getAuthor() == bookAuthor)
        {
            match = true;
        }

        return match;
    }

    // checks if the book at the index starts with bookToCheck
    // returns boolean of the result
    public boolean checkBookMatch(String bookToCheck, int index)
    {
        bookToCheck = bookToCheck.toLowerCase();
        boolean match = false;

        String arrayTitle = bookList[index].getTitle().toLowerCase();

        if (arrayTitle.startsWith(bookToCheck))
        {
            match = true;
        }
    
        return match;
    }
}