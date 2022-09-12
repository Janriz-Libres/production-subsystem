import java.util.Scanner;
import java.util.Random;

/**
 * Members:
 * - Janriz Mathew Libres
 * - Mark Olaivar
 * - Timothy Robert A. Mutia
 */

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int initResources = initiateSupply();
    static Product[] products = {
        new Product("Handbag", randomize()),
        new Product("Jacket", randomize()),
        new Product("Mug", randomize()),
        new Product("T-shirt", randomize()),
        new Product("Hat", randomize())
    };

    public static void main(String[] args) {
        Resources resources = new Resources(initResources);

        System.out.println("\nInitial resources: " + initResources + "\n");
        printProducts();

        ProductionProgram program = new ProductionProgram(scanner, resources, products);
        program.produceItem();
    }

    static void printProducts() {
        System.out.println("Items:");
        for (Product item : products) {
            System.out.println("- " + item);
        }
        System.out.println();
    }

    static int randomize() {
        Random rand = new Random();
        return rand.nextInt(initResources / 2) + 10;
    }

    static int initiateSupply() {
        while (true) {
            System.out.print("Enter amount of initial resources (Minimum of 100): ");
            int amount = Integer.valueOf(scanner.nextLine());

            if (amount < 100) {
                System.out.println("Enter a minimum value of 100.\n");
                continue;
            }

            return amount;
        }
    }
}
