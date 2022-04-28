/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.controller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import progfinalproject.dbhelper.BAMSDBConnection;
import progfinalproject.models.ClientsModel;
import progfinalproject.views.ClientsView;
import progfinalproject.models.TransactionsModel;
import progfinalproject.views.TransactionsView;

/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class BAMSController {
    // CLIENT
    List<ClientsModel> cModel;
    ClientsView cView;
    //TRANSACTION
    List<TransactionsModel> tModels;
    TransactionsView tview;
    //CONNECTION
    Connection con;

    public BAMSController(List<ClientsModel> cModel, ClientsView cView)
    throws Exception{
        this.cModel = cModel;
        this.cView = cView;
        con = BAMSDBConnection.getSingleBAMSCon();
    }

    public BAMSController(List<TransactionsModel> tModels,
    TransactionsView tview) throws Exception{
        this.tModels = tModels;
        this.tview = tview;
        con = BAMSDBConnection.getSingleBAMSCon();
    }

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
                + "(CLIENTID       INT  PRIMARY KEY    NOT NULL,"
                + "FIRSTNAME       TEXT                NOT NULL,"
                + "LASTNAME        TEXT                NOT NULL,"
                + "IDENTIFICATION  TEXT                NOT NULL,"
                + "ADDRESS         TEXT                NOT NULL)";
        
        stmt.executeUpdate("DROP TABLE if exists CLIENTS;");
        stmt.executeUpdate(query);
        System.out.println("Table CLIENTS created...");
    }
    
    /**
     * Creates the Transactions table
     * @throws Exception creates exception message if database failed to be created
     */
    public void createTransactionsTable() throws Exception {
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE TRANSACTIONS"
                + "(TRANSACTIONID       INT  PRIMARY KEY    AUTOINCREMENT,"
                + "TOACCOUNTNUM         INT                 NOT NULL,"
                + "FROMACCOUNTNUM       INT                 NOT NULL,"
                + "TRANSACTIONDETAIL    TEXT                NOT NULL,"
                + "VALUE                INT                 NOT NULL)";
        
        stmt.executeUpdate("DROP TABLE if exists TRANSACTIONS;");
        stmt.executeUpdate(query);
        System.out.println("Table TRANSACTIONS created...");
    }
    
    /**
     * Creates Accounts table
     * @throws Exception creates exception message if database failed to be created
     */
    public void createAccountsTable() throws Exception {
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE ACCOUNTS"
                + "(ACCOUNTNUM          INT  PRIMARY KEY    NOT NULL,"
                + "OPENDATE             TEXT                NOT NULL,"
                + "BALANCE              INT                 NOT NULL,"
                + "ISACTIVE             BIT                 NOT NULL,"
                + "VALUE                INT                 NOT NULL)"
                + "FOREIGN KEY(CLIENTID) REFERENCES CLIENTS(CLIENTSID),"
                + "FOREIGN KEY(TRANSACTIONID) REFERENCES TRANSACTIONS"
                + "(TRANSACTIONID))";
        
        stmt.executeUpdate("DROP TABLE if exists TRANSACTIONS;");
        stmt.executeUpdate(query);
        System.out.println("Table TRANSACTIONS created...");
    }
    
    /**
     * Adds a client in a database
     * @param c Client Model object
     * @throws Exception creates exception message if database is inaccessible
     */
    public void addClients(ClientsModel c) throws Exception {
        Statement stmt = con.createStatement();
        String query = "INSERT INTO CLIENTS (CLIENTID, FIRSTNAME, LASTNAME,"
                + " IDENTIFICATION, ADDRESS) "
                + "VALUES (" + c.getClientId() + ", '" + c.getFirstName() +
                "', '" + c.getLastName() + "', '" + c.getIdentification() +
                "','" + c.getAddress() + "');";
        stmt.executeUpdate(query);
        System.out.println("Client Added");
    }
    
    /**
     * Creates Teller table
     * @throws Exception 
     */
    public void createTellerTable() throws Exception {
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE TELLER";
        
        stmt.executeUpdate("DROP TABLE if exists TELLER;");
        stmt.executeUpdate(query);
        System.out.println("Table TELLER created...");
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
