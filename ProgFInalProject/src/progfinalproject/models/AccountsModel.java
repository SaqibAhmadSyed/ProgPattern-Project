/*
 * Account model which converts database value into java values
 */

package progfinalproject.models;
/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class AccountsModel {
    private int accountid;
    private int clientId;
    private String accType;
    private String openDate;
    private double balance;
    private boolean isActive;

    public AccountsModel(int accountId, int clientId, String accType, String openDate, double balance, boolean isActive) {
        this.accountid = accountId;
        this.clientId = clientId;
        this.accType = accType;
        this.openDate = openDate;
        this.balance = balance;
        this.isActive = isActive;
    }

    public int getAccountId() {
        return accountid;
    }

    public void setAccountId(int accountId) {
        this.accountid = accountId;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    @Override
    public String toString() {
        String str = "";
        str += String.format("%d %5d %10s %15s %20.2f$ %25b\n", accountid, clientId, accType , openDate, balance, isActive);
        return str;
    }
    
}
