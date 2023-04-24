import java.rmi.*;

public interface StockInterface extends Remote {
  public int getQuantity(String productName) throws RemoteException;
  public String[] getProducts() throws RemoteException;
  public void addProduct(String productName, int quantity) throws RemoteException;
  public void removeProduct(String productName, int quantity) throws RemoteException;
  public void registerForUpdates(ClientInterface client) throws RemoteException;
  public void unregisterForUpdates(ClientInterface client) throws RemoteException;
}
