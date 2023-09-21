import java.util.Scanner;

public class InputReader {
    private static final Scanner scanner = new Scanner(System.in);
    public static String getInput() {
        StringBuilder sb = new StringBuilder();
        String line = InputReader.scanner.nextLine();
        return line.isEmpty() ? null : line;
    }
    public static Product readProduct() {
        System.out.println("ID ?");
        String strID = InputReader.getInput();
        if (strID == null) {
            return null;
        }
        try {
            int id = Integer.parseInt(strID);
            System.out.println("Peso ?");
            String strWeight = InputReader.getInput();
            if (strWeight == null) {
                return null;
            }
            double weight;
            try {
                weight = Double.parseDouble(strWeight);
                System.out.println("Description ?");
                String description = InputReader.getInput();
                description = description == null ? "":description;
                return new Product(id, weight, description);
            }
            catch (NumberFormatException e) {
                return null;
            }
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
}
