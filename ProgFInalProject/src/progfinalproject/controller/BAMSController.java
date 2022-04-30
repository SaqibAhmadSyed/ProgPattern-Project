
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
    public void fetchAllAccounts() {
        aDAO.readAllAccounts();
    }
    public void fetchAllTransactions() {
        tDAO.readAllTransaction();
    }

   //INSERT STATEMENTS THAT MAY BE USED LATER ON
    /*
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (1, 'Max',    'Johnson', 'passport', '5th avenue');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (2, 'John',   'Neil',    'passport', '77th avenue');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (3, 'Elly',   'Parker',  'passport', '88th avenue');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (4, 'Maggie', 'Bell',    'passport', '1st street');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (5, 'Pat',    'Stuart',  'passport', '97th street');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (6, 'Nick',   'McDuff',  'passport', '101st street');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (7, 'Maxxie', 'Maxxie',  'passport', '8e rue');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (8, 'JJ', 	   'Abrams', 'passport', '92nd avenue');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (9, 'Leeroy', 'Jenkins', 'passport', '66th avenue');
    INSERT INTO CLIENTS ( clientId, firstName,  lastName, identification, address) VALUES (10, 'Max',   'Johnson', 'passport', '34th street');

    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (1, 1, 2, 'Payment', '2000');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (2, 3, 2, 'Payment', '1000');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (3, 5, 1, 'Payment', '3000');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (4, 3, 6, 'Payment', '400');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (5, 1, 2, 'Payment', '7000');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (6, 2, 1, 'Payment', '900');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (7, 8, 4, 'Payment', '200');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (8, 7, 10, 'Payment', '80');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (9, 6, 7, 'Payment', '90');
    INSERT INTO TRANSACTIONS (TransactionId, ToAccountNum, FromAccountNum,TransactionDetail, Value) VALUES (10, 9, 8, 'Payment', '1900');
    */
    
    
    
}
