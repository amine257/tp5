import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class StockServer extends UnicastRemoteObject implements StockInterface {
  private Map<String, Integer> stock = new HashMap<String, Integer>();
  private List<ClientInterface> clients = new ArrayList<ClientInterface>();

  public StockServer() throws RemoteException {
    super();
  }

  public int getQuantity(String productName) throws RemoteException {
    Integer quantity = stock.get(productName);
    return quantity != null ? quantity.intValue() : 0;
  }

  public String[] getProducts() throws RemoteException {
    Set<String> productNames = stock.keySet();
    return productNames.toArray(new String[productNames.size()]);
  }

  public void addProduct(String productName, int quantity) throws RemoteException {
    Integer oldQuantity = stock.get(productName);
    int newQuantity = oldQuantity != null ? oldQuantity.intValue() + quantity : quantity;
    stock.put(productName, Integer.valueOf(newQuantity));
    String message = productName + " quantity increased by " + quantity;
    notifyClients(message);
  }

  public void removeProduct(String productName, int quantity) throws RemoteException {
    Integer oldQuantity = stock.get(productName);
    int newQuantity = oldQuantity != null ? oldQuantity.intValue() - quantity : -quantity;
    stock.put(productName, Integer.valueOf(newQuantity));
    String message = productName + " quantity decreased by " + quantity;
    notifyClients(message);
  }

  public void registerForUpdates(ClientInterface client) throws RemoteException {
    if (!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterForUpdates(ClientInterface client) throws RemoteException {
    clients.remove(client);
  }

  private void notifyClients(String message) throws RemoteException {
    for (ClientInterface client : clients) {
      client.update(message);
    }
  }
}
