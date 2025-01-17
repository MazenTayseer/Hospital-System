import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
		    System.setProperty("java.rmi.server.codebase", "file:../src");


            IPaymentService paymentService = new PaymentServiceImpl();  

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PaymentService", paymentService);

            System.out.println("Donation RMI Server is running...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
