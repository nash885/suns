package CFun.performance_test.fourthChapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizeTest {

    public static void main(String[] args) {

        //test3();
        test4();



    }



    static void test1()
    {
        MyBlockQueue queue = new MyBlockQueue();
        ExecutorService executors = Executors.newCachedThreadPool();
        int max = 10;
        for(int i = 0; i < max;i++)
        {
            executors.execute(new Runnable() {
                @Override
                public void run() {


                    while(true) {
                        System.out.println("consume No is ="+this.toString());
                        queue.pop();
                    }
                }
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 1;i++)
        {
            executors.execute(new Runnable() {
                @Override
                public void run() {


                    while(true) {
                        System.out.println("hello i am producer");
                        queue.put("1");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }

    }


    /**
     * 重入锁，读写锁测试
     */
    static void test2()
    {
        MyQueue queue = new MyQueue();
        ExecutorService service = Executors.newCachedThreadPool();

        int max = 1000;

        long start = System.currentTimeMillis();
        for(int i = 0 ; i < max;i++)
        {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    queue.handleRead();
                }
            });
        }
        service.shutdown();

        while(!service.isTerminated())
        {

        }
        System.out.println("ReentrantLock read cost "+(System.currentTimeMillis() - start)+" ms");


        service = Executors.newCachedThreadPool();
        start = System.currentTimeMillis();


        for(int i = 0 ; i < max;i++)
        {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    queue.handleRead2();
                }
            });
        }



        service.shutdown();

        while(!service.isTerminated())
        {

        }
        System.out.println("ReentrantReadWriteLock read cost "+(System.currentTimeMillis() - start)+" ms");



        service = Executors.newCachedThreadPool();
        start = System.currentTimeMillis();
        for(int i = 0 ; i < max;i++)
        {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    queue.handleWrite(1);
                }
            });
        }

        service.shutdown();

        while(!service.isTerminated())
        {

        }
        System.out.println("ReentrantLock write cost "+(System.currentTimeMillis() - start)+" ms");



        service = Executors.newCachedThreadPool();
        start = System.currentTimeMillis();
        for(int i = 0 ; i < max;i++)
        {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    queue.handleWrite2(1);;
                }
            });
        }

        service.shutdown();

        while(!service.isTerminated())
        {

        }
        System.out.println("ReentrantReadWriteLock write cost "+(System.currentTimeMillis() - start)+" ms");






    }


    /**
     * condition test
     */
    static  void test3()
    {

        MyBlockQueue2 queue2 = new MyBlockQueue2();
        int max = 1000;
    new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i < max;i++) {
                    queue2.put();
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < max;i++) {
                    queue2.take();
                }
            }
        }).start();

    }


    static void test4()
    {
        int max = 1000000;

        long start = System.currentTimeMillis();

        for(int i = 0 ; i < max; i++)
        {
            synchronized (SynchronizeTest.class)
            {

            }
            synchronized (SynchronizeTest.class)
            {

            }

        }
        System.out.println("cost time is "+(System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for(int i = 0 ; i < max; i++)
        {
            synchronized (SynchronizeTest.class)
            {

            }


        }

        System.out.println("cost time is "+(System.currentTimeMillis() - start));







    }







}


/**
 * 重入 读写锁 锁测试
 */
class MyQueue
{
    static Lock lock= new ReentrantLock();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    static int value;

    public Object handleRead()
    {
        lock.lock();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return value;

    }

    public void handleWrite(int index)
    {
        lock.lock();
        try {
            Thread.sleep(1);
            value =index;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }



    }

    public Object handleRead2()
    {
        readLock.lock();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }

        return value;

    }

    public void handleWrite2(int index)
    {
        writeLock.lock();
        try {
            Thread.sleep(1);
            value =index;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }



    }




}

class MyBlockQueue
{
    private List list = new ArrayList();


    public synchronized Object pop()
    {
        while(list.size() == 0)
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        if(list.size() > 0)
        {
            return list.remove(0);
        }else
        {
            return null;
        }





    }


    public synchronized  void put(Object o)
    {
        list.add(o);
    //    this.notify();
    }
}


class MyBlockQueue2
{
    private final ReentrantLock lock = new ReentrantLock(false) ;
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    int i = 0;
    public  void put()
    {
        lock.lock();

        while( i == 10 )
        {
            try {
                System.out.println("notFull.await()");
                notFull.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
                notFull.signal();
            }

        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i ++;
        notEmpty.signal();
        System.out.println("put "+i);
        lock.unlock();



    }

public void take()
{
    lock.lock();

    while(i == 0)
    {
        try {

            System.out.println("notEmpty.await();");
            notEmpty.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    System.out.println("take "+i);
    i--;
    notFull.signal();
    lock.unlock();


}




}
