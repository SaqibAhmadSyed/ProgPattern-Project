package progfinalproject.dbhelper;

import progfinalproject.Interfaces.Accounts;
import progfinalproject.models.ClientsModel;
import progfinalproject.models.AccountsModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountsDAO implements Accounts{
    public boolean createAccount(int cId, String accountType) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy"); //formatted date
        LocalDate localDate = LocalDate.now();
        String dateString = dtf.format(localDate);
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            String query = "INSERT INTO ACCOUNTS (CLIENTID, ACCOUNTTYPE, OPENDATE, BALANCE, ISACTIVE) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, cId);
            pstmt.setString(2, accountType);
            pstmt.setString(3, dateString);
            pstmt.setDouble(4, 0.00); //default 0.00$
            pstmt.setBoolean(5, true); //default is true because account is active
            pstmt.executeUpdate();
            System.out.println("Account successfuly created");
            return true;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
            return false;
        }
    }

    public AccountsModel readAccount(int id) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE ACCOUNTID=" + id);

            if (rs.next()) {
                int aId = rs.getInt("ACCOUNTID");
                int cId = rs.getInt("CLIENTID");
                String accType = rs.getString("ACCOUNTTYPE");
                String date = rs.getString("OPENDATE");
                double balance = rs.getDouble("BALANCE");
                boolean isActive = rs.getBoolean("ISACTIVE");

                if (aId == id) {
                    return new AccountsModel(aId, cId, accType, date, balance, isActive);
                }
            } else {
                System.out.println("id does not exist");
                return null;
            }
            return null;
        } catch(Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return null;
    }

    public boolean deactivateAccount(int id) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT BALANCE, ISACTIVE FROM ACCOUNTS");

            if (rs.next()) {
                double balance = rs.getDouble("BALANCE");
                if (balance == 0.00) {
                    System.out.println("Cannot deactivate account with a balance of 0");
                    return false;
                } else {
                    stmt.executeUpdate("UPDATE ACCOUNTS SET ISACTIVE='FALSE'");
                    System.out.println("successfuly deactivated");
                    return true;
                }
            } else {
                System.out.println("id does not exist");
                return false;
            }
        } catch(Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return false;
    }

    public boolean addBalance(int id, double depositAmount) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNTID=" + id);

            if (rs.next()) {
                double currentAmount = rs.getDouble("BALANCE");
                double newAmount =  readAccount(id).getBalance() + depositAmount;
                stmt.executeUpdate("UPDATE ACCOUNTS SET BALANCE=" + newAmount + " WHERE ACCOUNTID=" + id);
                System.out.println("Successfully added " + depositAmount + "$ in your account");
                return true;
            } else {
                System.out.println("id does not exist");
                return false;
            }

        } catch(Exception e) {
        System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return false;
    }
    public List<AccountsModel> readAllAccounts() {
        List<AccountsModel> accountList = new ArrayList<>();

        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS");

            while (rs.next()) {
                int aId = rs.getInt("ACCOUNTID");
                int cId = rs.getInt("CLIENTID");
                String accType = rs.getString("ACCOUNTTYPE");
                String date = rs.getString("OPENDATE");
                double balance = rs.getDouble("BALANCE");
                boolean isActive = rs.getBoolean("ISACTIVE");

                accountList.add(new AccountsModel(aId, cId, accType, date, balance, isActive));
            }
            String formattedString = accountList.toString()
                    .replace(", ", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", ""); //remove the left bracket
            System.out.println(formattedString);
            return accountList;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
            return null;
        }
    }
}
