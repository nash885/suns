package CFun.performance_test.ThirdChapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OtherTest {


    /**
     * 测试try catch 在循环体内
     */
    static void testTryCatch()
    {

        int a = 0;
        int max = 1000000000;

        long start = System.currentTimeMillis();
        for(int i =0 ;i < max ; i++);
        {
            try {
                    a++;
            }catch (Exception e)
            {

            }
        }

        System.out.println(" catch in for cycle cost time "+(System.currentTimeMillis() - start)+" ms");
        start = System.currentTimeMillis();

        a = 0 ;
        try {
            for (int i = 0; i < max; i++) ;
            {

                a++;

            }
        }catch (Exception e)
        {

        }
        System.out.println(" cost time "+(System.currentTimeMillis() - start)+" ms");
    }


    /**
     *
     */

    static int static_a=0;
    static void testStaticVariable()
    {
        int a = 0;
        long start = System.currentTimeMillis();
        int max = 2000000000;
        for (int i =0 ; i < max;i++)
        {
            a++;

        }
        System.out.println(" local variable cost time: "+(System.currentTimeMillis() - start)+" ms");
        start = System.currentTimeMillis();
        for (int i =0 ; i < max;i++)
        {
            static_a++;

        }
        System.out.println(" static  variable cost time: "+(System.currentTimeMillis() - start)+" ms");

    }


    static int  max= 100000000;
    static void testBitCompute()
    {

        long a = 100;
        long start = System.currentTimeMillis();

        for(int i = 0; i< max ; i++)
        {
            a *=2;
            a /=2;
        }
        System.out.println(" normal costs times "+(System.currentTimeMillis() - start)+" ms");

        start = System.currentTimeMillis();
        for(int i = 0; i< max ; i++)
        {
            a <<=1;
            a >>=1;
        }
        System.out.println(" bit compute costs times "+(System.currentTimeMillis() - start)+" ms");




    }


    static void testCycle()
    {
        int max = 99999;
        int [] array =  new int[max];


        long start = System.currentTimeMillis();

        for(int j = 0 ; j < 1000;j++) {
            for (int i = 0; i < max; i++) {
                array[i] = i;
            }
        }

        System.out.println("time1 = "+(System.currentTimeMillis() - start) +" ms");
        start = System.currentTimeMillis();
        for(int j = 0 ; j < 1000;j++) {
        for(int i = 0 ; i < max ;i+=3)
        {
            array[i] = i;
            array[i+1] = i+1;
            array[i+2] = i+2;
        }
        }

        System.out.println("time1 = "+(System.currentTimeMillis() - start) +" ms");



    }


    static void testArrayCopy()
    {
        int size = 100000;
        int [] array = new int[size];
        int [] arraydst = new int[size];
        for (int i = 0 ; i < array.length;i++)
        {
            array[i] = i;
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000;i++)
        {
            System.arraycopy(array,0,arraydst,0,size);
        }
        System.out.println("array copy costs times "+(System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000;i++)
        {
            for(int j = 0; j < size; j++) {
              arraydst[j] = array[j];
            }
        }
        System.out.println("normal costs times "+(System.currentTimeMillis() - start));


    }


     static  void testClone()
     {
         Student student = new Student();
         student.setId(10);
         student.setName("a");
         student.addList("math");
         int max = 1000000;
        Student s2 = student.newInstance();
        s2.printCource();
        s2.addList(" chinese");
        s2.printCource();
         student.printCource();
         System.out.println( s2.getId() +"  "+s2.getName());
         s2.setName("b");
         System.out.println( s2.getId() +"  "+s2.getName());
         System.out.println( student.getId() +"  "+student.getName());
//         for(int i =0; i < max; i ++)
//         {
//             student.newInstance();
//         }




         long start = System.currentTimeMillis();

         for (int i = 0; i < max; i++)
         {
             Student.test1();
         }

         System.out.println("static method costs "+(System.currentTimeMillis() - start));
         start = System.currentTimeMillis();

         for (int i = 0; i < max; i++)
         {
            student.test2();
         }

         System.out.println("local method costs "+(System.currentTimeMillis() - start));


     }
    public static void main(String[] args) {
      //  testTryCatch();
       // testStaticVariable();
       // testBitCompute();
      //  testCycle();
    //   testArrayCopy();
        testClone();
    }
}


class Student implements Cloneable
{

    int id ;
    String name;
    List courceList = new ArrayList<>();

    public List getCourceList() {
        return courceList;
    }

    public void setCourceList(List courceList) {
        this.courceList = courceList;
    }

    public void addList(String cource )
    {
        courceList.add(cource);
    }
    public void printCource()
    {
        System.out.println(courceList.toString());
       // Arrays.toString()
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  Student()
    {
        System.out.println("student constructor");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Student newInstance()
    {
        try {
            return (Student) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

    }

    static void test1()
    {

    }
      void test2()
    {

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {



     Student student = (Student) super.clone();

        List list = new ArrayList();
       list.addAll(student.getCourceList());
        student.setCourceList(list);
     return student;

    }
}