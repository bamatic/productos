import java.io.IOException;

public class Menu {

    public static void show() {
        System.out.println("1. Crear productos");
        System.out.println("2. Mostrar productos");
        System.out.println("3. Guardar productos");
        System.out.println("4. Cargar productos desde disco");
        System.out.println("5. Borrar los productos de la memoria");
        System.out.println("6. Borrar un producto de la memoria");
        System.out.println("7. Salir.");
    }
    public static void readOption(Storage storage) {
        String option = InputReader.getInput();
        if (option == null) {
            System.out.println("Opcion Incorrecta");
        }
        else {
            switch (option) {
                case "1":
                    Menu.introducirProds(storage);
                    break;
                case "2":
                    Menu.visualizaProds(storage);
                    break;
                case "3":
                    Menu.guardaProds(storage);
                    break;
                case "4":
                    Menu.leeProds(storage);
                    break;
                case "5":
                    Menu.clear(storage);
                    break;
                case "6":
                    Menu.remove(storage);
                    break;
                case "7":
                    Menu.exit();
                default:
                    System.out.println("Opcion Incorrecta");
            }
        }
        Menu.show();
        Menu.readOption(storage);

    }

    private static void remove(Storage storage) {
        boolean stop = false;
        while (!stop) {
            if (storage.countProducts() == 0) {
                System.out.println("No hay productos que borrar");
                return;
            }
            System.out.println("Hay " + storage.countProducts() + " productos");
            System.out.println("Introduzca el id del producto a eliminar o 0 para volver al menu principal:");
            String strId = InputReader.getInput();
            if (strId == null) {
                continue;
            }
            if (strId.equals("0")) {
                stop = true;
                continue;
            }
            int id;
            try {
                id = Integer.parseInt(strId);
                if (storage.deleteProductById(id)) {
                    System.out.println("el producto con id " + id + " ha sido eliminado");
                }
                else {
                    System.out.println("Imposible de eliminar el producto con id " + id);
                }
                System.out.println("Quedan " + storage.countProducts() + " productos");
            }
            catch (NumberFormatException e) {
                System.out.println("el id debe ser un numero");
                continue;
            }
        }
    }

    private static void clear(Storage storage) {
        storage.clear();
    }

    public static void introducirProds(Storage storage) {
        boolean stop = false;
        while (!stop) {
            System.out.println("Hay " + storage.countProducts() + " productos");
            System.out.println("Introduzca los datos del producto:");
            Product product = InputReader.readProduct();
            if (product == null) {
                System.out.println("Error");
            }
            else {
                product.show();
                if (!storage.addProduct(product)) {
                    System.out.println("Imposible de agregar mas productos con id  " + product.getId());
                    System.out.println("hay otro producto con el mismo id " + product.getId());
                }
                else {
                    System.out.println("Agregado producto con id " + product.getId());
                }
            }
            System.out.println("Para introducir otro producto pulse 1");
            String strStop = InputReader.getInput();
            if ( strStop == null || !strStop.equals("1"))
                stop = true;
        }
        System.out.println("Hay " + storage.countProducts() + " productos");

    }
    public static void visualizaProds(Storage storage) {
        storage.showProducts();
    }
    public static void  guardaProds(Storage storage) {
        try {
            if (storage.saveProducts()) {
                System.out.println(storage.countProducts() + " productos guardados");
            }
            else {
                System.out.println("Imposible de guardar los productos");
            }
        } catch (IOException e) {
            System.out.println("Error con el fichero de datos");
            System.out.println("los archivos no se han guardado");
        }
    }
    public static void leeProds(Storage storage) {
        try {
            if (!storage.loadProductsFromFile()) {
                System.out.println("El formato del fichero de datos es incorrecto");
            }
        }
        catch (IOException e) {
            System.out.println("Imposible de el fichero de datos");
            System.out.println("Compruebe que el fichero " + storage.getFilename() + " esta en la misma carpeta que este software");
        }
    }
    public static void exit() {
        System.out.println("Bye :)");
        System.exit(0);
    }
}
