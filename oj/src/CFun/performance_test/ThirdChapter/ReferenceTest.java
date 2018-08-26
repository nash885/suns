package CFun.performance_test.ThirdChapter;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;

public class ReferenceTest {



    //softreference
    static void test1()
    {
        int max = 4*1024*925;
        MyObject obj = new MyObject();

        ReferenceQueue softQueue = new ReferenceQueue<MyObject>();

        SoftReference<MyObject> softReference = new SoftReference<MyObject>(obj,softQueue);
       // new CheckRefQueue().start();

        obj = null;

        System.gc();

        System.out.println("After GC:Soft Get= "+softReference.get());
        System.out.println("分配大块内存,强迫GC");
        byte[] b = new byte[max];

        System.out.println("After new byte[] :Soft Get ="+softReference.get());





    }


    static void testWeakQueue()
    {
        MyObject obj = new MyObject();
        ReferenceQueue<MyObject> weakQueue = new ReferenceQueue<MyObject>();

        WeakReference<MyObject> weakRef = new WeakReference<MyObject>(obj,weakQueue);

        obj = null;

        System.out.println("Before GC:Weak Get  "+weakRef.get());
        System.gc();

        System.out.println("After GC: Weak Get= "+ weakRef.get());





    }


    static void testPhantomRef()
    {
        MyObject obj = new MyObject();
        ReferenceQueue<MyObject> phantonQueue = new ReferenceQueue<MyObject>();

        PhantomReference<MyObject> phantomRef = new PhantomReference<MyObject>(obj,phantonQueue);

        System.out.println("Phantom Get :"+phantomRef.get() );

        obj = null;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1;

        while(true)
        {
            System.out.println("第"+i+++"次 gc");
            System.gc();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }






    }

    static void testReliveObj()
    {
        CanReliveObj.obj = new CanReliveObj();

         CanReliveObj.obj = null;

        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if( CanReliveObj.obj== null)
        {
            System.out.println("obj 是 null");
        }
        else
        {
            System.out.println("obj 是 可用");
        }
        System.out.println("第二次 gc");
        System.gc();

        if( CanReliveObj.obj== null)
        {
            System.out.println("obj 是 null");
        }
        else
        {
            System.out.println("obj 是 可用");
        }


        int i = 1;

        while(true)
        {
            System.out.println("第"+i+++"次 gc");
            System.gc();
            try {
                Thread.sleep(1000);
                System.out.println(CanReliveObj.obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }


    static void testWeakHashMap()
    {
        Map map = new WeakHashMap();

        List l = new ArrayList();
        int max = 10000;
        for (int i = 0; i < max ;i++)
        {
            Integer ii = new Integer(i);
            l.add(ii);
            map.put(ii,new byte[i]);
            System.out.println("map size = "+map.size());
        }

        System.out.println("map size = "+map.size());
        for(Object integer :map.entrySet())
        {
            System.out.println("key = "+integer);
        }
//        map = new HashMap();
//        for (int i = 0; i < max ;i++)
//        {
//            Integer ii = new Integer(i);
//            map.put(ii,new byte[i]);
//        }

    }

    public static void main(String[] args) {
         //test1();//软引用测试
        //testWeakQueue();//测试弱引用
      //  testPhantomRef();
     //   testReliveObj();

        testWeakHashMap();


    }



}

class MyObject
{
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyObject's finalize called");
    }

    @Override
    public String toString() {
        return "I am MyObject";
    }
}

class CanReliveObj
{
    public  static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }
}
