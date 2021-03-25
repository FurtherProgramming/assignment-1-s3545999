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
}