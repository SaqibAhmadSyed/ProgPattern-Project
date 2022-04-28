
package progfinalproject.dbhelper;

import progfinalproject.Interfaces.Clients;
import progfinalproject.models.ClientsModel;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
        
/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class ClientsDAO implements Clients {
    
    public ClientsDAO() {
        
    }
    public void createClient(int id, String fName, String lName, String identification, String address) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            String query = "INSERT INTO CLIENTS (CLIENTID, FIRSTNAME, LASTNAME, IDENTIFICATION, ADDRESS)" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, fName);
            stmt.setString(3, lName);
            stmt.setString(4, identification);
            stmt.setString(5, address);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
    }

    public ClientsModel readClients(int id) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            PreparedStatement stm
                    = con.prepareStatement("SELECT CLIENTID, FIRSTNAME, LASTNAME, IDENTIFICATION, ADDRESS FROM CLIENTS WHERE CLIENTID=?");
            stm.setInt(1, id);
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
    
    public void updateClientIdentification(int id, String identification) {
        
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE CLIENTS SET IDENTIFICATION='" + identification + "';");
            System.out.println("Update Successful!");
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
    }

    public void updateClientAddress(int id, String address) {
        
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE CLIENTS SET ADDRESS='" + address + "';");
            System.out.println("Update Successful!");
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
    }
    
    public Map<Integer, String> readAllClients(){
        Map<Integer, String> clientMap = new HashMap<>();

        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTS");
            
            while (rs.next()) {
                int id = rs.getInt("CLIENTID");
                String fName = rs.getString("FIRSTNAME");
                String lName = rs.getString("LASTNAME");
                String identification = rs.getString("IDENTIFICATION");
                String address = rs.getString("ADDRESS");

                clientMap.put(id, fName + lName + identification + address + "\n");
            }
        } catch (Exception e) { 
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return clientMap;
    }
}