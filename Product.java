public class Product {
    
    private String name;
    private int requirement;

    public Product(String name, int r) {
        this.name = name;
        this.requirement = r;
    }
    
    public String getName() {
        return name;
    }

    public int getRequirement() {
        return requirement;
    }

    @Override
    public String toString() {
        return this.name + " (Required resources: " + this.requirement + ")";
    }
}
