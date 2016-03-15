import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vuko on 15.03.2016.
 */
public class GameManagerTest {

    static GameManager gm;

    @Before
    public void before()
    {
        gm = new GameManager();
    }

    @Test
    public void listShouldBeEmptyAtTheBeginning()
    {
        assertEquals(gm.getAll().size(), 0);
    }

    @Test
    public void testAdd() throws Exception
    {
        Game game = new Game("Diablo 3");
        gm.add(game);

        assertEquals(gm.getAll().size(), 1);
    }

    @Test
    public void testGetAll() throws Exception
    {
        Game game = new Game("Diablo 3");
        gm.add(game);

        game = new Game("Uncharted");
        gm.add(game);

        game = new Game("Warcraft");
        gm.add(game);

        assertEquals(gm.getAll().size(), 3);
    }
}