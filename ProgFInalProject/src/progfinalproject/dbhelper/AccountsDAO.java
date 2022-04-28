package progfinalproject.dbhelper;
import progfinalproject.Interfaces.Accounts;
import progfinalproject.models.ClientsModel;
import progfinalproject.models.AccountsModel;
import java.sql.*;
import java.util.ArrayList;

public class AccountsDAO {
//    public boolean createClient(int id, String fName, String lName, String identification, String address) {
//        try {
//            Connection con = BAMSDBConnection.createConnection();
//            String query = "INSERT INTO CLIENTS (FIRSTNAME, LASTNAME, IDENTIFICATION, ADDRESS)" +
//                    "VALUES (?,?,?,?,?)";
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setInt(1, id);
//            stmt.setString(2, fName);
//            stmt.setString(3, lName);
//            stmt.setString(4, identification);
//            stmt.setString(5, address);
//            stmt.executeUpdate();
//            return true;
//        } catch (Exception e) {
//            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
//            return false;
//        }
//    }

    public void createAccount(int accId, int cId, String accountType, int balance, boolean isActive) {

        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            String query = "INSERT INTO ACCOUNTS (ACCOUNTNUM, CLIENTID, ACCOUNTTYPE, OPENDATE, BALANCE, ISACTIVE) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            java.sql.Date date = getCurrentDatetime();
            stmt.setInt(1, accId);
            stmt.setInt(2, cId);
            stmt.setString(3, accountType);
            stmt.setDate(4, date);
            stmt.setInt(5, balance);
            stmt.setBoolean(6, isActive);
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
    }



    public java.sql.Date getCurrentDatetime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
}
