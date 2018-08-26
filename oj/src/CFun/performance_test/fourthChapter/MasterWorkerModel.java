package CFun.performance_test.fourthChapter;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.internal.util.xml.impl.Input;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MasterWorkerModel {


    public static void main(String[] args) {
        Master m = new Master(new PlusWorker(),5);
        Integer re = 0;
        for(int i = 0; i < 4;i++)
        {
            m.submit(i);
        }
        m.execute();
        Map<String,Object> resultMap = m.getResultMap();
        while (resultMap.size() > 0 || !m.isComplete())
        {
            Set<String> keys = resultMap.keySet();
            String key = null;

            for(String k :keys)
            {
                key = k;
                break;
            }
            Integer i = null;
            if(key != null)
            {
                i = (Integer) resultMap.get(key);
            }
            if(i != null)
            {
                re += i;
            }
            if(key != null)
            {
                resultMap.remove(key);
            }


        }
        System.out.println("result is "+re);


    }



}



class Worker implements Runnable
{

    protected  Queue<Object> workQueue;
    protected  Map<String,Object> resultMap;

    public Queue<Object> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }


    public Object handle(Object input)
    {
        return input;
    }

    @Override
    public void run() {

        while(true)
        {
            Object input = workQueue.poll();
            if(input == null)
            {
                break;
            }

            Object re = handle(input);

            resultMap.put(input.hashCode()+"",re);


        }


    }
}

class Master
{
    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedDeque<Object>();

    //worker 进程队列
    protected Map<String,Thread> threadMap = new HashMap<String,Thread>();

    //子任务结果集
    protected  Map<String,Object> resultMap = new ConcurrentHashMap<String,Object>();



    public boolean isComplete()
    {
        for (Map.Entry<String,Thread> entry : threadMap.entrySet())
        {
            if(entry.getValue().getState() != Thread.State.TERMINATED)
            {
                return false;
            }
        }

        return true;

    }


    public Master(Worker worker ,int countWorker)
    {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for(int i = 0; i < countWorker ;i++)
        {
            threadMap.put(i+"",new Thread(worker,Integer.toString(i)));
        }
    }

    public  void submit(Object job)
    {
        workQueue.add(job);
    }

    //返回结果集
    public  Map<String ,Object> getResultMap()
    {
        return resultMap;
    }


    public void execute()
    {
        for (Map.Entry<String,Thread> entry:threadMap.entrySet())
        {
            entry.getValue().start();
        }

    }




}


class PlusWorker extends Worker
{
    @Override
    public Object handle(Object input) {
        Integer i = (Integer)input;
        return i*i*i;
    }
}


