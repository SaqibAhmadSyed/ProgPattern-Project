
package progfinalproject.controller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import progfinalproject.Interfaces.Accounts;
import progfinalproject.dbhelper.AccountsDAO;
import progfinalproject.dbhelper.BAMSDBConnection;
import progfinalproject.dbhelper.ClientsDAO;
import progfinalproject.dbhelper.TransactionDAO;
import progfinalproject.models.AccountsModel;
import progfinalproject.models.ClientsModel;
import progfinalproject.models.TransactionsModel;


/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class BAMSController {
    int index = -1;
    Connection con;
    List<ClientsModel> clients;
    List<TransactionsModel> transactions;
    List<AccountsModel> accounts;


    ClientsDAO cDAO = new ClientsDAO();
    AccountsDAO aDAO = new AccountsDAO();
    TransactionDAO tDAO = new TransactionDAO();


    public BAMSController() {
    }
    /**
     * Creates the Clients table
     * @throws Exception creates exception message if database failed to be created
     */
    public void createClientsTable() throws Exception {
        Connection con = BAMSDBConnection.getSingleBAMSCon();
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE CLIENTS"
                + "(CLIENTID       INTEGER  PRIMARY KEY AUTOINCREMENT,"
                + "FIRSTNAME       TEXT                 NOT NULL,"
                + "LASTNAME        TEXT                 NOT NULL,"
                + "IDENTIFICATION  TEXT                 NOT NULL,"
                + "ADDRESS         TEXT                 NOT NULL)";

        stmt.executeUpdate("DROP TABLE if exists CLIENTS;");
        stmt.executeUpdate(query);
        System.out.println("Table CLIENTS created...");
    }

    /**
     * Creates the Transactions table
     * @throws Exception creates exception message if database failed to be created
     */
    public void createTransactionsTable() throws Exception {
        Connection con = BAMSDBConnection.getSingleBAMSCon();
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE TRANSACTIONS"
                + "(TRANSACTIONID       INTEGER  PRIMARY KEY    AUTOINCREMENT,"
                + "TOACCOUNTID        INT                     NOT NULL,"
                + "FROMACCOUNTID       INT                     NOT NULL,"
                + "TRANSACTIONDETAIL    TEXT                    NOT NULL,"
                + "VALUE                DECIMAL                 NOT NULL)";

        stmt.executeUpdate("DROP TABLE if exists TRANSACTIONS;");
        stmt.executeUpdate(query);
        System.out.println("Table TRANSACTIONS created...");
    }

    /**
     * Creates Accounts table
     * @throws Exception creates exception message if database failed to be created
     */
    public void createAccountsTable() throws Exception {
        Connection con = BAMSDBConnection.getSingleBAMSCon();
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE ACCOUNTS"
                + "(ACCOUNTID           INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "CLIENTID             INT                 NOT NULL,"
                + "ACCOUNTTYPE          TEXT                NOT NULL,"
                + "OPENDATE             DATE                NOT NULL,"
                + "BALANCE              DECIMAL             NOT NULL,"
                + "ISACTIVE             BIT                 NOT NULL,"
                + "FOREIGN KEY(CLIENTID) REFERENCES CLIENTS(CLIENTID))";

        stmt.executeUpdate("DROP TABLE if exists ACCOUNTS;");
        stmt.executeUpdate(query);
        System.out.println("Table ACCOUNTS created...");
    }

    public void fetchAllClients() {
        cDAO.readAllClients();
    }

    public void createClient(String fName, String lName, String identification, String address) {
        cDAO.createClient(fName, lName, identification, address);
    }

    public ClientsModel readClients(int id) {
        return cDAO.readClients(id);
    }

    public void updateClientIdentification(int id, String identification) {
        cDAO.updateClientIdentification(id, identification);
    }

    public void updateClientAddress(int id, String address) {
        cDAO.updateClientAddress(id, address);
    }

    public void fetchAllAccounts() {
        aDAO.readAllAccounts();
    }

    public void createAccount(int cId, String accountType) {
        aDAO.createAccount(cId, accountType);
    }

    public AccountsModel readAccount(int id) {
        return aDAO.readAccount(id);
    }

    public void deactivateAccount(int id) {
        aDAO.deactivateAccount(id);
    }

    public void addBalance(int id, double depositAmount) {
        aDAO.addBalance(id, depositAmount);
    }

    public void fetchAllTransactions() {
        tDAO.readAllTransaction();
    }

    public boolean createTransaction(int toAccNum, int fromAccNum, String detail, double value) {
        return tDAO.createTransaction(toAccNum, fromAccNum, detail, value);
    }

    public void readClientTransaction(int id) {
        tDAO.readClientTransaction(id);
    }

    public TransactionsModel readSingleTransaction(int id) {
        return tDAO.readSingleTransaction(id);
    }

    public void cancelTransaction(int id) {
        tDAO.cancelTransaction(id);
    }
}
