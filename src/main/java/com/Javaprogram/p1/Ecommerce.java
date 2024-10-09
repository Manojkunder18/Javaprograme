package com.Javaprogram.p1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ecommerce{

    static class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Price: $" + price;
        }
    }

    static class Category {
        private String name;
        private List<Product> products;
        private List<Category> subCategories;

        public Category(String name) {
            this.name = name;
            this.products = new ArrayList<>();
            this.subCategories = new ArrayList<>();
        }

        public void addProduct(Product product) {
            products.add(product);
        }

        public void addSubCategory(Category category) {
            subCategories.add(category);
        }

        public String getName() {
            return name;
        }

        public List<Product> getProducts() {
            return products;
        }

        public List<Category> getSubCategories() {
            return subCategories;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class ShoppingCart {
        private Map<Product, Integer> items;

        public ShoppingCart() {
            items = new HashMap<>();
        }

        public void addItem(Product product, int quantity) {
            items.put(product, items.getOrDefault(product, 0) + quantity);
        }

        public double calculateTotal() {
            double total = 0;
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                total += entry.getKey().getPrice() * entry.getValue();
            }
            return total;
        }

        public void displayCart() {
            System.out.println("Shopping Cart:");
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                System.out.println(entry.getKey() + ", Quantity: " + entry.getValue());
            }
            System.out.println("Total: $" + calculateTotal());
        }
    }

    public static void main(String[] args) {
        Category groceries = new Category("Groceries");
        Category fashion = new Category("Fashion");
        Category electronics = new Category("Electronics");
        Category homeAppliances = new Category("Home Appliances");

        groceries.addProduct(new Product(1, "Milk", 1.99));
        groceries.addProduct(new Product(2, "Bread", 2.49));
        groceries.addProduct(new Product(3, "Eggs", 3.19));
        groceries.addProduct(new Product(4, "Apples", 4.99));

        electronics.addProduct(new Product(5, "TV", 299.99));
        electronics.addProduct(new Product(6, "Laptop", 799.99));
        electronics.addProduct(new Product(7, "Headphones", 49.99));
        electronics.addProduct(new Product(8, "Smartphone", 499.99));

        homeAppliances.addProduct(new Product(9, "Vacuum Cleaner", 129.99));
        homeAppliances.addProduct(new Product(10, "Washing Machine", 399.99));
        homeAppliances.addProduct(new Product(11, "Microwave", 89.99));
        homeAppliances.addProduct(new Product(12, "Refrigerator", 499.99));

        Category menFashion = new Category("Men");
        Category womenFashion = new Category("Women");
        Category childrenFashion = new Category("Children");

        fashion.addSubCategory(menFashion);
        fashion.addSubCategory(womenFashion);
        fashion.addSubCategory(childrenFashion);

        menFashion.addProduct(new Product(13, "Men's Shirt", 29.99));
        menFashion.addProduct(new Product(14, "Men's Jeans", 39.99));
        menFashion.addProduct(new Product(15, "Men's Jacket", 59.99));
        menFashion.addProduct(new Product(16, "Men's Shoes", 79.99));

        womenFashion.addProduct(new Product(17, "Women's Dress", 49.99));
        womenFashion.addProduct(new Product(18, "Women's Skirt", 29.99));
        womenFashion.addProduct(new Product(19, "Women's Shoes", 69.99));
        womenFashion.addProduct(new Product(20, "Women's Jacket", 89.99));

        childrenFashion.addProduct(new Product(21, "Children's T-Shirt", 19.99));
        childrenFashion.addProduct(new Product(22, "Children's Shorts", 24.99));
        childrenFashion.addProduct(new Product(23, "Children's Jacket", 39.99));
        childrenFashion.addProduct(new Product(24, "Children's Shoes", 29.99));

        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n\t\t\t\t\t\033[31m\033[1mE-COMMERCE APPLICATION\033[0m"); 
            System.out.println("1. Groceries");
            System.out.println("2. Fashion");
            System.out.println("3. Electronics");
            System.out.println("4. Home Appliances");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    displayCategory(groceries, cart);
                    break;
                case 2:
                    displayCategory(fashion, cart);
                    break;
                case 3:
                    displayCategory(electronics, cart);
                    break;
                case 4:
                    displayCategory(homeAppliances, cart);
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    checkout(cart);
                    return; 
                case 7:
                    System.out.println("Thank you for shopping with us!");
                    return; 
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void displayCategory(Category category, ShoppingCart cart) {
        System.out.println("\nCategory: " + category.getName());
        for (Product product : category.getProducts()) {
            System.out.println(product);
        }
        for (Category subCategory : category.getSubCategories()) {
            System.out.println("\nSub-Category: " + subCategory.getName());
            for (Product product : subCategory.getProducts()) {
                System.out.println(product);
            }
        }
        System.out.print("Enter product ID to add to cart or 0 to return: ");
        Scanner scanner = new Scanner(System.in);
        int productId = scanner.nextInt();
        scanner.nextLine();  

        if (productId != 0) {
            Product selectedProduct = findProductById(category, productId);
            if (selectedProduct != null) {
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();  
                cart.addItem(selectedProduct, quantity);
                System.out.println("Added to cart: " + selectedProduct.getName() + ", Quantity: " + quantity);
            } else {
                System.out.println("Product not found.");
            }
        }
    }

    private static Product findProductById(Category category, int id) {
        for (Product product : category.getProducts()) {
            if (product.getId() == id) {
                return product;
            }
        }
        for (Category subCategory : category.getSubCategories()) {
            Product product = findProductById(subCategory, id);
            if (product != null) {
                return product;
            }
        }
        return null;
    }

    private static void checkout(ShoppingCart cart) {
        cart.displayCart();
        System.out.println("Choose payment option:");
        System.out.println("1. UPI");
        System.out.println("2. Credit Card");
        System.out.print("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); 

        double total = cart.calculateTotal();
        switch (paymentChoice) {
            case 1:
                System.out.println("You chose UPI. Please complete your payment using your UPI app.");
                System.out.println("Total amount: $" + total);
                System.out.println("Payment successful. Thank you for your purchase!");
                break;
            case 2:
                System.out.println("You chose Credit Card.Please complete your payment.");
                System.out.println("Total amount: $" + total);
                System.out.println("Payment successful. Thank you for your purchase!");
                break;
            default:
                System.out.println("Invalid payment option. Please try again.");
                break;
        }
    }
}
