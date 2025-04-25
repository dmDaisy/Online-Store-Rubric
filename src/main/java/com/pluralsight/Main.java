package com.pluralsight;
import javax.annotation.processing.SupportedSourceVersion;
import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Product> inventory = new ArrayList<>();
    static ArrayList<Product> cart = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static final String FILE_NAME = "Products.csv";

    public static void main(String[] args) {
        loadProducts(FILE_NAME);

        int mainMenuCommand = 10;

        do{
            System.out.println("Welcome to online store! Choose your option by typing an integer: " +
                    "\n1. Display products." +
                    "\n2. Display cart." +
                    "\n0. Exit.");

            mainMenuCommand = getUserInt();

            if(mainMenuCommand == 1) displayProducts();
            else if(mainMenuCommand == 2) displayCart();
            else if(mainMenuCommand == 0) System.out.println("Bye! ");
            else System.out.println("Invalid input, try again. ");

        } while(mainMenuCommand != 0);
    }

    public static void loadProducts(String fileName){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));

            String input;

            while((input = bufferedReader.readLine()) != null){
                String[] fields = input.split("\\|");
                String sku = fields[0];
                String name = fields[1];
                float price = Float.parseFloat(fields[2]);
                String category = fields[3];
                Product product = new Product(sku, name, price, category);
                inventory.add(product);
            }

            bufferedReader.close();

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void displayProducts(){
        printArrayList(inventory);
        System.out.println("Display products:" +
                "\n1. Search/Filter" +
                "\n2. Add product" +
                "\n0. Back");

        int command = getUserInt();

        switch (command){
            case 1:
                searchFilterProducts();
                break;
            case 2:
                addProduct();
                break;
            case 0:
                System.out.println("Going back...");
                break;
            default:
                System.out.println("Incorrect command, going back...");
        }
    }

    private static void searchFilterProducts() {
        // display all
        // search by name
        // filter by category
        // back

        System.out.println("Search or filter products, choose your option: " +
                "\n1. Display all" +
                "\n2. Find product(s) by name" +
                "\n3. Filter products by department" +
                "\n0. Go back");

        int command = getUserInt();
        switch (command){
            case 1:
                printArrayList(inventory);
                break;
            case 2:
                System.out.println("Enter product name: ");
                String inputName = scanner.nextLine();
                for(Product product : inventory){
                    if(product.getName().equalsIgnoreCase(inputName)){
                        System.out.println(product);
                    }
                }
                break;
            case 3:
                System.out.println("Enter product category: ");
                String inputCategory = scanner.nextLine();
                for(Product product : inventory){
                    if(product.getDepartment().equalsIgnoreCase(inputCategory)){
                        System.out.println(product);
                    }
                }
                break;
            case 0:
                System.out.println("Going back...");
                break;
            default:
                System.out.println("Invalid input, going back...");
        }
    }

    // add product to inventory
    private static void addProduct(){
        System.out.println("Enter product sku: ");
        String input = scanner.nextLine();
        for(Product product : inventory){
            if(product.getSku().equalsIgnoreCase(input)){
                inventory.remove(product);
                cart.add(product);
                System.out.println("Added the following item from inventory to cart: " +
                        "\n" + product);
                break;
            }
        }
    }

    public static void displayCart(){
        System.out.println("Display cart:" +
                "\nChoose your option: " +
                "\n1. Check out" +
                "\n2. Remove product" +
                "\n0. Go back");

        int command = getUserInt();

        switch (command){
            case 1:
                handleCheckOut();
                break;
            case 2:
                removeProduct();
                break;
            case 0:
                System.out.println("Going back...");
                break;
            default:
                System.out.println("Invalid input, going back...");
        }
    }

    private static void handleCheckOut(){
        printArrayList(cart);

    }

    private static void removeProduct() {
        System.out.println("Enter product sku: ");
        String input = scanner.nextLine();
        for(Product product : cart){
            if(product.getSku().equalsIgnoreCase(input)){
                cart.remove(product);
                inventory.add(product);
                System.out.println("Removed the following item from cart to inventory: " +
                        "\n" + product);
                break;
            }
        }
    }

    static void printArrayList(ArrayList<Product> list){
        for(Product product : list)
            System.out.println(product);
    }

    // guarentee to get integer input from user
    private static int getUserInt(){
        while( ! scanner.hasNextInt()){
            System.out.println("Not an int, input again: ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // consumes the additional \n
        return result;
    }
}