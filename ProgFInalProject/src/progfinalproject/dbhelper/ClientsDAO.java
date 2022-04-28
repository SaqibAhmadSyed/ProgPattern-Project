
package progfinalproject.dbhelper;

import progfinalproject.Interfaces.Clients;
import progfinalproject.models.ClientsModel;
import java.sql.*;
        
/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class ClientsDAO implements Clients {
    public boolean createClient(int id, String fName, String lName, String identification, String address) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            String query = "INSERT INTO CLIENTS (FIRSTNAME, LASTNAME, IDENTIFICATION, ADDRESS)" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, fName);
            stmt.setString(3, lName);
            stmt.setString(4, identification);
            stmt.setString(5, address);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
            return false;
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

    public boolean updateClient(ClientsModel c) {
        if (c==null) {
            return false;
        }
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            PreparedStatement stm
                    = con.prepareStatement("INSERT INTO CLIENTS (CLIENTID, FIRSTNAME,  LASTNAME, IDENTIFICATION, ADDRESS) VALUES (?, ?, ?, ?, ?) " +
                    " ON CONFLICT (CLIENTID) DO UPDATE SET FIRSTNAME=?, LASTNAME=?, IDENTIFICATION=? ");
            stm.setInt(1, c.getClientId());
            stm.setString(2, c.getFirstName());
            stm.setString(3, c.getLastName());
            stm.setString(4, c.getIdentification());
            stm.setString(5, c.getAddress());

            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return false;
    }
}