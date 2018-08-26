package CFun.performance_test.remotetest;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class OrderManagerServer {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(1099);//注册RMI端口
            IOrderManager usermanager = new OrderManager();//RMI远程对象
            Naming.rebind( "OrderManager",usermanager);//绑定RMI对象
            System.out.println("OrderManager is ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }



}
