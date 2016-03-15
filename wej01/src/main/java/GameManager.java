import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vuko on 15.03.2016.
 */
public class GameManager
{
    private List<Game> games;

    public GameManager()
    {
        games = new ArrayList<Game>();
    }

    public void add(Game g)
    {
        games.add(g);
    }

    public List<Game> getAll()
    {
        return games;
    }
}
