package com.pluralsight;
import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Product> inventory = new ArrayList<>();
    static ArrayList<Product> cart = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static final String FILE_NAME = "";

    public static void main(String[] args) {
        loadProducts(FILE_NAME);

        int mainMenuCommand = scanner.nextInt();

        do{
            System.out.println("Welcome to online store! Choose your option by typing an integer: " +
                    "\n1. Display products." +
                    "\n2. Display cart." +
                    "\n0. Exit.");

            mainMenuCommand = scanner.nextInt();

            if(mainMenuCommand == 1) displayProducts();
            else if(mainMenuCommand == 2) displayCart();
            else if(mainMenuCommand == 0) System.out.println("Bye! ");
            else System.out.println("Invalid input, try again. ");

        } while(mainMenuCommand != 0);
    }

    public static void loadProducts(String fileName){

    }

    public static void displayProducts(){
        printArrayList(inventory);
        System.out.println("Display products:" +
                "\n1. Search/Filter" +
                "\n2. Add product" +
                "\n0. Back");

        int command = scanner.nextInt();

        switch (command){
            case 1:
                searchFilterProducts();
                break;
            case 2:
                addProduct();
                break;
            case 0:
                System.out.println("Going back...");
            default:
                System.out.println("Incorrect command, going back...");
        }
    }

    private static void searchFilterProducts() {
    }

    private static void addProduct(){

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
            default:
                System.out.println("Invalid input, going back...");
        }
    }

    private static void handleCheckOut(){

    }

    private static void removeProduct() {
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
        return scanner.nextInt();
    }
}