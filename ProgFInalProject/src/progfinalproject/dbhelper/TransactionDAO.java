/*
 * Transactions DAO class that perform CRUD operations
 */

package progfinalproject.dbhelper;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import progfinalproject.Interfaces.Transaction;
import progfinalproject.models.*;

/**
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class TransactionDAO implements Transaction{
    public boolean createTransaction(int toAccNum, int fromAccNum, String detail, double value) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            String query = "INSERT INTO TRANSACTIONS (TOACCOUNTID, FROMACCOUNTID, TRANSACTIONDETAIL, VALUE) VALUES (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            Statement createStmt = con.createStatement();
            Statement receiverStmt = con.createStatement();
            Statement senderStmt = con.createStatement();
            ResultSet createRs = createStmt.executeQuery("SELECT ACCOUNTID FROM ACCOUNTS");
            ResultSet receiverBalanceRs = receiverStmt.executeQuery("SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNTID=" + toAccNum);
            ResultSet senderBalanceRs = senderStmt.executeQuery("SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNTID=" + fromAccNum);
            Statement updateStmt = con.createStatement();

            List<Integer> idList = new ArrayList<>();
            while(createRs.next()) {
                idList.add(createRs.getInt("ACCOUNTID"));
            }

            if (!idList.contains(toAccNum) || !idList.contains(fromAccNum)) {
                return false;
            }

                pstmt.setInt(1, toAccNum);
                pstmt.setInt(2, fromAccNum);
                pstmt.setString(3, detail);
                pstmt.setDouble(4, value);
                pstmt.executeUpdate();

            if (senderBalanceRs.next()) {
                double currentBalance = senderBalanceRs.getDouble("BALANCE");
                if (currentBalance >= value) {
                    double total = currentBalance - value;
                    createStmt.executeUpdate("UPDATE ACCOUNTS SET BALANCE=" + total + " WHERE ACCOUNTID=" + fromAccNum);
                }
            }

            if (receiverBalanceRs.next()) {
                double currentBalance = receiverBalanceRs.getDouble("BALANCE");
                double total = currentBalance + value;
                updateStmt.executeUpdate("UPDATE ACCOUNTS SET BALANCE=" + total + " WHERE ACCOUNTID=" + toAccNum);
            }
            System.out.println("Transaction successfuly created ");
            return true;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB [" + e.getMessage() + "]");
        }
        return false;
    }

    public boolean readClientTransaction(int id) {
        List<TransactionsModel> transactionList = new ArrayList<>();
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS WHERE FROMACCOUNTID=" + id);

            while (rs.next()) {
                int tId = rs.getInt("TRANSACTIONID");
                int toAccId = rs.getInt("TOACCOUNTID");
                int fromAccId = rs.getInt("FROMACCOUNTID");
                String detail = rs.getString("TRANSACTIONDETAIL");
                double value = rs.getDouble("VALUE");
                transactionList.add(new TransactionsModel(tId, toAccId, fromAccId, detail, value));
            }
            String formattedString = transactionList.toString()
                    .replace(", ", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", ""); //removes the left bracket
            System.out.println(formattedString);
            return true;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB [" + e.getMessage() + "]");
        }
        return false;
    }
    public TransactionsModel readSingleTransaction(int id) {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS WHERE TRANSACTIONID=" + id);

            if (rs.next()) {
                int tId = rs.getInt("TRANSACTIONID");
                int toAccId = rs.getInt("TOACCOUNTID");
                int fromAccId = rs.getInt("FROMACCOUNTID");
                String detail = rs.getString("TRANSACTIONDETAIL");
                double value = rs.getDouble("VALUE");

                if (tId == id) {
                    return new TransactionsModel(tId, toAccId, fromAccId, detail, value);
                }
            } 
        } catch(Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
        }
        return null;
    }

    public boolean cancelTransaction(int id) {
        int toAccId = 0;
        int fromAccId = 0;
        double currentBalance = 0.0;
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            Statement senderStmt = con.createStatement();
            Statement receiverStmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS WHERE TRANSACTIONID=" + id);


            if (rs.next()) {
                toAccId = rs.getInt("TOACCOUNTID");
                fromAccId = rs.getInt("FROMACCOUNTID");
                currentBalance = rs.getDouble("VALUE");

                ResultSet receiverRs = stmt.executeQuery("SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNTID=" + fromAccId);
                if (receiverRs.next()) {
                    double receiverBal = receiverRs.getDouble("BALANCE");
                    double total = currentBalance + receiverBal;
                    receiverStmt.executeUpdate("UPDATE ACCOUNTS SET BALANCE=" + total + " WHERE ACCOUNTID=" + fromAccId);
                } else {
                    return false;
                }
                ResultSet senderRs = stmt.executeQuery("SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNTID=" + toAccId);
                if (senderRs.next()) {
                    double senderBal = senderRs.getDouble("BALANCE");
                    double total = senderBal - currentBalance;
                    senderStmt.executeUpdate("UPDATE ACCOUNTS SET BALANCE=" + total + " WHERE ACCOUNTID=" + toAccId);
                } else {
                    return false;
                }
            } else {
                return false;
            }
            stmt.executeUpdate("DELETE FROM TRANSACTIONS WHERE TRANSACTIONID=" + id);
            System.out.println("canceled transaction");
            return true;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB [" + e.getMessage() + "]");
        }
        return false;
    }

    public List<TransactionsModel> readAllTransaction() {
        List<TransactionsModel> transactionList = new ArrayList<>();
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS");

            while (rs.next()) {
                int tId = rs.getInt("TRANSACTIONID");
                int toAccId = rs.getInt("TOACCOUNTID");
                int fromAccId = rs.getInt("FROMACCOUNTID");
                String detail = rs.getString("TRANSACTIONDETAIL");
                double value = rs.getDouble("VALUE");
                transactionList.add(new TransactionsModel(tId, toAccId, fromAccId, detail, value));
            }
            String formattedString = transactionList.toString()
                    .replace(", ", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", ""); //removes the left bracket
            System.out.println(formattedString);
            return transactionList;
        } catch (Exception e) {
            System.out.println("Error Connecting to the DB [" + e.getMessage() + "]");
        }
        return null;
    }
}
