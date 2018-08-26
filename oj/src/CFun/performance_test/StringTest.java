package CFun.performance_test;

import java.util.Arrays;
import java.util.StringTokenizer;

public class StringTest {





    static void test1()
    {


        String str1 ="abc";
        String str0 =new String("ab") + new String("c");
        String str2 ="abc";
        String str3 = new String("abc");

        System.out.println(str0.intern() == str1);
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str1 == str3.intern());

    }


    static void test2()
    {
        String str = "a;b:c,d";
        System.out.println(Arrays.toString(str.split("[;|,|:]")));


    }

    static  void test3()
    {
        String orgStr = null;
        StringBuffer sb = new StringBuffer();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000;i++)
        {
            sb.append(i);
            sb.append(";");
        }

        orgStr = sb.toString();

        System.out.println(orgStr);

        System.out.println("StringBuilder costs time is "+(System.currentTimeMillis()-start)+" ms");

        orgStr ="";
        start = System.currentTimeMillis();
        for(int i = 0 ; i < 10000; i++)
        {
            orgStr +=i+";";
        }
        System.out.println(orgStr);
        System.out.println("String costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++)
        {
            orgStr.split(";");
        }
        System.out.println("String.split costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer(orgStr,";");
        for(int i = 0; i < 1000; i++)
        {
            while(st.hasMoreElements())
            {
                st.nextToken();

            }
        }
        System.out.println("StringTokenizer costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();
        String tmp = orgStr;
        for(int i = 0; i < 1000; i++)
        {
            while(true)
           {
                String splitStr = null;
          //     System.out.println(tmp);
                int j = tmp.indexOf(';');
            //System.out.println("j = "+j);
                if(j < 0)break;
                splitStr = tmp.substring(0,j);
                tmp=tmp.substring(j+1);

            }
           tmp = orgStr;

        }
        System.out.println("String.indexOf costs time is "+(System.currentTimeMillis()-start)+" ms");

    }



    static  void test4()
    {
        String orgStr = null;
        StringBuffer sb = new StringBuffer();
        int max = 10000000;
        long start = System.currentTimeMillis();

        sb.append("abc");
        for(int i = 0; i < 10000;i++)
        {
            sb.append(i);
            sb.append(";");
        }
        orgStr = sb.toString();
        System.out.println("StringBuilder costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < max; i++)
        {
            if(orgStr.charAt(0) == 'a' && orgStr.charAt(1) == 'b' && orgStr.charAt(2) == 'c')
            {

            }

        }
        System.out.println("CharAt costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < max; i++)
        {
            if(orgStr.startsWith("abc"))
            {

            }

        }
        System.out.println("startwith costs time is "+(System.currentTimeMillis()-start)+" ms");






    }


    /**
     * 字符串累加测试
     */
    static void test5()
    {

        int max = 100000;
        String str =null;
        long start = System.currentTimeMillis();

        for (int i = 0; i < max; i++)
        {
            str = "a"+"b"+"c";
        }
        System.out.println("String costs time is "+(System.currentTimeMillis()-start)+" ms");


         start = System.currentTimeMillis();

        for (int i = 0; i < max; i++)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("a");
            sb.append("b");
            sb.append("c");
        }
        System.out.println("stringBuilder costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();

        String a= "a";
        String b= "b";
        String c= "c";
        for (int i = 0; i < max; i++)
        {
           str =a+b+c;
        }
        System.out.println("String variables  costs time is "+(System.currentTimeMillis()-start)+" ms");







    }


    /**
     * 测试大string对象
     */
    static void test6()
    {

        String str = null;
        long max  =100000;

        long start = System.currentTimeMillis();
        for(int i  = 0 ; i < max; i++)
        {
            str+=i;
        }
        System.out.println("NO,1  costs time is "+(System.currentTimeMillis()-start)+" ms");


        start = System.currentTimeMillis();
        str = "";
        for(int i  = 0 ; i < max; i++)
        {
            str.concat(i+"");
        }
        System.out.println("NO,2  costs time is "+(System.currentTimeMillis()-start)+" ms");


        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for(int i  = 0 ; i < max; i++)
        {
            sb.append(i);
        }
        System.out.println("NO,3  costs time is "+(System.currentTimeMillis()-start)+" ms");



    }


    static void test7()
    {
        String str = null;
        int max  =50000000;

        StringBuilder sb = new StringBuilder();
        StringBuffer sbu = new StringBuffer();

        long start = System.currentTimeMillis();
        for(int i  = 0 ; i < max; i++)
        {
            sb.append(i);
        }
        System.out.println("StringBuilder  costs time is "+(System.currentTimeMillis()-start)+" ms");

        sb = new StringBuilder(max*2);
        start = System.currentTimeMillis();
        for(int i  = 0 ; i < max; i++)
        {
            sb.append(i);
        }
        System.out.println("StringBuilder with max*2 costs time is "+(System.currentTimeMillis()-start)+" ms");

        start = System.currentTimeMillis();
        for(int i  = 0 ; i < max; i++)
        {
            sbu.append(i);
        }
        System.out.println("StringBuffer  costs time is "+(System.currentTimeMillis()-start)+" ms");

        sbu = new StringBuffer(max*2);
        start = System.currentTimeMillis();
        for(int i  = 0 ; i < max; i++)
        {
            sbu.append(i);
        }
        System.out.println("StringBuffer witch capacity  costs time is "+(System.currentTimeMillis()-start)+" ms");




    }


    public static void main(String[] args) {

      //  test1();

        test7();

    }
}
