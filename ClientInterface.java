import java.rmi.*;

public interface ClientInterface extends Remote {
  public void update(String message) throws RemoteException;
}
