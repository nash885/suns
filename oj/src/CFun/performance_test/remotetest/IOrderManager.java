package CFun.performance_test.remotetest;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOrderManager extends Remote{

    public Order getOrder() throws RemoteException;

    int getOrderid()  throws RemoteException;
    String getClientName() throws RemoteException;
    int getNumber() throws RemoteException;
    String getProductName()  throws RemoteException;


}
