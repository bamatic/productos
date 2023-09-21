import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Storage storage = new Storage("./products.json");
            System.out.println("Hay " + storage.countProducts() + " productos");
            Menu.show();
            Menu.readOption(storage);
        } catch (IOException e) {
            System.out.println("Imposible leer de el fichero de datos");
        }
    }
}