/*
 * Main view clas to print everything out as well as take user inputs.
 */
package progfinalproject;

import progfinalproject.controller.BAMSController;
import progfinalproject.models.AccountsModel;
import progfinalproject.models.ClientsModel;
import progfinalproject.models.TellerModel;

import java.util.*;

/**
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */

//Factory to create english or french resource bundle according to the users wish
class Factory {
    /**
     * @param choice user input
     * @return ressource bundle to use for all classes
     */
    public static ResourceBundle I18N(String choice) {
        Locale locale = new Locale("", "");
        Scanner scan = new Scanner(System.in);
        boolean isSelected = false;

        switch (choice) {
            case "1":
                locale = new Locale("en", "US");
                isSelected = true;
            case "2":
                locale = new Locale("fr", "FR");
                isSelected = true;
        }
        return ResourceBundle.getBundle("progfinalproject/MessagesBundle", locale);
    }

    /**
     * @param choice user input
     * @return ressource bundle to be accessible for all class
     */
    public static ResourceBundle getRes(String choice) {
        return I18N(choice);
    }
}

/**
 * main class which prints everything out
 */
public class Menu {

    static String i18nChoice;
    static int cId; //client id

    public static void main(String[] args) throws Exception {
        BAMSController controller = new BAMSController();
        TellerModel teller = controller.getCredential();//get username and password for teller
        Scanner scan = new Scanner(System.in);

        System.out.println("Select 1 for english or 2 for french.");

        i18nChoice = scan.next();
        ResourceBundle res = Factory.getRes(i18nChoice);

        menuPipeline(scan, res);
    }

