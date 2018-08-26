package CFun.performance_test.fifthChapter;

import java.util.HashMap;

public class StopTheWorldTest {


    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        PrintThread printThread = new PrintThread();
        myThread.start();
        printThread.start();

    }


}


class MyThread extends  Thread
{
    HashMap map = new HashMap();

    @Override
    public void run() {

        while(true)
        {
            if(map.size()*512/1024/1024 > 400)
            {
                map.clear();
            }
            byte[] b1;
            for (int i = 0 ; i < 100; i++)
            {
                b1 = new byte[512];
                map.put(System.nanoTime(),b1);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

class PrintThread extends Thread
{
    long starttime = System.currentTimeMillis();

    @Override
    public void run() {
        while(true)
        {
            long t = System.currentTimeMillis() - starttime;
            System.out.println(t/1000+"."+t%1000);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}