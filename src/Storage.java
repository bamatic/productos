import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;


import com.google.gson.internal.LinkedTreeMap;

public class Storage {
    private final ArrayList<Product> products = new ArrayList<>();
    private final String filename;

    public Storage(String filename) throws IOException {
        this.filename = filename;
        this.loadProductsFromFile();
    }


    public boolean addProduct(Product p) {
        boolean found = false;
        for (Product product: this.products) {
            if (product.equals(p)) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.products.add(p);
        }
        return !found;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int countProducts() {
        return this.products.size();
    }

    public void showProducts() {
        System.out.println("Showing " + this.countProducts() + " Products");
        this.products.forEach(product -> {
            product.show();
            System.out.println("**********************************");
            System.out.println();
        });
    }
    public boolean saveProducts() throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this.products);
        if (jsonString == null) return false;
        FileWriter writer = new FileWriter(this.filename);
        writer.write(jsonString);
        writer.close();
        return true;
    }
    public boolean deleteProductById(int id) {
        Product tmp = new Product(id, 0, "");
        return this.products.remove(tmp);
    }
    public Product deleteProductByIndex(int index) {
        return this.products.remove(index);
    }
    public Product popProduct() {
        return this.products.remove(this.products.size() - 1);
    }
    public boolean loadProductsFromFile() throws IOException {
        Gson gson = new Gson();

        FileReader reader = new FileReader(this.filename);
        StringBuilder jsonString = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            jsonString.append((char) c);
        }
        reader.close();

        ArrayList<LinkedTreeMap<Object, Object>> objects = gson.fromJson(jsonString.toString(), ArrayList.class);

        this.products.clear();

        for (LinkedTreeMap<Object, Object> object : objects) {

            double dblId, weight;
            String description;
            try {
               dblId = Double.parseDouble(object.get("id").toString());
               try {
                   weight = Double.parseDouble(object.get("weight").toString());
                   description = object.get("description").toString();
                   if (description == null)
                       return false;
                   this.products.add(new Product((int) dblId, weight, description));
               }
               catch (NumberFormatException e) {
                   return false;
               }
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        return true;

    }

    public String getFilename() {
        return filename;
    }

    public void clear() {
        this.products.clear();
    }
}