    /**
     * @param scan scanner
     * @param res  ressource bundle from factory class
     * @throws Exception
     */
    public static void menuPipeline(Scanner scan, ResourceBundle res) throws Exception {
        BAMSController controller = new BAMSController();

        //welcome message
        System.out.println(res.getString("key1"));
        System.out.println(res.getString("key2"));
        System.out.println(res.getString("key3"));
        System.out.println(res.getString("key4"));

        boolean validInput = false;
        while (!validInput) {
            char input = scan.next().charAt(0);

            //switch case between teller and client pipeline
            switch (Character.toUpperCase(input)) {
                case 'T':
                    boolean isId = false; // breaks loop if the id exists in the teller table
                    boolean isPswd = false; // breaks loop if the password exists in the teller table
                    TellerModel teller = controller.getCredential();

                    System.out.println(res.getString("key5"));
                    while (isId != true) {
                        try {
                            String stringId = scan.next();
                            if (Integer.parseInt(stringId) == teller.getTellerId()) {
                                isId = true;
                            } else if (stringId.equals("0")) {
                                menuPipeline(scan, res); // returns to the main menu
                            } else {
                                System.out.println(res.getString("key6"));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(res.getString("key7")); //user must input numbers to prevent catching errors
                        }
                    }

                    System.out.println(res.getString("key29"));
                    while (isPswd != true) {
                        String pswd = scan.next();
                        if (pswd.equals(teller.getPswd())) {
                            isPswd = true;
                        } else if (pswd.equals("0")) {
                            menuPipeline(scan, res);
                        } else {
                            System.out.println(res.getString("key8"));
                        }
                    }
                    tellerPipeline(scan, res);
                    validInput = true;
                    break;
                case 'C':
                    boolean isCId = false;
                    System.out.println(res.getString("key9"));
                    while (isCId != true) {
                        try {
                            String stringCId = scan.next();
                            ClientsModel client = controller.readClients(Integer.parseInt(stringCId)); // reads client based on the inputed id
                            if (client != null) {
                                cId = Integer.parseInt(stringCId); //stores the inputted id from the client to use it in the client pipeline so that he can only view his own data
                                isCId = true;
                            } else if (stringCId.equals("0")) {
                                menuPipeline(scan, res);
                            } else {
                                System.out.println(res.getString("key6"));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(res.getString("key7"));
                        }
                    }
                    clientPipeline(scan, res);
                    validInput = true;
                    break;
                case 'X':
                    System.exit(0);
                default:
                    System.out.println(res.getString("key30")); //goes to default if user inputted wrongly
            }
        }
    }

    /**
     * main method for the teller pipeline. The user will be able to execute every method available to the teller here
     *
     * @param scan scanner
     * @param res  ressource bundle from factory class
     * @throws Exception
     */
    public static void tellerPipeline(Scanner scan, ResourceBundle res) throws Exception {
        BAMSController con = new BAMSController();
        res = Factory.getRes(i18nChoice);

        System.out.println(res.getString("key10"));
        System.out.println(res.getString("key11"));
        System.out.println(res.getString("key12"));
        while (true) {
            try {
                System.out.println(res.getString("key13"));
                System.out.println("\t1. " + res.getString("key14"));
                System.out.println("\t2. " + res.getString("key15"));
                System.out.println("\t3. " + res.getString("key16"));
                System.out.println("\t4. " + res.getString("key17"));
                System.out.println("\t5. " + res.getString("key18"));
                System.out.println("\t6. " + res.getString("key19"));
                System.out.println("\t7. " + res.getString("key20"));
                System.out.println("\t8. " + res.getString("key31"));
                System.out.println("\t9. " + res.getString("key32"));
                System.out.println("\t10. " + res.getString("key33"));
                System.out.println("\t11. " + res.getString("key34"));
                System.out.println("\t12. " + res.getString("key45"));
                System.out.println("\t13. " + res.getString("key46") + "\n");
                System.out.print(res.getString("key21"));
                int selection = scan.nextInt();

                // user input which allows the user to select between admin operations
                switch (selection) {
                    case 0:
                        menuPipeline(scan, res);
                        break;
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
                        System.out.println(res.getString("key22"));
                        boolean isTId = false;
                        while (isTId != true) {
                            try {
                                String stringTrId = scan.next();
                                if (con.readSingleTransaction(Integer.parseInt(stringTrId)) != null) {
                                    con.cancelTransaction(Integer.parseInt(stringTrId));
                                    isTId = true;
                                } else if (stringTrId.equals("0")) {
                                    tellerPipeline(scan, res);
                                } else {
                                    System.out.println(res.getString("key6"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        break;
                    case 5:
                        System.out.println(res.getString("key23"));
                        boolean isCId = false;
                        while (isCId != true) {
                            try {
                                String stringCId = scan.next();
                                if (con.readClients(Integer.parseInt(stringCId)) != null) {
                                    System.out.println(con.readClients(Integer.parseInt(stringCId)));
                                    isCId = true;
                                } else if (stringCId.equals("0")) {
                                    tellerPipeline(scan, res);
                                } else {
                                    System.out.println(res.getString("key6"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        break;
                    case 6:
                        System.out.println(res.getString("key24"));
                        boolean isAId = false;
                        while (isAId != true) {
                            try {
                                String stringAId = scan.next();
                                if (con.readAccount(Integer.parseInt(stringAId)) != null) {
                                    System.out.println(con.readAccount(Integer.parseInt(stringAId)));
                                    isAId = true;
                                } else if (stringAId.equals("0")) {
                                    tellerPipeline(scan, res);
                                } else {
                                    System.out.println(res.getString("6"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        break;
                    case 7:
                        boolean isTrId = false;
                        System.out.println(res.getString("key23"));
                        while (isTrId != true) {
                            try {
                                String stringTrId = scan.next();
                                if (con.readAccount(Integer.parseInt(stringTrId)) != null) {
                                    con.readClientTransaction(Integer.parseInt(stringTrId));
                                    isTrId = true;
                                } else if (stringTrId.equals("0")) {
                                    tellerPipeline(scan, res);
                                    tellerPipeline(scan, res);
                                } else {
                                    System.out.println(res.getString("key6"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        break;
                    case 8:
                        boolean isAccId = false;
                        System.out.println(res.getString("key24"));
                        while (isAccId != true) {
                            try {
                                String stringAccId = scan.next();
                                if (con.readAccount(Integer.parseInt(stringAccId)) != null) {
                                    if (!con.deactivateAccount(Integer.parseInt(stringAccId))) {
                                        System.out.println(res.getString("key49"));
                                        tellerPipeline(scan, res);
                                    } else {
                                        con.deactivateAccount(Integer.parseInt(stringAccId));
                                        System.out.println(res.getString("key50"));
                                        isAccId = true;
                                    }

                                } else if (stringAccId.equals("0")) {
                                    tellerPipeline(scan, res);
                                    tellerPipeline(scan, res);
                                } else {
                                    System.out.println(res.getString("key6"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        break;
                    case 9:
                        System.out.print(res.getString("key35") + " ");
                        String fName = scan.next();
                        System.out.print(res.getString("key36") + " ");
                        String lName = scan.next();
                        System.out.print(res.getString("key37") + " ");
                        String identification = scan.next();
                        System.out.print(res.getString("key38") + " ");
                        String address = scan.next();
                        con.createClient(fName, lName, identification, address);
                        break;
                    case 10:
                        boolean isClientCreated = false;
                        System.out.println(res.getString("key9"));
                        while (isClientCreated != true) {
                            try {
                                String clientId = scan.next();
                                ClientsModel client = con.readClients(Integer.parseInt(clientId));
                                if (client != null) {
                                    System.out.println(res.getString("key39") + " ");
                                    while (isClientCreated != true) {
                                        String accType = scan.next().toLowerCase();
                                        //checks user input if its checking or saving. Otherwise, the user won't be able to input an account type
                                        if (accType.equals("checking") || accType.equals("savings")) {
                                            con.createAccount(Integer.parseInt(clientId), accType);
                                            isClientCreated = true;
                                        } else if (accType.equals("0")) {
                                            tellerPipeline(scan, res);
                                        } else {
                                            System.out.println(res.getString("key30"));
                                        }
                                    }
                                    isClientCreated = true;
                                } else if (clientId.equals("0")) {
                                    tellerPipeline(scan, res);
                                } else {
                                    System.out.println(res.getString("key6"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        break;
                    case 11:
                        boolean isDestinationId = false;
                        boolean isSenderId = false;
                        boolean isValue = false;

                        String destinationId = "";
                        String senderId = "";
                        double value = 0.0;
                        while (isDestinationId != true) {
                            System.out.println(res.getString("key40"));
                            try {
                                destinationId = scan.next();
                                AccountsModel account = con.readAccount(Integer.parseInt(destinationId));
                                if (account != null) {
                                    isDestinationId = true;
                                } else if (destinationId.equals("0")) {
                                    tellerPipeline(scan, res);
                                } else {
                                    System.out.println(res.getString("key6"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }

                        while (isSenderId != true) {
                            System.out.println(res.getString("key41"));
                            try {
                                senderId = scan.next();
                                AccountsModel account = con.readAccount(Integer.parseInt(senderId));
                                if (account != null) {
                                    isSenderId = true;
                                } else if (senderId.equals("0")) {
                                    tellerPipeline(scan, res);
                                } else {
                                    System.out.println(res.getString("key6"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        System.out.println(res.getString("key42"));
                        String details = scan.next();

                        System.out.println(res.getString("key43"));
                        while (isValue != true) {
                            try {
                                String stringValue = scan.next();
                                if (stringValue.equals("0")) {
                                    tellerPipeline(scan, res);
                                } else {
                                    value = Double.parseDouble(stringValue);
                                    isValue = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        //if there is an error creating transactions, returns to the teller menu
                        if (!con.createTransaction(Integer.parseInt(destinationId), Integer.parseInt(senderId), details, value)) {
                            System.out.println(res.getString("key44"));
                            tellerPipeline(scan, res);
                        }
                        break;
                    case 12:
                        boolean isCId2 = false;
                        System.out.println(res.getString("key9"));
                        while (isCId2 != true) {
                            try {
                                String stringCId = scan.next();
                                ClientsModel client = con.readClients(Integer.parseInt(stringCId));
                                if (client != null) {
                                    System.out.println(res.getString("key37"));
                                    String updateIdentification = scan.next();
                                    if (updateIdentification.equals("0")) {
                                        tellerPipeline(scan, res);
                                    } else {
                                        con.updateClientIdentification(Integer.parseInt(stringCId), updateIdentification);
                                        isCId2 = true;
                                    }
                                } else if (stringCId.equals("0")) {
                                    tellerPipeline(scan, res);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        System.out.println(res.getString("key48"));
                        break;
                    case 13:
                        boolean isCId3 = false;
                        System.out.print(res.getString("key9"));
                        while (isCId3 != true) {
                            try {
                                String stringCId = scan.next();
                                ClientsModel client = con.readClients(Integer.parseInt(stringCId));
                                if (client != null) {
                                    System.out.print(res.getString("key38"));
                                    String updateAddress = scan.next();
                                    if (updateAddress.equals("0")) {
                                        tellerPipeline(scan, res);
                                    } else {
                                        con.updateClientIdentification(Integer.parseInt(stringCId), updateAddress);
                                        isCId3 = true;
                                    }
                                } else if (stringCId.equals("0")) {
                                    tellerPipeline(scan, res);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(res.getString("key7"));
                            }
                        }
                        System.out.println(res.getString("key48"));
                        break;
                    default:
                }
            } catch (InputMismatchException ime) {
                System.out.println(res.getString("key30"));
                scan.nextLine();
            }
        }
    }

    /**
     * main method for the client pipeline. After entering client id, the user will be able to view his/her informations
     *
     * @param scan scanner
     * @param res  ressource bundle from the factory class
     * @throws Exception
     */
    public static void clientPipeline(Scanner scan, ResourceBundle res) throws Exception {
        BAMSController con = new BAMSController();
        boolean active = true;
        res = Factory.getRes(i18nChoice);
        //prints name of the client
        System.out.println(res.getString("key25") + " " + con.readClients(cId).getFirstName() + ".");
        System.out.println(res.getString("key11"));
        System.out.println(res.getString("key12"));
        while (active) {
            try {
                System.out.println(res.getString("key13"));
                System.out.println("\t1. " + res.getString("key26"));
                System.out.println("\t2. " + res.getString("key27"));
                System.out.println("\t3. " + res.getString("key28"));

                System.out.print(res.getString("key21"));
                int selection = scan.nextInt();
                System.out.println("");

                switch (selection) {
                    case 0:
                        menuPipeline(scan, res);
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
                System.out.println(res.getString("key30"));
                scan.nextLine();
            }
        }
    }
}