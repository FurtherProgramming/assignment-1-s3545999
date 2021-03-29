package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.*;



/**
 * Unit test for simple App.
 */
public class BookStoreTest
{

    BookStoreManager bookStoreManager;
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
        
        bookStoreManager = new BookStoreManager();

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
    public void bookStoreManagerLength()
    {
        assertEquals(bookStoreManager.getLength(), 5);
    }
    
}
