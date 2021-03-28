package com.mycompany.app;

public class Selections
{
    private Book[] selections = null;
    private int length = 0;

    public Selections(int size)
    {
        selections = new Book[size];
    }

    public void addToSelections(Book bookToAdd, int index)
    {
        Book selectBook = new Book(bookToAdd.getTitle(), bookToAdd.getAuthor(), bookToAdd.getNumOfCopies(), bookToAdd.getEbookAvailability());
        selections[index] = selectBook;
        length ++;
    }

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
            System.out.print("There were no matching Books!\n");
        }
        return matching;        
    }

    public Book getBook(int index)
    {
        return selections[index];
    }

    public void clearSelection()
    {
        for(int i = 0; i < length; i++)
        {
            selections[i] = null;
        }
        length = 0;
    }

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

    public int getLength()
    {
        return length;
    }
}