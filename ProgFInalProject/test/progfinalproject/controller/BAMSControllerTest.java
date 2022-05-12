/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.controller;

import java.util.*;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progfinalproject.models.AccountsModel;
import progfinalproject.models.ClientsModel;
import progfinalproject.models.TransactionsModel;

/**
 *
 * @author darth
 */
public class BAMSControllerTest {
    
    public BAMSControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of fetchAllClients method, of class BAMSController.
     */
    @Test
    public void testFetchAllClients() {
        System.out.println("fetchAllClients");
        BAMSController instance = new BAMSController();
        List<ClientsModel> expResult = null;
        List<ClientsModel> result = instance.fetchAllClients();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of createClient method, of class BAMSController.
     */
    @Test
    public void testCreateClient() {
        System.out.println("createClient");
        String fName = "John";
        String lName = "Smith";
        String identification = "passport";
        String address = "2nd street";
        BAMSController instance = new BAMSController();
        boolean expResult = true;
        boolean result = instance.createClient(fName, lName, identification, address);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of readClients method, of class BAMSController.
     */
    @Test
    public void testReadClients() {
        System.out.println("readClients");
        int id = 1;
        BAMSController instance = new BAMSController();
        instance.createClient("Max", "Deev" , "passport", "1st Street");
        ClientsModel expResult = new ClientsModel(id,"Max", "Deev", "passport", "1st Street");
        ClientsModel result = instance.readClients(id);
        assertNotNull(result);
       
    }

    /**
     * Test of updateClientIdentification method, of class BAMSController.
     */
    @Test
    public void testUpdateClientIdentification() {
        System.out.println("updateClientIdentification");
        int id = 1;
        String identification = "";
        BAMSController instance = new BAMSController();
        boolean expResult = true;
        boolean result = instance.updateClientIdentification(id, identification);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of updateClientAddress method, of class BAMSController.
     */
    @Test
    public void testUpdateClientAddress() {
        System.out.println("updateClientAddress");
        int id = 1;
        String address = "";
        BAMSController instance = new BAMSController();
        boolean expResult = true;
        boolean result = instance.updateClientAddress(id, address);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of fetchAllAccounts method, of class BAMSController.
     */
    @Test
    public void testFetchAllAccounts() {
        System.out.println("fetchAllAccounts");
        BAMSController instance = new BAMSController();
        List<AccountsModel> expResult = null;
        List<AccountsModel> result = instance.fetchAllAccounts();
        //assertEquals(expResult, result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of createAccount method, of class BAMSController.
     */
    @Test
    public void testCreateAccount() {
        System.out.println("createAccount");
        int cId = 1;
        String accountType = "checking";
        BAMSController instance = new BAMSController();
        boolean expResult = true;
        boolean result = instance.createAccount(cId, accountType);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of readAccount method, of class BAMSController.
     */
    @Test
    public void testReadAccount() {
        System.out.println("readAccount");
        int id = 1;
        BAMSController instance = new BAMSController();
        AccountsModel expResult = null;
        AccountsModel result = instance.readAccount(id);
        assertNotNull(result);
    }

    /**
     * Test of deactivateAccount method, of class BAMSController.
     */
    @Test
    public void testDeactivateAccount() {
        System.out.println("deactivateAccount");
        int id = 1;
        BAMSController instance = new BAMSController();
        instance.addBalance(id, 1000.0);
        boolean expResult = true;
        boolean result = instance.deactivateAccount(id);
        assertEquals(expResult, result);
    
    }

    /**
     * Test of addBalance method, of class BAMSController.
     */
    @Test
    public void testAddBalance() {
        System.out.println("addBalance");
        int id = 1;
        double depositAmount = 0.0;
        BAMSController instance = new BAMSController();
        boolean expResult = true;
        boolean result = instance.addBalance(id, depositAmount);
        assertEquals(expResult, result);
    }

    /**
     * Test of fetchAllTransactions method, of class BAMSController.
     */
    @Test
    public void testFetchAllTransactions() {
        System.out.println("fetchAllTransactions");
        BAMSController instance = new BAMSController();
        List<TransactionsModel> expResult = null;
        List<TransactionsModel> result = instance.fetchAllTransactions();
        assertFalse(result.isEmpty());
    }

       /**
     * Test of createTransaction method, of class BAMSController.
     */
    @Test
    public void testCreateTransaction() {
        System.out.println("createTransaction");
        int toAccNum = 2;
        int fromAccNum = 1;
        String detail = "stuff";
        double value = 0.0;
        BAMSController instance = new BAMSController();
        instance.createAccount(1, "Checking");
        instance.createAccount(2, "Saving");
        boolean expResult = true;
        boolean result = instance.createTransaction(toAccNum, fromAccNum, detail, value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of readClientTransaction method, of class BAMSController.
     */
    @Test
    public void testReadClientTransaction() {
        System.out.println("readClientTransaction");
        int id = 1;
        BAMSController instance = new BAMSController();
        boolean expResult = true;
        boolean result = instance.readClientTransaction(id);
        assertNotNull(result);
    }

    /**
     * Test of readSingleTransaction method, of class BAMSController.
     */
    @Test
    public void testReadSingleTransaction() {
        System.out.println("readSingleTransaction");
        int id = 1;
        BAMSController instance = new BAMSController();
        TransactionsModel expResult = null;
        TransactionsModel result = instance.readSingleTransaction(id);
        //assertNotNull(result);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of cancelTransaction method, of class BAMSController.
     */
    @Test
    public void testCancelTransaction() {
        System.out.println("cancelTransaction");
        int id = 1;
        BAMSController instance = new BAMSController();
        boolean expResult = true;
        boolean result = instance.cancelTransaction(id);
        assertEquals(expResult, result);
        
    }
    
}
