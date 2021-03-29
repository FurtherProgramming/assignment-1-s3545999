package com.mycompany.app;

public class Book {
    
    private String title;
    private String author;
    private int numberOfCopies;
    private boolean ebookAvailability;

    // Book Constructor
    public Book(String title, String author, int numberOfCopies, boolean ebookAvailability) {
        this.title = title;
        this.author = author;
        this.numberOfCopies = numberOfCopies;
        this.ebookAvailability = ebookAvailability;
    }

    // Title Getter
    public String getTitle()
    {
        return title;
    }

    // Author Getter
    public String getAuthor()
    {
        return author;
    }

    //Physical copy getter
    public int getNumOfCopies()
    {
        return numberOfCopies;
    }

    // eBook Availability getter
    public boolean getEbookAvailability()
    {
        return ebookAvailability;
    }
    
    // Used to decrease number of books when one is added to cart
    public void decreaseNumBooks()
    {
        numberOfCopies --;
    }

    // increase number of books if a book is removed from cart
    public void increaseNumBooks()
    {
        numberOfCopies ++;
    }

    // Displaying a given book. Index used for the number at the front
    public void printBook(int index) 
    {
        System.out.printf("%-10d%-50s%-30s%-15d%-15b%n", index, title, author, numberOfCopies, ebookAvailability);
    }

    // Works with printBook to show how it is set out
    public void printBookHeader()
    {
        System.out.printf("%n%-10s%-50s%-30s%-15s%-15s%n", "Choice","Title", "Author", "Availabilty", "eBook Availability");
    }
}