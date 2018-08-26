package CFun.performance_test.fifthChapter;

public class JITTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1800;i++)
        {
            test();
        }

    }
     static int count = 0;
    static void test()
    {
        count++;
    }
}
