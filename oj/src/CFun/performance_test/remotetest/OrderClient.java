package CFun.performance_test.remotetest;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class OrderClient {
    public static void main(String[] args) {
        try {
            IOrderManager usermanager = (IOrderManager) Naming.lookup("OrderManager");
            int max = 100000;
            long begin = System.currentTimeMillis();
            for(int i = 0; i < max;i++) {
                usermanager.getOrder();
            }
            System.out.println("time is "+(System.currentTimeMillis() - begin));

            begin = System.currentTimeMillis();
            for(int i = 0; i < max;i++) {
                usermanager.getClientName();
                usermanager.getOrderid();
                usermanager.getNumber();
                usermanager.getProductName();
            }
            System.out.println("time is "+(System.currentTimeMillis() - begin));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
