import java.rmi.*;
import java.rmi.server.*;

public class Client extends UnicastRemoteObject implements ClientInterface {
  private StockInterface stock;

  public Client(StockInterface stock) throws RemoteException {
    super();
    this.stock = stock;
    stock.registerForUpdates(this);
  }

  public void update(String message) throws RemoteException {
    System.out.println(message);
  }

  public void close() throws RemoteException {
    stock.unregisterForUpdates(this);
  }
}
