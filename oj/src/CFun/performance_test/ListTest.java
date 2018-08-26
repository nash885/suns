package CFun.performance_test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ListTest {



    static  void test1()
    {

        int max =100000;
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();


        long start = System.currentTimeMillis();

        for (int i = 0; i < max; i++)
        {
            arrayList.add(i);
        }
        System.out.println("arrayList costs time is "+(System.currentTimeMillis()-start)+" ms");



        start = System.currentTimeMillis();

        for (int i = 0; i < max; i++)
        {
            arrayList.add(0,i);
        }
        System.out.println("arrayList.add(0,object) costs time is "+(System.currentTimeMillis()-start)+" ms");


        start = System.currentTimeMillis();

        for (int i = 0; i < max; i++)
        {
            linkedList.add(i);
        }
        System.out.println("linkedList costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();

        for (int i = 0; i < max; i++)
        {
            linkedList.add(0,i);
        }
        System.out.println("linkedList.add(0,object) costs time is "+(System.currentTimeMillis()-start)+" ms");




    }



    static void test2()
    {
        int max =10000;
        ArrayList<Integer> arrayList = new ArrayList();
        LinkedList<Integer> linkedList = new LinkedList();
        for(int i  = 0 ; i < max;i++)
        {
            arrayList.add(i);
           linkedList.add(i);
        }
        long start = System.currentTimeMillis();

        int temp = 0;
        for(int i : arrayList)
        {
            temp = i;
        }
        System.out.println("arraylist for each costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();

        for(Iterator<Integer> it = arrayList.iterator();it.hasNext();)
        {
            temp = it.next();

        }

        System.out.println("arraylist iterator costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();


        for (int i = 0; i < max; i++)
        {
            temp = arrayList.get(i);
        }
        System.out.println("arraylist size costs time is "+(System.currentTimeMillis()-start)+" ms");



        start = System.currentTimeMillis();


        for(int i : linkedList)
        {
            temp = i;
        }
        System.out.println("linkedList for each costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();

        for(Iterator<Integer> it = linkedList.iterator();it.hasNext();)
        {
            temp = it.next();

        }

        System.out.println("linkedList iterator costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();


        for (int i = 0; i < max; i++)
        {
           temp = linkedList.get(i);
        }
        System.out.println("linkedList size costs time is "+(System.currentTimeMillis()-start)+" ms");




        start = System.nanoTime();


        for (int i = 0; i < linkedList.size(); i++)
        {
            temp = linkedList.get(i);
        }
        System.out.println("linkedList linkedList.size() size costs time is "+(System.nanoTime()-start)+" ms");


    }

    public static void main(String[] args) {
       // test1();
        test2();
    }
}
