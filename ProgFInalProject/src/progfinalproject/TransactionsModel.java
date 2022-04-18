/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject;

/**
 *
 * @author Saqib Ahmad Syed
 */
public class TransactionsModel {
    private int transactionId;
    private int toAccountNumber;
    private String transactiondetails;
    private int value;

    public TransactionsModel(int transactionId, int toAccountNumber, String transactiondetails, int value) {
        this.transactionId = transactionId;
        this.toAccountNumber = toAccountNumber;
        this.transactiondetails = transactiondetails;
        this.value = value;
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

    @Override
    public String toString() {
        return "Transactions{" + "transactionId=" + transactionId + ", toAccountNumber=" + toAccountNumber + ", transactiondetails=" + transactiondetails + ", value=" + value + '}';
    }
}
