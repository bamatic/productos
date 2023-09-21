public class Product {
    private int id;
    private double weight;
    private String description;

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public Product(int id, double weight, String description) {
        this.id = id;
        this.weight = weight;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void show() {
        System.out.println("Product ID: " + this.id);
        System.out.println("Product Weight: " + this.weight + " Kg");
        System.out.println(this.description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Product p = (Product) obj;
            return this.id == p.id;
        }
        return false;
    }
}
