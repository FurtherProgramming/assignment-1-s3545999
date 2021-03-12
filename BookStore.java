public class BookStore 
{
    
    public void run()
    {
        final int NUMBEROFBOOKS = 5;
        Book[] books = new Book[NUMBEROFBOOKS];
        String[] titles = {"Absolute Java","JAVA: How to Program", "Computing Concepts with JAVA 8 Essentials (Horstman)", "Java Software Solutions", "Java Program Design"};

        String[] authors = {"Savitch", "Deitel and Deitel", "Horstman", "Lewis and Loftus", "Cohoon and Davidson"};

        int[] numberOfItems ={5, 0, 5, 5, 1};
        boolean[] ebookAvailable = {true, true, false, false, true};
        
        for(int i=0; i < NUMBEROFBOOKS; i++)
        {
            book[i] = new Book(titles[i], authors[i],numberOfItems[i], ebookAvailable[i]);
        }
    }
}