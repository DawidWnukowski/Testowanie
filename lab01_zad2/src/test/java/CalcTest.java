import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vuko on 08.03.2016.
 */
public class CalcTest
{
    static Calc c;

    double delta = 0.0001;

    @BeforeClass
    public static void globalSetup()
    {
        c = new Calc();
    }

    @Test
    public void testAdd() throws Exception
    {
        assertEquals(10, c.add(3, 7), delta);

        assertEquals(10.2, c.add(3.1, 7.1), delta);
    }

    @Test
    public void testSub() throws Exception
    {
        assertEquals(-4, c.sub(3, 7), delta);
        assertEquals(-13, c.sub(-3, 10), delta);
        assertEquals(13, c.sub(23, 10), delta);

        assertEquals(13.5, c.sub(23.5, 10), delta);
    }

    @Test
    public void testMulti() throws Exception
    {
        assertEquals(10, c.multi(10, 1), delta);
        assertEquals(100, c.multi(10, 10), delta);
        assertEquals(-100, c.multi(-10, 10), delta);
        assertEquals(100, c.multi(-10, -10), delta);

        assertEquals(101.1, c.multi(10.11, 10), delta);
    }

    @Test
    public void testDiv() throws Exception
    {
        assertEquals(10, c.div(100, 10), delta);
        assertEquals(-10, c.div(-100, 10), delta);
        assertEquals(10, c.div(-100, -10), delta);

        assertEquals(0, c.div(0, 100), delta);

        assertEquals(33.3333, c.div(100, 3), delta);
    }

    @Test
    public void testGreater() throws Exception
    {
        assertTrue(c.greater(100, 10));
        assertFalse(c.greater(10, 100));
    }
}