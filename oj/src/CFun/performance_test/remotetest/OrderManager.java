package CFun.performance_test.remotetest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OrderManager extends UnicastRemoteObject implements IOrderManager {
    protected OrderManager() throws RemoteException {
    }

    @Override
    public Order getOrder() throws RemoteException {
        Order order = new Order();
        order.setClientName("getClientName");
        order.setOrderid(1);
        order.setNumber(123);
        order.setProductName("ProductName");

        return order;
    }

    @Override
    public int getOrderid() throws RemoteException {
        return 1;
    }

    @Override
    public String getClientName() throws RemoteException {
        return "getClientName";
    }

    @Override
    public int getNumber() throws RemoteException {
        return 123;
    }

    @Override
    public String getProductName() throws RemoteException {
        return "ProductName";
    }
}
