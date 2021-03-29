package com.mycompany.app;

public class Selections
{
    private Book[] selections = null;
    private int length = 0;

    // creates array to hold books searched for
    public Selections(int size)
    {
        selections = new Book[size];
    }

    // Returns the length of the array
    public int getLength()
    {
        return length;
    }

    // Add to the object array by handing in the book to be copied
    public void addToSelections(Book bookToAdd, int index)
    {
        Book selectBook = new Book(bookToAdd.getTitle(), bookToAdd.getAuthor(), bookToAdd.getNumOfCopies(), bookToAdd.getEbookAvailability());
        selections[index] = selectBook;
        length ++;
    }

    // Prints all books currently in the array
    public boolean printSelection()
    {
        boolean matching = false;
        if (selections[0] != null)
        {
            matching = true;
            System.out.print("The following title(s) are a match:\n");
            for(int i = 0; i < length; i++)
            {
                System.out.printf("%d%s %s%n", i + 1, ".",selections[i].getTitle());
            }
            System.out.print("0. Cancel\n\n");
        }
        else
        {
            System.out.print("There were no matching Books!\n\n");
        }
        return matching;        
    }

    // returns the Book at the array index
    public Book getBook(int index)
    {
        return selections[index];
    }

    // Empties the selection array
    public void clearSelection()
    {
        for(int i = 0; i < length; i++)
        {
            selections[i] = null;
        }
        length = 0;
    }

    // Handed a book title and the bookStoreManager can determine all matching books
    public void getChoices(String bookToMatch, BookStoreManager bookStoreManager)
    {
        int counter = 0;
        for (int i = 0; i < bookStoreManager.getLength(); i++)
        {
            if (bookStoreManager.checkBookMatch(bookToMatch, i) == true)
            {
                
                addToSelections(bookStoreManager.getBook(i), counter);
                counter ++;
            }
        }
    }

    
}