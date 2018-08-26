package CFun;

public class NO63 {

    public static void main(String[] args) {

        int p=0,e=0,a=0,r=0;

        for(p = 1; p < 2; p++)
        {
            for(e = 0; e < 10; e++)
            {
                for(a = 1; a < 10;a ++)
                {
                    for(r =0; r <10;r++)
                    {
                        if((p * 1000 +e * 100 + 10 * a + r) == (a*102+r*10+e * 10 +p * 100))
                        {
                            System.out.println("  "+p+""+e+a+r);
                            System.out.println("-  "+a+""+r+a);
                            System.out.println("------------");
                            System.out.println("   "+p+""+e+a);
                        }
                    }
                }
            }
        }







    }
}
