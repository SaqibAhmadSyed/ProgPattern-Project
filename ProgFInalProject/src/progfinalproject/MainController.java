/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import progfinalproject.controller.BAMSController;
import progfinalproject.dbhelper.AccountsDAO;
import progfinalproject.dbhelper.BAMSDBConnection;
import progfinalproject.dbhelper.ClientsDAO;
import progfinalproject.dbhelper.TransactionDAO;
import progfinalproject.models.ClientsModel;

/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class MainController {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws Exception{
        BAMSController controller = new BAMSController();

//        ClientsModel cM = new ClientsModel();
//        ClientsDAO cDAO = new ClientsDAO();
//        AccountsDAO a = new AccountsDAO();
        TransactionDAO t = new TransactionDAO();
        controller.createClientsTable();
        controller.createAccountsTable();
        controller.createTransactionsTable();
//
//        cDAO.createClient("xesus", "christ", "goated", "no adress");
//        cDAO.createClient("jesus", "christ", "goated", "no adress");
////        cDAO.updateClientAddress(1, "12345");
////        cDAO.updateClientIdentification(2, "not goated");
////        System.out.println(cDAO.readClients(1));
////        cDAO.readAllClients();
//        a.createAccount(1, "Checking");
//        a.createAccount(2, "Saving");
////        System.out.println(a.readAccount(1));
//        a.addBalance(2, 20.23);
//        a.addBalance(1, 100.00);
//        a.readAllAccounts();
//        t.createTransaction(2, 1, "nigtwerk", 0.00);
//        t.createTransaction(2, 1, "fuck java", 0.00);
//        t.createTransaction(1, 2, "for my love quandale", 20.00);
//
//        System.out.println(a.readAccount(1));
//        System.out.println(a.readAccount(2));
//        t.cancelTransaction(3);
//        System.out.println(a.readAccount(1));
//        System.out.println(a.readAccount(2));
//        t.readClientTransaction(1);
//        t.readSingleTransaction(3);
//        t.readAllTransaction();
//        controller.createAccount(1, "Checking");
//        controller.createAccount(2, "Saving");
//        System.out.println(controller.createTransaction(2, 1, "nigtwerk", 0.00));
//        controller.fetchAllAccounts();
//        controller.fetchAllTransactions();
//        controller.fetchAllClients();
            System.out.println(controller.readSingleTransaction(1));
    }
}
