import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class PaymentServiceImpl extends UnicastRemoteObject implements IPaymentService {

    protected PaymentServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String processPayment(Long donorId, double amount) throws RemoteException {
        if (donorId == null || amount <= 0) {
            throw new RemoteException("Invalid donation details.");
        }
        String receipt = "RECEIPT-" + donorId + "-" + amount;
        return receipt;
    }
}
