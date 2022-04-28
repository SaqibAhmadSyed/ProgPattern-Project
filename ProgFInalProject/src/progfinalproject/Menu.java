/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class Menu {
    public static void main(String[] args) throws Exception{
    
        Locale[] availableLocales = Calendar.getAvailableLocales();
        Locale locale1 = Locale.ENGLISH;
        Locale locale2 = Locale.FRENCH;
        DateFormat df = DateFormat.getDateInstance();
        
        Scanner scan = new Scanner(System.in);
        menuPipeline(scan);
    }
    
    public static void menuPipeline(Scanner scan) throws Exception{
        boolean validInput = false;
        System.out.println("Welcome to B.A.M.S. or the \"Bank Account Management System\"\n");
        try {
            while (!validInput) {
                System.out.println("Below, please enter the type of user you are.");
                System.out.println("Enter 'T' for Teller, and 'C' for Client.");
                System.out.println("Finally, if you would like to close the program, enter 'X'.");

                char input = scan.next().charAt(0);

                switch (Character.toUpperCase(input)) {
                    case 'T':
                       tellerPipeline(scan);
                        validInput = true;
                        break;
                    case 'C':
                        clientPipeline(scan);
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
        
        boolean active = true;
        System.out.println("Welcome to the Teller system.");
        System.out.println("Here you'll find all the functions you may need.");
        System.out.println("If at any point you want to return to the welcome menu, simply type '0' in the input field\n");
        while (active) {
            try {
                System.out.println("Please select a function (enter the corresponding number):");
                System.out.println("\t1.  ");
                System.out.println("\t2.  ");
                System.out.println("\t3. ");
                System.out.println("\t4.  ");
                System.out.println("\t5.  \n");
                System.out.print("Select: ");
                int selection = scan.nextInt();

                switch (selection) {
                    case 0:
                        menuPipeline(scan);
                        active = false;
                    case 1:

                        break;
                    case 2:
                      
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        break;
                    case 5:
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
        
        boolean active = true;
        System.out.println("Welcome to the Client system.");
        System.out.println("Here you'll find all the functions you may need.");
        System.out.println("If at any point you want to return to the welcome menu, simply type '0' in the input field\n");
        while (active) {
            try {
                System.out.println("Please select a function (enter the corresponding number):");
                System.out.println("\t1.  ");
                System.out.println("\t2.  ");
                System.out.println("\t3. ");
                System.out.println("\t4.  ");
                System.out.println("\t5.  \n");
                System.out.print("Select: ");
                int selection = scan.nextInt();

                switch (selection) {
                    case 0:
                        menuPipeline(scan);
                        active = false;
                    case 1:

                        break;
                    case 2:
                      
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        break;
                    case 5:
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