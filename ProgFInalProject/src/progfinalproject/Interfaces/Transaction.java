package progfinalproject.Interfaces;

import progfinalproject.models.TransactionsModel;

import java.util.Map;

public interface Transaction {

    /**
     * gets all the transaction from the selected transaction id
     * @param id transaction id
     * @return an TransactionsModel object that stored the database data into itself
     */
    public TransactionsModel getTransaction(int id);

    /**
     * updates the taken parameter in the databases
     * @param transaction transaction in which it needs to be updated
     * @return true if the update is successful
     */
    public boolean UpdateTransactions (TransactionsModel transaction);

}
