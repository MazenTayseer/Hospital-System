import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPaymentService extends Remote {
    String processPayment(Long donorId, double amount) throws RemoteException;
}
