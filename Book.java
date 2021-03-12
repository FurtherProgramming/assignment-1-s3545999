public class Book {
    
    private String title;
    private String author;
    private int numberOfCopies;
    private boolean ebookAvailability;

    public Book(String title, String author, int numberOfCopies, boolean ebookAvailability) {
        this.title = title;
        this.author = author;
        this.numberOfCopies = numberOfCopies;
        this.ebookAvailability = ebookAvailability;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getAuthor()
    {
        return this.author;
    }

    public int getNumOfCopies()
    {
        return this.numberOfCopies;
    }

    public boolean getEbookAvailability()
    {
        return this.ebookAvailability;
    }
}