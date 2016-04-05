import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.easymock.EasyMock.*;
//import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by Vuko on 15.03.2016.
 */
public class GameManagerTest
{
    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    IMyList iml; //rename mock
    @TestSubject
    GameManager gm = new GameManager(iml);

//    @Before
//    public void before()
//    {
//        //iml = mock(IMyList.class);
//        iml = createMock(IMyList.class);
//        gm = new GameManager(iml);
//    }

    @Test
    public void testAdd() throws Exception
    {
        Game game = new Game("Diablo 3");

        iml.add(game);
        expectLastCall();
        expect(iml.size()).andReturn(1);

        expect(iml.getAll()).andReturn(iml);

        replay(iml);
        gm.add(game);

        assertEquals(1, gm.getAll().size());

        verify(iml);


//        gm.add(game);
//
//        assertEquals(gm.getAll().size(), 1);
//        assertEquals(gm.getAll().get(0).getTitle(), "Diablo 3");
    }

//    @Test
//    public void testGetAll() throws Exception
//    {
//        Game game = new Game("Diablo 3");
//        gm.add(game);
//
//        game = new Game("Uncharted");
//        gm.add(game);
//
//        game = new Game("Warcraft");
//        gm.add(game);
//
//        assertEquals(gm.getAll().size(), 3);
//        assertEquals(gm.getAll().get(1).getTitle(), "Uncharted");
//    }
}