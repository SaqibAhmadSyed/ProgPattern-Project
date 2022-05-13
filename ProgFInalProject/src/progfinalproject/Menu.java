/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject;

import progfinalproject.controller.BAMSController;
import progfinalproject.models.ClientsModel;
import progfinalproject.models.TellerModel;

import java.text.DateFormat;
import java.util.*;


/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class Menu {
    static int cId;
    public static void main(String[] args) throws Exception{
    
//        Locale[] availableLocales = Calendar.getAvailableLocales();
//        Locale locale1 = Locale.ENGLISH;
//        Locale locale2 = Locale.FRENCH;
//        DateFormat df = DateFormat.getDateInstance();

        BAMSController controller = new BAMSController();
        TellerModel teller = controller.getCredential();
        
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to B.A.M.S. or the \"Bank Account Management System\"\n");
        System.out.println("Below, please enter the type of user you are.");
        System.out.println("Enter 'T' for Teller, and 'C' for Client.");
        System.out.println("Finally, if you would like to close the program, enter 'X'.");


        menuPipeline(scan);
    }
    public static void I18N(String[] args){
        String language;
        String country;
        if (args.length != 2) {
        language = new String("en");
        country = new String("US");
        } else {
        language = new String(args[0]);
        country = new String(args[1]);
        }
        Locale currentLocale;
        ResourceBundle messages;
        currentLocale = new Locale(language, country);
        messages = ResourceBundle.getBundle("", currentLocale);
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));

    }
    public static void menuPipeline(Scanner scan) throws Exception{
        BAMSController controller = new BAMSController();
        TellerModel teller = controller.getCredential();

        boolean validInput = false;

        try {
            while (!validInput) {
                char input = scan.next().charAt(0);

                switch (Character.toUpperCase(input)) {
                    case 'T':
                        boolean isId = false;
                        boolean isPswd = false;
                        boolean isThrown = false;
                        System.out.println("Enter id");

                            try {
                                int id = scan.nextInt();
                                while(isId != true) {
                                    if (id != teller.getTellerId()) {
                                        System.out.println("Wrong id. Try again");
                                        id = scan.nextInt();
                                    } else {
                                        isId = true;
                                        break;
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("input only numbers. Try again");
                                menuPipeline(scan);
                            }

                        System.out.println("Enter Password");
                        String pswd = scan.next();
                        while (isPswd != true) {
                            if (!pswd.equals(teller.getPswd())) {
                                System.out.println("Wrong password. Try again");
                                pswd = scan.next();
                            } else {
                                System.out.println("Login Successful \n");
                                isPswd = true;
                            }
                        }
                        tellerPipeline(scan);
                        validInput = true;
                        break;

                    case 'C':
                        controller.createClient("xesus", "christ", "goated", "no adress");
                        controller.createAccount(1, "Checking");
                        boolean isCId = false;
                        System.out.println("Enter Client Id");
                        cId = scan.nextInt();
                        while (isCId != true) {
                            ClientsModel client = controller.readClients(cId);
                            if (client == null) {
                                cId = scan.nextInt();
                            } else {
                                isCId = true;
                            }
                        }
                            clientPipeline(scan);
                            System.out.println(cId);
                        validInput = true;
                        break;
                    case 'X':
                        System.exit(0);
                    default:
                        System.out.println("Invalid Value. Please try again");
                }
            }
        }
        catch(InputMismatchException e) {System.out.println("Invalid Value. Please try again");}
    }

    public static void tellerPipeline(Scanner scan) throws Exception{
        BAMSController con = new BAMSController();
        boolean active = true;
        System.out.println("Welcome to the Teller system.");
        System.out.println("Here you'll find all the functions you may need.");
        System.out.println("If at any point you want to return to the welcome menu, simply type '0' in the input field\n");
        while (active) {
            try {
                System.out.println("Please select a function (enter the corresponding number):");
                System.out.println("\t1. Fetch all Clients ");
                System.out.println("\t2. Fetch all Accounts");
                System.out.println("\t3. Fetch all Transactions");
                System.out.println("\t4. Cancel Transaction ");
                System.out.println("\t5. Read Clients\n");
                System.out.println("\t6. Read Account");
                System.out.println("\t7. Read Client Transaction\n");
                System.out.print("Select: ");
                int selection = scan.nextInt();

                switch (selection) {
                    case 0:
                        menuPipeline(scan);
                        active = false;
                    case 1:
                        System.out.println(con.fetchAllClients());
                        break;
                    case 2:
                        System.out.println(con.fetchAllAccounts());
                        break;
                    case 3:
                        System.out.println(con.fetchAllTransactions());
                        break;
                    case 4:
                        con.cancelTransaction(cId);
                        break;
                    case 5:
                        System.out.println(con.readClients(cId));
                        break;
                    case 6:
                        System.out.println(con.readAccount(cId));
                        break;
                    case 7:
                        con.readClientTransaction(cId);
                        break;    
                    default:
                }
            } 
            catch (InputMismatchException ime) {
                System.out.println("Invalid value. Please try again.");
                scan.nextLine();
            } // Handles SQLite exceptions such as not respecting the PK constraint
            catch(org.sqlite.SQLiteException e) {
                System.out.println(e.toString());
                System.out.println("There was a Database Error. Please try again with different values");
                scan.nextLine();
            }
        }
    }
    
    public static void clientPipeline(Scanner scan) throws Exception{
        BAMSController con = new BAMSController();
        boolean active = true;
        System.out.println(cId);
        System.out.println("Welcome to the Client system " + con.readClients(cId).getFirstName() + " .");
        System.out.println("Here you'll find all the functions you may need.");
        System.out.println("If at any point you want to return to the welcome menu, simply type '0' in the input field\n");
        while (active) {
            try {
                System.out.println("Please select a function (enter the corresponding number):");
                System.out.println("\t1. Read Client ");
                System.out.println("\t2. Read Client Transaction ");
//                System.out.println("\t3. Read Single Transaction");
                System.out.println("\t3. Read Account");
                
                System.out.print("Select: ");
                int selection = scan.nextInt();
                System.out.println("");

                switch (selection) {
                    case 0:
                        menuPipeline(scan);
                        active = false;
                        break;
                    case 1:
                        System.out.println(con.readClients(cId));
                        break;
                    case 2:
                        con.readClientTransaction(cId);
                        break;
                    case 3:
                        System.out.println(con.readAccount(cId));
                        break;
                    default:
                }
            } 
            catch (InputMismatchException ime) {
                System.out.println("Invalid value. Please try again.");
                scan.nextLine();
            } // Handles SQLite exceptions such as not respecting the PK constraint
            catch(org.sqlite.SQLiteException e) {
                System.out.println(e.toString());
                System.out.println("There was a Database Error. Please try again with different values");
                scan.nextLine();
            }
        }
    }
}