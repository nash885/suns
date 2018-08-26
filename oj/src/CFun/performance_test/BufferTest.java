package CFun.performance_test;

import java.io.*;

/**
 *缓存测试
 */
public class BufferTest {


    static void test1()
    {
        try {
            Writer writer = new FileWriter(new File("file.txt"));
            int CIRCLE = 100000;
            long begin = System.currentTimeMillis();
            for(int i = 0 ; i < CIRCLE ;i++)
            {
                writer.write(i);

            }
            writer.close();
            System.out.println("testFileWriter spend : "+(System.currentTimeMillis() -begin));

            begin = System.currentTimeMillis();
            writer = new BufferedWriter(new FileWriter(new File("file2.txt")));
            for(int i = 0 ; i < CIRCLE ;i++)
            {
                writer.write(i);

            }
            writer.close();
            System.out.println("testFileWriterBuffer spend : "+(System.currentTimeMillis() -begin));



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        test1();
    }
}