package CFun.performance_test.ThirdChapter;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class NIOTest {





    static void test1()
    {
        try {
            FileInputStream fin = new FileInputStream(new File("G:\\work\\oj\\nio.txt"));
            FileChannel fc = fin.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fc.read(byteBuffer);
            System.out.println(byteBuffer);
            System.out.println(fc);
            fc.close();

            byteBuffer.flip();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * position limit capacity 测试
     */

    static void test2()
    {
        ByteBuffer b = ByteBuffer.allocate(15);
        System.out.println("Limit = "+b.limit() +" capacity="+b.capacity()+" position="+b.position());

        for(int i = 0;i < 10; i++)
        {
            b.put((byte)i);
        }
        System.out.println("Limit = "+b.limit() +" capacity="+b.capacity()+" position="+b.position());
        b.flip();
        for(int i = 0; i < 5; i++)
        {
            System.out.println((byte)b.get());
        }

        System.out.println("Limit = "+b.limit() +" capacity="+b.capacity()+" position="+b.position());

        b.flip();
        System.out.println("Limit = "+b.limit() +" capacity="+b.capacity()+" position="+b.position());

        for(int i = 0; i < 10; i++)
        {
            System.out.println((byte)b.get());
        }
        System.out.println("Limit = "+b.limit() +" capacity="+b.capacity()+" position="+b.position());





    }


    /**
     * reset mark的使用
     */
    static void test3()
    {

        ByteBuffer b = ByteBuffer.allocate(15);
        for(int i = 0; i < 10; i++)
        {
            b.put((byte)i);
        }
        b.flip();

        for (int i = 0; i < b.limit();i++)
        {
            System.out.println(b.get());
            if(i == 4)
            {
                b.mark();
                System.out.println("(mark at "+i+")");
            }

        }

        b.reset();
        System.out.println("\nreset to mark");
        while(b.hasRemaining())
        {
            System.out.println(b.get());
        }




    }


    /**
     * 共享缓存区
     */
    static void test4()
    {
        ByteBuffer b = ByteBuffer.allocate(15);
        for(int i = 0; i < 10; i++)
        {
            b.put((byte)i);
        }

        ByteBuffer c = b.duplicate();

        System.out.println("After b.duplicate()");

        System.out.println(b);
        System.out.println(c);
        c.flip();

        System.out.println("After    c.flip(); ");

        System.out.println(b);
        System.out.println(c);
        c.put((byte)100);

        System.out.println("After c.flip(); ");

        System.out.println("b.get(0)"+b.get(0));
        System.out.println("c.get(0)"+c.get(0));


    }

    static void test5()
    {

        ByteBuffer b = ByteBuffer.allocate(15);
        for (int i = 0; i < 10 ;i++) {
            b.put((byte) i);

        }
        b.position(2);
        b.limit(6);

        ByteBuffer subBuffer = b.slice();
        System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());
        System.out.println("subBuffer Limit = "+subBuffer.limit() +"  subBuffer capacity="+subBuffer.capacity()+" subBuffer position="+subBuffer.position());
        for(int i = 0; i < subBuffer.capacity();i++)
        {
            byte bb = subBuffer.get(i);
            bb*=10;
            subBuffer.put(i,bb);

        }

        System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());

        b.position(0);
        b.limit(b.capacity());

        while(b.hasRemaining())
        {
            System.out.print(b.get()+" ");

        }

    }

    /**
     * 只读缓存区
     */
    static  void test6()
    {
        ByteBuffer b = ByteBuffer.allocate(15);

        for(int i = 0; i < 10; i++)
        {
            b.put((byte)i);
        }

        ByteBuffer readOnly = b.asReadOnlyBuffer();//创建只读缓存区
        readOnly .flip();

        while (readOnly.hasRemaining())
        {
            System.out.print(readOnly.get()+" ");
        }
        System.out.println();
        System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());
        b.put(2,(byte)20);
      //  readOnly.put(2,(byte)20);
        System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());
        readOnly.flip();
        System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());
        while (readOnly.hasRemaining())
        {
            System.out.print(readOnly.get()+" ");
        }




    }

    /**
     * 缓存映射到文件
     */
    static  void test7()
    {
        FileInputStream fin = null;
        try {
          //  fin = new FileInputStream(new File("G:\\work\\oj\\nio.txt"));
            RandomAccessFile raf = new RandomAccessFile("G:\\work\\oj\\nio.txt","rw");
            FileChannel fc = raf.getChannel();

            System.out.println("raf.length() = "+raf.length());
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,0,raf.length());

            while (mbb.hasRemaining())
            {
                System.out.println((char)mbb.get());
            }

            mbb.put(0,(byte)98);
            raf.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    static void testIOStreamAndBuffer()
    {
        try {




            int numOfInts = 10000000;
            long start = System.currentTimeMillis();

            //DataOutputStream
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("G:\\work\\oj\\DataOutputStream.txt"))));
            for (int i = 0; i< numOfInts;i++)
            {
                dos.writeInt(i);
            }
            if(dos != null)
            {
                dos.close();
            }
            System.out.println("stream write costs "+(System.currentTimeMillis() - start));
            start = System.currentTimeMillis();

            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("G:\\work\\oj\\DataOutputStream.txt"))));


            for(int i = 0; i < numOfInts;i++)
            {
                dis.readInt();
            }
            if(dis != null)
            {
                dis.close();
            }
            System.out.println("stream read costs "+(System.currentTimeMillis() - start));


            //ByteBuffer

            ByteBuffer byteBuffer = ByteBuffer.allocate(numOfInts*4);
            start = System.currentTimeMillis();
            FileOutputStream fout = new FileOutputStream(new File("G:\\work\\oj\\ByteBuffer.txt"));
            FileChannel fc = fout.getChannel();

         //   System.out.println("Limit = "+byteBuffer.limit() +"  capacity="+byteBuffer.capacity()+"  position="+byteBuffer.position());

            for (int i = 0; i < numOfInts;i++)
            {
                byteBuffer.put(int2Byte(i));
            }
         //   System.out.println("Limit = "+byteBuffer.limit() +"  capacity="+byteBuffer.capacity()+"  position="+byteBuffer.position());
            byteBuffer.flip();

          //  System.out.println("Limit = "+byteBuffer.limit() +"  capacity="+byteBuffer.capacity()+"  position="+byteBuffer.position());
            fc.write(byteBuffer);


            fc.close();
            fout.close();
            System.out.println("ByteBuffer write costs "+(System.currentTimeMillis() - start));

            start = System.currentTimeMillis();
            FileInputStream fin = new FileInputStream(new File("G:\\work\\oj\\ByteBuffer.txt"));
            fc = fin.getChannel();
         //   System.out.println("Limit = "+byteBuffer.limit() +"  capacity="+byteBuffer.capacity()+"  position="+byteBuffer.position());

             fc.read(byteBuffer);
         //   System.out.println("Limit = "+byteBuffer.limit() +"  capacity="+byteBuffer.capacity()+"  position="+byteBuffer.position());
             byteBuffer.flip();
        //    System.out.println("Limit = "+byteBuffer.limit() +"  capacity="+byteBuffer.capacity()+"  position="+byteBuffer.position());
             while(byteBuffer.hasRemaining())
             {

                 byte2Int(byteBuffer.get(),byteBuffer.get(),byteBuffer.get(),byteBuffer.get());
             }

        //    System.out.println("Limit = "+byteBuffer.limit() +"  capacity="+byteBuffer.capacity()+"  position="+byteBuffer.position());
            byteBuffer.flip();
        //    System.out.println("Limit = "+byteBuffer.limit() +"  capacity="+byteBuffer.capacity()+"  position="+byteBuffer.position());
             fc.close();
             fin.close();
           System.out.println("ByteBuffer read costs "+(System.currentTimeMillis() - start));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    static  byte[] int2Byte(int res)
    {
        byte[] targets = new byte[4];
        targets[3] = (byte)(res & 0Xff);
        targets[2] = (byte)((res>> 8) & 0Xff);
        targets[1] = (byte)((res >> 16 )& 0Xff);
        targets[0] = (byte)((res >>24) & 0Xff);

        return targets;

    }



    static void testDirect()
    {
        int max = 500;
        int count1 = 100000;
        int count2 = 99;

        long start =0;
      // start = System.currentTimeMillis();
        ByteBuffer  b = ByteBuffer.allocateDirect(max);
        start = System.currentTimeMillis();
        for (int i =0 ;i < count1;i++)
        {
            for(int j = 0; j < count2;j++)
            {
                b.putInt(j);
            }
            //System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());
            b.flip();
            //System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());
            for (int j = 0; j < count2 ; j++)
            {
                b.getInt();;
            }

            b.clear();

        }
        System.out.println("direct costs "+(System.currentTimeMillis() - start)+" ms");




       // start = System.currentTimeMillis();
         b = ByteBuffer.allocate(max);
        start = System.currentTimeMillis();
        for (int i =0 ;i < count1;i++)
        {
            for(int j = 0; j < count2;j++)
            {
                b.putInt(j);
            }
            //System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());
            b.flip();
            //System.out.println("Limit = "+b.limit() +"  capacity="+b.capacity()+"  position="+b.position());
            for (int j = 0; j < count2 ; j++)
            {
                b.getInt();;
            }

            b.clear();

        }
        System.out.println("normal costs "+(System.currentTimeMillis() - start)+" ms");








    }


    static void testMemory()
    {
        int max = 1024;
        int count3 = 3000;
        ByteBuffer  b =null;
        long  start = System.currentTimeMillis();
//        for(int i = 0; i < count3;i++)
//        {
//            b = ByteBuffer.allocate(max);
//        }
//
//        System.out.println("normal allocate memory costs "+(System.currentTimeMillis() - start)+" ms");

        start = System.currentTimeMillis();
        monDirectBuffer();
        ArrayList arrayList = new ArrayList();
        for(int i = 0; i < count3;i++)
        {
            b = ByteBuffer.allocateDirect(max);
            arrayList.add(b);
        }
        monDirectBuffer();
        System.out.println("normal allocate memory cost"+(System.currentTimeMillis() - start)+" ms");
    }

    static int byte2Int(byte b1,byte b2,byte b3 ,byte b4)
    {
        return ((b1 & 0xff ) <<24 |(b2 & 0xff) << 16 |(b3 & 0xff) <<8 |( b4 & 0xff));
    }


    static void monDirectBuffer()
    {
        try {
            Class c = Class.forName("java.nio.Bits");

            try {
                Field maxMemory = c.getDeclaredField("maxMemory");
                maxMemory.setAccessible(true);
                Field reservedMemory = c.getDeclaredField("reservedMemory");
                reservedMemory.setAccessible(true);

                synchronized (c)
                {
                    long maxMemoryValue = (Long)maxMemory.get(null);
                    long reservedMemoryValue =((AtomicLong) reservedMemory.get(null)).longValue();
                   //         System.out.println((Long)maxMemory.get(null));
                    //System.out.println(reservedMemory.get(null));
                    //long maxMemoryValue = ((Long) maxMemory.get(null)).longValue();
//                    long reservedMemoryValue = (Long) reservedMemory.get(null)).longValue();
//
                    System.out.println("maxMemoryValue: "+maxMemoryValue);
                    System.out.println("reservedMemoryValue:"+reservedMemoryValue);

                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //test1();
       //test2();
//        test3();
     //   test4();
      //  test5();
     //   test6();
     //   test7();
       // testIOStreamAndBuffer();
     //   testDirect();
        testMemory();

    }
}
