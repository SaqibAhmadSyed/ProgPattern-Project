/*
 * Main controller class which takes every method from the DAO's and puts in the controller so that it can be accessed in the view method
 */
package progfinalproject.controller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import progfinalproject.dbhelper.*;
import progfinalproject.models.AccountsModel;
import progfinalproject.models.ClientsModel;
import progfinalproject.models.TellerModel;
import progfinalproject.models.TransactionsModel;


/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class BAMSController {
//    Connection con;
    List<ClientsModel> clients;
    List<TransactionsModel> transactions;
    List<AccountsModel> accounts;


    ClientsDAO cDAO = new ClientsDAO();
    AccountsDAO aDAO = new AccountsDAO();
    TransactionDAO tDAO = new TransactionDAO();
    TellerDAO tellerDAO = new TellerDAO();

    public List<ClientsModel> fetchAllClients() {
        return cDAO.readAllClients();
    }

    public boolean createClient(String fName, String lName, String identification, String address) {
        return cDAO.createClient(fName, lName, identification, address);
    }

    public ClientsModel readClients(int id) {
        return cDAO.readClients(id);
    }

    public boolean updateClientIdentification(int id, String identification) {
        return cDAO.updateClientIdentification(id, identification);
    }

    public boolean updateClientAddress(int id, String address) {
        return cDAO.updateClientAddress(id, address);
    }

    public List<AccountsModel> fetchAllAccounts() {
        return aDAO.readAllAccounts();
    }

    public boolean createAccount(int cId, String accountType) {
        return aDAO.createAccount(cId, accountType);
    }

    public AccountsModel readAccount(int id) {
        return aDAO.readAccount(id);
    }

    public boolean deactivateAccount(int id) {
        return aDAO.deactivateAccount(id);
    }

    public List<TransactionsModel> fetchAllTransactions() {
        return tDAO.readAllTransaction();
    }

    public boolean createTransaction(int toAccNum, int fromAccNum, String detail, double value) {
        return tDAO.createTransaction(toAccNum, fromAccNum, detail, value);
    }

    public boolean readClientTransaction(int id) {
        return tDAO.readClientTransaction(id);
    }

    public TransactionsModel readSingleTransaction(int id) {
        return tDAO.readSingleTransaction(id);
    }

    public boolean cancelTransaction(int id) {
        return tDAO.cancelTransaction(id);
    }

    public TellerModel getCredential() {
        return tellerDAO.getCredential();
    }
}
