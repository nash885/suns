package CFun.performance_test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {


    public static void main(String[] args) {
        test1();
        test2();
    }

    static void test1()
    {
        long time = System.currentTimeMillis();


        IDBQuery q = new DBQueryProxy();

        System.out.println("time1 = "+(System.currentTimeMillis()-time));
        q.request();
        System.out.println("time2 = "+(System.currentTimeMillis()-time));

    }

    /**
     * 动态代理
     */
    static void test2()
    {
        System.out.println("test2");
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[] {IDBQuery.class},new JdkDnQeuryHandler());
        jdkProxy.request();
    }

}


interface IDBQuery
{

    String request();
    String request2();


}
class DBQuery implements IDBQuery
{


    public DBQuery()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String request() {
        return "request string";
    }

    @Override
    public String request2() {
        return "request string2";
    }
}



class DBQueryProxy implements IDBQuery
{
    private DBQuery real = null;

    @Override
    public String request() {
        if(real == null)
        {
            real = new DBQuery();
        }

        return real.request();
    }

    @Override
    public String request2() {
        if(real == null)
        {
            real = new DBQuery();
        }

        return real.request2();
    }


}


 class JdkDnQeuryHandler implements InvocationHandler
 {

     IDBQuery real = null;

     @Override
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         if( real == null )
         {
             real = new DBQuery();
         }
         return real.request();

     }
 }