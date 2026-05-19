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

class Restaurant {

    private FoodItem[] menu;

    Restaurant(FoodItem[] menu) {
        this.menu = menu;
    }

    void displayMenu() {

        System.out.println("\nMenu:");

        for (int i = 0; i < menu.length; i++) {
            System.out.println(
                    (i + 1) + ". "
                    + menu[i].getItemName()
                    + " - ₹"
                    + (int)menu[i].getPrice()
            );
        }
    }

    FoodItem getItem(int index) {
        return menu[index];
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

        tax = subtotal * 0.05;

        total = subtotal + deliveryCharge + tax;
    }

    void displayOrderSummary() {

        System.out.println("\nOrder Summary:");
        System.out.println("-----------------------");

        for (int i = 0; i < count; i++) {

            System.out.println(
                    items[i].getItemName()
                    + " x"
                    + quantities[i]
                    + " = ₹"
                    + (int)(items[i].getPrice() * quantities[i])
            );
        }

        System.out.println("Subtotal: ₹" + (int)subtotal);
        System.out.println("Delivery Charge: ₹" + (int)deliveryCharge);
        System.out.println("Tax (5%): ₹" + (int)tax);
        System.out.println("Total Amount: ₹" + (int)total);
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FoodItem f1 = new FoodItem("Burger", 100);
        FoodItem f2 = new FoodItem("Pizza", 300);
        FoodItem f3 = new FoodItem("Fries", 80);

        FoodItem[] menu = {f1, f2, f3};

        Restaurant restaurant = new Restaurant(menu);

        Order order = new Order();

        restaurant.displayMenu();

        System.out.print("\nEnter item number: ");
        int itemNo = sc.nextInt();

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        order.addItem(menu[itemNo - 1], quantity);

        order.calculateBill();

        order.displayOrderSummary();

        sc.close();
    }
}
