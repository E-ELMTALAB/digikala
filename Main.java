
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main{

    public static boolean run = true;
    public static boolean user_run = false;
    public static boolean seller_run = false;
    public static boolean admin_run = false;

    public static class Product {
        public String name;
        public int price;
        public int quantity;

        public Product(String name, int price , int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

    }

    public static class Book extends Product {

        private int pages;
        private String genre;

        public Book(String name , int price , int quantity , int pages , String genre) {
            super(name , price , quantity);
            this.pages = pages;
            this.genre = genre;
        }
    }

    public static class ElectronicProduct extends Product {
        public String brand;


        public ElectronicProduct(String name, int price , int quantity , String brand) {
            super(name, price , quantity);
            this.brand = brand;

        }
    }

    public static class VehicleProduct extends Product {
        public int torque;

        public VehicleProduct(String name, int price , int quantity , int torque) {
            super(name, price , quantity);
            this.torque = torque;

        }
    }

    public static class Phone extends ElectronicProduct {
        private String model;

        public Phone(String name, int price , int quantity,  String brand, String model ) {
            super(name, price, quantity ,brand);
            this.model = model;

        }

    }

    public static class Motor extends VehicleProduct {
        private String model;

        public Motor(String name, int price,int quantity , int torque, String model) {
            super(name, price,quantity , torque);
            this.model = model;
        }

    }

    public static class Car extends VehicleProduct {
        private int horsePower;

        public Car(String name, int price, int quantity, int torque, int horsePower ) {
            super(name, price, quantity, torque);
            this.horsePower = horsePower;
        }

    }





    public static class Admin {

        public String username;
        public String password;
        public Admin(String username , String password ){

            this.username = username;
            this.password = password;
        }

        public static boolean IsSellerAccepted(String username, String shop_name ) {

            try {
                BufferedReader reader = new BufferedReader(new FileReader("accepted_sellers.txt"));
                String line = reader.readLine();
                while (line != null) {

                    String[] user_shop_arr = line.split("_");
                    if (user_shop_arr[0].equals(username) && user_shop_arr[1].equals(shop_name) && user_shop_arr[2].equals("YES")) {
                        reader.close();
                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        // writing the user in the file
        public static void writeAcceptRequest(String username, String shop_name ) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("accepted_sellers.txt", true));
                writer.write(username + "_" + shop_name + "_" + "waiting" );
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void writeFundRequest(String username, String fund ) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("fund_requests.txt", true));
                writer.write(username + "_" + fund + "_" + "waiting" );
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static boolean SellerIsAuthorized(String username , String shop_name) {

            if(IsSellerAccepted(username , shop_name)) {
                return true;
            }
            else{
                writeAcceptRequest(username , shop_name);
            }
            return false;
        }

        public static void acceptAllRequests() {

            try{
                File inputFile = new File("accepted_sellers.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Create a temporary file to write the output
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                // Read the input file line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file");
                    String[] user_shop_arr = line.split("_");

                    if (! (user_shop_arr[2].equals("waiting")) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                    else {
                        writer.write(user_shop_arr[0] + "_" + user_shop_arr[1] + "_" + "YES");
                        writer.newLine();
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);

            } catch (IOException e){
                e.printStackTrace();
            }

        }

        public static void acceptAllFundRequests() {

            try{
                File inputFile = new File("fund_requests.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Create a temporary file to write the output
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                // Read the input file line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file");
                    String[] user_shop_arr = line.split("_");

                    if (! (user_shop_arr[2].equals("waiting")) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                    else {
                        writer.write(user_shop_arr[0] + "_" + user_shop_arr[1] + "_" + "YES");
                        writer.newLine();
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);

            } catch (IOException e){
                e.printStackTrace();
            }

        }

        public static void checkRequests() {

            try {
                BufferedReader reader = new BufferedReader(new FileReader("accepted_sellers.txt"));
                String line = reader.readLine();
                while (line != null) {

                    String[] user_shop_arr = line.split("_");
                    if (user_shop_arr[2].equals("waiting")) {
                        System.out.println("username : " + user_shop_arr[0] );
                        System.out.println("shop name : " + user_shop_arr[1] );
//                        reader.close();
//                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("if you accept all request type 1 otherwise 0 ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){
                acceptAllRequests();
            }
        }

        public void check_fund_request(){

            try {
                BufferedReader reader = new BufferedReader(new FileReader("fund_requests.txt"));
                String line = reader.readLine();
                while (line != null) {

                    String[] user_shop_arr = line.split("_");
                    if (user_shop_arr[2].equals("waiting")) {
                        System.out.println("username : " + user_shop_arr[0] );
                        System.out.println("requested wallet fund : " + user_shop_arr[1] );
//                        reader.close();
//                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("if you accept all request type 1 otherwise 0 ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){
                acceptAllFundRequests();
            }


        }

        public void check_transaction_request(){

            try {
                BufferedReader reader = new BufferedReader(new FileReader("transaction_request.txt"));
                String line = reader.readLine();
                while (line != null) {

                    String[] user_shop_arr = line.split("_");
                    if (user_shop_arr[2].equals("waiting")) {
                        System.out.println("username : " + user_shop_arr[0] );
                        System.out.println("shop name : " + user_shop_arr[1] );
//                        reader.close();
//                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("if you accept all request type 1 otherwise 0 ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){
                acceptAllPurchaseRequest();
            }

        }

        public void writePurchaseRequest(String username , String password , int cost) {
            System.out.println("trying to write purchase request");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("transaction_request.txt", true));
                writer.write(username + "_" + cost + "_" + "waiting" );
                System.out.println("wrote a request for purchase");
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void acceptAllPurchaseRequest() {

            try{
                File inputFile = new File("transaction_request.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Create a temporary file to write the output
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                // Read the input file line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file");
                    String[] user_shop_arr = line.split("_");

                    if (! (user_shop_arr[2].equals("waiting")) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                    else {
                        writer.write(user_shop_arr[0] + "_" + user_shop_arr[1] + "_" + "YES");
                        writer.newLine();
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);

            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }

//    static class Movie extends TVShow {
//
//        public String length;
//
//        public Movie(String name , String genre , String release , String rating , String length)
//        {
//            super(name , genre , release, rating);
//             this.length = length;
//        }
//    }

    public static class Seller {

        public String username;
        public String product_name ;
        public String shop_name;
        public String password;
        public String price ;
        public String model;

        public Seller(String username , String password , String shop_name) {
            this.username = username;
            this.password = password;
            this.shop_name = shop_name;
        }

        public void add_book(Book book) {

            try {
                File inputFile = new File("sellers.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");;
                    String[] credentials = whole_string[0].split("_");

                    if (! (credentials[0].equals(username) && (credentials[1].equals(password))) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                    else {
                        writer.write(whole_string[0] + "@" + "book" + book.name + "_" + book.price + "_" + book.quantity + "_" + book.pages + "_" + book.genre  + "#");
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        public void add_phone(Phone phone) {

            try {
                File inputFile = new File("sellers.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");;
                    String[] credentials = whole_string[0].split("_");

                    if (! (credentials[0].equals(username) && (credentials[1].equals(password))) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                    else {
                        writer.write(whole_string[0] + "@" + "phone" + phone.name + "_" + phone.price + "_" + phone.quantity + "_" + phone.brand + "_" + phone.model  + "#");
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        public void add_car(Car car) {

            try {
                File inputFile = new File("sellers.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");;
                    String[] credentials = whole_string[0].split("_");

                    if (! (credentials[0].equals(username) && (credentials[1].equals(password))) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                    else {
                        writer.write(whole_string[0] + "@" + "car" + car.name + "_" + car.price + "_" + car.quantity + "_" + car.torque + "_" + car.horsePower  + "#");
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        public void add_motor(Motor motor) {

            try {
                File inputFile = new File("sellers.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");;
                    String[] credentials = whole_string[0].split("_");

                    if (! (credentials[0].equals(username) && (credentials[1].equals(password))) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                    else {
                        writer.write(whole_string[0] + "@" + "motor" +  motor.name + "_" + motor.price + "_" + motor.quantity + "_" + motor.torque + "_" + motor.model  + "#");
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void add_products() {
            while(run){

                System.out.println("What product do you want to add to your online shop");
                System.out.println("1. Car");
                System.out.println("2. Motor ");
                System.out.println("3. Book ");
                System.out.println("4. Phone ");
                System.out.println("6. exit ");

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:


                        System.out.println("how many new cars do you want to add : ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        for(int i= 0 ; i < choice ; i++){

                            System.out.print("name :");
                            String name = scanner.nextLine();
                            System.out.print("price :");
                            int price = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("quantity :");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("torque :");
                            int torque = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("horse power :");
                            int horsePower = scanner.nextInt();
                            scanner.nextLine();
                            Car car = new Car(name , price , quantity , torque , horsePower);
                            add_car(car);

                        }

                        break;

                    case 2:
//                        login("users.txt");
                        System.out.println("how many new motors do you want to add : ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        for(int i= 0 ; i < choice ; i++){

                            System.out.print("name :");
                            String name = scanner.nextLine();
                            System.out.print("price :");
                            int price = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("quantity :");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("torque: :");
                            int torque = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("model :");
                            String model = scanner.nextLine();
                            Motor motor = new Motor(name , price , quantity , torque , model);
                            add_motor(motor);

                        }
                        break;

                    case 3:
//                        createAccount("sellers.txt");
                        System.out.println("how many new books do you want to add : ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        for(int i= 0 ; i < choice ; i++){

                            System.out.print("name :");
                            String name = scanner.nextLine();
                            System.out.print("price :");
                            int price = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("quantity :");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("pages: :");
                            int pages = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("genre :");
                            String genre = scanner.nextLine();
                            Book book = new Book(name , price , quantity , pages , genre);
                            add_book(book);

                        }
                        break;

                    case 4:
//                        login("sellers.txt");
                        System.out.println("how many new phones do you want to add : ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        for(int i= 0 ; i < choice ; i++){

                            System.out.print("name :");
                            String name = scanner.nextLine();
                            System.out.print("price :");
                            int price = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("quantity :");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("brand :");
                            String brand = scanner.nextLine();
                            scanner.nextLine();
                            System.out.print("model :");
                            String model = scanner.nextLine();
                            Phone phone = new Phone(name , price , quantity , brand , model);
                            add_phone(phone);

                        }

                        break;

                    case 5:
//                        list_shows_movies();
//                        login("admins.txt");
                        break;

                    case 6:
                        run = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }

        }

        public void remove_product(String product_name , String shop_name){

            String remaining_products = "";
            try{
                File inputFile = new File("sellers.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Create a temporary file to write the output
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                // Read the input file line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");
//                    String[] user_show_str = whole_string[0].split(",");
                    String[] products = whole_string[0].split("@");

                    if ( (products[0].split("_")[2].equals(shop_name)) ) {

                        // check each product
                        for (int i = 0 ; i < products.length ; i++) {

                            String[] product_details = products[i].split("_");

                            if(!product_details[1].equals(product_name)) {
                                System.out.println("this is not the specific product");
                                if (i == 0) {
                                    remaining_products +=  products[i];
                                }
                                else{
                                    remaining_products +=  "@" + products[i];
                                }

                            }

                            else {
                                System.out.println("this is the product we are looking for");
                                String product = "" ;
                                int quantity = Integer.parseInt(product_details[3]);
                                System.out.println("quantity : " + quantity);
                                if (quantity > 1) {
                                    quantity -= 1;
                                    product += product_details[0]+ "_" + product_details[1] + "_" + product_details[2] + "_" + quantity + "_" + product_details[4] + "_" + product_details[5] ;
                                    System.out.println("the redefined product" + product);
                                    remaining_products += "@" +  product ;

                                }

                            }

                        }
                        remaining_products += "#";
                        System.out.println("now the whole new string is : " + remaining_products);
                        writer.write(remaining_products);
                        writer.newLine();
                    }
                    else{
                        writer.write(line);
                        writer.newLine();
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);

            } catch (IOException e){
                e.printStackTrace();
            }

        }

        public void sell_product(String product_name) {

        }

        
    }

    public static class User {
        /*
         * The user should contain username password.
         * The user should contain an ArrayList of favorite shows and watch history.
         * FUNCTION: the user should have a function to watch a show and add it to watch history.
         *  *** NOTE: All search functions in user are for searching in favorite shows ***
         */
        public String username;
        public String password;
        public String str_choice;
        public Scanner scanner;
        public String product_name;
        public String fund;

        public User(String username , String password){

            this.username = username ;
            this.password = password;
        }

        public boolean IsPurchaseAccepted() {

            try {
                BufferedReader reader = new BufferedReader(new FileReader("transaction_request.txt"));
//                File inputFile = new File("users.txt");
//                BufferedReader user_reader = new BufferedReader(new FileReader(inputFile));
                String line = reader.readLine();
                while (line != null) {

                    String[] user_shop_arr = line.split("_");
                    if (user_shop_arr[0].equals(username) && user_shop_arr[2].equals("YES")) {
                        this.fund = user_shop_arr[1];
//                        System.out.println("fund : " + fund);
                        reader.close();
                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        public boolean IsFundAccepted(){

            try {
                BufferedReader reader = new BufferedReader(new FileReader("fund_requests.txt"));
//                File inputFile = new File("users.txt");
//                BufferedReader user_reader = new BufferedReader(new FileReader(inputFile));
                String line = reader.readLine();
                while (line != null) {

                    String[] user_shop_arr = line.split("_");
                    if (user_shop_arr[0].equals(username) && user_shop_arr[2].equals("YES")) {
                        this.fund = user_shop_arr[1];
//                        System.out.println("fund : " + fund);
                        reader.close();
                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        public void write_wallet_fund(){

            try{
                File inputFile = new File("users.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Create a temporary file to write the output
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                // Read the input file line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");
                    String[] credentials = whole_string[0].split("_");

                    if (!(credentials[0].equals(username) && (credentials[1].equals(password)))) {
                        System.out.println("i have entered the if statement");
                        writer.write(line);
                        writer.newLine();
                    } else {
                        System.out.println("fund : " + fund);
                        System.out.println("i have entered the else statement");
                        writer.write(credentials[0] + "_" + credentials[1] + "_" + fund + "#");
                        writer.newLine();
                    }

                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);


            } catch (IOException e){
                e.printStackTrace();
            }

        }

        public void find_product(String product_name , String shop_name){

            try {
                BufferedReader reader = new BufferedReader(new FileReader("sellers.txt"));
                String line = reader.readLine();
                while (line != null) {

                    String[] whole_string = line.split("#");
                    String[] products = whole_string[0].split("@");
                    String[] user_pass_arr = products[0].split("_");
                    String seller_shop_name = user_pass_arr[2];

                    for (int i = 1 ; i < products.length ; i ++ ) {

                        String[] details = products[i].split("_");
//                        System.out.println("category : " + details[0]);
//                        System.out.println("product name : " + product_name);

                        if (details[1].equals(product_name) && seller_shop_name.equals(shop_name)){
//                            System.out.println("entered");
                            add_to_cart(details[1] , details[2] , details[3] , details[4] , details[5]);

                        }
                    }

                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        public void buy(int cost){

            String remaining_string = "";
            try{
                File inputFile = new File("users.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Create a temporary file to write the output
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                // Read the input file line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");
//                    String[] user_show_str = whole_string[0].split(",");
                    String[] products = whole_string[0].split("@");
                    String username_pass = products[0];
                    String[] username_pass_details = username_pass.split("_");

                    if ( (products[0].split("_")[0].equals(username)) && (products[0].split("_")[1].equals(password))) {

                        remaining_string = username_pass_details[0] + "_" + username_pass_details[1] + "_" + (Integer.parseInt(username_pass_details[2]) - cost) + "#";
                        writer.write(remaining_string);
                    }
                    else{
                        writer.write(line);
                        writer.newLine();
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);

            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("you have bought the products");

        }

        public int purchaseSumCost(){

            int cost = 0;
            try {
                File inputFile = new File("users.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");;

                    if (whole_string[0].split("@")[0].split("_")[0].equals(username) && (whole_string[0].split("@")[0].split("_")[1].equals(password))) {
//                        writer.write(line);
//                        writer.newLine();
                        String[] cart_products = whole_string[0].split("@");

                        for (int i = 1 ; i < cart_products.length ; i++) {
                            String[] product_details = cart_products[i].split("_");
//                            System.out.println("name : " + product_details[0] + "  ,  price " + product_details[1] + "  ,  quantity : " + product_details[2]);
                            cost += Integer.parseInt(product_details[1]);
                        }

                    }

                }

                // Close the input and output files
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return cost;
        }

        public boolean can_pay() {

            int cost = 0;
            int wallet = 0;
            try {
                File inputFile = new File("users.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");;
                    wallet = Integer.parseInt(whole_string[0].split("@")[0].split("_")[2]);

                    if (whole_string[0].split("@")[0].split("_")[0].equals(username) && (whole_string[0].split("@")[0].split("_")[1].equals(password))) {
//                        writer.write(line);
//                        writer.newLine();
                        String[] cart_products = whole_string[0].split("@");

                        for (int i = 1 ; i < cart_products.length ; i++) {
                            String[] product_details = cart_products[i].split("_");
//                            System.out.println("name : " + product_details[0] + "  ,  price " + product_details[1] + "  ,  quantity : " + product_details[2]);
                            cost += Integer.parseInt(product_details[1]);
                        }

                    }

                }
                System.out.println("in user can_pay() the cost is : " + cost);

                // Close the input and output files
                reader.close();
                if (wallet > cost) {
                    System.out.println("the user can pay for the products ");
                    return true;
                }
                else{
                    System.out.println("you don't have enough money to buy what you have in your cart");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return false;

        }

        public void add_to_cart(String product_details1 ,String product_details2 ,String product_details3 ,String product_details4 ,String product_details5 ) {

            try {
                File inputFile = new File("users.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");;
                    String[] credentials = whole_string[0].split("_");

                    if (! (credentials[0].equals(username) && (credentials[1].equals(password))) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                    else {
                        writer.write(whole_string[0] + "@" + product_details1 + "_" + product_details2 + "_" + 1 + "_" + product_details4 + "_" + product_details5  + "#");
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void view_cart(){

            try {
                File inputFile = new File("users.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");;

                    if (whole_string[0].split("@")[0].split("_")[0].equals(username) && (whole_string[0].split("@")[0].split("_")[1].equals(password))) {
//                        writer.write(line);
//                        writer.newLine();
                        String[] cart_products = whole_string[0].split("@");

                        for (int i = 1 ; i < cart_products.length ; i++) {
                            String[] product_details = cart_products[i].split("_");
                            System.out.println("name : " + product_details[0] + "  ,  price " + product_details[1] + "  ,  quantity : " + product_details[2]);
                        }

                    }

                }

                // Close the input and output files
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void remove_product(String product_name){

            String remaining_products = "";
            try{
                File inputFile = new File("users.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Create a temporary file to write the output
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                // Read the input file line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("#");
//                    String[] user_show_str = whole_string[0].split(",");
                    String[] products = whole_string[0].split("@");

                    if ( (products[0].split("_")[0].equals(username)) && (products[0].split("_")[1].equals(password))) {

                        // check each product
                        for (int i = 0 ; i < products.length ; i++) {

                            String[] product_details = products[i].split("_");

                            if(!product_details[0].equals(product_name)) {
                                System.out.println("this is not the specific product");
                                if (i == 0) {
                                    remaining_products +=  products[i];
                                }
                                else{
                                    remaining_products +=  "@" + products[i];
                                }

                            }

                        }
                        remaining_products += "#";
                        System.out.println("now the whole new string is : " + remaining_products);
                        writer.write(remaining_products);
                        writer.newLine();
                    }
                    else{
                        writer.write(line);
                        writer.newLine();
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);

            } catch (IOException e){
                e.printStackTrace();
            }

        }

//        public ArrayList<TVShow> searchByTitle() {
//            // Implement search by title in favorite shows  logic here
//            boolean name_found = false;
//            try {
//
//                System.out.println("please enter the name of the show/movie you are looking for : ");
//                scanner = new Scanner(System.in);
//                str_choice = scanner.nextLine();
//
//                BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
//                String line = reader.readLine();
//                while (line != null) {
//
//                    String[] whole_string = line.split("@");
//                    String[] shows_user_str = whole_string[0].split(",");
//                    String[] user_pass = shows_user_str[0].split("_");
//
//                    if(user_pass[0].equals(this.username) && user_pass[1].equals(this.password)){
//
//                        for(int i = 1; i < shows_user_str.length ; i++){
//
//                            String[] show_splited = shows_user_str[i].split("_");
//                            if(show_splited[0].equals(str_choice)){
//
//                                System.out.println("found a show/movie with name " + show_splited[0]);
//                                name_found = true;
//
//                            }
//                            System.out.println("");
//                        }
//
//                        if(!name_found){
//
//                            System.out.println(" there is no show/movie with this name ... ");
//                        }
//                    }
//
//                    line = reader.readLine();
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//        public ArrayList<TVShow> searchByGenre() {
//            // Implement search by genre in favorite shows  logic here
//
//            boolean name_found = false;
//            try {
//
//                System.out.println("please enter the genre of the show/movie you are looking for : ");
//                scanner = new Scanner(System.in);
//                str_choice = scanner.nextLine();
//
//                BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
//                String line = reader.readLine();
//                while (line != null) {
//
//                    String[] whole_string = line.split("@");
//                    String[] shows_user_str = whole_string[0].split(",");
//                    String[] user_pass = shows_user_str[0].split("_");
//
//                    if(user_pass[0].equals(this.username) && user_pass[1].equals(this.password)){
//
//                        for(int i = 1; i < shows_user_str.length ; i++){
//
//                            String[] show_splited = shows_user_str[i].split("_");
//                            if(show_splited[1].equals(str_choice)){
//
//                                System.out.println("found the show/movie " + show_splited[0] + " with the genre " + str_choice);
//                                name_found = true;
//
//                            }
//                            System.out.println("");
//                        }
//
//                        if(!name_found){
//
//                            System.out.println(" there is no show/movie with this name ... ");
//                        }
//                    }
//
//                    line = reader.readLine();
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//        public ArrayList<TVShow> searchByReleaseYear() {
//            // Implement search by release year in favorite shows logic here
//
//            boolean name_found = false;
//            try {
//
//                System.out.println("please enter the release year of the show/movie you are looking for : ");
//                scanner = new Scanner(System.in);
//                str_choice = scanner.nextLine();
//
//                BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
//                String line = reader.readLine();
//                while (line != null) {
//
//                    String[] whole_string = line.split("@");
//                    String[] shows_user_str = whole_string[0].split(",");
//                    String[] user_pass = shows_user_str[0].split("_");
//
//                    if(user_pass[0].equals(this.username) && user_pass[1].equals(this.password)){
//
//                        for(int i = 1; i < shows_user_str.length ; i++){
//
//                            String[] show_splited = shows_user_str[i].split("_");
//                            if(show_splited[2].equals(str_choice)){
//
//                                System.out.println("found the show/movie " + show_splited[0] + " with the release year " + str_choice);
//                                name_found = true;
//
//                            }
//                            System.out.println("");
//                        }
//
//                        if(!name_found){
//
//                            System.out.println(" there is no show/movie with this name ... ");
//                        }
//                    }
//
//                    line = reader.readLine();
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }


        public void pick_favorite() {

            boolean show_exist = false;
            boolean movie_exist = false;
            System.out.println("if you want to pick a favorite , type the name of the show/movie ");
            System.out.println("else type quit ");

            scanner = new Scanner(System.in);
            str_choice = scanner.nextLine();

            if (str_choice != "quit"){

                try {
                    BufferedReader reader = new BufferedReader(new FileReader("tvshows.txt"));
                    String line = reader.readLine();
                    while (line != null) {

                        String[] show_splited = line.split("_");

                        if(show_splited[0].equals(str_choice)){

                            show_exist = true;
                            try{
                                File inputFile = new File("users.txt");
                                reader.close();
                                reader = new BufferedReader(new FileReader(inputFile));

                                // Create a temporary file to write the output
                                File tempFile = new File("temp.txt");
                                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                                // Read the input file line by line

                                while ((line = reader.readLine()) != null) {
                                    // If the line doesn't match the line to be removed, write it to the output file

                                    String[] whole_string = line.split("@");
                                    String[] credentials = whole_string[0].split(",");
                                    String[] user_pass_arr = credentials[0].split("_");

                                    if (!user_pass_arr[0].equals(this.username)) {
                                        writer.write(line);
                                        writer.newLine();
                                    }
                                    else {


                                        writer.write(whole_string[0] + "," + show_splited[0] + "_" + show_splited[1] + "_" + show_splited[2] + "_"+ show_splited[3] + "@");
                                        writer.newLine();
                                    }
                                }

                                // Close the input and output files
                                reader.close();
                                writer.close();

                                // Delete the input file
                                inputFile.delete();

                                // Rename the temporary file to the input file
                                tempFile.renameTo(inputFile);

                            } catch (IOException e){
                                e.printStackTrace();
                            }

                        }


                        line = reader.readLine();
                    }



                    if(!show_exist){

                        reader = new BufferedReader(new FileReader("movies.txt"));
                        line = reader.readLine();
                        while (line != null) {

                            String[] movie_splited = line.split("_");

                            if(movie_splited[0].equals(str_choice)) {

                                movie_exist = true;
                                try{
                                    reader.close();
                                    File inputFile = new File("users.txt");
                                    reader = new BufferedReader(new FileReader(inputFile));

                                    // Create a temporary file to write the output
                                    File tempFile = new File("temp.txt");
                                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                                    // Read the input file line by line

                                    while ((line = reader.readLine()) != null) {
                                        // If the line doesn't match the line to be removed, write it to the output file

                                        String[] whole_string = line.split("@");
                                        String[] credentials = whole_string[0].split(",");
                                        String[] user_pass_arr = credentials[0].split("_");

                                        if (!user_pass_arr[0].equals(this.username)) {
                                            writer.write(line);
                                            writer.newLine();
                                        }
                                        else {

                                            writer.write(whole_string[0] + "," + movie_splited[0] + "_" + movie_splited[1] + "_" + movie_splited[2] + "_"+ movie_splited[3] + "_" + movie_splited[4] + "@");
                                            writer.newLine();
                                        }
                                    }

                                    // Close the input and output files
                                    reader.close();
                                    writer.close();

                                    // Delete the input file
                                    inputFile.delete();

                                    // Rename the temporary file to the input file
                                    tempFile.renameTo(inputFile);

                                } catch (IOException e){
                                    e.printStackTrace();
                                }
                            }

                            line = reader.readLine();
                        }

                    }

                    if((!show_exist)&&(!movie_exist)){
                        System.out.println("the name you entered doesn't exist among shows nor movies ");
                    }

                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
        public void viewFavorites() {
            // Implement view favorites logic here
            try {
                BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
                String line = reader.readLine();
                while (line != null) {

                    String[] whole_string = line.split("@");
                    String[] shows_user_str = whole_string[0].split(",");
                    String[] user_pass = shows_user_str[0].split("_");

                    if(user_pass[0].equals(this.username) && user_pass[1].equals(this.password)){

                        for(int i = 1; i < shows_user_str.length ; i++){

                            String[] show_splited = shows_user_str[i].split("_");
                            if(show_splited.length == 4) {

                                System.out.print(" name : " + show_splited[0] + " genre : " + show_splited[1] + " release : " + show_splited[2] + " rating : " + show_splited[3]);
                            }

                            if(show_splited.length == 5) {

                                System.out.print("name : " + show_splited[0] + " genre : " + show_splited[1] + " release : " + show_splited[2] + " length : " + show_splited[3] + " rating : " + show_splited[4]);
                            }
                            System.out.println("");
                        }
                    }

                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

//        public void logout() {
//            // Implement logout logic here
//            scanner = new Scanner(System.in);
//            System.out.println("please enter your username : ");
//            username = scanner.nextLine();
//
//            System.out.println("please enter your password : ");
//            password = scanner.nextLine();
//
//            try{
//                File inputFile = new File("users.txt");
//                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//
//                // Create a temporary file to write the output
//                File tempFile = new File("temp.txt");
//                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
//
//                // Read the input file line by line
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    // If the line doesn't match the line to be removed # write it to the output file
//                    String[] whole_string = line.split("@");
//                    String[] user_show_str = whole_string[0].split(",");
//                    String[] credentials = user_show_str[0].split("_");
//
//                    if (! (credentials[0].equals(username) && (credentials[1].equals(password))) ) {
//                        writer.write(line);
//                        writer.newLine();
//                    }
//                }
//
//                // Close the input and output files
//                reader.close();
//                writer.close();
//
//                // Delete the input file
//                inputFile.delete();
//
//                // Rename the temporary file to the input file
//                tempFile.renameTo(inputFile);
//
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//            user_run = false;
//        }
    }

    public static class Digikala {

        public User temp_user ;
        public Seller temp_seller;
        public Admin temp_admin;
        private Scanner scanner;
        private Integer choice;
        private String str_choice;
        private String username;
        private String password;
        private String shop_name;



        public Digikala(){

            this.scanner = new Scanner(System.in);
            this.temp_user = new User("" , "");
            this.temp_seller = new Seller("" , "" , "");
            this.temp_admin = new Admin("" , "");
            main_menu();
        }

        public void main_menu(){

            while(run){

                System.out.println("1. create account as costumer ");
                System.out.println("2. login as costumer ");
                System.out.println("3. create account as seller ");
                System.out.println("4. login as seller ");
                System.out.println("5. login as admin ");
                System.out.println("6. exit ");


                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createAccount("users.txt");
                        break;

                    case 2:
                        login("users.txt");
                        break;

                    case 3:
                        createAccount("sellers.txt");
                        break;

                    case 4:
                        login("sellers.txt");
                        break;

                    case 5:
//                        list_shows_movies();
                        login("admins.txt");
                        break;

                    case 6:
                        run = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }


        }

        public void user_menu(){

            while(user_run) {

                System.out.println("1. logout");
                System.out.println("2. search product by category ");
                System.out.println("3. charge wallet ");
                System.out.println("4. check request for fund");
                System.out.println("5. list cart products");
                System.out.println("6. buy whats inside your cart ");
                System.out.println("7. exit ");


                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        logout("users.txt");
                        break;

                    case 2:

                        System.out.println("available products are : car , motor , book , phone ");
                        System.out.print("please choose your product by typing the name : ");
                        str_choice = scanner.nextLine();
                        list_products(str_choice);

                        System.out.print("if you wish to buy one the items , write 1 otherwise 0 ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("enter the name of the product and name of the shop");
                        String product_name = scanner.nextLine();
                        shop_name = scanner.nextLine();
                        temp_user.find_product(product_name , shop_name);
                        temp_seller.remove_product(product_name , shop_name);
                        break;
//
                    case 3:
                        System.out.print("how much do you want to charge you wallet");
                        String requested_fund = scanner.nextLine();
                        temp_admin.writeFundRequest(username , requested_fund);
                        break;
//
                    case 4:
                        boolean ok = temp_user.IsFundAccepted();
                        if (ok){
                            temp_user.write_wallet_fund();
                        }
                        break;

                    case 5:
                        temp_user.view_cart();
                        System.out.print("do you wish to remove products from your cart ? (y/n)");
                        str_choice = scanner.nextLine();
                        while(! str_choice.equals("n")) {
                            product_name = scanner.nextLine();
                            temp_user.remove_product(product_name);
                            System.out.print("do you wish to remove products from your cart ? (y/n)");
                            str_choice = scanner.nextLine();
                        }
                        break;

                    case 6:
                        int cost = 0;
                        if(temp_user.IsPurchaseAccepted()) {
                            System.out.println("the purchase is accepted and you are buying the products");
                            cost = temp_user.purchaseSumCost();
                            temp_user.buy(cost);
                        }
                        else{
                            System.out.println("purchase is not accepted so making a request");
                            if (temp_user.can_pay()) {
                                System.out.println("the user can pay ");
                                temp_admin.writePurchaseRequest(temp_user.username, temp_user.password, cost);
                            }
                        }
                        break;

                    case 7:
                        user_run = false;
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }

        }

        public void seller_menu(){

            while(seller_run) {

                System.out.println("1. logout");
                System.out.println("2. add products");
                System.out.println("7. exit ");


                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        logout("sellers.txt");
                        break;

                    case 2:
                        temp_seller.add_products();
                        break;

//                    case 3:
//                        temp_user.searchByGenre();
//                        break;
//
//                    case 4:
//                        temp_user.searchByReleaseYear();
//                        break;

                    case 5:
//                        list_shows_movies();
                        System.out.println(temp_user.username);
                        temp_user.pick_favorite();
                        break;

                    case 6:
                        temp_user.viewFavorites();
                        break;

                    case 7:
                        seller_run = false;
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }

        }
//
        public void admin_menu(){

            while(admin_run) {

                System.out.println("1. check all requests ");
                System.out.println("2. check all fund requests ");
                System.out.println("3. check all transaction requests ");
                System.out.println("4. exit ");


                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        temp_admin.checkRequests();
                        break;

                    case 2:
                        temp_admin.check_fund_request();
                        break;

                    case 3:
                        temp_admin.check_transaction_request();
                        break;

                    case 4:
                        admin_run = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }

        }
//        public void new_movie(){
//
//            Movie movie = new Movie(name , genre , release , length , rating);
//            System.out.println("how many new movies do you want to add : ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            for(int i= 0 ; i < choice ; i++){
//
//                name = scanner.nextLine();
//                genre = scanner.nextLine();
//                release = scanner.nextLine();
//                length = scanner.nextLine();
//                rating = scanner.nextLine();
//                 movie = new Movie(name , genre , release , length , rating);
//                addMovie(movie);
//
//            }
//
//        }
//
//        public void new_tvshow(){
//
//            TVShow tvshow;
//            System.out.println("how many new tv shows do you want to add : ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            for(int i= 0 ; i < choice ; i++){
//
//                name = scanner.nextLine();
//                genre = scanner.nextLine();
//                release = scanner.nextLine();
//                rating = scanner.nextLine();
//                tvshow = new TVShow(name , genre , release , rating);
//                addTVShow(tvshow);
//
//            }
//
//        }
//
//        public void addTVShow(TVShow tvshow){
//            // Implement add tv show logic here
//
//            try {
//                BufferedWriter writer = new BufferedWriter(new FileWriter("tvshows.txt", true));
//                writer.write( tvshow.name + "," + tvshow.genre + "," + tvshow.release + "," + tvshow.rating + "|" );
//                writer.newLine();
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public void addMovie(Movie movie){
//            // Implement add movie logic here
//
//            try {
//                BufferedWriter writer = new BufferedWriter(new FileWriter("movies.txt", true));
//                writer.write( movie.name + "," + movie.genre + "," + movie.release + "," + movie.length + "," + movie.rating + "|" );
//                writer.newLine();
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        public void createAccount(String file_name) {
            // Implement create account logic here

            System.out.println("please enter your username : ");
            username = scanner.nextLine();

            System.out.println("please enter your password : ");
            password = scanner.nextLine();

            if (file_name == "sellers.txt") {
                System.out.println("please enter your shop name: ");
                shop_name = scanner.nextLine();

            }
//            if (file_name == "users.txt") {
//                System.out.println("please enter your shop name: ");
//                shop_name = scanner.nextLine();
//
//            }

            if(!checkUserCredentials(username , password , file_name)){

                if (file_name == "sellers.txt") {

                    if(temp_admin.SellerIsAuthorized(username , shop_name)) {
                        writeSellerToFile(username, password, shop_name, file_name);
                    }
                    else{
                        System.out.println("Your account is not yet accepted , please wait to be authorized by the Admin");
                    }
                }
                else {
                    writeUserToFile(username, password, file_name);
                }
            }

            else {

                // user_menu();
            }
        }

        public boolean login(String file_name) {
            // Implement login logic here

            System.out.println("please enter your username : ");
            username = scanner.nextLine();

            System.out.println("please enter your password : ");
            password = scanner.nextLine();

            if (file_name == "sellers.txt") {
                System.out.println("please enter your shop name: ");
                shop_name = scanner.nextLine();
            }
            if (file_name == "sellers.txt") {
                temp_seller = new Seller(username , password , shop_name);
            }
            if (file_name == "users.txt") {
                temp_user = new User(username, password);
            }
            if (file_name == "admin.txt") {
                temp_admin = new Admin(username , password);
            }

            if(!checkUserCredentials(username , password ,file_name)){

                System.out.println("user with this username and password doesn't exist .");
            }

            else {
                if (file_name == "users.txt") {
                    user_run = true;
                    user_menu();
                }
                if (file_name == "sellers.txt") {
                    System.out.println(temp_seller.username);
                    seller_run = true;
                    seller_menu();
                }
                if(file_name== "admins.txt") {
                    System.out.println(temp_seller.username);
                    admin_run = true;
                    admin_menu();
                }

            }

            return false;
        }

        public void logout(String file_name) {
            // Implement logout logic here
            scanner = new Scanner(System.in);
            System.out.println("please enter your username : ");
            username = scanner.nextLine();

            System.out.println("please enter your password : ");
            password = scanner.nextLine();

            try{
                File inputFile = new File(file_name);
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Create a temporary file to write the output
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                // Read the input file line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line doesn't match the line to be removed # write it to the output file
                    String[] whole_string = line.split("@");
                    String[] user_show_str = whole_string[0].split(",");
                    String[] credentials = user_show_str[0].split("_");

                    if (! (credentials[0].equals(username) && (credentials[1].equals(password))) ) {
                        writer.write(line);
                        writer.newLine();
                    }
                }

                // Close the input and output files
                reader.close();
                writer.close();

                // Delete the input file
                inputFile.delete();

                // Rename the temporary file to the input file
                tempFile.renameTo(inputFile);

            } catch (IOException e){
                e.printStackTrace();
            }
            user_run = false;
        }

        public static boolean checkUserCredentials(String username, String password , String file_name) {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file_name));
                String line = reader.readLine();
                while (line != null) {
                    String[] credentials = line.split("#");
                    String[] user_pass_arr = credentials[0].split("_");
                    if (user_pass_arr[0].equals(username)) {
                        reader.close();
                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        // writing the user in the file
        public static void writeUserToFile(String username, String password , String file_name) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, true));
                writer.write(username + "_" + password + "#");
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void writeSellerToFile(String username, String password , String shop_name , String file_name) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, true));
//                writer.newLine();
                writer.write(username + "_" + password + "_" + shop_name + "#");
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void list_products( String product_name){

            try {
                BufferedReader reader = new BufferedReader(new FileReader("sellers.txt"));
                String line = reader.readLine();
                while (line != null) {

                    String[] whole_string = line.split("#");
                    String[] products = whole_string[0].split("@");
                    String[] user_pass_arr = products[0].split("_");
                    String shop_name = user_pass_arr[2];

                    for (int i = 1 ; i < products.length ; i ++ ) {

                        String[] details = products[i].split("_");
//                        System.out.println("category : " + details[0]);
//                        System.out.println("product name : " + product_name);

                        if (details[0].equals(product_name)){
//                            System.out.println("entered");

                            if ( product_name.equals("motor")) {
                                System.out.println("category : " + details[0] + "  ,    shop_name : " + shop_name + "  ,   name : " + details[1] + "  ,   price : " + details[2] + "  ,   quantity : " + details[3] + "  ,   torque : " + details[4] + "  ,   model : " + details[5]);
                            }
                            if ( product_name.equals("car")) {
                                System.out.println("categori=y : " + details[0] + "  ,    shop_name : " +shop_name + "  ,   name : " + details[1] + "  ,   price : " + details[2] + "  ,   quantity : " + details[3] + "  ,   torque : " + details[4] + "  ,   horse power : " + details[5]);
                            }
                            if ( product_name.equals("book")) {
                                System.out.println("category : " + details[0] + "  ,   shop_name : " +shop_name + "  ,   name : " + details[1] + "  ,   price : " + details[2] + "  ,   quantity : " + details[3] + "  ,   pages : " + details[4] + "  ,   genre : " + details[5]);
                            }
                            if ( product_name.equals("phone")) {
                                System.out.println("category : " + details[0] + "  ,   shop_name : "  +shop_name + "  ,   name : " + details[1] + "  ,   price : " + details[2] + "  ,   quantity : " + details[3] + "  ,   brand : " + details[4] + "  ,   model : " + details[5]);
                            }

                        }
                    }

                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public static void main(String[] args) {

        // Library library = new Library();
        // Menu menu = new Menu();
        Digikala net = new Digikala();


    }



}


