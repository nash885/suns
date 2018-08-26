package CFun.performance_test.fifthChapter;

import java.util.HashMap;

public class GCTimeTest {


    public static void main(String[] args) {

        HashMap map = new HashMap();
        long start =System.currentTimeMillis();
        for(int j = 0; j < 10000; j ++) {
            if (map.size() * 512 / 1024 / 1024 > 400) {
                map.clear();
            }
            byte[] b1;
            for (int i = 0; i < 100; i++) {
                b1 = new byte[512];
                map.put(System.nanoTime(), b1);
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("time costs "+(end -start) );


    }
}
