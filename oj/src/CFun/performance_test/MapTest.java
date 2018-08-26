package CFun.performance_test;

import java.util.*;

public class MapTest {



    static  void test1()
    {
        int max = 100;

        HashMap<Object,Object> hm1 = new HashMap();
        HashMap<Object,Object> hm2 = new HashMap();
        BadHash[] arrBadHash = new BadHash[max];
        GoodHash[] arrGoodHash = new GoodHash[max];
        for(int i = 0; i < max; i++)
        {
            System.out.println("i = " +i);
            arrBadHash[i] =  new BadHash(i);
            arrGoodHash[i] = new GoodHash(i);
            hm1.put(arrBadHash[i] ,i);
            hm2.put(arrGoodHash[i],i);
        }

        System.out.println("here");
        System.out.println("hm1.size() = "+hm1.size());
        long start = System.currentTimeMillis();

        for(int i = 0 ; i < max;i++)
        {
            hm2.get(arrGoodHash[i]);
        }

        System.out.println("bad hash costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();
        for(int i = 0 ; i < max;i++)
        {
            hm1.get(arrBadHash[i]);
        }

        System.out.println("bad hash costs time is "+(System.currentTimeMillis()-start)+" ms");

    }


    static void test2()
    {
        int max = 100;

        HashMap<Object,Object> hm1 = new HashMap();
        LinkedHashMap<Object,Object> linkedHashMap = new LinkedHashMap<>();
     //   LinkedHashMap<Object,Object> linkedHashMap = new LinkedHashMap<Object,Object>(max,0.5f,true);

        for(int i = 0; i < max;i++)
        {
           hm1.put(i+"",i);
            linkedHashMap.put(i+"",i);

        }

        System.out.println("HashMap println");
        for(HashMap.Entry entry :hm1.entrySet())
        {
            System.out.println(entry.getKey());
        }


        System.out.println("linkedHashMap println");
       linkedHashMap.get("6");

        for(Iterator iterator = linkedHashMap.keySet().iterator();iterator.hasNext();)
        {
            String key = (String)iterator.next();
            System.out.println(key);
           System.out.println(linkedHashMap.get(key));
        }
//        for(HashMap.Entry entry :linkedHashMap.entrySet())
//        {
//            System.out.println(entry.getKey());
//        }


    }

    static void testTreeMap()
    {
        Student s1 = new Student("Billy",70);
        Student s2 = new Student("David",85);
        Student s3 = new Student("Kite",92);
        Student s4 = new Student("Cissy",68);
        TreeMap<Student,StudentDetailInfo> map = new TreeMap<Student,StudentDetailInfo>();
        map.put(s1,new StudentDetailInfo(s1));
        map.put(s2,new StudentDetailInfo(s2));
        map.put(s3,new StudentDetailInfo(s3));
        map.put(s4,new StudentDetailInfo(s4));

        Map map1 = map.subMap(s4,s2);
        for (Iterator iterator = map1.keySet().iterator();iterator.hasNext();)
        {
            Student key = (Student) iterator.next();
            System.out.println(key +" ->"+map1.get(key));

        }




    }


    public static void main(String[] args) {
        //test1();
       // test2();
        testTreeMap();

    }
}


class BadHash
{
    double d;

    public BadHash(double d) {
        this.d = d;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

class GoodHash
{
    double d;

    public GoodHash(double d) {
        this.d = d;
    }
}



class Student implements Comparable<Student>
{
    String name;
    int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        if(o.score < this.score)
        {
            return 1;
        }else if(o.score > this.score)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("name :");
        sb.append(name);
        sb.append("   score:");
        sb.append(score);
         return sb.toString();
    }
}



class StudentDetailInfo
{
    Student s;

    public StudentDetailInfo(Student s) {
        this.s = s;
    }


    @Override
    public String toString() {
        return s.name+"'s detail infomation";
    }
}

