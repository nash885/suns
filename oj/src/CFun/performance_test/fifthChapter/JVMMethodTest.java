package CFun.performance_test.fifthChapter;

public class JVMMethodTest {


    public static void permGenGC()
    {
        for (int i =0  ;i < Integer.MAX_VALUE;i++)
        {
               String t = String.valueOf(i).intern();//加入常量池

          //  System.out.println("t ="+t);
        }
    }
    public static void main(String[] args) {
        //permGenGC();
        System.out.println(Runtime.getRuntime().maxMemory());
    }
}
