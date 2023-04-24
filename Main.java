import java.rmi.*;
import java.util.Scanner;

public class Alice {
  public static void main(String[] args) {
    try {
      // Obtention de la référence du serveur
      StockInterface server = (StockInterface) Naming.lookup("//localhost/StockServer");

      // Enregistrement en tant que client
      ClientInterface client = new Client();
      server.registerForUpdates(client);

      // Consultation du stock
      String[] products = server.getProducts();
      for (String product : products) {
        int quantity = server.getQuantity(product);
        System.out.println(product + " : " + quantity);
      }

      // Ajout d'un produit
      Scanner scanner = new Scanner(System.in);
      System.out.println("Nom du produit à ajouter : ");
      String productName = scanner.nextLine();
      System.out.println("Quantité à ajouter : ");
      int quantity = scanner.nextInt();
      server.addProduct(productName, quantity);

      // Désenregistrement en tant que client
      server.unregisterForUpdates(client);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
