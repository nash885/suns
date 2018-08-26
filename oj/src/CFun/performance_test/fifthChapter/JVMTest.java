package CFun.performance_test.fifthChapter;

import java.util.Vector;

public class JVMTest {


    static void test1()
    {
        Vector v = new Vector();
        for(int i = 1;i <= 10;i++)
        {
            byte[] b = new byte[1024*1024];
            v.add(b);
            if(v.size() == 3)
            {
                v.clear();
            }
        }

    }


    static void test2()
        {

            for(int i = 0; i <1000;i++)
            {
                //System.out.println("i = "+i);
                new TestXss().start();
            }


        }

        public static void main(String[] args) {
            System.out.println("hello world");
            test2();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.gc();
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class TestXss extends Thread
    {
        @Override
        public void run() {
            try {
                byte[] a = new byte[1024];

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
