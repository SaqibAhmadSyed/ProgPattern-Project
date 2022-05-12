package progfinalproject.Interfaces;

import progfinalproject.models.TransactionsModel;

import java.util.List;
import java.util.Map;

public interface Transaction {

    /**
     * creates a new transaction and send the desired value to the receiver. The sender will also be deducted after the
     * transaction.
     * @param toAccNum destination account number
     * @param fromAccNum sender account number
     * @param detail transaction details
     * @param value value to be sent and received
     * @return true if transaction has been created and sent
     */
    public boolean createTransaction(int toAccNum, int fromAccNum, String detail, double value);

    /**
     * reads a list of all transactions from the same sender.
     * @param id transaction id
     */
    public boolean readClientTransaction(int id);

    /**
     * Reads a specific transaction
     * @param id transaction id
     * @return Transaction Model with database values
     */
    public TransactionsModel readSingleTransaction(int id);

    /**
     * Cancels the transactions. the money from the sender is retrieved and the money of the receiver is deducted.
     * @param id transaction id
     */
    public boolean cancelTransaction(int id);

    /**
     * reads all transactions in database
     * @return list of all datas
     */
    public List<TransactionsModel> readAllTransaction();

}
