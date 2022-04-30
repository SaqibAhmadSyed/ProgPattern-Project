/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author darth
 */
public class BAMSControllerTest {
    
    BAMSController test;            
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
        test = new BAMSController();
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
        instance.fetchAllClients();
        
    }

    /**
     * Test of fetchAllAccounts method, of class BAMSController.
     */
    @Test
    public void testFetchAllAccounts() {
        System.out.println("fetchAllAccounts");
        BAMSController instance = new BAMSController();
        instance.fetchAllAccounts();
        
    }

    /**
     * Test of fetchAllTransactions method, of class BAMSController.
     */
    @Test
    public void testFetchAllTransactions() {
        System.out.println("fetchAllTransactions");
        BAMSController instance = new BAMSController();
        instance.fetchAllTransactions();
        
    }
    
}
