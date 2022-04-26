/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.models;

import progfinalproject.models.ClientsModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class TransactionsModel {
    private int transactionId;
    private int toAccountNumber;
    private String transactiondetails;
    private int value;
    private List<ClientsModel> clients;

    public TransactionsModel(ResultSet rs) {
        try{
        this.transactionId = rs.getInt("transactionId");
        this.toAccountNumber = rs.getInt("toAccountNum");
        this.transactiondetails = rs.getString("transactiondetails");
        this.value = rs.getInt("value");
        }catch(SQLException e){
             System.out.println("Error creating transaction model [" + e.getMessage() + "]");
        }
        clients = new ArrayList<>();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(int toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getTransactiondetails() {
        return transactiondetails;
    }

    public void setTransactiondetails(String transactiondetails) {
        this.transactiondetails = transactiondetails;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<ClientsModel> getClents(){
        return clients;
    }
    
    public void addClient(ClientsModel client){
        clients.add(client);
    }
    
    @Override
    public String toString() {
       String w = "Transactions=" + transactionId + ", toAccountNumber=" + toAccountNumber + ", transactiondetails=" + transactiondetails + ", value=" + value + '}';
       for(ClientsModel c: clients){
           w+="\n   "+w.toString();
       }
       return w;
    }
}
