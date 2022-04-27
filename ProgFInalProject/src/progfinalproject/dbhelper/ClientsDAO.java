/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.dbhelper;

import progfinalproject.models.ClientsModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
        
/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class ClientsDAO {
    
    public ClientsModel getClients(int id){
       try {
            Connection con = BAMSDBConnection.createConnection();
            PreparedStatement stm  
                    = con.prepareStatement("SELECT clientId, firstName,  lastName, identification, address FROM clients WHERE id=?");
            stm.setInt(0, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new ClientsModel(rs);
            }
            return null;
        } catch(Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return null;
    }
    
    public static boolean saveOrUpdate(ClientsModel c) {
        if (c==null) {
            return false;
        }
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            PreparedStatement stm  
                    = con.prepareStatement("INSERT INTO clients (clientId, firstName,  lastName, identification, address) VALUES (?, ?, ?, ?) " +
                    " ON CONFLICT (id) DO UPDATE SET firstName=?, lastName=?, identification=? ");
            stm.setInt(1, c.getClientId());
            stm.setString(2, c.getFirstName());
            stm.setString(3, c.getLastName());
            stm.setString(4, c.getIdentification());
            
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return false;        
    }
}