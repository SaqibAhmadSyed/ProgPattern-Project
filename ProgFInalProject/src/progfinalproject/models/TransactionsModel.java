
package progfinalproject.models;

/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class TransactionsModel {
    private int transactionId;
    private int toAccountNumber;
    private int fromAccountNumber;
    private String transactionDetails;
    private double value;

    public TransactionsModel(int transactionId, int toAccountNumber, int fromAccountNumber, String transactionDetails, double value) {
        this.transactionId = transactionId;
        this.toAccountNumber = toAccountNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.transactionDetails = transactionDetails;
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

    public int getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void fromToAccountNumber(int fromAccountNumber) {
        this.fromAccountNumber = toAccountNumber;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String str = "";
        str += String.format("%d %5d %10d %15s %20.2f$ \n", transactionId, toAccountNumber, fromAccountNumber , transactionDetails, value);
        return str;
    }
}
