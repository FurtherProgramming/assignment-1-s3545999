package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.*;



/**
 * Unit test for simple App.
 */
public class BookStoreTest
{

    Book book1;
    Book book2;
    Book book3;

    /**
     * Rigorous Test :-)
     */

    @BeforeClass
    public static void setUpBeforeClass() throws Exception 
    {
        
    }

    public void setup() throws Exception 
    {
        
        book1 = new Book("Absolute Java", "Savitch", 5, true);
        book2 = new Book("JAVA: How to Program", "Deitel and Deitel", 0, true);
        book3 = new Book("Computing Concepts with JAVA 8 Essentials", "Horstman", 5, false);

    }
    
    @After
    public void tearDown() throws Exception 
    {
        
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void eBookAvailable()
    {
        
    }

    
}
