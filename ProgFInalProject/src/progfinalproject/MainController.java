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
import progfinalproject.dbhelper.BAMSDBConnection;
import progfinalproject.dbhelper.ClientsDAO;
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
        ClientsDAO cDAO = new ClientsDAO();
        controller.createClientsTable();
        
        cDAO.createClient(1, "jesus", "christ", "goated", "no adress");
        cDAO.createClient(2, "jesus", "christ", "goated", "no adress");
        cDAO.updateClientIdentification(1, "12345");
        System.out.println();


        Map<Integer, String> map = cDAO.readAllClients();

    for (Entry<Integer, String> entry : map.entrySet()) {
        System.out.println(entry.getKey() + " = " + entry.getValue());
    }

    }
}
