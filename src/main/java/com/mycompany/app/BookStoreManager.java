package com.mycompany.app;
import java.util.Scanner;

public class BookStoreManager
{
    private final int NUMBEROFBOOKS = 5;
    
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
    
    public Book getBook(int index)
    {   
        return bookList[index];
    }

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

     // Returns an int of the index of the first book in the bookstore which matches the input
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