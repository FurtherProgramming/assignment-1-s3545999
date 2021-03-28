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

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public int getNumOfCopies()
    {
        return numberOfCopies;
    }

    public boolean getEbookAvailability()
    {
        return ebookAvailability;
    }
    
    public void decreaseNumBooks()
    {
        numberOfCopies --;
    }

    public void increaseNumBooks()
    {
        numberOfCopies ++;
    }

    public void printBook(int index) 
    {
        System.out.printf("%-10d%-50s%-30s%-15d%-15b%n", index, title, author, numberOfCopies, ebookAvailability);
    }

    public void printBookHeader()
    {
        System.out.printf("%n%-10s%-50s%-30s%-15s%-15s%n", "Choice","Title", "Author", "Availabilty", "eBook Availability");
    }
}