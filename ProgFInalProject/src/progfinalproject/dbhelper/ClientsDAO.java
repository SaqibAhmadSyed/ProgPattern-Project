
package progfinalproject.dbhelper;

import progfinalproject.Interfaces.Clients;
import progfinalproject.models.ClientsModel;

import java.sql.*;
import java.util.*;
        
/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class ClientsDAO implements Clients{

    public boolean createClient(String fName, String lName, String identification, String address) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            String query = "INSERT INTO CLIENTS (FIRSTNAME, LASTNAME, IDENTIFICATION, ADDRESS)" +
                    "VALUES (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, identification);
            stmt.setString(4, address);
            stmt.executeUpdate();
            System.out.println("Client created successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
            return false;
        }

    }

    public ClientsModel readClients(int id) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTS WHERE CLIENTID=" + id);

            if (rs.next()) {
                int cId = rs.getInt("CLIENTID");
                String fName = rs.getString("FIRSTNAME");
                String lName = rs.getString("LASTNAME");
                String identification = rs.getString("IDENTIFICATION");
                String address = rs.getString("ADDRESS");

                return new ClientsModel(cId, fName, lName, identification, address);
            } else {
                return null;
            }
        } catch(Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return null;
    }
    
    public boolean updateClientIdentification(int id, String identification) {
        
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE CLIENTS SET IDENTIFICATION='" + identification + "'" + "WHERE CLIENTID=" + id);
            return true;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
            return false;
        }
    }

    public boolean updateClientAddress(int id, String address) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE CLIENTS SET ADDRESS='" + address + "'" + "WHERE CLIENTID=" + id);
            System.out.println("Update Successful!");
            return true;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
            return false;
        }
    }
    
    public List<ClientsModel> readAllClients(){
        List<ClientsModel> clientList = new ArrayList<>();

        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTS");

            while (rs.next()) {
                int cId = rs.getInt("CLIENTID");
                String fName = rs.getString("FIRSTNAME");
                String lName = rs.getString("LASTNAME");
                String identification = rs.getString("IDENTIFICATION");
                String address = rs.getString("ADDRESS");

                clientList.add(new ClientsModel(cId, fName, lName, identification, address));
            }
            clientList.sort((ClientsModel c1, ClientsModel c2) -> c1.getFirstName().
                    compareTo(c2.getFirstName()));
            String formattedString = clientList.toString()
                .replace(", ", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", ""); //remove the left bracket
            System.out.println(formattedString);
            return clientList;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return null;
    }
}