// online food delivery system

import java.util.Scanner;

class FoodItem {
    String name;
    double price;

    FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Restaurant {
    FoodItem[] menu;

    Restaurant(FoodItem[] menu) {
        this.menu = menu;
    }

    void showMenu() {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i].name + " - ₹" + menu[i].price);
        }
    }
}

class Order {
    FoodItem[] items = new FoodItem[10];
    int[] qty = new int[10];
    int count = 0;

    double total = 0;

    void add(FoodItem f, int q) {
        if (count < items.length) {
            items[count] = f;
            qty[count] = q;
            count++;
        } else {
            System.out.println("Order limit reached!");
        }
    }

    