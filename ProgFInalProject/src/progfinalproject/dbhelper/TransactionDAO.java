/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.dbhelper;

import java.sql.*;
import progfinalproject.models.*;
/**
 *
 * @author xsyed
 */
public class TransactionDAO {

    public static TransactionsModel getTransaction(int id) throws Exception{
        Connection c = BAMSDBConnection.getSingleBAMSCon();
        String sql = "SELECT TRANSACTIONID, TOACCOUNTUNM, FROMACCOUNTNUM,"
                + "TRANSACTIONDETAIL, VALUE FROM TRANSACTIONS";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new TransactionsModel(rs);
        } else {
            System.out.println("Error retrieving transactions");
            return null;
        }
    }
    
    public static boolean saveOrUpdateTransactions (TransactionsModel transaction) throws Exception{
        if (transaction == null) {
            return true;
        }
        Connection c = BAMSDBConnection.getSingleBAMSCon();
        String sql = "INSERT INTO TRANSACTIONS (TRANSACTIONID, TOACCOUNTNUM,"
                + " FROMACCOUNTNUM, TRANSACTIONDETAIL, VALUE)"
                + " VALUES (?, ?, ?, ?, ?))"
                + "ON CONFLICT(TRANSACTIONID) DO UPDATE SET"
                + "TOACCOUNTNUM=?, FROMACCOUNTNUM=? TRANSACTIONDETAIL=?,"
                + " VALUE=?;";
        
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, transaction.getTransactionId());
        stmt.setInt(2, transaction.getToAccountNumber());
        stmt.setInt(3, transaction.getFromAccountNumber());
        stmt.setString(4, transaction.getTransactiondetails());
        stmt.setInt(5, transaction.getValue());
        stmt.executeUpdate();
        return true;
    }
}
