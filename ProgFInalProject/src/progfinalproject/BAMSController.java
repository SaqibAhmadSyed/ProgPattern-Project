/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Saqib Syed
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
    
    /**
     * Creates the Clients table
     * @throws Exception 
     */
    public void createClientsTable() throws Exception {
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
     * @throws Exception 
     */
    public void createTransactionsTable() throws Exception {
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE TRANSACTIONS"
                + "(TRANSACTIONID       INT  PRIMARY KEY    NOT NULL,"
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
     * @throws Exception 
     */
    public void createAccountsTable() throws Exception {
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE ACCOUNTS"
                + "(ACCOUNTNUM          INT  PRIMARY KEY    AUTOINCREMENT,"
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
     * @throws Exception 
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
    
    
}
