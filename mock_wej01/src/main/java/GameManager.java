import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vuko on 15.03.2016.
 */
public class GameManager implements IMyList
{
    //private List<Game> games;
    private IMyList games;

    public GameManager(IMyList iml)
    {
        //games = new ArrayList<Game>();
        games = iml;
    }

    public void add(Game g)
    {
        games.add(g);
    }

    public IMyList getAll()
    {
        return games.getAll();
    }

    public Game get(int index) {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }
}
