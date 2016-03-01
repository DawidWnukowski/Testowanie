
public class LiczbaRzymska
{
    private int a;

    public LiczbaRzymska(int a)
    {
        this.a = a;
    }

    @Override
    public String toString()
    {
        String str = "";
        int val;

        if(a >= 2000)
        {
            a-=2000;
            str+="MM";
        }
        if(a >= 1500)
        {
            a-=1500;
            str+="MD";
        }

        return str;
    }
}
