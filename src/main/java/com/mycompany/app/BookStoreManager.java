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

    
}