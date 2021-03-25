package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.*;



/**
 * Unit test for simple App.
 */
public class BookStoreTest
{

    BookStore bookstore;
    /**
     * Rigorous Test :-)
     */

    @BeforeClass
    public static void setUpBeforeClass() throws Exception 
    {
        
    }

    @Before
    public void setup() throws Exception 
    {
        
        bookstore = new BookStore();

    }
    
    @After
    public void tearDown() throws Exception 
    {
        
    }

    @Test
    public void searchForBook()
    {
        assertEquals(bookstore.searchForBook("Java"), 1);
    }
    
    @Test
    public void addEbookTest()
    {
        assertTrue(bookstore.addEbookToCart(1)); // book 1 has an eBook available
    }

    
}
