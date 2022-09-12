import java.util.Scanner;

public class ProductionProgram {

    private Scanner scanner;
    private Resources resources;
    private Product[] products;

    private String border = "+---------------------------------------------------+++";
    private final int LONGEST = border.length() - 2;
    private int middle = LONGEST / 2 - 1;

    public ProductionProgram(Scanner s, Resources r, Product[] p) {
        this.scanner = s;
        this.resources = r;
        this.products = p;
    }
    
    public void produceItem() {
        String actor = ": Director";
        int count = (LONGEST - actor.length()) / 2;

        printSpaces(count);
        System.out.println(actor);

        printLifeline("produceItem()");
        
        while(true) {
            System.out.print("| Enter item to produce: ");
            String item = scanner.nextLine();
            Product product = itemListContains(item);

            if (product == null) {
                System.out.println("| Item cannot be found.\n|");
                continue;
            }

            printTarget(": Production Program");
            printLifeline("askQuantity()");

            askQuantity(item);
            int quantity = enterQuantity();

            printTarget(": Director");
            connect("enterQuantity()");
            printTargetWithoutBorder(": Production Program");
            connect("checkResources()");
            printTargetWithoutBorder(": Resources List");

            int required = product.getRequirement() * quantity;
            checkResources(required);
            break;
        }
    }

    public void connect(String target) {
        for (int i = 0; i < 2; i++) {
            printSpaces(middle);
            System.out.println("|");
        }

        int count = calculateSpaces(target);
        printSpaces(count);
        System.out.println(target);
    }

    public void printTarget(String target) {
        System.out.println(border);
        printTargetWithoutBorder(target);
    }

    public void printTargetWithoutBorder(String target) {
        printSpaces(middle);
        System.out.println("|");
        printSpaces(middle - 1);
        System.out.println("\\|/");
        
        int count = calculateSpaces(target);
        printSpaces(count);
        System.out.println(target);
    }

    public int calculateSpaces(String word) {
        return (LONGEST - word.length()) / 2;
    }

    public void printLifeline(String message) {
        connect(message);
        printSpaces(middle);
        System.out.println("|");

        System.out.println(border);
    }

    public void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    public Product itemListContains(String item) {
        for (Product product : products) {
            if (product.getName().equals(item)) {
                return product;
            }
        }
        return null;
    }

    public void askQuantity(String item) {
        System.out.print("| How many of item \"" + item + "\" to produce? ");
    }

    public int enterQuantity() {
        return Integer.valueOf(scanner.nextLine());
    }

    public void sendProductionPlan() {
        Messenger msgr = new Messenger();
        msgr.requestProduction();
    }

    public void checkResources(int required) {
        if (resources.isEnough(required)) {
            printLifeline("Resources OK");
            System.out.println("| There are enough resources.");

            printTarget(": Production Program");
            connect("sendProductionPlan()");

            printTargetWithoutBorder(": Messenger");
            printLifeline("requestProduction()");

            sendProductionPlan();

            System.out.println(border + "\n");
            printSpaces(calculateSpaces("=========="));
            System.out.println("==========\n");
            
            printSpaces(calculateSpaces(": Production Program"));
            System.out.println(": Production Program");

            printLifeline("displaySuccess()");
            displaySuccess();
            printTarget(": Director");
        } else {
            connect("Resources NOT OK");
            printTargetWithoutBorder(": Production Program");

            printLifeline("displayFailure()");
            displayFailure();
            
            printTarget(": Director");
        }
    }

    public void displaySuccess() {
        System.out.println("| Production request is a success!");
    }

    public void displayFailure() {
        System.out.println("| Producton failure. Required resources not met.");
    }
}
