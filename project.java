// online food delivey system
import java.util.Scanner;

class FoodItem {
    private String itemName;
    private double price;

    FoodItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    String getItemName() {
        return itemName;
    }

    double getPrice() {
        return price;
    }
}


class SpecialFoodItem extends FoodItem {

    SpecialFoodItem(String itemName, double price) {
        super(itemName, price);
    }

    String getItemName() {
        return "Special " + super.getItemName();
    }
}

class Restaurant {
    private String name;
    private FoodItem[] menu;

    Restaurant(String name, FoodItem[] menu) {
        this.name = name;
        this.menu = menu;
    }

    void displayMenu() {
        System.out.println("\nMenu of " + name);
        System.out.println("----------------------");

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". "
                    + menu[i].getItemName()
                    + " - ₹"
                    + (int) menu[i].getPrice());
        }
    }

    void displayMenu(String category) {
        System.out.println("\nCategory: " + category);
        displayMenu();
    }

    FoodItem getFoodItem(int index) {
        return menu[index];
    }

    int getMenuLength() {
        return menu.length;
    }
}

class Order {

    private FoodItem[] items = new FoodItem[10];
    private int[] quantities = new int[10];

    private int count = 0;

    private double subtotal;
    private double deliveryCharge;
    private double tax;
    private double total;

    void addItem(FoodItem item, int quantity) {
        items[count] = item;
        quantities[count] = quantity;
        count++;
    }

    void calculateBill() {

        subtotal = 0;

        for (int i = 0; i < count; i++) {
            subtotal += items[i].getPrice() * quantities[i];
        }

        if (subtotal >= 500) {
            deliveryCharge = 0;
        } else {
            deliveryCharge = 50;
        }

        final double GST = 0.05; 

        tax = subtotal * GST;
        total = subtotal + deliveryCharge + tax;
    }

    void displayOrderSummary() {

        System.out.println("\nOrder Summary");
        System.out.println("----------------------");

        for (int i = 0; i < count; i++) {
            System.out.println(
                    items[i].getItemName()
                            + " x"
                            + quantities[i]
                            + " = ₹"
                            + (int) (items[i].getPrice() * quantities[i]));
        }

        System.out.println("Subtotal: ₹" + (int) subtotal);
        System.out.println("Delivery Charge: ₹" + (int) deliveryCharge);
        System.out.println("Tax (5%): ₹" + (int) tax);
        System.out.println("Total Amount: ₹" + (int) total);
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FoodItem f1 = new FoodItem("Burger", 100);
        FoodItem f2 = new FoodItem("Pizza", 300);
        FoodItem f3 = new SpecialFoodItem("Fries", 80);

        FoodItem[] menu = {f1, f2, f3};

        Restaurant restaurant = new Restaurant("Food Hub", menu);

        Order order = new Order();

        int choice = 0;

        do {

            restaurant.displayMenu();

            System.out.print("\nEnter item number: ");
            int itemNumber = sc.nextInt();

            if (itemNumber < 1 || itemNumber > restaurant.getMenuLength()) {
                System.out.println("Invalid Item Number!");
                continue;
            }

            int quantity;

            try {

                System.out.print("Enter quantity: ");
                quantity = sc.nextInt();

                if (quantity <= 0) {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("Invalid Quantity!");
                sc.nextLine();
                continue;
            }

            order.addItem(
                    restaurant.getFoodItem(itemNumber - 1),
                    quantity
            );

            System.out.print("Add more items? (1-Yes / 0-No): ");
            choice = sc.nextInt();

        } while (choice == 1);

        order.calculateBill();
        order.displayOrderSummary();

        sc.close();
    }
}
    

