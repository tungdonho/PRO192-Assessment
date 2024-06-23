import java.util.Scanner;

public class Food {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the category (Main Course, Appetizers, Drinks):");
        String category = scanner.nextLine().trim();

        System.out.println("Enter the item:");
        String item = scanner.nextLine().trim();

        double price = 0.0;

        switch (category.toLowerCase()) {
            case "main course":
                switch (item.toLowerCase()) {
                    case "cheese sandwiches":
                    case "cheese burger":
                        price = 10.99;
                        break;
                    case "chicken burgers":
                        price = 9.99;
                        break;
                    case "spicy chicken":
                        price = 11.99;
                        break;
                    default:
                        System.out.println("Invalid item in Main Course category");
                        return;
                }
                break;
            case "appetizers":
                switch (item.toLowerCase()) {
                    case "fruit salad":
                        price = 4.99;
                        break;
                    case "chips":
                        price = 2.99;
                        break;
                    case "nuggets":
                        price = 5.99;
                        break;
                    case "cocktails":
                        price = 6.99;
                        break;
                    default:
                        System.out.println("Invalid item in Appetizers category");
                        return;
                }
                break;
            case "drinks":
                switch (item.toLowerCase()) {
                    case "milk shake":
                        price = 2.99;
                        break;
                    case "iced tea":
                        price = 1.99;
                        break;
                    case "lemon tea":
                        price = 1.99;
                        break;
                    case "coffee":
                        price = 1.49;
                        break;
                    default:
                        System.out.println("Invalid item in Drinks category");
                        return;
                }
                break;
            default:
                System.out.println("Invalid category");
                return;
        }

        System.out.println("The price of " + item + " is $" + price);
    }
}