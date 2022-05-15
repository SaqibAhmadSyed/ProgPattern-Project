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
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class Menu {
    static int cId;

    public static void main(String[] args) throws Exception {

//        Locale[] availableLocales = Calendar.getAvailableLocales();
//        Locale locale1 = Locale.ENGLISH;
//        Locale locale2 = Locale.FRENCH;
//        DateFormat df = DateFormat.getDateInstance();

        BAMSController controller = new BAMSController();
        TellerModel teller = controller.getCredential();

        Scanner scan = new Scanner(System.in);
        menuPipeline(scan);
    }

    public static ResourceBundle I18N(Scanner scan) {
        Locale locale = new Locale("", "");

        System.out.println("Select 1 for english or 2 for french.");
        switch (scan.nextLine()) {
            case "1":
                locale = new Locale("en", "US");
                break;
            case "2":
                locale = new Locale("fr", "FR");
                break;
            default:
                System.out.println("select a valid choice.");
        }
        return ResourceBundle.getBundle("progfinalproject/MessagesBundle", locale);
    }

    public static void menuPipeline(Scanner scan) throws Exception {
        BAMSController controller = new BAMSController();
        ResourceBundle res = I18N(scan);


//        System.out.println("Welcome to B.A.M.S. or the \"Bank Account Management System\"\n");
//        System.out.println("Below, please enter the type of user you are.");
//        System.out.println("Enter 'T' for Teller, and 'C' for Client.");
//        System.out.println("Finally, if you would like to close the program, enter 'X'.");
        System.out.println(res.getString("greetings"));

        boolean validInput = false;
        while (!validInput) {
            char input = scan.next().charAt(0);

            switch (Character.toUpperCase(input)) {
                case 'T':
                    boolean isId = false;
                    boolean isPswd = false;
                    TellerModel teller = controller.getCredential();

                    System.out.println("Enter Id. To exit teller input, select 0");
                    while (isId != true) {
                        try {
                            String stringId = scan.next();
                            if (Integer.parseInt(stringId) == teller.getTellerId()) {
                                isId = true;
                            } else if (stringId.equals("0")) {
                                menuPipeline(scan);
                            } else {
                                System.out.println("id invalid, try again");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please input only numbers.");
                        }
                    }

                    System.out.println("Enter password. To exist teller input, select 0");
                    while (isPswd != true) {
                        String pswd = scan.next();
                        if (pswd.equals(teller.getPswd())) {
                            isPswd = true;
                        } else if (pswd.equals("0")) {
                            menuPipeline(scan);
                        } else {
                            System.out.println("wrong password, try again");
                        }
                    }
                    tellerPipeline(scan);
                    validInput = true;
                    break;
                case 'C':
                    boolean isCId = false;
                    System.out.println("Enter Client Id. To exit client input, select 0");
                    while (isCId != true) {
                        try {
                            String stringCId = scan.next();
                            ClientsModel client = controller.readClients(Integer.parseInt(stringCId));
                            if (client != null) {
                                cId = Integer.parseInt(stringCId);
                                isCId = true;
                            } else if (stringCId.equals("0")) {
                                menuPipeline(scan);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please input only numbers.");
                        }
                    }
                    clientPipeline(scan);
                    validInput = true;
                    break;
                case 'X':
                    System.exit(0);
                default:
                    System.out.println("Wrong input, Try again.");
            }
        }

    }

    public static void tellerPipeline(Scanner scan) throws Exception {
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
                System.out.println("\t5. Read Clients");
                System.out.println("\t6. Read Account");
                System.out.println("\t7. Read Client Transaction\n");
                System.out.print("Select: ");
                int selection = scan.nextInt();

                switch (selection) {
                    case 0:
                        menuPipeline(scan);
                        active = false;
                    case 1:
                        con.fetchAllClients();
                        break;
                    case 2:
                        con.fetchAllAccounts();
                        break;
                    case 3:
                        con.fetchAllTransactions();
                        break;
                    case 4:
                        System.out.println("Enter transaction id");
                        boolean isTId = false;
                        while (isTId != true) {
                            try {
                                String stringTrId = scan.next();
                                if (con.readSingleTransaction(Integer.parseInt(stringTrId)) != null) {
                                    con.cancelTransaction(Integer.parseInt(stringTrId));
                                    isTId = true;
                                } else {
                                    System.out.println("id does not exist, Try again.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please input only numbers.");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Enter client id");
                        boolean isCId = false;
                        while (isCId != true) {
                            try {
                                String stringCId = scan.next();
                                if (con.readClients(Integer.parseInt(stringCId)) != null) {
                                    System.out.println(con.readClients(Integer.parseInt(stringCId)));
                                    isCId = true;
                                } else {
                                    System.out.println("id does not exist. Try again.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please input only numbers.");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Enter account id");
                        boolean isAId = false;
                        while (isAId != true) {
                            try {
                                String stringAId = scan.next();
                                if (con.readAccount(Integer.parseInt(stringAId)) != null) {
                                    System.out.println(con.readAccount(Integer.parseInt(stringAId)));
                                    isAId = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please input only numbers.");
                            }
                        }
                        break;
                    case 7:
                        boolean isTrId = false;
                        System.out.println("Enter Client id");
                        while (isTrId != true) {
                            try {
                                String stringTrId = scan.next();
                                if (con.readAccount(Integer.parseInt(stringTrId)) != null) {
                                    System.out.println(con.readClientTransaction(Integer.parseInt(stringTrId)));
                                    isTrId = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please input only numbers.");
                            }
                        }
                        break;
                    default:
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid value. Please try again.");
                scan.nextLine();
            } // Handles SQLite exceptions such as not respecting the PK constraint
            catch (org.sqlite.SQLiteException e) {
                System.out.println(e.toString());
                System.out.println("There was a Database Error. Please try again with different values");
                scan.nextLine();
            }
        }
    }

    public static void clientPipeline(Scanner scan) throws Exception {
        BAMSController con = new BAMSController();
        boolean active = true;
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
            } catch (InputMismatchException ime) {
                System.out.println("Invalid value. Please try again.");
                scan.nextLine();
            } // Handles SQLite exceptions such as not respecting the PK constraint
            catch (org.sqlite.SQLiteException e) {
                System.out.println(e.toString());
                System.out.println("There was a Database Error. Please try again with different values");
                scan.nextLine();
            }
        }
    }
}