package CFun.performance_test;

import java.io.*;

public class SingletonTest {

    public static void main(String[] args) {
       StaticSingleton.createString();

      long time = System.currentTimeMillis();
        System.out.println("time is "+time);
        int max = 1000000;
        for(int i = 0; i < max;i++)
        {
            Singleton.getInstance();
        }
        System.out.println("spend :"+(System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        for(int i = 0; i < max;i++)
        {
            LazySingleton.getInstance();
        }
        System.out.println("spend :"+(System.currentTimeMillis() - time));


        time = System.currentTimeMillis();
        for(int i = 0; i < max;i++)
        {
            StaticSingleton.getInstance();
        }
        System.out.println("spend :"+(System.currentTimeMillis() - time));



        testSerialSingleton();




    }



    public static  void testSerialSingleton()
    {
        SerSingleton s1 = null;
        SerSingleton s = SerSingleton.getInstance();
        try {
            FileOutputStream fos = new FileOutputStream("SerSingleton.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(s);
            oos.flush();
            oos.close();

            //从文件读取原来的单例类

            FileInputStream fis = new FileInputStream("SerSingleton.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            s1 = (SerSingleton)ois.readObject();

            if(s.equals(s1))
            {
                System.out.println("s equals s1");
            }
            else
            {
                System.out.println("s not equals s1");
            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }





}

class Singleton
{
    private static Singleton instance = new Singleton();
    private Singleton()
    {
        System.out.println("Singleton is create");
    }

    public  static Singleton getInstance()
    {
        return instance;
    }

    public static void createString()
    {
        System.out.println("createString in Singleton");
    }



}


class LazySingleton
{
    private LazySingleton()
    {
        System.out.println("LazySingleton is create");
    }


    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance()
    {
        if(instance == null)
        {
            instance = new LazySingleton();
        }
        return instance;

    }

}


class StaticSingleton
{
    private StaticSingleton()
    {
        System.out.println("StaticSingleton is create");
    }


    private static class SingletonHolder
    {
        private static StaticSingleton instance = new StaticSingleton();
    }


    public static StaticSingleton getInstance()
    {
        return SingletonHolder.instance;
    }

    public static void createString()
    {
        System.out.println("createString in Singleton");
    }




}

class SerSingleton implements Serializable
{
    String name;

    private SerSingleton()
    {
        System.out.println("Singleton is create");

        name = "SerSingleton";
    }

    private static SerSingleton instance = new SerSingleton();


    public static SerSingleton getInstance()
    {
        return instance;
    }

    public static void createString()
    {
        System.out.println("createString in Singleton");
    }

    private Object readResolve()
    {
        //阻止新生成的新实例，总是返回当前对象

        return instance;

    }






}