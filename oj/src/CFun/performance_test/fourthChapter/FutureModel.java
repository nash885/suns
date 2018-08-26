package CFun.performance_test.fourthChapter;

import java.util.concurrent.*;

public class FutureModel {


    public static void main(String[] args) {
        //test1();
    test2();
}

    static void test1()
    {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Task task = new Task();

        Future<Integer> future = executorService.submit(task);
        executorService.shutdown();

        try {
            System.out.println("future.get() = "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


    static void test2()
    {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
         executor.submit(futureTask);
        executor.shutdown();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ;

    }
}


class Task implements Callable<Integer>
{

    @Override
    public Integer call() throws Exception {

        int sum =0;
        Thread.sleep(10000);

        return sum;
    }
}
