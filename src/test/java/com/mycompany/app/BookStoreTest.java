package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.*;



/**
*   Contains tests for all non void methods which do not require the scanner
**/
public class BookStoreTest
{

    BookStoreManager bookStoreManager;
    Selections selection;
    Book book1;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception 
    {
        
    }

    @Before
    public void setup() throws Exception 
    {
        
        bookStoreManager = new BookStoreManager();
        selection = new Selections(1);
        book1 = new Book("JAVA: How to Program", "Deitel and Deitel", 0, true);
    }
    
    @After
    public void tearDown() throws Exception 
    {
        
    }

    @Test
    public void checkExactMatch()
    {
        assertTrue(bookStoreManager.checkExactMatch("Absolute Java", "Savitch", 0));
    }

    @Test
    public void checkExactMatchFalse()
    {
        assertFalse(bookStoreManager.checkExactMatch("Absolute Java", "Savit", 0));
    }
    
    @Test
    public void checkMatch()
    {
        assertTrue(bookStoreManager.checkBookMatch("Abs", 0));
    }

    @Test
    public void checkMatchFalse()
    {
        assertFalse(bookStoreManager.checkBookMatch("zzzz", 0));
    }

    @Test
    public void printSelect()
    {
        selection.addToSelections(book1, 0);
        assertTrue(selection.printSelection());
    }
    
    @Test
    public void printSelectfail()
    {
        assertFalse(selection.printSelection());
    }
}
