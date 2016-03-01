import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CalculatorTest
{
    static Calculator c;

    @BeforeClass
    public static void globalSetup()
    {
        c = new Calculator();
    }

    @Test
    public void testAdd() throws Exception
    {
        assertEquals(10, c.add(3, 7));
    }

    @Test
    public void testSub() throws Exception
    {
        assertEquals(-4, c.sub(3, 7));
        assertEquals(-13, c.sub(-3, 10));
        assertEquals(13, c.sub(23, 10));
    }

    @Test
    public void testMulti() throws Exception
    {
        assertEquals(10, c.multi(10, 1));
        assertEquals(100, c.multi(10, 10));
        assertEquals(-100, c.multi(-10, 10));
        assertEquals(100, c.multi(-10, -10));
    }

    @Test
    public void testDiv() throws Exception
    {
        assertEquals(10, c.div(100, 10));
        assertEquals(-10, c.div(-100, 10));
        assertEquals(10, c.div(-100, -10));

        assertEquals(0, c.div(0, 100));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivWithException()
    {
        int i = c.div(1, 0);
    }

    @Test
    public void testGreater() throws Exception
    {
        assertTrue(c.greater(100, 10));
        assertFalse(c.greater(10, 100));
    }
}