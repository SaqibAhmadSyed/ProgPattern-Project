/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class AccountsModel {
    private int accountNum;
    private int clientId;
    private String accType;
    private String openDate;
    private int balance;
    private boolean IsActive;
    private List<ClientsModel> clients;

    public AccountsModel(ResultSet rs) {
        try{
            this.accountNum = rs.getInt("accountNum");
            this.clientId = rs.getInt("clientId");
            this.accType = rs.getString("accType");
            this.openDate = rs.getString("openDate");
            this.balance = rs.getInt("balance");
            this.IsActive = rs.getBoolean("IsActive");
        }catch(SQLException e){
            System.out.println("Error creating accounts model [" + e.getMessage() + "]");
        }
        clients = new ArrayList<>();
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }

    public List<ClientsModel> getClents(){ return clients; }

    public void addClient(ClientsModel client){ clients.add(client); }

    @Override
    public String toString() {
        String w = "AccountsModel=" + "accountNum=" + accountNum + ", clientId=" + clientId + ", accType=" + accType + ", openDate=" + openDate + ", balance=" + balance + ", IsActive=" + IsActive + '}';
        for(ClientsModel c: clients){
            w+="\n   "+w.toString();
        }
        return w;
    }
    
}
