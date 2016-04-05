/**
 * Created by Vuko on 22.03.2016.
 */
public interface IMyList
{
    void add(Game g);
    IMyList getAll();
    Game get(int index);
    int size();
    boolean isEmpty();
}
