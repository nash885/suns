package CFun.performance_test.fifthChapter;

public class TestStack {



    static void test2()
    {

        {
            byte[] b = new byte[6*1024*1024];
         b = null;
        }
      //  int a=0;
        System.gc();
        System.out.println("hello world");

    }
    public static void main(String[] args) {

        test2();
    }
    static int count=0;


    public static void recursion(double x,double b ,double c ,double e, double d)
    {
        System.out.println("count = "+count);
        count++;
        recursion(1,2,3,4,5);
    }

    /**
     * 测试堆栈深度
     */
    public static void test1()
    {

            recursion(1,2,3,4,5);



    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("count = "+count);
    }
}
